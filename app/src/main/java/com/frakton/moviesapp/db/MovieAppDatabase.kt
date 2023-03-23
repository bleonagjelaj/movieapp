package com.frakton.moviesapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.frakton.moviesapp.db.dao.FiltersDao
import com.frakton.moviesapp.db.dao.GenresDao
import com.frakton.moviesapp.db.tables.Filters
import com.frakton.moviesapp.db.tables.Genres

@Database(entities = [Filters::class, Genres::class], version = 2, exportSchema = false)
abstract class MovieAppDatabase : RoomDatabase() {
    abstract fun FiltersDao(): FiltersDao
    abstract fun GenresDao(): GenresDao
}