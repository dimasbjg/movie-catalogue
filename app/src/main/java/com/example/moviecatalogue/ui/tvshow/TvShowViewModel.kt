package com.example.moviecatalogue.ui.tvshow

import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.data.DataTvShow
import com.example.moviecatalogue.data.TvShowEntity

class TvShowViewModel : ViewModel() {

    fun getTvShow(): List<TvShowEntity> = DataTvShow.listData
}