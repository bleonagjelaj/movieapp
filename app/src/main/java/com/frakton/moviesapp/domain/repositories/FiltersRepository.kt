package com.frakton.moviesapp.domain.repositories

import com.frakton.moviesapp.db.dao.FiltersDao
import com.frakton.moviesapp.db.tables.Filters
import com.frakton.moviesapp.domain.mappers.FiltersDBModelMapper
import com.frakton.moviesapp.domain.mappers.MovieFiltersModelMapper
import com.frakton.moviesapp.domain.models.MovieFiltersModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FiltersRepository @Inject constructor(
    private val filtersDao: FiltersDao,
    private val filtersDBModelMapper: FiltersDBModelMapper,
    private val movieFiltersModelMapper: MovieFiltersModelMapper
) {
    suspend fun getFiltersFromDB(): Flow<Filters> = flow {
        emit(filtersDao.getFilters())
    }

    suspend fun getFiltersInitialStateFromDB(): Flow<MovieFiltersModel> = flow {
        emit(filtersDBModelMapper.map(filtersDao.getFilters()))
    }

    suspend fun updateFilters(movieFilters: MovieFiltersModel) {
        filtersDao.deleteAll()
        filtersDao.insertFilters(movieFiltersModelMapper.map(movieFilters))
    }
}