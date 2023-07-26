package com.bleonahasanaj.moviesapp.domain.interactors

import com.bleonahasanaj.moviesapp.data.retrofit.MoviesApiSource
import com.bleonahasanaj.moviesapp.data.retrofit.models.request.GetMovieDetailsRequest
import com.bleonahasanaj.moviesapp.data.retrofit.models.response.MovieTrailerVideosResponse
import com.bleonahasanaj.moviesapp.domain.MovieInteractors
import javax.inject.Inject

class GetMovieTrailerVideosInteractor @Inject constructor(
    private val moviesApiSource: MoviesApiSource
) : MovieInteractors.GetMovieTrailerVideos {
    override suspend operator fun invoke(input: GetMovieDetailsRequest): MovieTrailerVideosResponse {
        return moviesApiSource.getMovieTrailerVideos(input)
    }
}