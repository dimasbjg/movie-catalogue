package com.example.moviecatalogue.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviecatalogue.BuildConfig
import com.example.moviecatalogue.data.source.Repository
import com.example.moviecatalogue.data.source.local.MovieEntity
import com.example.moviecatalogue.data.source.local.TvShowEntity
import kotlinx.coroutines.launch

class DetailViewModel(private val repository: Repository) : ViewModel() {

    private var _movie = MutableLiveData<MovieEntity>()
    val movie: LiveData<MovieEntity> = _movie

    private var _tvShow = MutableLiveData<TvShowEntity>()
    val tvShow: LiveData<TvShowEntity> = _tvShow

    companion object {
        private const val TAG = "DetailViewModel"
        private const val apiKey = BuildConfig.API_KEY
    }


    fun setMovieDetail(id: Int) {
        viewModelScope.launch {
            val result = repository.getMovieDetail(id)
            _movie.postValue(result)
        }
    }

    fun setTvShowDetail(id: Int) {
        viewModelScope.launch {
            val detailResult = repository.getTvShowDetail(id)
            _tvShow.postValue(detailResult)
        }

    }
}