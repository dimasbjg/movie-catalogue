package com.example.moviecatalogue.ui.tvshow_favorites

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.example.moviecatalogue.data.source.Repository
import com.example.moviecatalogue.data.source.local.entity.MovieEntity
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TvShowFavoritesFragmentTest {
    private lateinit var viewModel: TvShowFavoritesViewModel

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
        viewModel = TvShowFavoritesViewModel(repository)
    }

    @Test
    fun getTvShowFavorites() {
        val dummyList = pagedList
        Mockito.`when`(dummyList.size).thenReturn(5)

        val tvShow = MutableLiveData<PagedList<MovieEntity>>()
        tvShow.value = dummyList

        Mockito.`when`(repository.getTvShowFavorites()).thenReturn(tvShow)
        val favorites = repository.getTvShowFavorites().value
        verify(repository).getTvShowFavorites()
        assertNotNull(favorites)

        assertEquals(5, favorites?.size)

        viewModel.getTvShowFavorites().observeForever(observer)
        verify(observer).onChanged(dummyList)
    }
}