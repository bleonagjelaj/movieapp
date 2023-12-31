package com.bleonahasanaj.moviesapp.domain.mappers

import android.util.Log
import com.bleonahasanaj.moviesapp.R
import com.bleonahasanaj.moviesapp.db.tables.Genres
import com.bleonahasanaj.moviesapp.domain.enums.MovieGenreEnum
import com.bleonahasanaj.moviesapp.domain.models.GenreFilterModel
import com.bleonahasanaj.moviesapp.domain.models.GenresModel
import com.squareup.moshi.JsonAdapter
import javax.inject.Inject

class GenresDBModelMapper @Inject constructor(
    private val genresModelListJsonAdapter: JsonAdapter<List<GenresModel>>
) {
    private val TAG = "GenresDBModelMapper"
    private val scienceFictionGenreName = "Science Fiction"

    fun mapToJson(genresModelList: List<GenresModel>): Genres {
        return try {
            Genres(genres = genresModelListJsonAdapter.toJson(genresModelList))
        } catch (exception: Exception) {
            Log.d(TAG, "mapping list to Json exception: $exception")
            Genres(genres = "")
        }
    }

    fun mapToList(genres: Genres?): List<GenreFilterModel> {
        return try {
            val genresList = arrayListOf<GenreFilterModel>()
            getGenresListFromJson(genres)
                ?.filter { !genresToHide().contains(it.name) }
                ?.forEach { genresModel ->
                    val genreName = getGenreName(genresModel.name)
                    genresList.add(
                        GenreFilterModel(
                            id = genresModel.id,
                            name = genreName,
                            icon = getGenreIcon(genreName),
                            isChecked = false
                        )
                    )
                }
            genresList
        } catch (exception: Exception) {
            Log.d(TAG, "mapping Json to list exception: $exception")
            emptyList()
        }
    }

    private fun getGenreName(name: String) =
        if (name == scienceFictionGenreName) MovieGenreEnum.Science_Fiction.genreName else name


    private fun getGenresListFromJson(genres: Genres?) =
        genres?.genres?.let { genresModelListJsonAdapter.fromJson(it) }

    private fun genresToHide() = listOf(
        MovieGenreEnum.Family.genreName,
        MovieGenreEnum.Music.genreName,
        MovieGenreEnum.TV_Movie.genreName
    )

    private fun getGenreIcon(name: String) =
        MovieGenreEnum.getGenreIconByName(name) ?: R.drawable.ic_image_not_supported
}