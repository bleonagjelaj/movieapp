package com.bleonahasanaj.moviesapp.db

import android.content.Context
import androidx.room.Room

object DatabaseBuilder {
    private var INSTANCE: MovieAppDatabase? = null

    fun getInstance(context: Context): MovieAppDatabase? {
        if (INSTANCE == null) {
            synchronized(MovieAppDatabase::class) {
                INSTANCE = buildRoomDB(context)
            }
        }
        return INSTANCE
    }

    private fun buildRoomDB(context: Context) = Room.databaseBuilder(
        context,
        MovieAppDatabase::class.java,
        "movieAppDatabase"
    ).fallbackToDestructiveMigration().build()
}