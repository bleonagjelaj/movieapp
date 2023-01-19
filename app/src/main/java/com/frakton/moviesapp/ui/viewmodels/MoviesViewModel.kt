package com.frakton.moviesapp.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
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

    suspend fun loadMovies(): LiveData<PagingData<MovieDataModel>> =
                getMoviesUseCase.invoke(MovieParams.GetMoviesParams(1)).cachedIn(viewModelScope)

    suspend fun searchMovies(movieTitle: String): LiveData<PagingData<MovieDataModel>>  =
        searchMovieUseCase.invoke(MovieParams.SearchMovieParams(1, movieTitle)).cachedIn(viewModelScope)


}