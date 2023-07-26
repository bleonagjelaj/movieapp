package com.bleonahasanaj.moviesapp.data.retrofit

import com.bleonahasanaj.moviesapp.data.retrofit.models.response.GenreResponse
import com.bleonahasanaj.moviesapp.data.retrofit.models.response.MovieDetailsResponse
import com.bleonahasanaj.moviesapp.data.retrofit.models.response.MovieTrailerVideosResponse
import com.bleonahasanaj.moviesapp.data.retrofit.models.response.MoviesResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesApiService {
    @GET("/3/discover/movie")
    suspend fun getMovies(
        @Query("page") pageIndex: Int,
        @Query("sort_by") sortBy: String? = null,
        @Query("year") filterByYear: Int? = null,
        @Query("with_genres") filterByGenres: String? = null
    ): MoviesResponse

    @GET("/3/search/movie")
    suspend fun searchMovies(
        @Query("page") pageIndex: Int,
        @Query("query") movieTitle: String
    ): MoviesResponse

    @GET("/3/movie/{movieId}")
    suspend fun getMovieWithId(@Path("movieId") movieId: Long): MovieDetailsResponse

    @GET("/3/movie/{movie_id}/videos")
    suspend fun getMovieTrailerVideos(@Path("movie_id") movieId: Long): MovieTrailerVideosResponse

    @GET("/3/genre/movie/list")
    suspend fun getGenresList(): GenreResponse
}