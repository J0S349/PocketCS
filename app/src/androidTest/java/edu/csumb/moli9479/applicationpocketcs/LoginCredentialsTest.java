package edu.csumb.moli9479.applicationpocketcs;

import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.rule.ServiceTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static java.util.regex.Pattern.matches;
import static org.junit.Assert.assertEquals;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class LoginCredentialsTest {

    @Rule
    public final ActivityTestRule mActivityRule = new ActivityTestRule(LoginActivity.class);
    /*
    @Test
    public void testForEmptyCredentials() throws Exception {
        // Context of the app under test.
        // Create the service Intent.
        Intent serviceIntent =
                new Intent(InstrumentationRegistry.getTargetContext(),
                        LoginActivity.class);

        // Click on a given operation button
        onView(withId(R.id.login_button)).perform(click());

        onView(withId(R.id.operand_one_edit_text)).perform(typeText(operandOne),
                closeSoftKeyboard());
        onView(withId(R.id.operand_two_edit_text)).perform(typeText(operandTwo),
                closeSoftKeyboard());

        // Check the expected test is displayed in the Ui
        onView(withId(R.id.operation_result_text_view)).check(matches(withText(expectedResult)));



        // Verify that the service is working correctly.
        //assertThat(service.getRandomInt(), is(any(Integer.class)));
    }
    */

    @Test
    public void testForInvalidCredentials() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("edu.csumb.moli9479.applicationpocketcs", appContext.getPackageName());
    }
}
