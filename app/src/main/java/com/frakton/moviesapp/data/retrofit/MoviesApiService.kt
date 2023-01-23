package com.frakton.moviesapp.data.retrofit

import com.frakton.moviesapp.data.retrofit.models.response.MoviesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApiService {
    @GET("/3/discover/movie")
    suspend fun getMovies(@Query("page") pageIndex: Int): MoviesResponse

    @GET("/3/search/movie")
    suspend fun searchMovies(
        @Query("page") pageIndex: Int,
        @Query("query") movieTitle: String
    ): MoviesResponse
}