package com.frakton.moviesapp.domain.usecases

import com.frakton.moviesapp.domain.UseCases
import com.frakton.moviesapp.domain.models.GenresModel
import com.frakton.moviesapp.domain.repositories.GenresRepository
import javax.inject.Inject

class UpdateGenresUseCase @Inject constructor(
    private val genresRepository: GenresRepository
): UseCases.UpdateGenres {
    override suspend operator fun invoke(genresList: List<GenresModel>) =
        genresRepository.updateGenres(genresList)
}