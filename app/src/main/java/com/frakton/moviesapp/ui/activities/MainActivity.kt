package com.frakton.moviesapp.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.frakton.moviesapp.R
import com.frakton.moviesapp.databinding.ActivityMainBinding
import com.frakton.moviesapp.domain.callbacks.MovieItemClickCallback
import com.frakton.moviesapp.ui.adapters.MoviesViewPagerAdapter
import com.frakton.moviesapp.ui.viewmodels.MoviesViewModel
import com.frakton.moviesapp.util.Constants
import com.frakton.moviesapp.util.gone
import com.frakton.moviesapp.util.visible
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.HttpException

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MoviesViewModel by viewModels()
    private lateinit var moviesViewPagerAdapter: MoviesViewPagerAdapter
    private val DELAY_WHILE_SEARCHING = 500L
    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setListeners()
        viewModel.loadMovies()
    }

    private fun setListeners() {
        setMoviesViewPagerLoadListener()
        setSearchListeners()
        setViewModelObservers()
    }

    private fun setMoviesViewPagerLoadListener() {
        val movieItemClickCallback = object : MovieItemClickCallback {
            override fun onMovieItemClicked(movieId: Long) {
                val intent = Intent(this@MainActivity, MovieDetailsActivity::class.java)
                intent.putExtra(Constants.MOVIE_ID_EXTRA, movieId)
                startActivity(intent)
            }
        }
        moviesViewPagerAdapter = MoviesViewPagerAdapter(movieItemClickCallback)
        moviesViewPagerAdapter.addLoadStateListener {
            val currentState = it.refresh
            if (currentState is LoadState.Error) {
                val error = currentState.error
                onLoadingMoviesError(
                    if (error is HttpException) {
                        error.localizedMessage
                    } else {
                        getString(R.string.could_not_reach_server)
                    }
                )
            }
        }
    }

    private fun setViewModelObservers() {
        viewModel.movieData.observe(this) { moviePagingData ->
            binding.movieViewPager.adapter = moviesViewPagerAdapter
            if (moviePagingData != null) {
                binding.errorMessage.gone()
                moviesViewPagerAdapter.submitData(lifecycle, moviePagingData)
            } else {
                onLoadingMoviesError(getString(R.string.no_results))
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