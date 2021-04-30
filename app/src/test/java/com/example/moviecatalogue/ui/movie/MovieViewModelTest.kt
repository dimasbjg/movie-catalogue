package com.example.moviecatalogue.ui.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.moviecatalogue.data.source.Repository
import com.example.moviecatalogue.data.source.local.MovieEntity
import com.example.moviecatalogue.utils.DataDummy
import com.nhaarman.mockitokotlin2.verify
import junit.framework.Assert.assertEquals
import junit.framework.TestCase.assertNotNull
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest {
    private lateinit var viewModel: MovieViewModel
    private val dispatcher = TestCoroutineDispatcher()


    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: Repository

    @Mock
    private lateinit var observer: Observer<List<MovieEntity>>

    @Before
    fun setUp() {
        viewModel = MovieViewModel(repository)
        Dispatchers.setMain(dispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun getMovie() = runBlocking{
//        val movieDummy = DataDummy.getDummyListMovie()
//        val movie = MutableLiveData<List<MovieEntity>>()
//        runBlocking {
//            movie.postValue(repository.getListMovie("harry potter and the deathly"))
//        }
//        runBlocking {
//            `when`(repository.getListMovie("harry potter and the deathly")).thenReturn(movieDummy)
//        }
//
//        val movieEntities = viewModel.listMovie
//        assertNotNull(movieEntities)
////        assertEquals(viewModel.listMovie.value?.size, movieEntities.value?.size)
//        runBlocking {
//            verify(repository).getListMovie("harry potter and the deathly")
//        }
//        movieEntities.observeForever(observer)
//        verify(observer).onChanged(movieDummy)

        val dummyMovies = DataDummy.getDummyListMovie()
        `when`(repository.getListMovie("Harry potter and the death")).thenReturn(dummyMovies)
        val movieEntities = viewModel.listMovie
        assertNotNull(movieEntities)
        Mockito.verify(repository).getListMovie("Harry potter and the death")
        viewModel.listMovie.observeForever(observer)
        Mockito.verify(observer).onChanged(dummyMovies)

    }
}