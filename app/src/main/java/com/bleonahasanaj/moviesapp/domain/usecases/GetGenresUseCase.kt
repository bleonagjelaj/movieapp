package com.bleonahasanaj.moviesapp.domain.usecases

import com.bleonahasanaj.moviesapp.domain.UseCases
import com.bleonahasanaj.moviesapp.domain.models.GenresModel
import com.bleonahasanaj.moviesapp.domain.repositories.GenresRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetGenresUseCase @Inject constructor(
    private val genresRepository: GenresRepository
) : UseCases.GetGenres {
    override suspend operator fun invoke(): Flow<List<GenresModel>> = genresRepository.getGenres()
}