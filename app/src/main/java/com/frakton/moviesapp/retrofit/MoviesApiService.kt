package com.frakton.moviesapp.retrofit

import com.frakton.moviesapp.models.MoviesResponse
import retrofit2.Response
import retrofit2.http.GET

interface MoviesApiService {
    @GET("/discover/movie")
    suspend fun getMovies(): Response<MoviesResponse>
}