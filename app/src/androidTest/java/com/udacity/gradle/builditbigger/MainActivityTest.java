package com.udacity.gradle.builditbigger;

import android.support.test.espresso.IdlingRegistry;
import android.support.test.espresso.IdlingResource;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static junit.framework.Assert.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {


    @Rule
    public ActivityTestRule<MainActivity> activityActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setupView(){
        activityActivityTestRule.getActivity()
                .getSupportFragmentManager().beginTransaction();
    }


    @Test
    public void verifyItensDisplayed_ExpectedSuccess()  {
        onView(withId(com.udacity.gradle.builditbigger.R.id.instructions_text_view)).check(matches(isDisplayed()));
        onView(withId(com.udacity.gradle.builditbigger.R.id.btn_tellJoke)).check(matches(isDisplayed()));
        onView(withId(com.udacity.gradle.builditbigger.R.id.progressBar)).check(matches(not(isDisplayed())));
    }

    @Test
    public void verifyOnBtnClick_ExpectedFragmentLaunch(){
        onView(withId(com.udacity.gradle.builditbigger.R.id.btn_tellJoke)).perform(click());
        onView(withId(R.id.textView_joke)).check(matches(isDisplayed()));
    }


    //Adds tests for AsyncTask
    private IdlingResource mIdlingResource;

    @Test
    public void jokeFetchAsyncTask_fetches_joke_correctly() {

        JokerFetchAsyncTask.JokerListener listener = new JokerFetchAsyncTask.JokerListener() {
            @Override
            public void onJokerFetching() {
                //Nothing to do
            }

            @Override
            public void onJokerFetched(String joke) {
                assertThat(joke, notNullValue());
                assertTrue(joke.length() > 0);
            }
        };


        JokerFetchAsyncTask jokeFetchAsyncTask = new JokerFetchAsyncTask(listener);

        mIdlingResource = jokeFetchAsyncTask.getIdlingResource();
        IdlingRegistry.getInstance().register(mIdlingResource);

        jokeFetchAsyncTask.execute();
    }

    @After
    public void tearDown() {
        if (mIdlingResource != null){
            IdlingRegistry.getInstance().unregister(mIdlingResource);
        }
    }
}
