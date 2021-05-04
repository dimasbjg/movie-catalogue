package com.example.moviecatalogue.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviecatalogue.data.source.Repository
import com.example.moviecatalogue.data.source.remote.response.DetailMovieResponse
import com.example.moviecatalogue.di.Injection
import com.example.moviecatalogue.ui.detail.DetailViewModel
import com.example.moviecatalogue.ui.movie.MovieViewModel
import com.example.moviecatalogue.ui.tvshow.TvShowViewModel

class ViewModelFactory private constructor(private val mRepository: Repository) :
    ViewModelProvider.NewInstanceFactory() {

        companion object {
            @Volatile
            private var instance: ViewModelFactory? = null

            fun getInstance(context: Context): ViewModelFactory =
                instance ?: synchronized(this) {
                    instance?: ViewModelFactory(Injection.provideRepository(context)).apply { instance = this }
                }
        }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MovieViewModel::class.java) -> {
                MovieViewModel(mRepository) as T
            }
            modelClass.isAssignableFrom(TvShowViewModel::class.java) -> {
                TvShowViewModel(mRepository) as T
            }
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
                DetailViewModel(mRepository) as T
            }
            else ->throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
    }

}