package com.frakton.moviesapp.testcases

import androidx.test.core.app.ApplicationProvider
import com.frakton.moviesapp.db.DatabaseBuilder
import com.frakton.moviesapp.db.dao.FiltersDao
import com.frakton.moviesapp.db.dao.GenresDao
import com.frakton.moviesapp.db.tables.Filters
import com.frakton.moviesapp.db.tables.Genres
import com.frakton.moviesapp.util.Constants
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test


class RoomDatabaseTest : TestCase() {
    private var filtersDao: FiltersDao? = null
    private var genresDao: GenresDao? = null

    @Before
    fun `initialize Dao Instances`() {
        val dbInstance = DatabaseBuilder.getInstance(ApplicationProvider.getApplicationContext())
        filtersDao = dbInstance?.FiltersDao()
        genresDao = dbInstance?.GenresDao()
    }

    @Test
    fun `test deletion on Filters Table`() = runBlocking {
        val filter = Filters(
            id = Constants.FILTERS_TABLE_ID,
            sortBy = null, filterByYear = 2005,
            filterByGenres = null
        )
        filtersDao?.insertFilters(filter)
        filtersDao?.deleteAll()
        assert(getFiltersData() == null)
    }

    @Test
    fun `test deletion on Genres Table`() = runBlocking {
        val genresString = "TestGenre"
        genresDao?.insertGenres(Genres(Constants.GENRES_TABLE_ID, genresString))
        genresDao?.deleteAll()
        assert(getGenresData() == null)
    }

    @Test
    fun `test insertion on Filters Table`() = runBlocking {
        filtersDao?.deleteAll()
        val testFilter = Filters(
            id = Constants.FILTERS_TABLE_ID,
            sortBy = null, filterByYear = 2005,
            filterByGenres = null
        )
        filtersDao?.insertFilters(testFilter)
        val filterFromDB = getFiltersData()

        assert(filterFromDB?.filterByYear == testFilter.filterByYear)
    }

    private suspend fun getFiltersData() = filtersDao?.getFilters()

    @Test
    fun `test insertion on Genres Table`() = runBlocking {
        genresDao?.deleteAll()
        val genresString = "TestGenre"
        genresDao?.insertGenres(Genres(Constants.GENRES_TABLE_ID, genresString))
        assert(getGenresData()?.genres == genresString)
    }

    private suspend fun getGenresData() = genresDao?.getGenres()
}