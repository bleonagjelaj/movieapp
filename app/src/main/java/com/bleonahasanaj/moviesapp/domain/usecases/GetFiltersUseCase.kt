package com.bleonahasanaj.moviesapp.domain.usecases

import com.bleonahasanaj.moviesapp.db.tables.Filters
import com.bleonahasanaj.moviesapp.domain.UseCases
import com.bleonahasanaj.moviesapp.domain.repositories.FiltersRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFiltersUseCase @Inject constructor(
    private val filtersRepository: FiltersRepository
) : UseCases.GetFilters {
    override suspend operator fun invoke(): Flow<Filters?> = filtersRepository.getFiltersFromDB()
}