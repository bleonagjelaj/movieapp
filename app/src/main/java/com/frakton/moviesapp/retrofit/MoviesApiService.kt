package com.frakton.moviesapp.retrofit

import com.frakton.moviesapp.models.MoviesResponse
import com.frakton.moviesapp.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApiService {
    @GET("/discover/movie")
    suspend fun getMovies(@Query("api_key") apiKey: String = Constants.MOVIES_API_KEY): MoviesResponse
}