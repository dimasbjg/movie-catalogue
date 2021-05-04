package com.example.moviecatalogue.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.moviecatalogue.data.source.remote.RemoteDataSource
import com.example.moviecatalogue.utils.DataDummy
import com.nhaarman.mockitokotlin2.doAnswer
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
    private val repository = FakeRepository(remote)

    private val movieResponse = DataDummy.getDummyMovieResponse()
    val movie = DataDummy.getDummyDetailMovieResponse()
    private val tvShowResponse = DataDummy.getDummyTvShowResponse()
    val tvShow = DataDummy.getDummyDetailTvShowResponse()

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
}