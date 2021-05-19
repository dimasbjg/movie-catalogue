package com.example.moviecatalogue.ui.tvshow_favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.moviecatalogue.data.source.Repository
import com.example.moviecatalogue.data.source.local.entity.MovieEntity

class TvShowFavoritesViewModel(private val repository: Repository): ViewModel() {

    fun getTvShowFavorites(): LiveData<PagedList<MovieEntity>> {
        return repository.getTvShowFavorites()
    }

}