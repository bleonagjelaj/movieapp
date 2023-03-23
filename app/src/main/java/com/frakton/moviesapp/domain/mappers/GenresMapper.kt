package com.frakton.moviesapp.domain.mappers

import com.frakton.moviesapp.data.retrofit.models.response.GenreResponse
import com.frakton.moviesapp.domain.models.GenresModel

class GenresMapper {
    fun map(genresResponse: GenreResponse): List<GenresModel> {
        val genres = arrayListOf<GenresModel>()
        genresResponse.genres.forEach { genre->
            genres.add(GenresModel(genre.id, genre.name))
        }
        return genres
    }
}