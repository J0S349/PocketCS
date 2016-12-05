package edu.csumb.moli9479.applicationpocketcs;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import android.widget.EditText;
import android.widget.LinearLayout;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.HashMap;
import java.util.Locale;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class ScreenTransitionTest extends AppCompatActivity {

    @Rule
    public ActivityTestRule mActivityRule = new ActivityTestRule<>(
            MainActivity.class);

    @Test
    public void algorithmsButtonDisplaysSorting() throws Exception {
        onView(withId(R.id.AlgorithmsButton)).perform(click());
        onView(withId(5)).check(matches(withText("Sorting")));
    }

    /*@Test
    public void createSpinner() throws Exception {
        LinearLayout spinnerLayout = new LinearLayout(this);
        String [] algorithmCategories = {"Sorting", "Searching", "String Matching", "Graph Problems", "Optimization"};
        CategoryScreen categoryScreen = new CategoryScreen();
        boolean dropBoxIsCreated = categoryScreen.displayButton(spinnerLayout, algorithmCategories);
        assertThat(dropBoxIsCreated, is(true));
    }*/
}