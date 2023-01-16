package com.frakton.moviesapp.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.frakton.moviesapp.adapters.MoviesViewPagerAdapter
import com.frakton.moviesapp.databinding.ActivityMainBinding
import com.frakton.moviesapp.viewmodels.MoviesViewModel
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MoviesViewModel
    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[MoviesViewModel::class.java]
        lifecycleScope.launch {
            viewModel.loadMovies()?.observe(this@MainActivity) {
                val adapter = MoviesViewPagerAdapter()
                binding.movieViewPager.adapter = adapter
                it?.let {
                    adapter.submitData(lifecycle, it)
                }
            }
        }
    }
}