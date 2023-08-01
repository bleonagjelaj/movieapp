package com.bleonahasanaj.moviesapp.data

import com.bleonahasanaj.moviesapp.data.retrofit.models.response.MovieDataModel
import com.bleonahasanaj.moviesapp.data.retrofit.models.response.MoviesResponse
import com.bleonahasanaj.moviesapp.domain.models.MovieDetailsModel
import com.bleonahasanaj.moviesapp.domain.models.TrailerDetails
import com.bleonahasanaj.moviesapp.domain.models.TrailersVideosModel

val superMarioMovie = lazy {
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

val ratatouilleMovieTrailers = lazy {
    TrailersVideosModel(
        movieId = 2062,
        trailerVideos = listOf(
            TrailerDetails(
                key = "NgsQ8mVkN8w",
                thumbnailPath = "https://img.youtube.com/vi/NgsQ8mVkN8w/hqdefault.jpg",
                site = "YouTube"
            ),
            TrailerDetails(
                key = "-tNqfcZKn6k",
                thumbnailPath = "https://img.youtube.com/vi/-tNqfcZKn6k/hqdefault.jpg",
                site = "YouTube"
            ),
            TrailerDetails(
                key = "ppPrfCdu8VM",
                thumbnailPath = "https://img.youtube.com/vi/ppPrfCdu8VM/hqdefault.jpg",
                site = "YouTube"
            )
        )
    )
}

val searchForWolfOfWallStreet = lazy {
    MoviesResponse(
        page = 1,
        results = listOf(
            MovieDataModel(
                adult = false,
                backdropPath = "/7Nwnmyzrtd0FkcRyPqmdzTPppQa.jpg",
                genreIds = listOf(
                    80,
                    18,
                    35
                ),
                id = 106646,
                originalLanguage = "en",
                originalTitle = "The Wolf of Wall Street",
                overview = "A New York stockbroker refuses to cooperate in a large securities fraud case involving corruption on Wall Street, corporate banking world and mob infiltration. Based on Jordan Belfort's autobiography.",
                popularity = 118.152,
                posterPath = "/34m2tygAYBGqA9MXKhRDtzYd4MR.jpg",
                releaseDate = "2013-12-25",
                title = "The Wolf of Wall Street",
                video = false,
                voteAverage = 8.036F,
                voteCount = 21561
            ),
            MovieDataModel(
                adult = false,
                backdropPath = "/k0OuFXdGRJ1krCkUF4MdbyX0alv.jpg",
                genreIds = listOf(18),
                id = 423766,
                originalLanguage = "en",
                originalTitle = "The Wolf of Wall Street",
                overview = "A ruthless stockbroker sells short in the copper business and ruins the life of his " +
                        "friends by ruining their finances.",
                popularity = 2.575,
                posterPath = "/ueUAIuI5TH9LPA8NpJCOjlopz57.jpg",
                releaseDate = "1929-02-09",
                title = "The Wolf of Wall Street",
                video = false,
                voteAverage = 9.5F,
                voteCount = 1
            ),
            MovieDataModel(
                adult = false,
                backdropPath = "/dIG6YYmo7JVfRIJqEDVLSzPPkXP.jpg",
                genreIds = listOf(
                    99,
                    80
                ),
                id = 646967,
                originalLanguage = "en",
                originalTitle = "The PM, the Playboy and the Wolf of Wall Street",
                overview = "Investigative reporters examine the 1MDB scandal, tracing money from the Wolf of Wall " +
                        "Street all the way to the Malaysian Prime Minister.",
                popularity = 2.152,
                posterPath = "/bJnML8e7qXbaWrAO41f7J0VIZF.jpg",
                releaseDate = "2019-10-21",
                title = "The PM, the Playboy and the Wolf of Wall Street",
                video = false,
                voteAverage = 0.0F,
                voteCount = 0
            ),
            MovieDataModel(
                adult = false,
                backdropPath = null,
                genreIds = listOf(80),
                id = 633593,
                originalLanguage = "ja",
                originalTitle = "新網走番外地　吹雪のはぐれ狼",
                overview = "",
                popularity = 0.6,
                posterPath = "/uL4CJRCaf7o74owuHqL2i5A86ov.jpg",
                releaseDate = "1970-12-30",
                title = "New Prison Walls of Abashiri: Stray Wolf in Snow",
                video = false,
                voteAverage = 0.0F,
                voteCount = 0
            )
        ),
        totalPages = 1,
        totalResults = 4
    )
}