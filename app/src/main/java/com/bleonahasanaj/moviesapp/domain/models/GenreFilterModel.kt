package com.bleonahasanaj.moviesapp.domain.models

data class GenreFilterModel(
    val id: Int,
    val name: String,
    val icon: Int,
    var isChecked: Boolean
)
