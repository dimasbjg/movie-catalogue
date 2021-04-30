package com.example.moviecatalogue.ui.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.moviecatalogue.data.source.Repository
import com.example.moviecatalogue.data.source.local.TvShowEntity
import com.example.moviecatalogue.utils.DataDummy
import junit.framework.TestCase.assertNotNull
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner.Silent::class)
class TvShowViewModelTest {
    private lateinit var viewModel: TvShowViewModel
    private val dispatcher = TestCoroutineDispatcher()


    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()


    @Mock
    private lateinit var repository: Repository

    @Mock
    private lateinit var observer: Observer<List<TvShowEntity>>

    @Before
    fun setUp() {
        viewModel = TvShowViewModel(repository)
        Dispatchers.setMain(dispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun getTvShow() = runBlocking {
        val tvShowDummy = DataDummy.getDummyListTvShow()
        `when`(repository.getListTvShow("animal fight")).thenReturn(tvShowDummy)
        val tvShowEntities = viewModel.listTvShow
        assertNotNull(tvShowEntities)
        assertEquals(viewModel.listTvShow.value?.size, tvShowEntities.value?.size)
    }
}