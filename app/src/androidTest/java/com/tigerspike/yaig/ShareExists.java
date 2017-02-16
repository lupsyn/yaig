package com.tigerspike.yaig;


import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingPolicies;
import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.tigerspike.yaig.utils.RecycleViewIdlingResource;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.TimeUnit;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isRoot;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withResourceName;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

/**
 * Example of Espresso tests with idle resource
 */

@LargeTest
@RunWith(AndroidJUnit4.class)
public class ShareExists {

    public static RecyclerViewMatcher withRecyclerView(final int recyclerViewId) {
        return new RecyclerViewMatcher(recyclerViewId);
    }


    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @BeforeClass
    public static void beforeClass() {
        // Make sure Espresso does not time out
        IdlingPolicies.setMasterPolicyTimeout(10, TimeUnit.SECONDS);
        IdlingPolicies.setIdlingResourceTimeout(10, TimeUnit.SECONDS);
    }

    //TODO : https://medium.com/azimolabs/wait-for-it-idlingresource-and-conditionwatcher-602055f32356#.zeqlj4n7e

    private IdlingResource.ResourceCallback callback = new IdlingResource.ResourceCallback() {
        @Override
        public void onTransitionToIdle() {
            onView(withId(R.id.card_share)).perform(click());
        }
    };

    @Test
    public void shareExists() {

        // FIXME : there are 2 approch to fix the recycleview test after the rest this is one
        // here we are registeridling if the adapter in the recycle is not null
        // but it could be better to handle directly the callback from the presenter
        RecycleViewIdlingResource rcvIdle = new RecycleViewIdlingResource();
        rcvIdle.setActivity(mActivityTestRule.getActivity());
        Espresso.registerIdlingResources(rcvIdle);
        onView(withRecyclerView(R.id.recycleview).atPositionOnView(0, R.id.card_share)).perform(click());
        Espresso.unregisterIdlingResources(rcvIdle);

    }


}
