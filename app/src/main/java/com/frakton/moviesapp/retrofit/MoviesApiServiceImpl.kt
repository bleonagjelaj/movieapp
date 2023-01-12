package com.frakton.moviesapp.retrofit

import com.frakton.moviesapp.models.MoviesResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

object MoviesApiServiceImpl {
    fun getMoviesFromApi(): Flow<MoviesResponse?> = flow {
        emit(RetrofitHelper.getMoviesApiService()?.getMovies()?.body())
    }
}