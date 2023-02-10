package com.frakton.moviesapp.ui.viewmodels

import android.text.SpannableStringBuilder
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.frakton.moviesapp.domain.MovieParams
import com.frakton.moviesapp.domain.models.MovieDetailsModel
import com.frakton.moviesapp.domain.models.TrailerDetails
import com.frakton.moviesapp.domain.usecases.GetMovieDetailsUseCase
import com.frakton.moviesapp.domain.usecases.GetMovieTrailerVideosUseCase
import com.frakton.moviesapp.util.makeTextBiggerAndBold
import com.frakton.moviesapp.util.makeTextBold
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    private val getMovieTrailerVideosUseCase: GetMovieTrailerVideosUseCase
) : ViewModel() {
    private val _movieDetails = MutableLiveData<MovieDetailsModel>()
    val movieDetails: MutableLiveData<MovieDetailsModel> = _movieDetails
    private val youTubeSite = "YouTube"
    private val _movieTrailerVideos = MutableLiveData<List<TrailerDetails>>()
    val movieTrailerVideos: MutableLiveData<List<TrailerDetails>> = _movieTrailerVideos

    fun getMovieDetails(movieId: Long) {
        viewModelScope.launch {
            getMovieDetailsUseCase(MovieParams.GetMovieDetailsParams(movieId))
                .collect { movieDetailsResponse ->
                    _movieDetails.value = movieDetailsResponse
                }
        }
    }

    fun formatRatingNumberText(ratingNumber: Float, maxRating: String): SpannableStringBuilder =
        ratingNumber.toString().makeTextBiggerAndBold().append(maxRating)

    fun formatMovieDetailText(
        detailTitle: String, separator: String, detailText: String
    ): SpannableStringBuilder = detailTitle.makeTextBold().append(separator).append(detailText)

    fun getMovieTrailerVideos(movieId: Long) {
        viewModelScope.launch {
            getMovieTrailerVideosUseCase(MovieParams.GetMovieDetailsParams(movieId))
                .collect { movieTrailerVideosResponse ->
                    _movieTrailerVideos.value =
                        movieTrailerVideosResponse.trailerVideos.filter { it.site == youTubeSite }
                }
        }
    }
}