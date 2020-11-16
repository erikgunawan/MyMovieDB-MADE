package id.ergun.mymoviedb.ui.view.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import id.ergun.mymoviedb.R
import id.ergun.mymoviedb.data.local.MovieDB
import id.ergun.mymoviedb.data.local.TvShowDB
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeActivityTest {

    private val movies = MovieDB().getMovies()
    private val tvShows = TvShowDB().getTvShows()

    @get:Rule
    val rule = activityScenarioRule<HomeActivity>()

    @Test
    fun loadMovies() {
        onView(withId(R.id.rv_movie))
            .check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(movies.size))
    }

    @Test
    fun loadDetailMovie() {
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.tv_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_title)).check(matches(withText(movies[0].title)))
        onView(withId(R.id.tv_tagline)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_tagline)).check(matches(withText(movies[0].tagline)))
        onView(withId(R.id.tv_rating)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_rating)).check(matches(withText(movies[0].voteAverage.toString())))
        onView(withId(R.id.tv_overview)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_overview)).check(matches(withText(movies[0].overview)))
    }


    @Test
    fun loadTvShows() {
        onView(withText(R.string.tv_shows)).perform(click())
        onView(withId(R.id.rv_tv_show))
            .check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_show))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(tvShows.size))
    }

    @Test
    fun loadDetailTvShow() {
        onView(withText(R.string.tv_shows)).perform(click())
        onView(withId(R.id.rv_tv_show)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.tv_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_title)).check(matches(withText(tvShows[0].name)))
        onView(withId(R.id.tv_tagline)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_tagline)).check(matches(withText(tvShows[0].tagline)))
        onView(withId(R.id.tv_rating)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_rating)).check(matches(withText(tvShows[0].voteAverage.toString())))
        onView(withId(R.id.tv_overview)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_overview)).check(matches(withText(tvShows[0].overview)))
    }
}