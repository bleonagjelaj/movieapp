package com.bleonahasanaj.moviesapp.domain.usecases

import com.bleonahasanaj.moviesapp.domain.UseCases
import com.bleonahasanaj.moviesapp.domain.models.GenresModel
import com.bleonahasanaj.moviesapp.domain.repositories.GenresRepository
import javax.inject.Inject

class UpdateGenresUseCase @Inject constructor(
    private val genresRepository: GenresRepository
) : UseCases.UpdateGenres {
    override suspend operator fun invoke(genresList: List<GenresModel>) =
        genresRepository.updateGenres(genresList)
}