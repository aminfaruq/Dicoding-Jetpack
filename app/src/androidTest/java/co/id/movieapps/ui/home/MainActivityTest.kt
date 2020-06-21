package co.id.movieapps.ui.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.swipeLeft
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import co.id.movieapps.R
import co.id.movieapps.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainActivityTest {

    @get:Rule
    var activityRule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun setUp() {
        IdlingRegistry.getInstance()
            .register(EspressoIdlingResource.getEspressoIdlingResourceMainActivity())
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance()
            .unregister(EspressoIdlingResource.getEspressoIdlingResourceMainActivity())
    }


    @Test
    fun loadMovie() {
        onView(withId(R.id.rv_movies))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(7))
        onView(withId(R.id.rv_movies))
            .perform(
                RecyclerViewActions
                    .actionOnItemAtPosition<RecyclerView
                    .ViewHolder>(6, ViewActions.click())
            )
        Espresso.pressBack()
        onView(withId(R.id.rv_movies)).check(matches(isDisplayed()))

        onView(withId(R.id.view_pager)).perform(swipeLeft());
        onView(withId(R.id.rv_tvShow))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(7))
        onView(withId(R.id.rv_tvShow))
            .perform(
                RecyclerViewActions
                    .actionOnItemAtPosition<RecyclerView
                    .ViewHolder>(6, ViewActions.click())
            )
        Espresso.pressBack()
        onView(withId(R.id.rv_tvShow)).check(matches(isDisplayed()))

    }
}
