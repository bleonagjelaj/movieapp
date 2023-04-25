package com.frakton.moviesapp.domain.enums

import com.frakton.moviesapp.R

enum class MovieGenreEnum(val genreName: String, val icon: Int) {
    Action("Action", R.drawable.ic_action),
    Adventure("Adventure", R.drawable.ic_adventure),
    Animation("Animation", R.drawable.ic_animation),
    Comedy("Comedy", R.drawable.ic_comedy),
    Crime("Crime", R.drawable.ic_crime),
    Documentary("Documentary", R.drawable.ic_documentary),
    Drama("Drama", R.drawable.ic_drama),
    Family("Family", R.drawable.ic_image_not_supported),
    Fantasy("Fantasy", R.drawable.ic_fantasy),
    History("History", R.drawable.ic_history),
    Horror("Horror", R.drawable.ic_horror),
    Music("Music", R.drawable.ic_image_not_supported),
    Mystery("Mystery", R.drawable.ic_mistery),
    Romance("Romance", R.drawable.ic_romance),
    Science_Fiction("Sci-Fi", R.drawable.ic_sci_fi),
    TV_Movie("TV Movie", R.drawable.ic_image_not_supported),
    Thriller("Thriller", R.drawable.ic_thriller),
    War("War", R.drawable.ic_war),
    Western("Western", R.drawable.ic_western);

    companion object {
        fun getGenreIconByName(name: String) = values().firstOrNull { it.name == name }?.icon
    }
}