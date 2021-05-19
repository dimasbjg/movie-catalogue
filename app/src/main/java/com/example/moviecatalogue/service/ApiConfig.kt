package com.example.moviecatalogue.service

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiConfig {
    companion object {

        @Volatile
        private var retrofit: Retrofit? = null
        fun getInstance(): Retrofit {
            val client = OkHttpClient.Builder()
                .build()
            return retrofit ?: synchronized(this) {
                retrofit ?: Retrofit.Builder()
                    .baseUrl("https://api.themoviedb.org/3/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build()
            }
        }
    }
}