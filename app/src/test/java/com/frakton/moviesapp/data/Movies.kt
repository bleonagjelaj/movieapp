package com.frakton.moviesapp.data

import com.frakton.moviesapp.domain.models.MovieDetailsModel

val firstMovie = lazy {
    MovieDetailsModel(
        id = 502356,
        title = "The Super Mario Bros. Movie",
        rating = 7.5F,
        posterPath = "https://image.tmdb.org/t/p/original/qNBAXBIQlnOThrVvA6mA2B5ggV6.jpg",
        description = "While working underground to fix a water main, Brooklyn plumbers—and " +
                "brothers—Mario and Luigi are transported down a mysterious pipe and wander " +
                "into a magical new world. But when the brothers are separated, Mario embarks " +
                "on an epic quest to find Luigi.",
        releaseDate = "2023-04-05",
        revenue = "876438061",
        budget = "$100000000",
        productionCompany = "Universal Pictures, Illumination, Nintendo",
        genres = listOf("Animation", "Adventure", "Family", "Fantasy", "Comedy"),
        releaseYear = "(2023)"
    )
}