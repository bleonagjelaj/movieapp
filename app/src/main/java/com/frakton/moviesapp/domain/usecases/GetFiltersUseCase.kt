package com.frakton.moviesapp.domain.usecases

import com.frakton.moviesapp.data.retrofit.models.request.MovieFilters
import com.frakton.moviesapp.domain.UseCases
import com.frakton.moviesapp.domain.repositories.FiltersRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFiltersUseCase @Inject constructor(
    private val filtersRepository: FiltersRepository
) : UseCases.GetFilters{
    override suspend operator fun invoke(): Flow<MovieFilters> = filtersRepository.getFiltersFromDB()
}