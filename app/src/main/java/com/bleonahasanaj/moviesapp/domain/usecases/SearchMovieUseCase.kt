package com.bleonahasanaj.moviesapp.domain.usecases

import androidx.paging.PagingData
import com.bleonahasanaj.moviesapp.domain.MovieParams
import com.bleonahasanaj.moviesapp.domain.UseCases
import com.bleonahasanaj.moviesapp.domain.models.MovieModel
import com.bleonahasanaj.moviesapp.domain.repositories.MoviesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchMovieUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository
) : UseCases.SearchMovie {
    override suspend operator fun invoke(
        input: MovieParams.SearchMovieParams
    ): Flow<PagingData<MovieModel>> {
        return moviesRepository.searchMovies(input.movieTitle)
    }
}