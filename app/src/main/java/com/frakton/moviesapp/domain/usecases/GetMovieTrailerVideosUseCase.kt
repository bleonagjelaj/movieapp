package com.frakton.moviesapp.domain.usecases

import com.frakton.moviesapp.domain.MovieParams
import com.frakton.moviesapp.domain.UseCases
import com.frakton.moviesapp.domain.models.TrailersVideosModel
import com.frakton.moviesapp.domain.repositories.MovieDetailsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMovieTrailerVideosUseCase @Inject constructor(
    private val movieDetailsRepository: MovieDetailsRepository
) : UseCases.GetMovieVideoTrailers {
    override suspend fun invoke(input: MovieParams.GetMovieDetailsParams): Flow<TrailersVideosModel> {
        return movieDetailsRepository.getMovieTrailerVideos(input)
    }
}