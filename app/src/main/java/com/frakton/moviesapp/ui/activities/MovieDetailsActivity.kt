package com.frakton.moviesapp.ui.activities

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.frakton.moviesapp.R
import com.frakton.moviesapp.databinding.ActivityMovieDetailsBinding
import com.frakton.moviesapp.domain.callbacks.TrailerItemClickCallback
import com.frakton.moviesapp.domain.models.MovieDetailsModel
import com.frakton.moviesapp.ui.adapters.MovieGenresRecyclerAdapter
import com.frakton.moviesapp.ui.adapters.TrailersPagerAdapter
import com.frakton.moviesapp.ui.viewmodels.MovieDetailsViewModel
import com.frakton.moviesapp.util.Constants
import com.frakton.moviesapp.util.gone
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerFragment
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MovieDetailsActivity : AppCompatActivity(), TrailerItemClickCallback {
    private val TAG = "MovieDetailsActivity"
    private lateinit var binding: ActivityMovieDetailsBinding
    private val viewModel: MovieDetailsViewModel by viewModels()
    private lateinit var trailersPagerAdapter: TrailersPagerAdapter
    private lateinit var youtubeFragment: YouTubePlayerFragment
    private var activePlayer: YouTubePlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.getMovieDetails(intent.getLongExtra(Constants.MOVIE_ID_EXTRA, -1))
        initMovieTrailersAdapter()
        setViewModelObservers()
        setClickListeners()
    }

    override fun onDestroy() {
        activePlayer?.pause()
        super.onDestroy()
    }

    private fun setClickListeners() {
        binding.closeButton.setOnClickListener { finish() }
    }

    private fun setViewModelObservers() {
        viewModel.movieDetails.observe(this) { movieDetailsModel ->
            showMovieDetails(movieDetailsModel)
            viewModel.getMovieTrailerVideos(movieDetailsModel.id)
        }
        viewModel.movieTrailerVideos.observe(this) { movieTrailerVideos ->
            trailersPagerAdapter.setData(movieTrailerVideos)
        }
    }

    private fun initMovieTrailersAdapter() {
        trailersPagerAdapter = TrailersPagerAdapter(this)
        binding.movieTrailersViewPager.adapter = trailersPagerAdapter
        youtubeFragment = YouTubePlayerFragment()
        fragmentManager.beginTransaction()
            .replace(R.id.youtubeFragmentContainer, youtubeFragment)
            .commit()
    }

    private fun showMovieDetails(movieDetailsModel: MovieDetailsModel) {
        binding.movieTitle.text = "${movieDetailsModel.title}\n(${movieDetailsModel.releaseYear})"
        binding.movieDescription.text = movieDetailsModel.description
        setMoviePosterImage(movieDetailsModel.posterPath, binding.moviePosterImage)
        setMovieGenres(movieDetailsModel.genres)
        binding.ratingNumber.text = movieDetailsModel.rating.toString()
        binding.movieRating.rating = movieDetailsModel.rating / 2F
        binding.productionText.text = movieDetailsModel.productionCompany
        binding.budgetText.text = movieDetailsModel.budget
        binding.revenueText.text = movieDetailsModel.revenue
        binding.releaseDateText.text = movieDetailsModel.releaseDate
    }

    private fun setMovieGenres(genres: List<String>) {
        val genresAdapter = MovieGenresRecyclerAdapter()
        binding.genresRecycleView.adapter = genresAdapter
        binding.genresRecycleView.layoutManager = GridLayoutManager(this, 4)
        genresAdapter.setData(genres)
    }

    private fun setMoviePosterImage(moviePosterPath: String, moviePosterImage: ImageView) {
        Picasso.get().load(moviePosterPath).into(moviePosterImage)
    }

    override fun onTrailerItemClicked(trailerKey: String) {
        binding.movieTrailersViewPager.gone()
        youtubeFragment.initialize(Constants.YOUTUBE_API_KEY,
            object : YouTubePlayer.OnInitializedListener {
                override fun onInitializationSuccess(
                    provider: YouTubePlayer.Provider,
                    player: YouTubePlayer,
                    wasRestored: Boolean
                ) {
                    activePlayer = player
                    activePlayer?.setShowFullscreenButton(false)
                    activePlayer?.setPlayerStyle(YouTubePlayer.PlayerStyle.MINIMAL)
                    if (!wasRestored) {
                        activePlayer?.loadVideo(trailerKey, 0)
                        activePlayer?.play()
                    }
                }

                override fun onInitializationFailure(
                    provider: YouTubePlayer.Provider,
                    result: YouTubeInitializationResult
                ) {
                    Log.d(TAG, "onInitializationFailure: $result")
                }
            })
    }
}