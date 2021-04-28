package com.example.moviecatalogue.ui.detail

import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.data.DataMovie
import com.example.moviecatalogue.data.DataTvShow
import com.example.moviecatalogue.data.MovieEntity
import com.example.moviecatalogue.data.TvShowEntity

class DetailViewModel : ViewModel() {
    private lateinit var title: String

    fun setMovieDetail(title: String) {
        this.title = title
    }

    fun setTvShowDetail(title: String) {
        this.title = title
    }

    fun getMovieDetail(): MovieEntity? {
        var movie: MovieEntity? = null
        for (movieEntity in DataMovie.listData) {
            if (movieEntity.title == this.title) {
                movie = movieEntity
            }
        }
        return movie
    }

    fun getTvShowDetail(): TvShowEntity? {
        var tvShow: TvShowEntity? = null
        for (tvShowEntity in DataTvShow.listData) {
            if (tvShowEntity.title == this.title) {
                tvShow = tvShowEntity
            }
        }
        return tvShow
    }
}