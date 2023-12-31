package com.bleonahasanaj.moviesapp.domain.interactors

import com.bleonahasanaj.moviesapp.data.retrofit.MoviesApiSource
import com.bleonahasanaj.moviesapp.data.retrofit.models.request.GetMoviesRequest
import com.bleonahasanaj.moviesapp.data.retrofit.models.response.MoviesResponse
import com.bleonahasanaj.moviesapp.domain.MovieInteractors
import javax.inject.Inject

class GetMoviesInteractor @Inject constructor(
    private val moviesApiSource: MoviesApiSource
) : MovieInteractors.GetMovies {
    override suspend fun invoke(input: GetMoviesRequest): MoviesResponse {
        return moviesApiSource.getMoviesFromApi(input)
    }
}