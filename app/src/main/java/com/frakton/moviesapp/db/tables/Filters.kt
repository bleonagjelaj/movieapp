package com.frakton.moviesapp.db.tables

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.frakton.moviesapp.util.Constants

@Entity(tableName = "Filters")
data class Filters(
    @PrimaryKey val id: Int = Constants.FILTERS_TABLE_ID,
    @ColumnInfo(name = "sortByFilter") val sortBy: String?,
    @ColumnInfo(name = "filterByYear") val filterByYear: Int?,
    @ColumnInfo(name = "filterByGenres") val filterByGenres: String?
)
