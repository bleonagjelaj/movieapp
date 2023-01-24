package com.frakton.moviesapp.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.frakton.moviesapp.domain.MovieParams
import com.frakton.moviesapp.domain.models.MovieDetailsModel
import com.frakton.moviesapp.domain.usecases.GetMovieDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase
) : ViewModel() {
    private val _movieDetails = MutableLiveData<MovieDetailsModel>()
    val movieDetails: MutableLiveData<MovieDetailsModel> = _movieDetails

    fun getMovieDetails(movieId: Long) {
        viewModelScope.launch {
            getMovieDetailsUseCase(MovieParams.GetMovieDetailsParams(movieId))
                .collect { movieDetailsResponse ->
                    _movieDetails.value = movieDetailsResponse
                }
        }
    }
}