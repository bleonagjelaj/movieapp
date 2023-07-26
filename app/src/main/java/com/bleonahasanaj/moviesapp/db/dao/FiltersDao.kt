package com.bleonahasanaj.moviesapp.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.bleonahasanaj.moviesapp.db.tables.Filters

@Dao
interface FiltersDao {
    @Query("SELECT * FROM Filters WHERE id='5'")
    suspend fun getFilters(): Filters

    @Insert
    suspend fun insertFilters(filters: Filters)

    @Query("DELETE from Filters")
    suspend fun deleteAll()
}