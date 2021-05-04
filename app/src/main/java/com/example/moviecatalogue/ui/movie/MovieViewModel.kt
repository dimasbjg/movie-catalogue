package com.example.moviecatalogue.ui.movie

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviecatalogue.data.source.Repository
import com.example.moviecatalogue.data.source.local.MovieEntity
import kotlinx.coroutines.launch

class MovieViewModel(private val repository: Repository) : ViewModel() {

    private var _listMovie = MutableLiveData<List<MovieEntity>>()
    val listMovie: LiveData<List<MovieEntity>> = _listMovie

    fun getMovieList(): LiveData<List<MovieEntity>> = listMovie


    fun setMovie(search: String) {
        viewModelScope.launch {
            val result = repository.getListMovie(search)
            _listMovie.postValue(result)
        }
        listMovie?.value?.get(0)?.title?.let { Log.d("iiii", it) }
    }

}