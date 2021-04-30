package com.example.moviecatalogue.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.moviecatalogue.data.source.Repository
import com.example.moviecatalogue.data.source.local.MovieEntity
import com.example.moviecatalogue.utils.DataDummy
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {

    private lateinit var viewModel: DetailViewModel
    private val dataMovie = DataDummy.getDummyListMovie()[0]
    private val dataTvShow = DataDummy.getDummyListTvShow()[0]
    private val dispatcher = TestCoroutineDispatcher()


    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var observer: Observer<MovieEntity>

    @Mock
    private lateinit var repository: Repository

    @Before
    fun setUp() {
        viewModel = DetailViewModel(repository)
        Dispatchers.setMain(dispatcher)
    }

    @Test
    fun getMovieDetail() = runBlocking {
        `when`(repository.getMovieDetail(12444)).thenReturn(dataMovie)
        viewModel.setMovieDetail(dataMovie.id)
        val movieEntity = viewModel.movie
        assertNotNull(movieEntity)
        assertEquals(dataMovie.title, movieEntity.value?.title)
        assertEquals(dataMovie.imgPoster, movieEntity.value?.imgPoster)
        assertEquals(dataMovie.releaseDate, movieEntity.value?.releaseDate)
        assertEquals(dataMovie.rating, movieEntity.value?.rating)
        assertEquals(dataMovie.description, movieEntity.value?.description)
        assertEquals(dataMovie.status, movieEntity.value?.status)

        viewModel.movie.observeForever(observer)
        verify(observer).onChanged(dataMovie)
    }

    @Test
    fun getTvShowDetail() = runBlocking {
        `when`(repository.getTvShowDetail(71906)).thenReturn(dataTvShow)
        viewModel.setTvShowDetail(dataTvShow.id)
        val tvShowEntity = viewModel.tvShow
        assertNotNull(tvShowEntity)
        assertEquals(dataTvShow.title, tvShowEntity.value?.title)
        assertEquals(dataTvShow.imgPoster, tvShowEntity.value?.imgPoster)
        assertEquals(dataTvShow.releaseDate, tvShowEntity.value?.releaseDate)
        assertEquals(dataTvShow.rating, tvShowEntity.value?.rating)
        assertEquals(dataTvShow.description, tvShowEntity.value?.description)
        assertEquals(dataTvShow.status, tvShowEntity.value?.status)
    }
}