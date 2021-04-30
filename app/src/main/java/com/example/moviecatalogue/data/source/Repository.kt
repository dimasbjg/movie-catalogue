package com.example.moviecatalogue.data.source

import com.example.moviecatalogue.data.source.local.MovieEntity
import com.example.moviecatalogue.data.source.local.TvShowEntity
import com.example.moviecatalogue.data.source.remote.RemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Repository private constructor(private val remoteDataSource: RemoteDataSource) : DataSource {

    companion object {
        @Volatile
        private var instance: Repository? = null

        fun getInstance(remoteData: RemoteDataSource): Repository =
            instance ?: synchronized(this) {
                Repository(remoteData).apply { instance = this }
            }

    }

    override suspend fun getListMovie(query: String): List<MovieEntity> {
        val result = withContext(Dispatchers.IO) { remoteDataSource.getListMovie(query) }
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

    override suspend fun getListTvShow(query: String): List<TvShowEntity> {
        val result = remoteDataSource.getListTvShow(query)
        val tvShowList = ArrayList<TvShowEntity>()
        result.results.forEach { tvShowItems ->
            val tvShow = TvShowEntity(
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

    override suspend fun getTvShowDetail(id: Int): TvShowEntity {
        val result = remoteDataSource.getTvShowDetail(id)
        return TvShowEntity(
            result.name,
            result.overview,
            result.voteAverage,
            result.posterPath,
            result.firstAirDate,
            result.status,
            result.id
        )
    }


}