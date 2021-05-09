package com.example.moviecatalogue.ui.movie_favorites

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.example.moviecatalogue.data.source.Repository
import com.example.moviecatalogue.data.source.local.entity.MovieEntity
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieFavoritesFragmentTest {
    private lateinit var viewModel: MovieFavoritesViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: Repository

    @Mock
    private lateinit var observer: Observer<PagedList<MovieEntity>>

    @Mock
    private lateinit var pagedList: PagedList<MovieEntity>

    @Before
    fun setUp() {
        viewModel = MovieFavoritesViewModel(repository)
    }

    @Test
    fun getMoviesFavorites() {
        val dummyList = pagedList
        `when`(dummyList.size).thenReturn(5)

        val movies = MutableLiveData<PagedList<MovieEntity>>()
        movies.value = dummyList

        `when`(repository.getMoviesFavorites()).thenReturn(movies)
        val favorites = repository.getMoviesFavorites().value
        verify(repository).getMoviesFavorites()
        assertNotNull(favorites)

        assertEquals(5, favorites?.size)

        viewModel.getMoviesFavorites().observeForever(observer)
        verify(observer).onChanged(dummyList)
    }
}