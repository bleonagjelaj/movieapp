package com.frakton.moviesapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.frakton.moviesapp.db.dao.FiltersDao
import com.frakton.moviesapp.db.tables.Filters

@Database(entities = [Filters::class], version = 1, exportSchema = false)
abstract class MovieAppDatabase : RoomDatabase() {
    abstract fun FiltersDao(): FiltersDao
}