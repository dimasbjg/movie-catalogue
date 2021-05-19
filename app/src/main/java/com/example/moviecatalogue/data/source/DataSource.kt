package com.example.moviecatalogue.data.source

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.example.moviecatalogue.data.source.local.entity.MovieEntity

interface DataSource {

    suspend fun getListMovie(query: String): List<MovieEntity>

    suspend fun getListTvShow(query: String): List<MovieEntity>

    suspend fun getMovieDetail(id: Int): MovieEntity

    suspend fun getTvShowDetail(id: Int): MovieEntity

    fun getMoviesFavorites(): LiveData<PagedList<MovieEntity>>

    fun getTvShowFavorites(): LiveData<PagedList<MovieEntity>>

    fun addFavorites(movie: MovieEntity)

    fun removeFavorites(movie: MovieEntity)

    fun checkFavorites(id: Int): LiveData<Boolean>

}