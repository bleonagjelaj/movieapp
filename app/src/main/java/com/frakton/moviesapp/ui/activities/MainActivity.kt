package com.frakton.moviesapp.ui.activities

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.lifecycleScope
import com.frakton.moviesapp.databinding.ActivityMainBinding
import com.frakton.moviesapp.ui.adapters.MoviesViewPagerAdapter
import com.frakton.moviesapp.ui.viewmodels.MoviesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

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
        loadMovies()
        setSearchListeners()
    }

    private fun loadMovies() {
        lifecycleScope.launch{
            viewModel.loadMovies()?.observe(this@MainActivity) {
                binding.movieViewPager.adapter = moviesViewPagerAdapter
                it?.let {
                    moviesViewPagerAdapter.submitData(lifecycle, it)
                }
            }
        }

    }

    private fun setSearchListeners() {
        binding.movieSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText.isNullOrBlank()) {
                    loadMovies()
                } else {
                    searchByTitle(newText)
                }
                return false
            }
        })
    }

    private fun searchByTitle(movieTitle: String) {
        lifecycleScope.launch {
            viewModel.searchMovies(movieTitle)?.observe(this@MainActivity) {
                moviesViewPagerAdapter.submitData(lifecycle, it)
            }
        }
    }
}