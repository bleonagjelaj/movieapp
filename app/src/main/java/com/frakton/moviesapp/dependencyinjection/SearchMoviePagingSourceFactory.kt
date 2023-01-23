package com.frakton.moviesapp.dependencyinjection

import com.frakton.moviesapp.domain.pagingsources.SearchMoviePagingSource
import dagger.assisted.AssistedFactory

@AssistedFactory
interface SearchMoviePagingSourceFactory {
    fun create(movieTitle: String): SearchMoviePagingSource
}