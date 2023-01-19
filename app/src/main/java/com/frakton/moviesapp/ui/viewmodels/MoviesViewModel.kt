package com.frakton.moviesapp.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.frakton.moviesapp.data.retrofit.models.response.MovieDataModel
import com.frakton.moviesapp.domain.MovieParams
import com.frakton.moviesapp.domain.usecases.GetMoviesUseCase
import com.frakton.moviesapp.domain.usecases.SearchMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase,
    private val searchMovieUseCase: SearchMovieUseCase
) : ViewModel() {

    val movieData = MutableLiveData<PagingData<MovieDataModel>>()

    fun loadMovies() =
        viewModelScope.launch {
            getMoviesUseCase.invoke(MovieParams.GetMoviesParams(1)).collect {
                movieData.value = it
            }
        }

    fun searchMovies(movieTitle: String) =
        viewModelScope.launch {
            searchMovieUseCase.invoke(MovieParams.SearchMovieParams(1, movieTitle)).collect {
                movieData.value = it
            }
        }
}