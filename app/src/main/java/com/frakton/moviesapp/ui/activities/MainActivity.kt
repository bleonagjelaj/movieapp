package com.frakton.moviesapp.ui.activities

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.paging.LoadState
import com.frakton.moviesapp.R
import com.frakton.moviesapp.databinding.ActivityMainBinding
import com.frakton.moviesapp.ui.adapters.MoviesViewPagerAdapter
import com.frakton.moviesapp.ui.viewmodels.MoviesViewModel
import com.frakton.moviesapp.util.gone
import com.frakton.moviesapp.util.visible
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.HttpException

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MoviesViewModel by viewModels()
    private lateinit var moviesViewPagerAdapter: MoviesViewPagerAdapter
    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        moviesViewPagerAdapter = MoviesViewPagerAdapter()
        setSearchListeners()
        setViewModelObservers()
        viewModel.loadMovies()
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

    private fun onLoadingMoviesError(errorMessage: String?) {
        binding.errorMessage.visible()
        binding.errorMessage.text = errorMessage
    }

    private fun setSearchListeners() {
        binding.movieSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText.isNullOrBlank()) {
                    viewModel.loadMovies()
                } else {
                    viewModel.searchMovies(newText)
                }
                return false
            }
        })
    }
}