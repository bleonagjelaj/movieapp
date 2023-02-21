package com.frakton.moviesapp.domain.usecases

import com.frakton.moviesapp.domain.UseCases
import com.frakton.moviesapp.domain.models.MovieFiltersModel
import com.frakton.moviesapp.domain.repositories.FiltersRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFiltersInitialStateUseCase @Inject constructor(
    private val filtersRepository: FiltersRepository
) : UseCases.GetFiltersInitialState {
    override suspend operator fun invoke(): Flow<MovieFiltersModel> =
        filtersRepository.getFiltersInitialStateFromDB()
}