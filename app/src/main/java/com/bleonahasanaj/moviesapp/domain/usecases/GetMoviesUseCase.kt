package com.bleonahasanaj.moviesapp.domain.usecases

import androidx.paging.PagingData
import com.bleonahasanaj.moviesapp.db.tables.Filters
import com.bleonahasanaj.moviesapp.domain.UseCases
import com.bleonahasanaj.moviesapp.domain.models.MovieModel
import com.bleonahasanaj.moviesapp.domain.repositories.MoviesRepository
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