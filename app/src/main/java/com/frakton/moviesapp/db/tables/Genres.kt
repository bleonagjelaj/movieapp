package com.frakton.moviesapp.db.tables

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Genres")
data class Genres(
    @PrimaryKey val id: Int = 9,
    @ColumnInfo(name = "genres") val genres: String?
)
