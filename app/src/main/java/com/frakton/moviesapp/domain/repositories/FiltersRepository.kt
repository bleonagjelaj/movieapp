package com.frakton.moviesapp.domain.repositories

import com.frakton.moviesapp.data.retrofit.models.request.MovieFilters
import com.frakton.moviesapp.db.MovieAppDatabase
import com.frakton.moviesapp.domain.mappers.FiltersDBModelMapper
import com.frakton.moviesapp.domain.mappers.MovieFiltersModelMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FiltersRepository @Inject constructor(
    private val moviesAppDB: MovieAppDatabase,
    private val filtersDBModelMapper: FiltersDBModelMapper,
    private val movieFiltersModelMapper: MovieFiltersModelMapper
) {
    suspend fun getFiltersFromDB(): Flow<MovieFilters> = flow {
        emit(filtersDBModelMapper.map(moviesAppDB.FiltersDao().getFilters()))
    }

    suspend fun updateFilters(movieFilters: MovieFilters) {
        moviesAppDB.FiltersDao().deleteAll()
        moviesAppDB.FiltersDao().insertFilters(movieFiltersModelMapper.map(movieFilters))
    }
}