package com.example.moviecatalogue.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.moviecatalogue.data.source.local.LocalDataSource
import com.example.moviecatalogue.data.source.local.entity.MovieEntity
import com.example.moviecatalogue.data.source.remote.RemoteDataSource
import com.example.moviecatalogue.utils.AppExecutors
import com.example.moviecatalogue.utils.DataDummy
import com.example.moviecatalogue.utils.LiveDataTestUnit
import com.example.moviecatalogue.utils.PagedDataTest
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class RepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val queryMovie = "harry potter and the deathly"
    private val queryTvShow = "animal fight night"
    private val movieId = 12444
    private val tvShowId = 71906

    private val remote = mock(RemoteDataSource::class.java)
    private val local = mock(LocalDataSource::class.java)
    private val appExecutors = mock(AppExecutors::class.java)
    private val repository = FakeRepository(remote, local, appExecutors)

    private val movieResponse = DataDummy.getDummyMovieResponse()
    private val movie = DataDummy.getDummyDetailMovieResponse()
    private val tvShowResponse = DataDummy.getDummyTvShowResponse()
    private val tvShow = DataDummy.getDummyDetailTvShowResponse()

    @Test
    fun getListMovie() = runBlocking {
        `when`(remote.getListMovie(queryMovie)).thenReturn(movieResponse)
        val movieListTest = repository.getListMovie(queryMovie)
        verify(remote).getListMovie(queryMovie)
        assertNotNull(movieListTest)
        assertEquals(movieResponse.results.size, movieListTest.size)
    }

    @Test
    fun getListTvShow() = runBlocking {
        `when`(remote.getListTvShow(queryTvShow)).thenReturn(tvShowResponse)
        val tvShowListTest = repository.getListTvShow(queryTvShow)
        verify(remote).getListTvShow(queryTvShow)
        assertNotNull(tvShowListTest)
        assertEquals(tvShowResponse.results.size, tvShowListTest.size)
    }

    @Test
    fun getMovieDetail() = runBlocking {
        `when`(remote.getMovieDetail(eq(movieId))).thenReturn(movie)
        val movieDetailTest = repository.getMovieDetail(movieId)
        verify(remote).getMovieDetail(eq(movieId))
        assertNotNull(movieDetailTest)
        assertEquals(movie.id, movieDetailTest.id)
        assertEquals(movie.title, movieDetailTest.title)
        assertEquals(movie.overview, movieDetailTest.description)
        assertEquals(movie.releaseDate, movieDetailTest.releaseDate)
        assertEquals(movie.voteAverage, movieDetailTest.rating)
        assertEquals(movie.posterPath, movieDetailTest.imgPoster)
        assertEquals(movie.status, movieDetailTest.status)
    }

    @Test
    fun getTvShowDetail() = runBlocking {
        `when`(remote.getTvShowDetail(eq(tvShowId))).thenReturn(tvShow)
        val tvShowDetailTest = repository.getTvShowDetail(tvShowId)
        verify(remote).getTvShowDetail(eq(tvShowId))
        assertNotNull(tvShowDetailTest)
        assertEquals(tvShow.id, tvShowDetailTest.id)
        assertEquals(tvShow.name, tvShowDetailTest.title)
        assertEquals(tvShow.overview, tvShowDetailTest.description)
        assertEquals(tvShow.firstAirDate, tvShowDetailTest.releaseDate)
        assertEquals(tvShow.voteAverage, tvShowDetailTest.rating)
        assertEquals(tvShow.posterPath, tvShowDetailTest.imgPoster)
        assertEquals(tvShow.status, tvShowDetailTest.status)
    }

    @Test
    fun getMoviesFavorites() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(local.getMoviesFavorites()).thenReturn(dataSourceFactory)
        repository.getMoviesFavorites()

        val moviesFavorite = PagedDataTest.mockPagedList(DataDummy.getDummyListMovie())
        verify(local).getMoviesFavorites()
        assertNotNull(moviesFavorite)
    }

    @Test
    fun getTvShowFavorites() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(local.getTvShowFavorites()).thenReturn(dataSourceFactory)
        repository.getTvShowFavorites()

        val moviesFavorite = PagedDataTest.mockPagedList(DataDummy.getDummyListTvShow())
        verify(local).getTvShowFavorites()
        assertNotNull(moviesFavorite)
    }

    @Test
    fun checkFavorites() {
        val isFavorite = MutableLiveData<Boolean>()
        isFavorite.value = false
        `when`(local.checkFavorites(movieId)).thenReturn(isFavorite)
        val result = LiveDataTestUnit.getValue(repository.checkFavorites(movieId))
        verify(local).checkFavorites(movieId)
        assertEquals(false, result)
    }
}