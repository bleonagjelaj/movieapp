package com.frakton.moviesapp.data.retrofit

import com.frakton.moviesapp.data.retrofit.models.request.GetMovieDetailsRequest
import com.frakton.moviesapp.data.retrofit.models.request.GetMoviesRequest
import com.frakton.moviesapp.data.retrofit.models.request.SearchMovieRequest
import javax.inject.Inject

class MoviesApiSource @Inject constructor(private val moviesApiService: MoviesApiService) {
    suspend fun getMoviesFromApi(request: GetMoviesRequest) =
        moviesApiService.getMovies(
            request.page,
            request.movieFilters?.sortBy,
            request.movieFilters?.filterByYear,
            request.movieFilters?.filterByGenres
        )

    suspend fun searchMovie(request: SearchMovieRequest) =
        moviesApiService.searchMovies(request.page, request.movieTitle)

    suspend fun getMovieWithId(request: GetMovieDetailsRequest) =
        moviesApiService.getMovieWithId(request.movieId)

    suspend fun getMovieTrailerVideos(request: GetMovieDetailsRequest) =
        moviesApiService.getMovieTrailerVideos(request.movieId)
}