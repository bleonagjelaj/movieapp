package com.frakton.moviesapp.dependencyinjection

import com.frakton.moviesapp.data.retrofit.MoviesApiService
import com.frakton.moviesapp.data.retrofit.MoviesApiSource
import com.frakton.moviesapp.data.retrofit.RetrofitHelper
import com.frakton.moviesapp.domain.interactors.GetMoviesInteractor
import com.frakton.moviesapp.domain.interactors.SearchMovieInteractor
import com.frakton.moviesapp.domain.mappers.MoviesMapper
import com.frakton.moviesapp.domain.pagingsources.MoviePagingSource
import com.frakton.moviesapp.domain.repositories.MoviesRepository
import com.frakton.moviesapp.domain.usecases.GetMoviesUseCase
import com.frakton.moviesapp.domain.usecases.SearchMovieUseCase
import com.frakton.moviesapp.ui.viewmodels.MoviesViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MoviesAppModule {

    @Provides
    @Singleton
    fun provideMoviesApiService(): MoviesApiService {
        return RetrofitHelper.getMoviesApiService()
    }

    @Provides
    @Singleton
    fun provideMovieApiSource(moviesApiService: MoviesApiService)
            : MoviesApiSource {
        return MoviesApiSource(moviesApiService)
    }

    @Provides
    @Singleton
    fun provideGetMoviesInteractor(moviesApiSource: MoviesApiSource): GetMoviesInteractor {
        return GetMoviesInteractor(moviesApiSource)
    }

    @Provides
    @Singleton
    fun provideSearchMoviesInteractor(moviesApiSource: MoviesApiSource): SearchMovieInteractor {
        return SearchMovieInteractor(moviesApiSource)
    }

    @Provides
    @Singleton
    fun provideMoviesMapper(): MoviesMapper = MoviesMapper()

    @Provides
    @Singleton
    fun provideMovieRepository(
        moviesPagingSource: MoviePagingSource
    ): MoviesRepository {
        return MoviesRepository(moviesPagingSource)
    }

    @Provides
    @Singleton
    fun provideGetMoviesUseCase(moviesRepository: MoviesRepository): GetMoviesUseCase {
        return GetMoviesUseCase(moviesRepository)
    }

    @Provides
    @Singleton
    fun provideSearchMovieUseCase(moviesRepository: MoviesRepository): SearchMovieUseCase {
        return SearchMovieUseCase(moviesRepository)
    }

    @Provides
    @Singleton
    fun provideMoviesViewModel(
        getMoviesUseCase: GetMoviesUseCase, searchMovieUseCase: SearchMovieUseCase
    ): MoviesViewModel {
        return MoviesViewModel(getMoviesUseCase, searchMovieUseCase)
    }

    @Provides
    @Singleton
    fun provideMoviesPagingSource(
        getMoviesInteractor: GetMoviesInteractor,
        moviesMapper: MoviesMapper
    ): MoviePagingSource {
        return MoviePagingSource(getMoviesInteractor, moviesMapper)
    }
}