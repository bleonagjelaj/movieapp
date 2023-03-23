package com.frakton.moviesapp.domain.interactors

import com.frakton.moviesapp.data.retrofit.MoviesApiSource
import com.frakton.moviesapp.data.retrofit.models.response.GenreResponse
import com.frakton.moviesapp.domain.MovieInteractors
import javax.inject.Inject

class GetGenresInteractor @Inject constructor(
    private val moviesApiSource: MoviesApiSource
) : MovieInteractors.GetGenres {
    override suspend operator fun invoke(input: Unit?): GenreResponse {
        return moviesApiSource.getGenres()
    }
}