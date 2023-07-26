package com.bleonahasanaj.moviesapp.domain.usecases

import com.bleonahasanaj.moviesapp.domain.UseCases
import com.bleonahasanaj.moviesapp.domain.models.MovieFiltersModel
import com.bleonahasanaj.moviesapp.domain.repositories.FiltersRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFiltersInitialStateUseCase @Inject constructor(
    private val filtersRepository: FiltersRepository
) : UseCases.GetFiltersInitialState {
    override suspend operator fun invoke(): Flow<MovieFiltersModel> =
        filtersRepository.getFiltersInitialStateFromDB()
}