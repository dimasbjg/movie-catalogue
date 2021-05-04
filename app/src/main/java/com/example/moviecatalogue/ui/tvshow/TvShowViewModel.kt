package com.example.moviecatalogue.ui.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviecatalogue.BuildConfig
import com.example.moviecatalogue.data.source.Repository
import com.example.moviecatalogue.data.source.local.TvShowEntity
import kotlinx.coroutines.launch


class TvShowViewModel(private val repository: Repository) : ViewModel() {

    private var _listTvShow = MutableLiveData<List<TvShowEntity>>()
    val listTvShow: LiveData<List<TvShowEntity>> = _listTvShow

    companion object {
        private const val TAG = "TvShowViewModel"
        private const val apiKey = BuildConfig.API_KEY
    }

    fun setTvShow(search: String) {
        viewModelScope.launch {
            val result = repository.getListTvShow(search)
            _listTvShow.postValue(result)
        }
    }
}