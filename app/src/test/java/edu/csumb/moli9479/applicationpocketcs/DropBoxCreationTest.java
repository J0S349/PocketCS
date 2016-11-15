package edu.csumb.moli9479.applicationpocketcs;

import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThat;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(MockitoJUnitRunner.class)
public class DropBoxCreationTest extends AppCompatActivity{
    @Test
    public void createAlgorithmDropBox() throws Exception {
        LinearLayout spinnerLayout = new LinearLayout(this);
        String [] algorithmCategories = {"Sorting", "Searching", "String Matching", "Graph Problems", "Optimization"};
        CategoryScreen categoryScreen = new CategoryScreen();
        boolean dropBoxIsCreated = categoryScreen.createDefaultAlgorithmDropBox(algorithmCategories, spinnerLayout);
        assertThat(dropBoxIsCreated, is(true));
    }

    @Test
    public void createSpinner() throws Exception {
        LinearLayout spinnerLayout = new LinearLayout(this);
        String [] algorithmCategories = {"Sorting", "Searching", "String Matching", "Graph Problems", "Optimization"};
        CategoryScreen categoryScreen = new CategoryScreen();
        boolean dropBoxIsCreated = categoryScreen.displayDropBox(spinnerLayout, algorithmCategories);
        assertThat(dropBoxIsCreated, is(true));
    }
}