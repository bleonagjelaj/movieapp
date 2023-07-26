package com.bleonahasanaj.moviesapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bleonahasanaj.moviesapp.db.dao.FiltersDao
import com.bleonahasanaj.moviesapp.db.dao.GenresDao
import com.bleonahasanaj.moviesapp.db.tables.Filters
import com.bleonahasanaj.moviesapp.db.tables.Genres

@Database(entities = [Filters::class, Genres::class], version = 2, exportSchema = false)
abstract class MovieAppDatabase : RoomDatabase() {
    abstract fun FiltersDao(): FiltersDao
    abstract fun GenresDao(): GenresDao
}