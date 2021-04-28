package com.example.moviecatalogue.ui.detail

import com.example.moviecatalogue.data.DataMovie
import com.example.moviecatalogue.data.DataTvShow
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class DetailViewModelTest {

    private lateinit var viewModel: DetailViewModel
    private val dataMovie = DataMovie.listData[0]
    private val dataTvShow = DataTvShow.listData[0]

    @Before
    fun setUp() {
        viewModel = DetailViewModel()
        viewModel.setMovieDetail(dataMovie.title)
        viewModel.setTvShowDetail(dataMovie.title)
    }

    @Test
    fun getMovieDetail() {
        viewModel.setMovieDetail(dataMovie.title)
        val movieEntity = viewModel.getMovieDetail()
        assertNotNull(movieEntity)
        assertEquals(dataMovie.title, movieEntity?.title)
        assertEquals(dataMovie.imgPoster, movieEntity?.imgPoster)
        assertEquals(dataMovie.director, movieEntity?.director)
        assertEquals(dataMovie.starring, movieEntity?.starring)
        assertEquals(dataMovie.description, movieEntity?.description)
        assertEquals(dataMovie.writter, movieEntity?.writter)
        assertEquals(dataMovie.rating, movieEntity?.rating)
    }

    @Test
    fun getTvShowDetail() {
        viewModel.setTvShowDetail(dataTvShow.title)
        val tvShowEntity = viewModel.getTvShowDetail()
        assertNotNull(tvShowEntity)
        assertEquals(dataTvShow.title, tvShowEntity?.title)
        assertEquals(dataTvShow.description, tvShowEntity?.description)
        assertEquals(dataTvShow.imgPoster, tvShowEntity?.imgPoster)
        assertEquals(dataTvShow.rating, tvShowEntity?.rating)
        assertEquals(dataTvShow.starring, tvShowEntity?.starring)
    }
}