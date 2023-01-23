package com.frakton.moviesapp.domain.usecases

import com.frakton.moviesapp.domain.MovieParams
import com.frakton.moviesapp.domain.UseCases
import com.frakton.moviesapp.domain.repositories.MoviesRepository
import com.frakton.moviesapp.util.Resource
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class SearchMovieUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository
) : UseCases.SearchMovie {
    override suspend operator fun invoke(input: MovieParams.SearchMovieParams) = flow {
        try {
            moviesRepository.searchMovies(input.movieTitle).collect { movieData ->
                emit(Resource.Success(movieData))
            }
        } catch (exception: HttpException) {
            emit(
                Resource.Error(
                    exception.localizedMessage ?: exception.message ?: ("Unexpected error occurred")
                )
            )
        } catch (exception: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}