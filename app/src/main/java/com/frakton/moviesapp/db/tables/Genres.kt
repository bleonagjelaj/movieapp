package com.frakton.moviesapp.db.tables

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.frakton.moviesapp.util.Constants

@Entity(tableName = "Genres")
data class Genres(
    @PrimaryKey val id: Int = Constants.GENRES_TABLE_ID,
    @ColumnInfo(name = "genres") val genres: String?
)
