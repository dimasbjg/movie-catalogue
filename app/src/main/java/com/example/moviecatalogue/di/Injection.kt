package com.example.moviecatalogue.di

import android.content.Context
import com.example.moviecatalogue.data.source.Repository
import com.example.moviecatalogue.data.source.local.LocalDataSource
import com.example.moviecatalogue.data.source.remote.RemoteDataSource
import com.example.moviecatalogue.db.MovieRoomDatabase
import com.example.moviecatalogue.service.ApiConfig
import com.example.moviecatalogue.service.ApiServiceInterface
import com.example.moviecatalogue.utils.AppExecutors

object Injection {
    fun provideRepository(context: Context): Repository {
        val retrofit = ApiConfig.getInstance().create(ApiServiceInterface::class.java)
        val db = MovieRoomDatabase.getInstance(context)
        val remoteRepository = RemoteDataSource.getInstance(retrofit)
        val localDataSource = LocalDataSource.getInstance(db.movieDao())
        val appExecutors = AppExecutors()
        return Repository.getInstance(remoteRepository, localDataSource, appExecutors)
    }

}