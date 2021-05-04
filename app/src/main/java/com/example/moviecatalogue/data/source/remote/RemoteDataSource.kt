package com.example.moviecatalogue.data.source.remote

import com.example.moviecatalogue.BuildConfig
import com.example.moviecatalogue.data.source.local.MovieEntity
import com.example.moviecatalogue.data.source.local.TvShowEntity
import com.example.moviecatalogue.data.source.remote.response.*
import com.example.moviecatalogue.service.ApiServiceInterface
import com.example.moviecatalogue.utils.EspressoIdlingResource

class RemoteDataSource private constructor(private val apiServiceInterface: ApiServiceInterface) {

    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null
        private const val apiKey = BuildConfig.API_KEY


        fun getInstance(apiServiceInterface: ApiServiceInterface): RemoteDataSource =
            instance ?: synchronized(this) {
                RemoteDataSource(apiServiceInterface).apply { instance = this }
            }

    }

    suspend fun getListMovie(query: String): MovieResponse {
        EspressoIdlingResource.increment()
        val result = apiServiceInterface.getListMovie(apiKey, query)
        EspressoIdlingResource.decrement()
        return result
    }

    suspend fun getListTvShow(query: String): TvShowResponse {
        EspressoIdlingResource.increment()
        val result = apiServiceInterface.getListTvShow(apiKey, query)
        EspressoIdlingResource.decrement()
        return result
    }

    suspend fun getMovieDetail(id: Int): DetailMovieResponse {
        EspressoIdlingResource.increment()
        val result = apiServiceInterface.getMovieDetail(id, apiKey)
        EspressoIdlingResource.decrement()
        return result
    }

    suspend fun getTvShowDetail(id: Int): DetailTvShowResponse {
        EspressoIdlingResource.increment()
        val result = apiServiceInterface.getTvShowDetail(id, apiKey)
        EspressoIdlingResource.decrement()
        return result
    }

    interface MovieListCallback {
        fun onMovieListLoaded(movies: List<MovieItems>?)
    }

    interface MovieDetailCallback {
        fun onMovieDetailLoaded(movieDetail: MovieEntity?)
    }

    interface TvShowListCallback {
        fun onTvShowListLoaded(tvShows: List<MovieItems>?)
    }

    interface TvShowDetailCallback {
        fun onTvShowDetailLoaded(tvShowDetail: TvShowEntity?)
    }

}