package edu.csumb.moli9479.applicationpocketcs;

import android.support.test.espresso.ViewAssertion;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by josephmolina on 11/7/16.
 */

@RunWith(AndroidJUnit4.class)
public class FaceBookLoginTest {

    @Rule
    public ActivityTestRule<LoginActivity> loginActivityActivityTestRule =
            new ActivityTestRule<LoginActivity>(LoginActivity.class);

    @Test
    public void clickLoginButton() throws Exception{
        onView(withId(R.id.nameTextView))
                .check(matches(withText("Joseph Molina")));
    }

    @Test
    public void gotUserId() throws Exception{
        onView(withId(R.id.idTextView))
                .check(matches(withText("1219102721489385")));
    }
}
