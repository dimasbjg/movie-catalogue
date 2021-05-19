package com.example.moviecatalogue.data.source

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.moviecatalogue.data.source.local.LocalDataSource
import com.example.moviecatalogue.data.source.local.entity.MovieEntity
import com.example.moviecatalogue.data.source.remote.RemoteDataSource
import com.example.moviecatalogue.utils.AppExecutors

class FakeRepository(
    private val remoteDataSource: RemoteDataSource, private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) :
    DataSource {


    override suspend fun getListMovie(query: String): List<MovieEntity> {
        val result = remoteDataSource.getListMovie(query)
        val movieList = ArrayList<MovieEntity>()
        result.results.forEach { movieItems ->
            val movie = MovieEntity(
                title = movieItems.title,
                imgPoster = movieItems.posterPath,
                id = movieItems.id
            )
            movieList.add(movie)
        }
        return movieList
    }

    override suspend fun getListTvShow(query: String): List<MovieEntity> {
        val result = remoteDataSource.getListTvShow(query)
        val tvShowList = ArrayList<MovieEntity>()
        result.results.forEach { tvShowItems ->
            val tvShow = MovieEntity(
                title = tvShowItems.name,
                imgPoster = tvShowItems.posterPath,
                id = tvShowItems.id
            )
            tvShowList.add(tvShow)
        }
        return tvShowList
    }

    override suspend fun getMovieDetail(id: Int): MovieEntity {
        val result = remoteDataSource.getMovieDetail(id)
        return MovieEntity(
            result.title,
            result.overview,
            result.voteAverage,
            result.posterPath,
            result.releaseDate,
            result.status,
            result.id
        )
    }

    override suspend fun getTvShowDetail(id: Int): MovieEntity {
        val result = remoteDataSource.getTvShowDetail(id)
        return MovieEntity(
            result.name,
            result.overview,
            result.voteAverage,
            result.posterPath,
            result.firstAirDate,
            result.status,
            result.id
        )
    }

    override fun getMoviesFavorites(): LiveData<PagedList<MovieEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getMoviesFavorites(), config).build()
    }

    override fun getTvShowFavorites(): LiveData<PagedList<MovieEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getTvShowFavorites(), config).build()
    }

    override fun addFavorites(movie: MovieEntity) {
        appExecutors.diskIO().execute {
            localDataSource.insertFavorites(movie)
        }
    }

    override fun removeFavorites(movie: MovieEntity) {
        appExecutors.diskIO().execute {
            localDataSource.deleteFavorites(movie)
        }
    }

    override fun checkFavorites(id: Int): LiveData<Boolean> {
        return localDataSource.checkFavorites(id)
    }


}