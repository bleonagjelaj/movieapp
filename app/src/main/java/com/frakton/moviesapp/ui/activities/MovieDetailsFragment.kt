package com.frakton.moviesapp.ui.activities

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.transition.TransitionInflater
import com.frakton.moviesapp.R
import com.frakton.moviesapp.databinding.MovieDetailsFragmentBinding
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
class MovieDetailsFragment(private val movieId: Long) : Fragment(), TrailerItemClickCallback {
    private val TAG = "MovieDetailsActivity"
    private lateinit var binding: MovieDetailsFragmentBinding
    private val viewModel: MovieDetailsViewModel by viewModels()
    private lateinit var trailersViewPagerAdapter: TrailersViewPagerAdapter
    private var activePlayer: YouTubePlayer? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MovieDetailsFragmentBinding.inflate(layoutInflater, container, false)
        val transitionInflater = TransitionInflater.from(requireContext())
        enterTransition = transitionInflater.inflateTransition(R.transition.slide_right)
        exitTransition = transitionInflater.inflateTransition(R.transition.slide_right)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getMovieDetails(movieId)
        initMovieTrailersAdapter()
        setViewModelObservers()
        setClickListeners()
    }

    private fun setClickListeners() {
        binding.closeButton.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()?.remove(this)?.commit()
        }
        binding.closeTrailerVideoButton.setOnClickListener {
            activePlayer?.pause()
            binding.movieTrailersViewPager.visible()
        }
    }

    private fun setViewModelObservers() {
        viewModel.movieDetails.observe(this.viewLifecycleOwner) { movieDetailsModel ->
            showMovieDetails(movieDetailsModel)
            viewModel.getMovieTrailerVideos(movieDetailsModel.id)
        }
        viewModel.movieTrailerVideos.observe(this.viewLifecycleOwner) { movieTrailerVideos ->
            trailersViewPagerAdapter.setData(movieTrailerVideos)
        }
    }

    private fun initMovieTrailersAdapter() {
        trailersViewPagerAdapter = TrailersViewPagerAdapter(this)
        binding.movieTrailersViewPager.adapter = trailersViewPagerAdapter
        TabLayoutMediator(binding.movieTrailersTabLayout, binding.movieTrailersViewPager) { _, _ ->
            //do nothing here
        }.attach()
        initYoutubePlayer()
    }

    private fun initYoutubePlayer() {
        val youtubeFragment = YouTubePlayerFragment()
        activity?.fragmentManager?.beginTransaction()
            ?.replace(R.id.youtubeFragmentContainer, youtubeFragment)
            ?.commit()
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
        binding.movieTitle.text = viewModel.formatMovieDetailText(
            movieDetailsModel.title,
            String.NEW_LINE,
            movieDetailsModel.releaseYear
        )
        binding.movieDescription.text = movieDetailsModel.description
        setMoviePosterImage(movieDetailsModel.posterPath)
        setMovieGenres(movieDetailsModel.genres)
        binding.ratingNumber.text = viewModel.formatRatingNumberText(
            movieDetailsModel.rating,
            getString(R.string.max_rating)
        )
        binding.movieRating.rating = movieDetailsModel.rating / 2F
        binding.productionText.text = viewModel.formatMovieDetailText(
            getString(R.string.production),
            String.TWO_CHAR_SPACE,
            movieDetailsModel.productionCompany
        )
        binding.budgetText.text = viewModel.formatMovieDetailText(
            getString(R.string.budget),
            String.TWO_CHAR_SPACE,
            movieDetailsModel.budget
        )
        binding.revenueText.text = viewModel.formatMovieDetailText(
            getString(R.string.revenue),
            String.TWO_CHAR_SPACE,
            movieDetailsModel.revenue
        )
        binding.releaseDateText.text = viewModel.formatMovieDetailText(
            getString(R.string.release_date),
            String.TWO_CHAR_SPACE,
            movieDetailsModel.releaseDate
        )
    }

    private fun setMovieGenres(genres: List<String>) {
        val genresAdapter = MovieGenresRecyclerAdapter()
        binding.genresRecycleView.adapter = genresAdapter
        binding.genresRecycleView.layoutManager = GridLayoutManager(activity, 5)
        genresAdapter.setData(genres)
    }

    private fun setMoviePosterImage(moviePosterPath: String) {
        Picasso.get().load(moviePosterPath).into(binding.moviePosterImage)
        setBackgroundImage(moviePosterPath)
    }

    private fun setBackgroundImage(moviePosterPath: String) {
        val vignetteTransformation = VignetteFilterTransformation(activity)
        val blurTransformation = BlurTransformation(activity, 70)
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