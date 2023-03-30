package com.frakton.moviesapp.domain.usecases

import com.frakton.moviesapp.domain.UseCases
import com.frakton.moviesapp.domain.models.GenresModel
import com.frakton.moviesapp.domain.repositories.GenresRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetGenresUseCase @Inject constructor(
    private val genresRepository: GenresRepository
) : UseCases.GetGenres {
    override suspend operator fun invoke(): Flow<List<GenresModel>> = genresRepository.getGenres()
}