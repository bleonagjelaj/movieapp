package com.frakton.moviesapp.data

import com.frakton.moviesapp.domain.models.GenresModel

data class GenreTestModel(
    val genreList: List<GenresModel>
)

val movieGenres = lazy {
    GenreTestModel(
        listOf(
            GenresModel(id = 28, name = "Action"),
            GenresModel(id = 12, name = "Adventure"),
            GenresModel(id = 16, name = "Animation"),
            GenresModel(id = 35, name = "Comedy"),
            GenresModel(id = 80, name = "Crime"),
            GenresModel(id = 99, name = "Documentary"),
            GenresModel(id = 18, name = "Drama"),
            GenresModel(id = 10751, name = "Family"),
            GenresModel(id = 14, name = "Fantasy"),
            GenresModel(id = 36, name = "History"),
            GenresModel(id = 27, name = "Horror"),
            GenresModel(id = 10402, name = "Music"),
            GenresModel(id = 9648, name = "Mystery"),
            GenresModel(id = 10749, name = "Romance"),
            GenresModel(id = 878, name = "Science Fiction"),
            GenresModel(id = 10770, name = "TV Movie"),
            GenresModel(id = 53, name = "Thriller"),
            GenresModel(id = 10752, name = "War"),
            GenresModel(id = 37, name = "Western")
        )
    )
}