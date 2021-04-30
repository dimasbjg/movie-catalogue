package com.example.moviecatalogue.service

import com.example.moviecatalogue.BuildConfig
import com.example.moviecatalogue.data.source.remote.response.DetailMovieResponse
import com.example.moviecatalogue.data.source.remote.response.DetailTvShowResponse
import com.example.moviecatalogue.data.source.remote.response.MovieResponse
import com.example.moviecatalogue.data.source.remote.response.TvShowResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServiceInterface {

    @GET("search/movie/")
    suspend fun getListMovie(
        @Query("api_key") api_key: String,
        @Query("query") title: String
    ): MovieResponse

    @GET("search/tv")
    suspend fun getListTvShow(
        @Query("api_key") api_key: String,
        @Query("query") title: String
    ) : TvShowResponse

    @GET("movie/{id}")
    suspend fun getMovieDetail(
        @Path("id") id: Int,
        @Query("api_key") api_key: String
    ): DetailMovieResponse

    @GET("tv/{id}")
    suspend fun getTvShowDetail(
        @Path("id") id: Int,
        @Query("api_key") api_key: String
    ): DetailTvShowResponse
}