package com.example.a9726446.lab2brightidea;

import org.junit.Rule;
import org.junit.Test;

import android.content.pm.ActivityInfo;
import android.support.test.rule.ActivityTestRule;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;


/**
 * Created by 9726446 on 20/8/18. @ LB1-MAC-024
 */

public class UITestSwitch {
    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void UITestToggleLight(){
        //One can't match by srcCompat, so I'm going by content descr instead. Oh well.
        onView(withId(R.id.imgLight)).check(matches(withContentDescription(R.string.light_Off)));
        onView(withId(R.id.imgLight)).perform(click());
        onView(withId(R.id.imgLight)).check(matches(withContentDescription(R.string.light_On)));
    }

    @Test
    public void UIRotationTest(){
        //again, checking by descr instead of image.
        onView(withId(R.id.imgLight)).perform(click());
        mainActivityActivityTestRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        onView(withId(R.id.imgLight)).check(matches(withContentDescription(R.string.light_On)));
    }
}
