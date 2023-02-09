package com.frakton.moviesapp.domain.enums

import com.frakton.moviesapp.R

enum class MovieGenreEnum(val genreId: Int, val genre: String, val icon: Int) {
    Action(28, "Action", R.drawable.ic_action),
    Adventure(12, "Adventure", R.drawable.ic_adventure),
    Animation(16, "Animation", R.drawable.ic_animation),
    Comedy(35, "Comedy", R.drawable.ic_comedy),
    Crime(80, "Crime", R.drawable.ic_crime),
    Documentary(99, "Documentary", R.drawable.ic_documentary),
    Drama(18, "Drama", R.drawable.ic_drama),
    Family(10751, "Family", R.drawable.ic_image_not_supported),
    Fantasy(14, "Fantasy", R.drawable.ic_fantasy),
    History(36, "History", R.drawable.ic_history),
    Horror(27, "Horror", R.drawable.ic_horror),
    Music(10402, "Music", R.drawable.ic_image_not_supported),
    Mystery(9648, "Mystery", R.drawable.ic_mistery),
    Romance(10749, "Romance", R.drawable.ic_romance),
    Science_Fiction(878, "Sci-Fi", R.drawable.ic_sci_fi),
    TV_Movie(10770, "TV Movie", R.drawable.ic_image_not_supported),
    Thriller(53, "Thriller", R.drawable.ic_thriller),
    War(10752, "War", R.drawable.ic_war),
    Western(37, "Western", R.drawable.ic_western);

    companion object {
        fun getGenreById(id: Int) = values().firstOrNull { it.genreId == id }?.genre

        fun getGenresFilters() =
            values().filter { it != Family && it != Music && it != TV_Movie }
    }
}