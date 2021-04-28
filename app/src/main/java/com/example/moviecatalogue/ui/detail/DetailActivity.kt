package com.example.moviecatalogue.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.moviecatalogue.data.MovieEntity
import com.example.moviecatalogue.data.TvShowEntity
import com.example.moviecatalogue.databinding.ActivityDetailBinding

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

        val viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[DetailViewModel::class.java]

        val extras = intent.extras
        if (extras != null) {
            val title = extras.getString(EXTRA_DETAIL)
            if (title != null) {
                if (extras.getString(EXTRA_FROM) == "Movie") {
                    viewModel.setMovieDetail(title)
                    viewModel.getMovieDetail()?.let { populateMovieDetail(it) }
                } else {
                    viewModel.setTvShowDetail(title)
                    viewModel.getTvShowDetail()?.let { populateTvShowDetail(it) }
                }
            }
        }


    }

    private fun populateTvShowDetail(tvShow: TvShowEntity) {
        with(binding) {
            detailTitle.text = tvShow.title
            description.text = tvShow.description
            staring.text = tvShow.starring
            writter.text = ""
            staring.text = ""
            director.text = ""
            Glide.with(this@DetailActivity)
                .load(tvShow.imgPoster)
                .into(imgPoster)
            rating.text = tvShow.rating
        }
    }

    private fun populateMovieDetail(movieDetail: MovieEntity) {
        with(binding) {
            detailTitle.text = movieDetail.title
            description.text = movieDetail.description
            ("Director: " + movieDetail.director).also { director.text = it }
            ("Writter: " + movieDetail.writter).also { writter.text = it }
            ("Starring: " + movieDetail.starring).also { staring.text = it }
            rating.text = movieDetail.rating

            Glide.with(this@DetailActivity)
                .load(movieDetail.imgPoster)
                .into(imgPoster)
        }
    }
}