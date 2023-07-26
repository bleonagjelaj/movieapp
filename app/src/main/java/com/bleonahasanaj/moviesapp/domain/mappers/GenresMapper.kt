package com.bleonahasanaj.moviesapp.domain.mappers

import com.bleonahasanaj.moviesapp.data.retrofit.models.response.GenreResponse
import com.bleonahasanaj.moviesapp.domain.models.GenresModel

class GenresMapper {
    fun map(genresResponse: GenreResponse): List<GenresModel> {
        val genres = arrayListOf<GenresModel>()
        genresResponse.genres.forEach { genre ->
            genres.add(GenresModel(genre.id, genre.name))
        }
        return genres
    }
}