package com.frakton.moviesapp.ui.activities

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.frakton.moviesapp.databinding.ActivityMovieDetailsBinding
import com.frakton.moviesapp.ui.viewmodels.MovieDetailsViewModel
import com.frakton.moviesapp.util.Constants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMovieDetailsBinding
    private val viewModel: MovieDetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.getMovieDetails(intent.getLongExtra(Constants.MOVIE_ID_EXTRA, -1))
        setViewModelObservers()
        setClickListeners()
    }

    private fun setClickListeners() {
        binding.closeButton.setOnClickListener { finish() }
    }

    private fun setViewModelObservers() {
        viewModel.movieDetails.observe(this) { movieDetailsModel ->
            Log.d("belonatag", "setViewModelObservers: $movieDetailsModel")
        }
    }
}