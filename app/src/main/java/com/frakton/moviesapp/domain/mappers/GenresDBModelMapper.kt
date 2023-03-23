package com.frakton.moviesapp.domain.mappers

import android.util.Log
import com.frakton.moviesapp.db.tables.Genres
import com.frakton.moviesapp.domain.models.GenresModel
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class GenresDBModelMapper {
    private val TAG = "GenresDBModelMapper"

    fun map(genresModelList: List<GenresModel>): Genres {
        return try {
            val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
            val type = Types.newParameterizedType(List::class.java, GenresModel::class.java)
            val adapter = moshi.adapter<List<GenresModel>>(type)
            Genres(genres = adapter.toJson(genresModelList))
        } catch (exception: Exception) {
            Log.d(TAG, "mapping list to Json exception: $exception")
            Genres(genres = "")
        }
    }
}