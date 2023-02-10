package com.frakton.moviesapp.domain.interactors

import com.frakton.moviesapp.data.retrofit.MoviesApiSource
import com.frakton.moviesapp.data.retrofit.models.request.GetMovieDetailsRequest
import com.frakton.moviesapp.data.retrofit.models.response.MovieDetailsResponse
import com.frakton.moviesapp.domain.MovieInteractors
import javax.inject.Inject

class GetMovieDetailsInteractor @Inject constructor(
    private val moviesApiSource: MoviesApiSource
) : MovieInteractors.GetMovieDetails {
    override suspend operator fun invoke(input: GetMovieDetailsRequest): MovieDetailsResponse {
        return moviesApiSource.getMovieWithId(input)
    }
}