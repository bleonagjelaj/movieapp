package com.frakton.moviesapp.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.frakton.moviesapp.domain.MovieParams
import com.frakton.moviesapp.domain.models.MovieModel
import com.frakton.moviesapp.domain.usecases.GetMoviesUseCase
import com.frakton.moviesapp.domain.usecases.SearchMovieUseCase
import com.frakton.moviesapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase,
    private val searchMovieUseCase: SearchMovieUseCase
) : ViewModel() {
    private val _loadingMoviesError = MutableLiveData<String>()
    val loadingMoviesError: MutableLiveData<String> = _loadingMoviesError

    private val _movieData = MutableLiveData<PagingData<MovieModel>>()
    val movieData: MutableLiveData<PagingData<MovieModel>> = _movieData

    fun loadMovies() =
        viewModelScope.launch {
            getMoviesUseCase().collect { movieResult ->
                when (movieResult) {
                    is Resource.Success -> {
                        _movieData.value = movieResult.data
                    }
                    is Resource.Error -> {
                        _loadingMoviesError.value = movieResult.message
                    }
                }
            }

        }

    fun searchMovies(movieTitle: String) =
        viewModelScope.launch {
            searchMovieUseCase(MovieParams.SearchMovieParams(movieTitle)).collect { movieResult ->
                when (movieResult) {
                    is Resource.Success -> {
                        _movieData.value = movieResult.data
                    }
                    is Resource.Error -> {
                        _loadingMoviesError.value = movieResult.message
                    }
                }
            }
        }
}