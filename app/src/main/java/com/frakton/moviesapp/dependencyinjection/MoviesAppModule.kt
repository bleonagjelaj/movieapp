package com.frakton.moviesapp.dependencyinjection

import android.content.Context
import com.frakton.moviesapp.data.retrofit.MoviesApiService
import com.frakton.moviesapp.data.retrofit.MoviesApiSource
import com.frakton.moviesapp.data.retrofit.RetrofitHelper
import com.frakton.moviesapp.db.DatabaseBuilder
import com.frakton.moviesapp.db.MovieAppDatabase
import com.frakton.moviesapp.domain.interactors.GetMovieDetailsInteractor
import com.frakton.moviesapp.domain.interactors.GetMovieTrailerVideosInteractor
import com.frakton.moviesapp.domain.interactors.GetMoviesInteractor
import com.frakton.moviesapp.domain.interactors.SearchMovieInteractor
import com.frakton.moviesapp.domain.mappers.*
import com.frakton.moviesapp.domain.pagingsources.MoviePagingSource
import com.frakton.moviesapp.domain.pagingsources.SearchMoviePagingSource
import com.frakton.moviesapp.domain.repositories.FiltersRepository
import com.frakton.moviesapp.domain.repositories.MovieDetailsRepository
import com.frakton.moviesapp.domain.repositories.MoviesRepository
import com.frakton.moviesapp.domain.usecases.*
import com.frakton.moviesapp.ui.viewmodels.MoviesViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
        return MoviesApiSource(moviesApiService = moviesApiService)
    }

    @Provides
    @Singleton
    fun provideGetMoviesInteractor(moviesApiSource: MoviesApiSource): GetMoviesInteractor {
        return GetMoviesInteractor(moviesApiSource = moviesApiSource)
    }

    @Provides
    @Singleton
    fun provideSearchMoviesInteractor(moviesApiSource: MoviesApiSource): SearchMovieInteractor {
        return SearchMovieInteractor(moviesApiSource = moviesApiSource)
    }

    @Provides
    @Singleton
    fun provideGetMovieDetailsInteractor(
        moviesApiSource: MoviesApiSource
    ): GetMovieDetailsInteractor {
        return GetMovieDetailsInteractor(moviesApiSource = moviesApiSource)
    }

    @Provides
    @Singleton
    fun provideGetMovieTrailerVideosInteractor(
        moviesApiSource: MoviesApiSource
    ): GetMovieTrailerVideosInteractor {
        return GetMovieTrailerVideosInteractor(moviesApiSource = moviesApiSource)
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
    fun provideFiltersDBModelMapper(): FiltersDBModelMapper = FiltersDBModelMapper()

    @Provides
    @Singleton
    fun provideMovieFiltersModelMapper(): MovieFiltersModelMapper = MovieFiltersModelMapper()

    @Provides
    @Singleton
    fun provideMovieRepository(
        moviesPagingSource: MoviePagingSource,
        searchMoviePagingSourceFactory: SearchMoviePagingSource.Factory
    ): MoviesRepository {
        return MoviesRepository(
            moviesPagingSource = moviesPagingSource,
            searchMoviePagingSourceFactory = searchMoviePagingSourceFactory
        )
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
            getMovieDetailsInteractor = getMovieDetailsInteractor,
            getMovieTrailerVideosInteractor = getMovieTrailerVideosInteractor,
            movieDetailsMapper = movieDetailsMapper,
            movieTrailerVideosMapper = movieTrailerVideosMapper
        )
    }

    @Provides
    @Singleton
    fun provideFiltersRepository(
        moviesAppDB: MovieAppDatabase,
        filtersDBModelMapper: FiltersDBModelMapper,
        movieFiltersModelMapper: MovieFiltersModelMapper
    ): FiltersRepository {
        return FiltersRepository(
            moviesAppDB = moviesAppDB,
            filtersDBModelMapper = filtersDBModelMapper,
            movieFiltersModelMapper = movieFiltersModelMapper
        )
    }

    @Provides
    @Singleton
    fun provideGetMoviesUseCase(moviesRepository: MoviesRepository): GetMoviesUseCase {
        return GetMoviesUseCase(moviesRepository = moviesRepository)
    }

    @Provides
    @Singleton
    fun provideSearchMovieUseCase(moviesRepository: MoviesRepository): SearchMovieUseCase {
        return SearchMovieUseCase(moviesRepository = moviesRepository)
    }

    @Provides
    @Singleton
    fun provideGetMovieDetailsUseCase(moviesDetailsRepository: MovieDetailsRepository):
            GetMovieDetailsUseCase {
        return GetMovieDetailsUseCase(movieDetailsRepository = moviesDetailsRepository)
    }

    @Provides
    @Singleton
    fun provideGetFiltersUseCase(filtersRepository: FiltersRepository): GetFiltersUseCase {
        return GetFiltersUseCase(filtersRepository = filtersRepository)
    }

    @Provides
    @Singleton
    fun provideUpdateFiltersUseCase(filtersRepository: FiltersRepository): UpdateFiltersUseCase {
        return UpdateFiltersUseCase(filtersRepository = filtersRepository)
    }

    @Provides
    @Singleton
    fun provideGetMovieTrailerVideosUseCase(moviesDetailsRepository: MovieDetailsRepository):
            GetMovieTrailerVideosUseCase {
        return GetMovieTrailerVideosUseCase(movieDetailsRepository = moviesDetailsRepository)
    }

    @Provides
    @Singleton
    fun provideMoviesViewModel(
        getMoviesUseCase: GetMoviesUseCase,
        searchMovieUseCase: SearchMovieUseCase,
        getFiltersUseCase: GetFiltersUseCase
    ): MoviesViewModel {
        return MoviesViewModel(
            getMoviesUseCase = getMoviesUseCase,
            searchMovieUseCase = searchMovieUseCase,
            getFiltersUseCase = getFiltersUseCase
        )
    }

    @Provides
    @Singleton
    fun provideMoviesPagingSource(
        getMoviesInteractor: GetMoviesInteractor,
        moviesMapper: MoviesMapper
    ): MoviePagingSource {
        return MoviePagingSource(
            getMoviesInteractor = getMoviesInteractor,
            moviesMapper = moviesMapper
        )
    }

    @Provides
    @Singleton
    fun provideSearchMoviePagingSourceFactory(
        searchMovieInteractor: SearchMovieInteractor,
        moviesMapper: MoviesMapper
    ): SearchMoviePagingSource.Factory {
        return SearchMoviePagingSource.Factory(
            searchMovieInteractor = searchMovieInteractor,
            moviesMapper = moviesMapper
        )
    }

    @Provides
    @Singleton
    fun provideMoviesAppDatabaseInstance(@ApplicationContext context: Context): MovieAppDatabase =
        DatabaseBuilder.getInstance(context)
}