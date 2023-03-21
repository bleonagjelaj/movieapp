package com.frakton.moviesapp.domain.usecases

import com.frakton.moviesapp.domain.UseCases
import com.frakton.moviesapp.domain.models.MovieFiltersModel
import com.frakton.moviesapp.domain.repositories.FiltersRepository
import javax.inject.Inject

class UpdateFiltersUseCase @Inject constructor(
    private val filtersRepository: FiltersRepository
) : UseCases.UpdateFilters {
    override suspend operator fun invoke(movieFilters: MovieFiltersModel): Boolean {
        return filtersRepository.updateFilters(movieFilters)
    }
}