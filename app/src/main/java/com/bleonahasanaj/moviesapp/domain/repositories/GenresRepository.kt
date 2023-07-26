package com.bleonahasanaj.moviesapp.domain.repositories

import com.bleonahasanaj.moviesapp.db.dao.GenresDao
import com.bleonahasanaj.moviesapp.db.tables.Genres
import com.bleonahasanaj.moviesapp.domain.interactors.GetGenresInteractor
import com.bleonahasanaj.moviesapp.domain.mappers.GenresDBModelMapper
import com.bleonahasanaj.moviesapp.domain.mappers.GenresMapper
import com.bleonahasanaj.moviesapp.domain.models.GenreFilterModel
import com.bleonahasanaj.moviesapp.domain.models.GenresModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GenresRepository @Inject constructor(
    private val getGenresInteractor: GetGenresInteractor,
    private val genresMapper: GenresMapper,
    private val genresDBModelMapper: GenresDBModelMapper,
    private val genresDao: GenresDao?
) {
    fun getGenres(): Flow<List<GenresModel>> = flow {
        emit(genresMapper.map(getGenresInteractor.invoke(null)))
    }

    private suspend fun getGenresFromDB(): Flow<Genres?> = flow {
        emit(genresDao?.getGenres())
    }

    suspend fun getGenresListFromDb(): Flow<List<GenreFilterModel>> = flow {
        emit(genresDBModelMapper.mapToList(genresDao?.getGenres()))
    }

    suspend fun updateGenres(newGenresData: List<GenresModel>) {
        getGenresFromDB().collect { oldGenresData ->
            if (oldGenresData != genresDBModelMapper.mapToJson(newGenresData)) {
                genresDao?.deleteAll()
                genresDao?.insertGenres(genresDBModelMapper.mapToJson(newGenresData))
            }
        }
    }
}