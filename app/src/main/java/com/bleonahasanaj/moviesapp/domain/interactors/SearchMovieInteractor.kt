package com.bleonahasanaj.moviesapp.domain.interactors

import com.bleonahasanaj.moviesapp.data.retrofit.MoviesApiSource
import com.bleonahasanaj.moviesapp.data.retrofit.models.request.SearchMovieRequest
import com.bleonahasanaj.moviesapp.data.retrofit.models.response.MoviesResponse
import com.bleonahasanaj.moviesapp.domain.MovieInteractors

class SearchMovieInteractor(private val moviesApiSource: MoviesApiSource) :
    MovieInteractors.SearchMovie {
    override suspend fun invoke(input: SearchMovieRequest): MoviesResponse {
        return moviesApiSource.searchMovie(input)
    }
}