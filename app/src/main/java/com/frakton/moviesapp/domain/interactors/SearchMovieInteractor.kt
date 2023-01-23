package com.frakton.moviesapp.domain.interactors

import com.frakton.moviesapp.data.retrofit.MoviesApiSource
import com.frakton.moviesapp.data.retrofit.models.request.SearchMovieRequest
import com.frakton.moviesapp.data.retrofit.models.response.MoviesResponse
import com.frakton.moviesapp.domain.MovieInteractors

class SearchMovieInteractor(private val moviesApiSource: MoviesApiSource?) :
    MovieInteractors.SearchMovie {
    override suspend fun invoke(input: SearchMovieRequest): MoviesResponse? {
        return moviesApiSource?.searchMovie(input)
    }
}