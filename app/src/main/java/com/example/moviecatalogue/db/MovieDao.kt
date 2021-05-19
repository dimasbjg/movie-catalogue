package com.example.moviecatalogue.db

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.example.moviecatalogue.data.source.local.entity.MovieEntity
import com.example.moviecatalogue.utils.EntityType
import androidx.sqlite.db.SupportSQLiteQuery

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movie: MovieEntity)

    @Delete
    fun delete(movie: MovieEntity)

    @Update
    fun update(movie: MovieEntity)

    @Query("SELECT * FROM movies WHERE type = ${EntityType.TYPE_MOVIE}")
    fun getAllMoviesFavorites(): DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM movies WHERE type = ${EntityType.TYPE_TV}")
    fun getAllTvShowFavorites(): DataSource.Factory<Int, MovieEntity>

    @RawQuery(observedEntities = [MovieEntity::class])
    fun checkFavorites(query: SupportSQLiteQuery): LiveData<Boolean>

}