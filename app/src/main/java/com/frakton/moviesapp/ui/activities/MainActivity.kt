package com.frakton.moviesapp.ui.activities

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import com.frakton.moviesapp.R
import com.frakton.moviesapp.databinding.ActivityMainBinding
import com.frakton.moviesapp.ui.adapters.MoviesViewPagerAdapter
import com.frakton.moviesapp.ui.viewmodels.MoviesViewModel
import com.frakton.moviesapp.util.gone
import com.frakton.moviesapp.util.visible
import dagger.hilt.android.AndroidEntryPoint

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
            moviePagingData?.let {
            }
            if(moviePagingData != null) {
                binding.errorMessage.gone()
                moviesViewPagerAdapter.submitData(lifecycle, moviePagingData)
            } else {
                binding.errorMessage.visible()
                binding.errorMessage.text = getString(R.string.no_results)
            }
        }
        viewModel.loadingMoviesError.observe(this) { errorMessage ->
            binding.errorMessage.visible()
            binding.errorMessage.text = errorMessage
        }
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