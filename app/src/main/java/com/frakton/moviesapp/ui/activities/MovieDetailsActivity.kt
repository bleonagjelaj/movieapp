package com.frakton.moviesapp.ui.activities

import android.os.Bundle
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.frakton.moviesapp.databinding.ActivityMovieDetailsBinding
import com.frakton.moviesapp.domain.models.MovieDetailsModel
import com.frakton.moviesapp.ui.viewmodels.MovieDetailsViewModel
import com.frakton.moviesapp.util.Constants
import com.squareup.picasso.Picasso
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
            showMovieDetails(movieDetailsModel)
        }
    }

    private fun showMovieDetails(movieDetailsModel: MovieDetailsModel) {
        binding.movieDescriptionTitle.text =
            "${movieDetailsModel.title}\n(${movieDetailsModel.releaseYear})"
        binding.movieDescriptionTitle.text = movieDetailsModel.description
        setMoviePosterImage(movieDetailsModel.posterPath, binding.moviePosterImage)
        binding.ratingNumber.text = movieDetailsModel.rating.toString()
        binding.movieRating.rating = movieDetailsModel.rating / 2F
        binding.productionText.text = movieDetailsModel.productionCompany
        binding.budgetText.text = movieDetailsModel.budget
        binding.revenueText.text = movieDetailsModel.revenue
        binding.releaseDateText.text = movieDetailsModel.releaseDate
    }

    private fun setMoviePosterImage(moviePosterPath: String, moviePosterImage: ImageView) {
        Picasso.get().load(moviePosterPath).into(moviePosterImage)
    }
}