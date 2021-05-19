package com.example.moviecatalogue.ui.movie_favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.moviecatalogue.data.source.Repository
import com.example.moviecatalogue.data.source.local.entity.MovieEntity

class MovieFavoritesViewModel(private val repository: Repository) : ViewModel() {

    fun getMoviesFavorites(): LiveData<PagedList<MovieEntity>> {
        return repository.getMoviesFavorites()
    }

}