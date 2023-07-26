package com.bleonahasanaj.moviesapp.dependencyinjection

import android.content.Context
import com.bleonahasanaj.moviesapp.data.retrofit.MoviesApiService
import com.bleonahasanaj.moviesapp.data.retrofit.MoviesApiSource
import com.bleonahasanaj.moviesapp.data.retrofit.RetrofitHelper
import com.bleonahasanaj.moviesapp.db.DatabaseBuilder
import com.bleonahasanaj.moviesapp.db.MovieAppDatabase
import com.bleonahasanaj.moviesapp.db.dao.FiltersDao
import com.bleonahasanaj.moviesapp.db.dao.GenresDao
import com.bleonahasanaj.moviesapp.domain.interactors.*
import com.bleonahasanaj.moviesapp.domain.mappers.*
import com.bleonahasanaj.moviesapp.domain.models.GenresModel
import com.bleonahasanaj.moviesapp.domain.pagingsources.MoviePagingSource
import com.bleonahasanaj.moviesapp.domain.pagingsources.SearchMoviePagingSource
import com.bleonahasanaj.moviesapp.domain.repositories.FiltersRepository
import com.bleonahasanaj.moviesapp.domain.repositories.GenresRepository
import com.bleonahasanaj.moviesapp.domain.repositories.MovieDetailsRepository
import com.bleonahasanaj.moviesapp.domain.repositories.MoviesRepository
import com.bleonahasanaj.moviesapp.domain.usecases.*
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.lang.reflect.ParameterizedType
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
    fun provideGetGenresInteractor(moviesApiSource: MoviesApiSource): GetGenresInteractor {
        return GetGenresInteractor(moviesApiSource)
    }

    @Provides
    @Singleton
    fun provideMoviesMapper(getGenresFromDBUseCase: GetGenresFromDBUseCase): MoviesMapper =
        MoviesMapper(getGenresFromDBUseCase)

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
    fun provideGenresMapper(): GenresMapper = GenresMapper()

    @Provides
    @Singleton
    fun provideGenresDBModelMapper(
        genresModelJsonAdapter: JsonAdapter<List<GenresModel>>
    ): GenresDBModelMapper = GenresDBModelMapper(genresModelJsonAdapter)

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
        filtersDao: FiltersDao?,
        filtersDBModelMapper: FiltersDBModelMapper,
        movieFiltersModelMapper: MovieFiltersModelMapper
    ): FiltersRepository {
        return FiltersRepository(
            filtersDao = filtersDao,
            filtersDBModelMapper = filtersDBModelMapper,
            movieFiltersModelMapper = movieFiltersModelMapper
        )
    }

    @Provides
    @Singleton
    fun provideGenresRepository(
        getGenresInteractor: GetGenresInteractor,
        genresMapper: GenresMapper,
        genresDBModelMapper: GenresDBModelMapper,
        genresDao: GenresDao?
    ): GenresRepository {
        return GenresRepository(getGenresInteractor, genresMapper, genresDBModelMapper, genresDao)
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
    fun provideGetFiltersInitialStateUseCase(filtersRepository: FiltersRepository):
            GetFiltersInitialStateUseCase {
        return GetFiltersInitialStateUseCase(filtersRepository = filtersRepository)
    }

    @Provides
    @Singleton
    fun provideGetGenresUseCase(genresRepository: GenresRepository): GetGenresUseCase {
        return GetGenresUseCase(genresRepository)
    }

    @Provides
    @Singleton
    fun provideGetGenresFromDBUseCase(genresRepository: GenresRepository): GetGenresFromDBUseCase {
        return GetGenresFromDBUseCase(genresRepository)
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
    fun provideMoviesAppDatabaseInstance(@ApplicationContext context: Context): MovieAppDatabase? =
        DatabaseBuilder.getInstance(context)

    @Provides
    @Singleton
    fun provideFiltersDao(moviesAppDB: MovieAppDatabase?): FiltersDao? = moviesAppDB?.FiltersDao()

    @Provides
    @Singleton
    fun provideGenresDao(moviesAppDB: MovieAppDatabase?): GenresDao? = moviesAppDB?.GenresDao()

    @Provides
    @Singleton
    fun provideMoshi(): Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    @Provides
    @Singleton
    fun provideGenresModelListParameterizedType(): ParameterizedType =
        Types.newParameterizedType(List::class.java, GenresModel::class.java)

    @Singleton
    @Provides
    fun provideGenresModelListJsonAdapter(
        moshi: Moshi,
        genresModelListParameterizedType: ParameterizedType
    ): JsonAdapter<List<GenresModel>> = moshi.adapter(genresModelListParameterizedType)
}