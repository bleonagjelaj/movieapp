package com.frakton.moviesapp.domain.usecases

import com.frakton.moviesapp.domain.UseCases
import com.frakton.moviesapp.domain.models.GenreFilterModel
import com.frakton.moviesapp.domain.repositories.GenresRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetGenresFromDBUseCase @Inject constructor(
    private val genresRepository: GenresRepository
) : UseCases.GetGenresFromDB {
    override suspend operator fun invoke(): Flow<List<GenreFilterModel>> {
        return genresRepository.getGenresListFromDb()
    }
}