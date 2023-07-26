package com.bleonahasanaj.moviesapp.domain.repositories

import com.bleonahasanaj.moviesapp.db.dao.FiltersDao
import com.bleonahasanaj.moviesapp.db.tables.Filters
import com.bleonahasanaj.moviesapp.domain.mappers.FiltersDBModelMapper
import com.bleonahasanaj.moviesapp.domain.mappers.MovieFiltersModelMapper
import com.bleonahasanaj.moviesapp.domain.models.MovieFiltersModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FiltersRepository @Inject constructor(
    private val filtersDao: FiltersDao?,
    private val filtersDBModelMapper: FiltersDBModelMapper,
    private val movieFiltersModelMapper: MovieFiltersModelMapper
) {
    suspend fun getFiltersFromDB(): Flow<Filters?> = flow {
        emit(filtersDao?.getFilters())
    }

    suspend fun getFiltersInitialStateFromDB(): Flow<MovieFiltersModel> = flow {
        emit(filtersDBModelMapper.map(filtersDao?.getFilters()))
    }

    suspend fun updateFilters(newMovieFilters: MovieFiltersModel): Boolean {
        var filtersGotUpdated = false
        getFiltersFromDB().collect { oldMovieFilters ->
            if (oldMovieFilters != movieFiltersModelMapper.map(newMovieFilters)) {
                filtersDao?.deleteAll()
                filtersDao?.insertFilters(movieFiltersModelMapper.map(newMovieFilters))
                filtersGotUpdated = true
            }
        }
        return filtersGotUpdated
    }
}