package com.frakton.moviesapp.dependencyinjection

import com.frakton.moviesapp.data.retrofit.MoviesApiService
import com.frakton.moviesapp.data.retrofit.MoviesApiSource
import com.frakton.moviesapp.data.retrofit.RetrofitHelper
import com.frakton.moviesapp.domain.interactors.GetMovieDetailsInteractor
import com.frakton.moviesapp.domain.interactors.GetMovieTrailerVideosInteractor
import com.frakton.moviesapp.domain.interactors.GetMoviesInteractor
import com.frakton.moviesapp.domain.interactors.SearchMovieInteractor
import com.frakton.moviesapp.domain.mappers.MovieDetailsMapper
import com.frakton.moviesapp.domain.mappers.MovieTrailerVideosMapper
import com.frakton.moviesapp.domain.mappers.MoviesMapper
import com.frakton.moviesapp.domain.pagingsources.MoviePagingSource
import com.frakton.moviesapp.domain.pagingsources.SearchMoviePagingSource
import com.frakton.moviesapp.domain.repositories.MovieDetailsRepository
import com.frakton.moviesapp.domain.repositories.MoviesRepository
import com.frakton.moviesapp.domain.usecases.GetMovieDetailsUseCase
import com.frakton.moviesapp.domain.usecases.GetMovieTrailerVideosUseCase
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
    fun provideGetMovieDetailsInteractor(
        moviesApiSource: MoviesApiSource
    ): GetMovieDetailsInteractor {
        return GetMovieDetailsInteractor(moviesApiSource)
    }

    @Provides
    @Singleton
    fun provideGetMovieTrailerVideosInteractor(
        moviesApiSource: MoviesApiSource
    ): GetMovieTrailerVideosInteractor {
        return GetMovieTrailerVideosInteractor(moviesApiSource)
    }

    @Provides
    @Singleton
    fun provideMoviesMapper(): MoviesMapper = MoviesMapper()

    @Provides
    @Singleton
    fun provideMovieDetailsMapper(): MovieDetailsMapper = MovieDetailsMapper()

    @Provides
    @Singleton
    fun provideMovieTrailerVideosMapper(): MovieTrailerVideosMapper = MovieTrailerVideosMapper()

    @Provides
    @Singleton
    fun provideMovieRepository(
        moviesPagingSource: MoviePagingSource,
        searchMoviePagingSourceFactory: SearchMoviePagingSource.Factory
    ): MoviesRepository {
        return MoviesRepository(moviesPagingSource, searchMoviePagingSourceFactory)
    }

    @Provides
    @Singleton
    fun provideMovieDetailsRepository(
        getMovieDetailsInteractor: GetMovieDetailsInteractor,
        getMovieTrailerVideosInteractor: GetMovieTrailerVideosInteractor,
        movieDetailsMapper: MovieDetailsMapper,
        movieTrailerVideosMapper: MovieTrailerVideosMapper
    ): MovieDetailsRepository {
        return MovieDetailsRepository(
            getMovieDetailsInteractor,
            getMovieTrailerVideosInteractor,
            movieDetailsMapper,
            movieTrailerVideosMapper
        )
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
    fun provideGetMovieDetailsUseCase(moviesDetailsRepository: MovieDetailsRepository):
            GetMovieDetailsUseCase {
        return GetMovieDetailsUseCase(moviesDetailsRepository)
    }

    @Provides
    @Singleton
    fun provideGetMovieTrailerVideosUseCase(moviesDetailsRepository: MovieDetailsRepository):
            GetMovieTrailerVideosUseCase {
        return GetMovieTrailerVideosUseCase(moviesDetailsRepository)
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

    @Provides
    @Singleton
    fun provideSearchMoviePagingSourceFactory(
        searchMovieInteractor: SearchMovieInteractor,
        moviesMapper: MoviesMapper
    ): SearchMoviePagingSource.Factory {
        return SearchMoviePagingSource.Factory(searchMovieInteractor, moviesMapper)
    }
}