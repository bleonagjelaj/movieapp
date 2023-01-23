package com.frakton.moviesapp.domain.usecases

import androidx.paging.PagingData
import com.frakton.moviesapp.domain.UseCases
import com.frakton.moviesapp.domain.models.MovieModel
import com.frakton.moviesapp.domain.repositories.MoviesRepository
import com.frakton.moviesapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class GetMoviesUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository
) : UseCases.GetMovies {
    override suspend operator fun invoke(): Flow<Resource<PagingData<MovieModel>>> {
        return flow {
            try {
                moviesRepository.getMoviesFromApi().collect { movieData ->
                    emit(Resource.Success(movieData))
                }
            } catch (exception: Exception) {
                emit(Resource.Error(exception.localizedMessage))
            }
        }
    }
}