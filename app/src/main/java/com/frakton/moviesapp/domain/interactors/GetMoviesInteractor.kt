package com.frakton.moviesapp.domain.interactors

import com.frakton.moviesapp.data.retrofit.MoviesApiSource
import com.frakton.moviesapp.data.retrofit.models.request.GetMoviesRequest
import com.frakton.moviesapp.data.retrofit.models.response.MoviesResponse
import com.frakton.moviesapp.domain.MovieInteractors
import javax.inject.Inject

class GetMoviesInteractor @Inject constructor(
    private val moviesApiSource: MoviesApiSource?
) : MovieInteractors.GetMovies {
    override suspend fun invoke(input: GetMoviesRequest): MoviesResponse? {
        return moviesApiSource?.getMoviesFromApi(input)
    }
}