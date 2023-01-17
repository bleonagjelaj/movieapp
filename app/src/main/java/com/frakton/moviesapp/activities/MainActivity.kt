package com.frakton.moviesapp.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import com.frakton.moviesapp.adapters.MoviesViewPagerAdapter
import com.frakton.moviesapp.databinding.ActivityMainBinding
import com.frakton.moviesapp.viewmodels.MoviesViewModel


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MoviesViewModel
    private lateinit var moviesViewPagerAdapter: MoviesViewPagerAdapter
    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[MoviesViewModel::class.java]
        moviesViewPagerAdapter = MoviesViewPagerAdapter()
        loadMovies()
        setSearchListeners()
    }

    private fun loadMovies() {
        viewModel.loadMovies()?.observe(this@MainActivity) {
            binding.movieViewPager.adapter = moviesViewPagerAdapter
            it?.let {
                moviesViewPagerAdapter.submitData(lifecycle, it)

            }
        }
    }

    private fun setSearchListeners() {
        binding.movieSearch.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if(newText.isNullOrBlank()) {
                    loadMovies()
                } else {
                    searchByTitle(newText)
                }
                return false
            }
        })
    }

    private fun searchByTitle(movieTitle: String) {
        viewModel.searchMovies(movieTitle)?.observe(this@MainActivity) {
            moviesViewPagerAdapter.submitData(lifecycle, it)
        }
    }
}