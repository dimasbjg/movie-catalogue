package com.example.moviecatalogue.ui.movie

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class MovieViewModelTest {

    private lateinit var viewModel: MovieViewModel

    @Before
    fun setUp() {
        viewModel = MovieViewModel()
    }

    @Test
    fun getMovie() {
        val movieEntity = viewModel.getMovie()
        assertNotNull(movieEntity)
        assertEquals(10, movieEntity.size)
    }
}