package com.bleonahasanaj.moviesapp.domain.usecases

import com.bleonahasanaj.moviesapp.domain.MovieParams
import com.bleonahasanaj.moviesapp.domain.UseCases
import com.bleonahasanaj.moviesapp.domain.models.TrailersVideosModel
import com.bleonahasanaj.moviesapp.domain.repositories.MovieDetailsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMovieTrailerVideosUseCase @Inject constructor(
    private val movieDetailsRepository: MovieDetailsRepository
) : UseCases.GetMovieVideoTrailers {
    override suspend fun invoke(input: MovieParams.GetMovieDetailsParams): Flow<TrailersVideosModel> {
        return movieDetailsRepository.getMovieTrailerVideos(input)
    }
}