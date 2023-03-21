package com.frakton.moviesapp.domain.usecases

import androidx.paging.PagingData
import com.frakton.moviesapp.db.tables.Filters
import com.frakton.moviesapp.domain.UseCases
import com.frakton.moviesapp.domain.models.MovieModel
import com.frakton.moviesapp.domain.repositories.MoviesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetMoviesUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository
) : UseCases.GetMovies {
    override suspend operator fun invoke(filtersRequest: Filters?):
            Flow<PagingData<MovieModel>> {
        return moviesRepository.getMoviesFromApi(filtersRequest)
    }
}