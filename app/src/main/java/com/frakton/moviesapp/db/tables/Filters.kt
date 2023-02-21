package com.frakton.moviesapp.db.tables

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Filters")
data class Filters(
    @PrimaryKey val id: Int=5,
    @ColumnInfo(name = "sortByFilter") val sortBy: String?,
    @ColumnInfo(name = "filterByYear") val filterByYear: String?,
    @ColumnInfo(name = "filterByGenres") val filterByGenres: String?
)
