package com.frakton.moviesapp.domain.usecases

import androidx.paging.PagingData
import com.frakton.moviesapp.data.retrofit.models.response.MovieDataModel
import com.frakton.moviesapp.domain.MovieParams
import com.frakton.moviesapp.domain.UseCases
import com.frakton.moviesapp.domain.base.io
import com.frakton.moviesapp.domain.repositories.MoviesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetMoviesUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository
) : UseCases.GetMovies {
    override suspend fun invoke(input: MovieParams.GetMoviesParams): Flow<PagingData<MovieDataModel>> {
        return io {
            moviesRepository.getMoviesFromApi()
        }
    }
}