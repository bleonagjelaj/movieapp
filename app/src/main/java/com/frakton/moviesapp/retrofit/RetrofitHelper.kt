package com.frakton.moviesapp.retrofit

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitHelper {
    private const val baseUrl = "https://developers.themoviedb.org/"

    private var moviesApi: MoviesApiService? = null

    private fun getInstance(): Retrofit {
        return Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    fun getMoviesApiService(): MoviesApiService? {
        if (moviesApi == null) {
            moviesApi = getInstance().create(MoviesApiService::class.java)
        }
        return moviesApi
    }
}