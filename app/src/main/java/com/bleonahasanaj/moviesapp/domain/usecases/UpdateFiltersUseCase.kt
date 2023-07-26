package com.bleonahasanaj.moviesapp.domain.usecases

import com.bleonahasanaj.moviesapp.domain.UseCases
import com.bleonahasanaj.moviesapp.domain.models.MovieFiltersModel
import com.bleonahasanaj.moviesapp.domain.repositories.FiltersRepository
import javax.inject.Inject

class UpdateFiltersUseCase @Inject constructor(
    private val filtersRepository: FiltersRepository
) : UseCases.UpdateFilters {
    override suspend operator fun invoke(movieFilters: MovieFiltersModel): Boolean {
        return filtersRepository.updateFilters(movieFilters)
    }
}