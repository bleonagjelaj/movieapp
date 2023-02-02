package com.frakton.moviesapp.ui.activities

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.frakton.moviesapp.R
import com.frakton.moviesapp.databinding.ActivityMovieDetailsBinding
import com.frakton.moviesapp.domain.callbacks.TrailerItemClickCallback
import com.frakton.moviesapp.domain.models.MovieDetailsModel
import com.frakton.moviesapp.ui.adapters.MovieGenresRecyclerAdapter
import com.frakton.moviesapp.ui.adapters.TrailersViewPagerAdapter
import com.frakton.moviesapp.ui.viewmodels.MovieDetailsViewModel
import com.frakton.moviesapp.util.*
import com.google.android.material.tabs.TabLayoutMediator
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerFragment
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import jp.wasabeef.picasso.transformations.BlurTransformation
import jp.wasabeef.picasso.transformations.gpu.VignetteFilterTransformation


@AndroidEntryPoint
class MovieDetailsActivity : AppCompatActivity(), TrailerItemClickCallback {
    private val TAG = "MovieDetailsActivity"
    private lateinit var binding: ActivityMovieDetailsBinding
    private val viewModel: MovieDetailsViewModel by viewModels()
    private lateinit var trailersViewPagerAdapter: TrailersViewPagerAdapter
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

    private fun setClickListeners() {
        binding.closeButton.setOnClickListener { finish() }
        binding.closeTrailerVideoButton.setOnClickListener {
            activePlayer?.pause()
            binding.movieTrailersViewPager.visible()
        }
    }

    private fun setViewModelObservers() {
        viewModel.movieDetails.observe(this) { movieDetailsModel ->
            showMovieDetails(movieDetailsModel)
            viewModel.getMovieTrailerVideos(movieDetailsModel.id)
        }
        viewModel.movieTrailerVideos.observe(this) { movieTrailerVideos ->
            trailersViewPagerAdapter.setData(movieTrailerVideos)
        }
    }

    private fun initMovieTrailersAdapter() {
        trailersViewPagerAdapter = TrailersViewPagerAdapter(this)
        binding.movieTrailersViewPager.adapter = trailersViewPagerAdapter
        TabLayoutMediator(
            binding.movieTrailersTabLayout, binding.movieTrailersViewPager
        ) { tab, position -> }.attach()
        initYoutubePlayer()
    }

    private fun initYoutubePlayer() {
        val youtubeFragment = YouTubePlayerFragment()
        fragmentManager.beginTransaction().replace(R.id.youtubeFragmentContainer, youtubeFragment)
            .commit()
        youtubeFragment.initialize(
            Constants.YOUTUBE_API_KEY,
            object : YouTubePlayer.OnInitializedListener {
                override fun onInitializationSuccess(
                    provider: YouTubePlayer.Provider, player: YouTubePlayer, wasRestored: Boolean
                ) {
                    activePlayer = player
                    activePlayer?.setShowFullscreenButton(false)
                    activePlayer?.setPlayerStyle(YouTubePlayer.PlayerStyle.MINIMAL)
                }

                override fun onInitializationFailure(
                    provider: YouTubePlayer.Provider, result: YouTubeInitializationResult
                ) {
                    Log.d(TAG, "onInitializationFailure: $result")
                }
            })
    }

    private fun showMovieDetails(movieDetailsModel: MovieDetailsModel) {
        binding.movieTitle.text = formatMovieDetailText(
            movieDetailsModel.title,
            String.NEW_LINE,
            movieDetailsModel.releaseYear
        )
        binding.movieDescription.text = movieDetailsModel.description
        setMoviePosterImage(movieDetailsModel.posterPath)
        setMovieGenres(movieDetailsModel.genres)
        binding.ratingNumber.text = formatRatingNumberText(movieDetailsModel.rating)
        binding.movieRating.rating = movieDetailsModel.rating / 2F
        binding.productionText.text = formatMovieDetailText(
            getString(R.string.production),
            String.TWO_CHAR_SPACE,
            movieDetailsModel.productionCompany
        )
        binding.budgetText.text = formatMovieDetailText(
            getString(R.string.budget),
            String.TWO_CHAR_SPACE,
            movieDetailsModel.budget
        )
        binding.revenueText.text = formatMovieDetailText(
            getString(R.string.revenue),
            String.TWO_CHAR_SPACE,
            movieDetailsModel.revenue
        )
        binding.releaseDateText.text = formatMovieDetailText(
            getString(R.string.release_date),
            String.TWO_CHAR_SPACE,
            movieDetailsModel.releaseDate
        )
    }

    private fun formatRatingNumberText(ratingNumber: Float) =
        ratingNumber.toString().makeTextBiggerAndBold().append(getString(R.string.max_rating))

    private fun formatMovieDetailText(detailTitle: String, separator: String, detailText: String) =
        detailTitle.makeTextBold().append(separator).append(detailText)

    private fun setMovieGenres(genres: List<String>) {
        val genresAdapter = MovieGenresRecyclerAdapter()
        binding.genresRecycleView.adapter = genresAdapter
        binding.genresRecycleView.layoutManager = GridLayoutManager(this, 5)
        genresAdapter.setData(genres)
    }

    private fun setMoviePosterImage(moviePosterPath: String) {
        Picasso.get().load(moviePosterPath).into(binding.moviePosterImage)
        setBackgroundImage(moviePosterPath)
    }

    private fun setBackgroundImage(moviePosterPath: String) {
        val vignetteTransformation = VignetteFilterTransformation(this)
        val blurTransformation = BlurTransformation(this, 70)
        Picasso.get().load(moviePosterPath)
            .transform(vignetteTransformation)
            .transform(blurTransformation)
            .into(binding.backgroundImage)
    }

    override fun onTrailerItemClicked(trailerKey: String) {
        binding.movieTrailersViewPager.gone()
        activePlayer?.loadVideo(trailerKey)
        activePlayer?.play()
    }
}