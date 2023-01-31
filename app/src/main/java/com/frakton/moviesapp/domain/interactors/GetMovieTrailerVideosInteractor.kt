package com.frakton.moviesapp.domain.interactors

import com.frakton.moviesapp.data.retrofit.MoviesApiSource
import com.frakton.moviesapp.data.retrofit.models.request.GetMovieDetailsRequest
import com.frakton.moviesapp.data.retrofit.models.response.MovieTrailerVideosResponse
import com.frakton.moviesapp.domain.MovieInteractors
import javax.inject.Inject

class GetMovieTrailerVideosInteractor @Inject constructor(
    private val moviesApiSource: MoviesApiSource
) : MovieInteractors.GetMovieTrailerVideos {
    override suspend operator fun invoke(input: GetMovieDetailsRequest): MovieTrailerVideosResponse {
        return moviesApiSource.getMovieTrailerVideos(input)
    }
}