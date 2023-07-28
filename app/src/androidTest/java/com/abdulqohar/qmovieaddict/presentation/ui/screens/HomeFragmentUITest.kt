package com.abdulqohar.qmovieaddict.presentation.ui.screens

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.abdulqohar.qmovieaddict.MainActivity
import com.abdulqohar.qmovieaddict.R
import com.abdulqohar.qmovieaddict.TestQMovieAddictApplication
import dagger.hilt.android.testing.CustomTestApplication
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

//
//@RunWith(AndroidJUnit4::class)
//@HiltAndroidTest
//@CustomTestApplication(TestQMovieAddictApplication::class)
//class HomeFragmentUITest {

//    @get:Rule
//    val hiltRule = HiltAndroidRule(this)
//
//    @get:Rule
//    val activityRule = ActivityScenarioRule(MainActivity::class.java)
//
//    @Before
//    fun setUp() {
//        hiltRule.inject()
//    }
//
//    @Test
//    fun testHomeFragmentUI() {
//        // Wait for the fragment to be displayed
//        onView(withId(R.id.movie_addict_textView)).check(matches(isDisplayed()))
//
//        // Check if the RecyclerView is displaying items
//        onView(withId(R.id.movie_filter_recyclerView)).check(matches(hasMinimumChildCount(1)))
//
//        // Check if the filter RecyclerView is displayed
//        onView(withId(R.id.movie_filter_recyclerView)).check(matches(isDisplayed()))
//
//        // Click on the "Favourites" filter
//        onView(withText("Favourites")).perform(click())
//
////        // Check if the RecyclerView is displaying items after applying the "Favourites" filter
////        onView(withId(R.id.movies_recyclerView)).check(matches(hasMinimumChildCount(1)))
////
////        // Click on a movie item
////        onView(withId(R.id.movies_recyclerView)).perform(
////            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
////                0,
////                click()
////            )
////        )
//
//        // Check if the movie details screen is displayed
//        onView(withId(R.id.movieDetailsFragment)).check(matches(isDisplayed()))
//    }

//}