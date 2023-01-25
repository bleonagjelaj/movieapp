package com.frakton.moviesapp.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.frakton.moviesapp.domain.MovieParams
import com.frakton.moviesapp.domain.models.MovieDetailsModel
import com.frakton.moviesapp.domain.models.MovieTrailerVideosModel
import com.frakton.moviesapp.domain.usecases.GetMovieDetailsUseCase
import com.frakton.moviesapp.domain.usecases.GetMovieTrailerVideosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    private val getMovieTrailerVideosUseCase: GetMovieTrailerVideosUseCase
) : ViewModel() {
    private val _movieDetails = MutableLiveData<MovieDetailsModel>()
    val movieDetails: MutableLiveData<MovieDetailsModel> = _movieDetails

    private val _movieTrailerVideos = MutableLiveData<MovieTrailerVideosModel>()
    val movieTrailerVideos: MutableLiveData<MovieTrailerVideosModel> = _movieTrailerVideos

    fun getMovieDetails(movieId: Long) {
        viewModelScope.launch {
            getMovieDetailsUseCase(MovieParams.GetMovieDetailsParams(movieId))
                .collect { movieDetailsResponse ->
                    _movieDetails.value = movieDetailsResponse
                }
        }
    }

    fun getMovieTrailerVideos(movieId: Long) {
        viewModelScope.launch {
            getMovieTrailerVideosUseCase(MovieParams.GetMovieDetailsParams(movieId))
                .collect { movieTrailerVideosResponse ->
                    _movieTrailerVideos.value = movieTrailerVideosResponse
                }
        }
    }
}