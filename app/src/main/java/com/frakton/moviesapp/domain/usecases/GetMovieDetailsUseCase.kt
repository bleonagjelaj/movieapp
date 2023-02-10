package com.frakton.moviesapp.domain.usecases

import com.frakton.moviesapp.domain.MovieParams
import com.frakton.moviesapp.domain.UseCases
import com.frakton.moviesapp.domain.models.MovieDetailsModel
import com.frakton.moviesapp.domain.repositories.MovieDetailsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMovieDetailsUseCase @Inject constructor(
    private val movieDetailsRepository: MovieDetailsRepository
) : UseCases.GetMovieDetails {
    override suspend fun invoke(input: MovieParams.GetMovieDetailsParams): Flow<MovieDetailsModel> {
        return movieDetailsRepository.getMovieDetails(input)
    }
}