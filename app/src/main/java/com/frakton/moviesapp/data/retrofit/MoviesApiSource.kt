package com.frakton.moviesapp.data.retrofit

import com.frakton.moviesapp.data.retrofit.models.request.GetMoviesRequest
import com.frakton.moviesapp.data.retrofit.models.request.SearchMovieRequest
import javax.inject.Inject

class MoviesApiSource @Inject constructor(private val moviesApiService: MoviesApiService) {
    suspend fun getMoviesFromApi(request: GetMoviesRequest) =
        moviesApiService.getMovies(request.page)

    suspend fun searchMovie(request: SearchMovieRequest) =
        moviesApiService.searchMovies(request.page, request.movieTitle)
}