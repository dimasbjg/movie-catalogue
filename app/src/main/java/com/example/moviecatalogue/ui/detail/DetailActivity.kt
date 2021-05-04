package com.example.moviecatalogue.ui.detail

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.moviecatalogue.data.source.local.MovieEntity
import com.example.moviecatalogue.data.source.local.TvShowEntity
import com.example.moviecatalogue.databinding.ActivityDetailBinding
import com.example.moviecatalogue.viewmodel.ViewModelFactory

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DETAIL = "extra_detail"
        const val EXTRA_FROM = "extra_from"
    }

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(
            this,
            factory
        )[DetailViewModel::class.java]


        val extras = intent.extras
        if (extras != null) {
            val id = extras.getInt(EXTRA_DETAIL)
            if (extras.getString(EXTRA_FROM) == "Movie") {
                viewModel.setMovieDetail(id)

                viewModel.movie.observe(this, { movie ->
                    populateMovieDetail(movie)
                })
            } else {
                viewModel.setTvShowDetail(id)
                Log.d("iniini", "$id")
                viewModel.tvShow.observe(this, { tvShow ->
                    populateTvShowDetail(tvShow)
                })
            }
        }


    }

    private fun populateTvShowDetail(tvShow: TvShowEntity) {
        with(binding) {
            detailTitle.text = tvShow.title
            description.text = tvShow.description
            ("Status: " + tvShow.status).also { status.text = it }

            Glide.with(this@DetailActivity)
                .load("https://image.tmdb.org/t/p/w500" + tvShow.imgPoster)
                .into(imgPoster)
            rating.text = tvShow.rating.toString()
            ("Release Date: " + tvShow.releaseDate).also { releaseDate.text = it }
        }
    }

    private fun populateMovieDetail(movieDetail: MovieEntity) {
        with(binding) {
            detailTitle.text = movieDetail.title
            description.text = movieDetail.description
            rating.text = movieDetail.rating.toString()
            ("Release Data: " + movieDetail.releaseDate).also { releaseDate.text = it }
            ("Status: " + movieDetail.status).also { status.text = it }
            Glide.with(this@DetailActivity)
                .load("https://image.tmdb.org/t/p/w500" + movieDetail.imgPoster)
                .into(imgPoster)
        }
    }
}