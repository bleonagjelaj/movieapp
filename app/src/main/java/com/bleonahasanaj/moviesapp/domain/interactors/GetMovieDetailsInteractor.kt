package com.bleonahasanaj.moviesapp.domain.interactors

import com.bleonahasanaj.moviesapp.data.retrofit.MoviesApiSource
import com.bleonahasanaj.moviesapp.data.retrofit.models.request.GetMovieDetailsRequest
import com.bleonahasanaj.moviesapp.data.retrofit.models.response.MovieDetailsResponse
import com.bleonahasanaj.moviesapp.domain.MovieInteractors
import javax.inject.Inject

class GetMovieDetailsInteractor @Inject constructor(
    private val moviesApiSource: MoviesApiSource
) : MovieInteractors.GetMovieDetails {
    override suspend operator fun invoke(input: GetMovieDetailsRequest): MovieDetailsResponse {
        return moviesApiSource.getMovieWithId(input)
    }
}