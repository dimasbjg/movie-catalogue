package com.example.moviecatalogue.ui.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.swipeLeft
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.moviecatalogue.R
import com.example.moviecatalogue.data.DataMovie
import com.example.moviecatalogue.data.DataTvShow
import org.junit.Rule
import org.junit.Test

class HomeActivityTest {

    private val dataMovie = DataMovie.listData
    private val dataTvShow = DataTvShow.listData

    @get:Rule
    var activityRule = ActivityScenarioRule(HomeActivity::class.java)

    @Test
    fun loadMovie() {
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dataMovie.size
            )
        )
    }

    @Test
    fun loadDetailMovie() {
        onView(withId(R.id.rv_movie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.detail_title)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_title)).check(matches(withText(dataMovie[0].title)))

        onView(withId(R.id.director)).check(matches(isDisplayed()))
        onView(withId(R.id.director)).check(matches(withText("Director: ${dataMovie[0].director}")))

        onView(withId(R.id.writter)).check(matches(isDisplayed()))
        onView(withId(R.id.writter)).check(matches(withText("Writter: ${dataMovie[0].writter}")))

        onView(withId(R.id.staring)).check(matches(isDisplayed()))
        onView(withId(R.id.staring)).check(matches(withText("Starring: ${dataMovie[0].starring}")))

        onView(withId(R.id.rating)).check(matches(isDisplayed()))
        onView(withId(R.id.rating)).check(matches(withText(dataMovie[0].rating)))

        onView(withId(R.id.description)).check(matches(isDisplayed()))
        onView(withId(R.id.description)).check(matches(withText(dataMovie[0].description)))

        onView(withId(R.id.img_poster)).check(matches(isDisplayed()))
    }

    @Test
    fun loadTvShow() {
        onView(withText("TV Show")).perform(click())
        onView(withId(R.id.rv_tv_show)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_show)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dataTvShow.size
            )
        )
    }

    @Test
    fun loadDetailTvShow() {
        onView(withText("TV Show")).perform(click())
        onView(withId(R.id.rv_tv_show)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )

        onView(withId(R.id.detail_title)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_title)).check(matches(withText(dataTvShow[0].title)))

        onView(withId(R.id.director)).check(matches(isDisplayed()))
        onView(withId(R.id.director)).check(matches(withText("")))

        onView(withId(R.id.writter)).check(matches(isDisplayed()))
        onView(withId(R.id.writter)).check(matches(withText("")))

        onView(withId(R.id.staring)).check(matches(isDisplayed()))
        onView(withId(R.id.staring)).check(matches(withText("")))

        onView(withId(R.id.rating)).check(matches(isDisplayed()))
        onView(withId(R.id.rating)).check(matches(withText(dataTvShow[0].rating)))

        onView(withId(R.id.description)).check(matches(isDisplayed()))
        onView(withId(R.id.description)).check(matches(withText(dataTvShow[0].description)))

        onView(withId(R.id.img_poster)).check(matches(isDisplayed()))
    }

}