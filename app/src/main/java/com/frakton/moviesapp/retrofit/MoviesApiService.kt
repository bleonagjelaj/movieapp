package com.frakton.moviesapp.retrofit

import com.frakton.moviesapp.models.MoviesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApiService {
    @GET("/3/discover/movie")
    suspend fun getMovies(@Query("page") pageIndex: Int): Response<MoviesResponse>

    @GET("/3/search/movie")
    suspend fun searchMovies(
        @Query("page") pageIndex: Int,
        @Query("query") movieTitle: String
    ): Response<MoviesResponse>
}