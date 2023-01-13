package com.frakton.moviesapp.retrofit

import android.util.Log
import com.frakton.moviesapp.models.MoviesResponse
import com.squareup.moshi.JsonDataException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

object MoviesApiServiceImpl {
    private val TAG = "MoviesApiServiceImpl"

    fun getMoviesFromApi(): Flow<MoviesResponse?> = flow {
        try {
            val movies = RetrofitHelper.getMoviesApiService()?.getMovies()
            emit(movies?.body())
        } catch (e: JsonDataException){
            Log.d(TAG, "JsonDataException: ${e.localizedMessage}")
        }
    }
}