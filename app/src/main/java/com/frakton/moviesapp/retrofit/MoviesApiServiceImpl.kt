package com.frakton.moviesapp.retrofit

import android.util.Log
import com.frakton.moviesapp.models.MoviesResponse
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

object MoviesApiServiceImpl {
    fun getMoviesFromApi(): Flow<MoviesResponse?> {
        return flow {
            val moshi: Moshi = Moshi.Builder().build()
            val adapter: JsonAdapter<MoviesResponse> = moshi.adapter(MoviesResponse::class.java)
            val movies = adapter.fromJson(
                RetrofitHelper.getMoviesApiService()?.getMovies()?.body().toString()
            )
            emit(movies)
        }
    }
}