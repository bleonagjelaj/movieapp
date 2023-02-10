package com.frakton.moviesapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.paging.LoadState
import com.frakton.moviesapp.R
import com.frakton.moviesapp.databinding.FragmentMainBinding
import com.frakton.moviesapp.domain.callbacks.MovieItemClickCallback
import com.frakton.moviesapp.ui.adapters.MoviesViewPagerAdapter
import com.frakton.moviesapp.ui.viewmodels.MoviesViewModel
import com.frakton.moviesapp.util.gone
import com.frakton.moviesapp.util.visible
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.HttpException

@AndroidEntryPoint
class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private val viewModel: MoviesViewModel by viewModels()
    private lateinit var moviesViewPagerAdapter: MoviesViewPagerAdapter
    private val DELAY_WHILE_SEARCHING = 500L

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
        viewModel.loadMovies()
    }

    private fun setListeners() {
        setMoviesViewPagerLoadListener()
        setSearchListeners()
        setViewModelObservers()
        setClickListeners()
    }

    private fun setClickListeners() {
        binding.filterButton.setOnClickListener {
            findNavController(this).navigate(R.id.action_mainFragment_to_filterFragment)
        }
    }

    private fun setMoviesViewPagerLoadListener() {
        val movieItemClickCallback = object : MovieItemClickCallback {
            override fun onMovieItemClicked(movieId: Long) {
                startMovieDetailsFragment(movieId)
            }
        }
        moviesViewPagerAdapter =
            MoviesViewPagerAdapter(movieItemClickCallback = movieItemClickCallback)
        moviesViewPagerAdapter.addLoadStateListener {
            val currentState = it.refresh
            if (currentState is LoadState.Error) {
                val error = currentState.error
                onLoadingMoviesError(
                    errorMessage = if (error is HttpException) {
                        error.localizedMessage
                    } else {
                        getString(R.string.could_not_reach_server)
                    }
                )
            }
        }
    }

    private fun startMovieDetailsFragment(movieId: Long) {
        val action = MainFragmentDirections.actionMainFragmentToMovieDetailsFragment(movieId)
        findNavController(this).navigate(action)
    }

    private fun setViewModelObservers() {
        viewModel.movieData.observe(viewLifecycleOwner) { moviePagingData ->
            binding.movieViewPager.adapter = moviesViewPagerAdapter
            if (moviePagingData != null) {
                binding.errorMessage.gone()
                moviesViewPagerAdapter.submitData(lifecycle, moviePagingData)
            } else {
                onLoadingMoviesError(errorMessage = getString(R.string.no_results))
            }
        }
    }

    private fun onLoadingMoviesError(errorMessage: String?) {
        binding.errorMessage.visible()
        binding.errorMessage.text = errorMessage
    }

    private fun setSearchListeners() {
        var searchJob: Job? = null
        binding.movieSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                lifecycleScope.launch {
                    searchJob?.cancel()
                    searchJob = getSearchMovieJob(newText)
                }
                return false
            }
        })
    }

    /*** delay searching process for half a second to prevent
     *   multiple api calls while user is still typing,
     *   write at least 3 letters in order to hit api to get
     *   movies that match the written keyword in the search view,
     *   when text gets cleared load the movies from api again
     */
    private suspend fun getSearchMovieJob(newText: String?) =
        lifecycleScope.launch {
            delay(DELAY_WHILE_SEARCHING)
            if (newText.isNullOrBlank()) {
                viewModel.loadMovies()
            } else if (newText.count() > 2) {
                viewModel.searchMovies(newText)
            }
        }
}