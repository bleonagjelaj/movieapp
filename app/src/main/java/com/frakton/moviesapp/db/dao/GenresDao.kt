package com.frakton.moviesapp.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.frakton.moviesapp.db.tables.Genres

@Dao
interface GenresDao {
    @Query("SELECT * FROM Genres WHERE id='9'")
    suspend fun getGenres(): Genres

    @Insert
    suspend fun insertGenres(genres: Genres)

    @Query("DELETE from Genres")
    suspend fun deleteAll()
}