package com.bleonahasanaj.moviesapp.domain.usecases

import com.bleonahasanaj.moviesapp.domain.UseCases
import com.bleonahasanaj.moviesapp.domain.models.GenreFilterModel
import com.bleonahasanaj.moviesapp.domain.repositories.GenresRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetGenresFromDBUseCase @Inject constructor(
    private val genresRepository: GenresRepository
) : UseCases.GetGenresFromDB {
    override suspend operator fun invoke(): Flow<List<GenreFilterModel>> {
        return genresRepository.getGenresListFromDb()
    }
}