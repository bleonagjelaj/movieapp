package com.bleonahasanaj.moviesapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.paging.LoadState
import com.bleonahasanaj.moviesapp.R
import com.bleonahasanaj.moviesapp.databinding.FragmentMainBinding
import com.bleonahasanaj.moviesapp.domain.callbacks.MovieItemClickCallback
import com.bleonahasanaj.moviesapp.ui.adapters.MoviesViewPagerAdapter
import com.bleonahasanaj.moviesapp.ui.viewmodels.MoviesViewModel
import com.bleonahasanaj.moviesapp.util.gone
import com.bleonahasanaj.moviesapp.util.visible
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.HttpException

@AndroidEntryPoint
class MainFragment : Fragment() {
    private var binding: FragmentMainBinding? = null
    private val viewModel: MoviesViewModel by activityViewModels()
    private lateinit var moviesViewPagerAdapter: MoviesViewPagerAdapter
    private val DELAY_WHILE_SEARCHING = 500L

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (binding == null) {
            binding = FragmentMainBinding.inflate(inflater, container, false)
        }
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
        viewModel.loadMovies()
    }

    private fun setListeners() {
        setMoviesViewPagerLoadListener()
        setViewModelObservers()
        setClickListeners()
        setSearchListeners()
    }

    private fun setClickListeners() {
        binding?.filterButton?.setOnClickListener {
            findNavController(this).navigate(
                MainFragmentDirections.actionMainFragmentToFilterFragment()
            )
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
        binding?.movieViewPager?.adapter = moviesViewPagerAdapter
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
        viewModel.movieData.observe(requireActivity()) { moviePagingData ->
            if (moviePagingData != null) {
                binding?.errorMessage?.gone()
                lifecycleScope.launch {
                    moviesViewPagerAdapter.submitData(moviePagingData)
                }
            } else {
                onLoadingMoviesError(errorMessage = getString(R.string.no_results))
            }
        }
    }

    private fun onLoadingMoviesError(errorMessage: String?) {
        binding?.errorMessage?.visible()
        binding?.errorMessage?.text = errorMessage
    }

    private fun setSearchListeners() {
        var searchJob: Job? = null
        binding?.movieSearch?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
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
                viewModel.loadMovies(shouldReload = true)
            } else if (newText.count() > 2) {
                viewModel.searchMovies(newText)
            }
        }
}