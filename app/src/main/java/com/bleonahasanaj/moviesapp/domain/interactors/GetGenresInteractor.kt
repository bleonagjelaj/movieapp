package com.bleonahasanaj.moviesapp.domain.interactors

import com.bleonahasanaj.moviesapp.data.retrofit.MoviesApiSource
import com.bleonahasanaj.moviesapp.data.retrofit.models.response.GenreResponse
import com.bleonahasanaj.moviesapp.domain.MovieInteractors
import javax.inject.Inject

class GetGenresInteractor @Inject constructor(
    private val moviesApiSource: MoviesApiSource
) : MovieInteractors.GetGenres {
    override suspend operator fun invoke(input: Unit?): GenreResponse {
        return moviesApiSource.getGenres()
    }
}