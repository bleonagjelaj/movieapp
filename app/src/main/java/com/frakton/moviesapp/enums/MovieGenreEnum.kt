package com.frakton.moviesapp.enums

enum class MovieGenreEnum(val genreId: Int, val genre: String) {
    Action(28, "Action"),
    Adventure(12, "Adventure"),
    Animation(16, "Animation"),
    Comedy(35, "Comedy"),
    Crime(80, "Crime"),
    Documentary(99, "Documentary"),
    Drama(18, "Drama"),
    Family(10751, "Family"),
    Fantasy(14, "Fantasy"),
    History(36, "History"),
    Horror(27, "Horror"),
    Music(10402, "Music"),
    Mystery(9648, "Mystery"),
    Romance(10749, "Romance"),
    Science_Fiction(878, "Science Fiction"),
    TV_Movie(10770, "TV Movie"),
    Thriller(53, "Thriller"),
    War(10752, "War"),
    Western(37, "Western");

    companion object {
        fun getGenreById(id: Int) = values().firstOrNull { it.genreId == id }?.genre
    }
}