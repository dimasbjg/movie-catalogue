package com.example.moviecatalogue.ui.movie

import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.data.DataMovie
import com.example.moviecatalogue.data.MovieEntity

class MovieViewModel : ViewModel() {

    fun getMovie(): List<MovieEntity> = DataMovie.listData

}