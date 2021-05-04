package com.example.moviecatalogue.data.source

import com.example.moviecatalogue.data.source.local.MovieEntity
import com.example.moviecatalogue.data.source.local.TvShowEntity

interface DataSource {

    suspend fun getListMovie(query: String): List<MovieEntity>

    suspend fun getListTvShow(query: String): List<TvShowEntity>

    suspend fun getMovieDetail(id: Int): MovieEntity

    suspend fun getTvShowDetail(id: Int): TvShowEntity

}