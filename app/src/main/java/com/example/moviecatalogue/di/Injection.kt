package com.example.moviecatalogue.di

import android.content.Context
import com.example.moviecatalogue.data.source.Repository
import com.example.moviecatalogue.data.source.remote.RemoteDataSource
import com.example.moviecatalogue.service.ApiConfig
import com.example.moviecatalogue.service.ApiServiceInterface

object Injection {

    fun provideRepository(context: Context): Repository {

        val retrofit = ApiConfig.getInstance().create(ApiServiceInterface::class.java)
        val remoteRepository =RemoteDataSource.getInstance(retrofit)
        return Repository.getInstance(remoteRepository)

    }

}