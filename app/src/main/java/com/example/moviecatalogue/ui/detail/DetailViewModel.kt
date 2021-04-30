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


//        val client = ApiConfig.getApiService().getMovieDetail(id, apiKey)
//        client.enqueue(object : Callback<DetailMovieResponse> {
//            override fun onResponse(
//                call: Call<DetailMovieResponse>,
//                response: Response<DetailMovieResponse>
//            ) {
//                if (response.isSuccessful) {
//                    _movie.value = response.body()
//                } else {
//                    Log.e(TAG, "onFailure: ${response.message()}")
//
//                }
//            }
//
//            override fun onFailure(call: Call<DetailMovieResponse>, t: Throwable) {
//                Log.e(TAG, "onFailure: ${t.message.toString()}")
//
//            }
//
//        })
    }

    fun setTvShowDetail(id: Int) {
        viewModelScope.launch {
            val detailResult = repository.getTvShowDetail(id)
            _tvShow.postValue(detailResult)
        }

    }
//        val client = ApiConfig.getApiService().getTvShowDetail(id, apiKey)
//        client.enqueue(object : Callback<DetailTvShowResponse> {
//            override fun onResponse(
//                call: Call<DetailTvShowResponse>,
//                response: Response<DetailTvShowResponse>
//            ) {
//                if (response.isSuccessful) {
//                    _tvShow.value = response.body()
//                } else {
//                    Log.e(TAG, "onFailure: ${response.message()}")
//                }
//            }
//
//            override fun onFailure(call: Call<DetailTvShowResponse>, t: Throwable) {
//                Log.e(TAG, "onFailure: ${t.message.toString()}")
//            }
//
//        })
//    }
}