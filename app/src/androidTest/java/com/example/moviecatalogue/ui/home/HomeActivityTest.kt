package com.example.moviecatalogue.ui.home

import android.view.KeyEvent
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.espresso.matcher.ViewMatchers.Visibility.VISIBLE
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.moviecatalogue.R
import com.example.moviecatalogue.utils.EspressoIdlingResource
import org.hamcrest.Matchers.allOf
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class HomeActivityTest {

    @Before
    fun setUp() {
        ActivityScenario.launch(HomeActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.espressoTestIdlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.espressoTestIdlingResource)
    }

    @get:Rule
    var activityRule = ActivityScenarioRule(HomeActivity::class.java)

    @Test
    fun loadMovie() {
        onView(withId(R.id.search)).perform(click())
        onView(withId(R.id.search)).perform(typeText("harry potter"), closeSoftKeyboard())
        onView(withId(R.id.search)).perform(pressKey(KeyEvent.KEYCODE_ENTER))

        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
    }

    @Test
    fun loadDetailMovie() {
        onView(withId(R.id.search)).perform(click())
        onView(withId(R.id.search)).perform(typeText("harry potter"), closeSoftKeyboard())
        onView(withId(R.id.search)).perform(pressKey(KeyEvent.KEYCODE_ENTER))

        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.detail_title)).check(matches(isDisplayed()))
        onView(withId(R.id.release_date)).check(matches(isDisplayed()))
        onView(withId(R.id.release_date)).check(matches(isDisplayed()))
        onView(withId(R.id.status)).check(matches(isDisplayed()))
        onView(withId(R.id.rating)).check(matches(isDisplayed()))
        onView(withId(R.id.description)).check(matches(isDisplayed()))
        onView(withId(R.id.img_poster)).check(matches(isDisplayed()))
    }

    @Test
    fun loadTvShow() {
        onView(withText("TV Show")).perform(click())
        onView(allOf(isDisplayed(),withId(R.id.search), withContentDescription("TV Show"))).perform(click())
        onView(allOf(isDisplayed(),withId(R.id.search), withContentDescription("TV Show"))).perform(typeText("panda"), closeSoftKeyboard())
        onView(allOf(isDisplayed(),withId(R.id.search), withContentDescription("TV Show"))).perform(pressKey(KeyEvent.KEYCODE_ENTER))
        onView(withId(R.id.rv_tv_show)).check(matches(isDisplayed()))
    }

    @Test
    fun loadDetailTvShow() {
        onView(withText("TV Show")).perform(click())
        onView(allOf(isDisplayed(),withId(R.id.search), withContentDescription("TV Show"))).perform(click())
        onView(allOf(isDisplayed(),withId(R.id.search), withContentDescription("TV Show"))).perform(typeText("panda"), closeSoftKeyboard())
        onView(allOf(isDisplayed(),withId(R.id.search), withContentDescription("TV Show"))).perform(pressKey(KeyEvent.KEYCODE_ENTER))
        onView(withId(R.id.rv_tv_show)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_show)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.detail_title)).check(matches(isDisplayed()))
        onView(withId(R.id.release_date)).check(matches(isDisplayed()))
        onView(withId(R.id.release_date)).check(matches(isDisplayed()))
        onView(withId(R.id.status)).check(matches(isDisplayed()))
        onView(withId(R.id.rating)).check(matches(isDisplayed()))
        onView(withId(R.id.description)).check(matches(isDisplayed()))
        onView(withId(R.id.img_poster)).check(matches(isDisplayed()))
    }

    @Test
    fun loadMoviesFavorites() {
        onView(withId(R.id.search)).perform(click())
        onView(withId(R.id.search)).perform(typeText("harry potter"), closeSoftKeyboard())
        onView(withId(R.id.search)).perform(pressKey(KeyEvent.KEYCODE_ENTER))
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.fab)).perform(click())
        onView(isRoot()).perform(pressBack())
        onView(withId(R.id.menu_favorites)).perform(click())
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.fab)).perform(click())
        onView(isRoot()).perform(pressBack())

    }

    @Test
    fun loadTvShowFavorites() {
        onView(withText("TV Show")).perform(click())
        onView(allOf(isDisplayed(),withId(R.id.search), withContentDescription("TV Show"))).perform(click())
        onView(allOf(isDisplayed(),withId(R.id.search), withContentDescription("TV Show"))).perform(typeText("panda"), closeSoftKeyboard())
        onView(allOf(isDisplayed(),withId(R.id.search), withContentDescription("TV Show"))).perform(pressKey(KeyEvent.KEYCODE_ENTER))
        onView(withId(R.id.rv_tv_show)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_show)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.fab)).perform(click())
        onView(isRoot()).perform(pressBack())
        onView(withId(R.id.menu_favorites)).perform(click())
        onView(withText("TV Show")).perform(click())
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.fab)).perform(click())
        onView(isRoot()).perform(pressBack())
    }

}