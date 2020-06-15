package co.id.movieapps.ui.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.swipeLeft
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import co.id.movieapps.R
import org.junit.Rule
import org.junit.Test

class MainActivityTest {

    @get:Rule
    var activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun loadMovie() {
        onView(withId(R.id.rv_movies))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(7))
        Thread.sleep(3000)
        onView(withId(R.id.rv_movies))
            .perform(
                RecyclerViewActions
                    .actionOnItemAtPosition<RecyclerView
                    .ViewHolder>(6, ViewActions.click())
            )
        Thread.sleep(3000)
        Espresso.pressBack()
        onView(withId(R.id.rv_movies)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movies)).check(RecyclerViewItemCountAssertion(10))
    }

    @Test
    fun loadTv() {
        onView(withId(R.id.view_pager)).perform(swipeLeft());
        onView(withId(R.id.rv_tvShow))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(7))
        Thread.sleep(3000)
        onView(withId(R.id.rv_tvShow))
            .perform(
                RecyclerViewActions
                    .actionOnItemAtPosition<RecyclerView
                    .ViewHolder>(6, ViewActions.click())
            )
        Thread.sleep(3000)
        Espresso.pressBack()
        onView(withId(R.id.rv_tvShow)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tvShow)).check(RecyclerViewItemCountAssertion(10))

    }
}