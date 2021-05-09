package com.example.moviecatalogue.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.sqlite.db.SimpleSQLiteQuery
import com.example.moviecatalogue.data.source.local.entity.MovieEntity
import com.example.moviecatalogue.db.MovieDao

class LocalDataSource private constructor(private val movieDao: MovieDao) {

    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(movieDao: MovieDao): LocalDataSource {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: LocalDataSource(movieDao)
            }
        }
    }

    fun getMoviesFavorites(): DataSource.Factory<Int, MovieEntity> =
        movieDao.getAllMoviesFavorites()

    fun getTvShowFavorites(): DataSource.Factory<Int, MovieEntity> =
        movieDao.getAllTvShowFavorites()

    fun insertFavorites(movie: MovieEntity) {
        movieDao.insert(movie)
    }

    fun deleteFavorites(movie: MovieEntity) {
        movieDao.delete(movie)
    }

    fun checkFavorites(id: Int): LiveData<Boolean> {
        val query = SimpleSQLiteQuery("SELECT EXISTS(SELECT * FROM movies where id = $id)")
        return movieDao.checkFavorites(query)
    }

}