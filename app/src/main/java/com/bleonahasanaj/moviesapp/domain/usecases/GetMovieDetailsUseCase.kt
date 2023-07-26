package com.bleonahasanaj.moviesapp.domain.usecases

import com.bleonahasanaj.moviesapp.domain.MovieParams
import com.bleonahasanaj.moviesapp.domain.UseCases
import com.bleonahasanaj.moviesapp.domain.models.MovieDetailsModel
import com.bleonahasanaj.moviesapp.domain.repositories.MovieDetailsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMovieDetailsUseCase @Inject constructor(
    private val movieDetailsRepository: MovieDetailsRepository
) : UseCases.GetMovieDetails {
    override suspend fun invoke(input: MovieParams.GetMovieDetailsParams): Flow<MovieDetailsModel> {
        return movieDetailsRepository.getMovieDetails(input)
    }
}