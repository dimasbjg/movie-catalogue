package com.example.moviecatalogue.ui.detail

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.moviecatalogue.R
import com.example.moviecatalogue.data.source.local.entity.MovieEntity
import com.example.moviecatalogue.databinding.ActivityDetailBinding
import com.example.moviecatalogue.viewmodel.ViewModelFactory
import kotlin.properties.Delegates

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DETAIL = "extra_detail"
        const val EXTRA_FROM = "extra_from"
    }

    private var type by Delegates.notNull<Int>()
    private lateinit var detail: MovieEntity

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

        var isFavorites = false

        val extras = intent.extras
        if (extras != null) {
            val id = extras.getInt(EXTRA_DETAIL)
            if (extras.getString(EXTRA_FROM) == "Movie") {
                viewModel.setMovieDetail(id)
                type = 1

                viewModel.movie.observe(this, { movie ->
                    detail = movie
                    populateMovieDetail(movie)
                })
            } else {
                viewModel.setTvShowDetail(id)
                type = 2
                viewModel.tvShow.observe(this, { tvShow ->
                    detail = tvShow
                    populateTvShowDetail(tvShow)
                })
            }
        }

        extras?.let {
            viewModel.checkFavorites(it.getInt(EXTRA_DETAIL)).observe(this, {
                isFavorites = it
                val icFavorites =
                    if (isFavorites) R.drawable.ic_baseline_favorite_24 else R.drawable.ic_baseline_favorite_border_24
                binding.fab.setImageResource(icFavorites)
            })
        }

        binding.fab.setOnClickListener {
            detail.let {
                detail = getDetail(it, type)
                if (isFavorites) {
                    viewModel.deleteFavorites(detail)
                    Toast.makeText(this, "Un-Favorites", Toast.LENGTH_SHORT).show()
                } else {
                    viewModel.insertFavorites(detail)
                    Toast.makeText(this, "Favorites", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }

    private fun populateTvShowDetail(tvShow: MovieEntity) {
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

    private fun getDetail(movie: MovieEntity, type: Int): MovieEntity {
        return MovieEntity(
            movie.title,
            movie.description,
            movie.rating,
            movie.imgPoster,
            movie.releaseDate,
            movie.status,
            movie.id,
            type
        )
    }
}