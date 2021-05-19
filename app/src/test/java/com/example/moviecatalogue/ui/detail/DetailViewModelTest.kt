package com.example.moviecatalogue.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.moviecatalogue.data.source.Repository
import com.example.moviecatalogue.data.source.local.entity.MovieEntity
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
    private lateinit var observerMovie: Observer<MovieEntity>

    @Mock
    private lateinit var observerTvShow: Observer<MovieEntity>

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

        viewModel.movie.observeForever(observerMovie)
        verify(observerMovie).onChanged(dataMovie)
    }

    @Test
    fun getTvShowDetail() = runBlocking {
        `when`(repository.getTvShowDetail(71906)).thenReturn(dataTvShow)
        viewModel.setTvShowDetail(dataTvShow.id)
        val MovieEntity = viewModel.tvShow
        assertNotNull(MovieEntity)
        assertEquals(dataTvShow.title, MovieEntity.value?.title)
        assertEquals(dataTvShow.imgPoster, MovieEntity.value?.imgPoster)
        assertEquals(dataTvShow.releaseDate, MovieEntity.value?.releaseDate)
        assertEquals(dataTvShow.rating, MovieEntity.value?.rating)
        assertEquals(dataTvShow.description, MovieEntity.value?.description)
        assertEquals(dataTvShow.status, MovieEntity.value?.status)

        viewModel.tvShow.observeForever(observerTvShow)
        verify(observerTvShow).onChanged(dataTvShow)
    }

    @Test
    fun addFavorites() = runBlocking {
        val movieDetailTest = DataDummy.getDummyListMovie()[0]
        viewModel.insertFavorites(movieDetailTest)
        verify(repository).addFavorites(movieDetailTest)
    }

    @Test
    fun removeFavorites() = runBlocking {
        val movieDetailTest = DataDummy.getDummyListMovie()[0]
        viewModel.deleteFavorites(movieDetailTest)
        verify(repository).removeFavorites(movieDetailTest)
    }
}