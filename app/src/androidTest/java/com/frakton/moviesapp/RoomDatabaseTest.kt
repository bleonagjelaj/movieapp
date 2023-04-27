package com.frakton.moviesapp

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.frakton.moviesapp.db.MovieAppDatabase
import com.frakton.moviesapp.db.dao.FiltersDao
import com.frakton.moviesapp.db.dao.GenresDao
import com.frakton.moviesapp.db.tables.Filters
import com.frakton.moviesapp.db.tables.Genres
import com.frakton.moviesapp.domain.mappers.MovieFiltersModelMapper
import com.frakton.moviesapp.domain.models.MovieFiltersModel
import com.frakton.moviesapp.util.Constants
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class RoomDatabaseTest : TestCase() {
    private var filtersDao: FiltersDao? = null
    private var genresDao: GenresDao? = null
    private lateinit var dbInstance: MovieAppDatabase

    @Before
    fun initDao_Instances() = runBlocking {
        dbInstance = buildTestRoomDB(ApplicationProvider.getApplicationContext())
        filtersDao = dbInstance.FiltersDao()
        genresDao = dbInstance.GenresDao()
    }

    private fun buildTestRoomDB(context: Context) = Room.inMemoryDatabaseBuilder(
        context,
        MovieAppDatabase::class.java
    ).fallbackToDestructiveMigration().build()

    @After
    fun closeDatabase() {
        dbInstance.close()
    }

    @Test
    fun testDeletionOnFiltersTable() = runBlocking {
        val filter = Filters(
            id = Constants.FILTERS_TABLE_ID,
            sortBy = null, filterByYear = 2005,
            filterByGenres = null
        )
        filtersDao?.insertFilters(filter)
        filtersDao?.deleteAll()
        assertNull(getFiltersData())
    }

    @Test
    fun testDeletionOnGenresTable() = runBlocking {
        val genresString = "TestGenre"
        genresDao?.insertGenres(Genres(Constants.GENRES_TABLE_ID, genresString))
        genresDao?.deleteAll()
        assertNull(getGenresData())
    }

    @Test
    fun testInsertionOnFiltersTable() = runBlocking {
        filtersDao?.deleteAll()
        val testFilter = MovieFiltersModel(
            sortBy = "popularity",
            ordering = "asc",
            filterByYear = "2005",
            filterByGenres = emptyList()
        )
        val filterToInsert = MovieFiltersModelMapper().map(testFilter)
        filtersDao?.insertFilters(filterToInsert)
        val filtersFromDB = getFiltersData()
        assertNotNull(filtersFromDB)
        assertEquals(filtersFromDB, filterToInsert)
    }

    private suspend fun getFiltersData() = filtersDao?.getFilters()

    @Test
    fun testInsertionOnGenresTable() = runBlocking {
        genresDao?.deleteAll()
        val genresString = "TestGenre"
        genresDao?.insertGenres(Genres(Constants.GENRES_TABLE_ID, genresString))
        val genresFromDB = getGenresData()?.genres
        assertNotNull(genresFromDB)
        assertEquals(genresFromDB, genresString)
    }

    private suspend fun getGenresData() = genresDao?.getGenres()
}