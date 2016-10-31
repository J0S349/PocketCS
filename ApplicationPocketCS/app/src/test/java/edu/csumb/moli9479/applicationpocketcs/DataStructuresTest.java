package edu.csumb.moli9479.applicationpocketcs;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(MockitoJUnitRunner.class)
public class DataStructuresTest {
    @Test
    public void testRunTime() throws Exception {
        DataStructures a = new DataStructures();
        a.setRuntime("O(n*log(n))");
        assertEquals(a.getRuntime(), "O(n*log(n))");
    }

    @Test
    public void testName() throws Exception {
        DataStructures a = new DataStructures();
        a.setName("Quick Sort");
        assertEquals(a.getName(), "Quick Sort");
    }

    @Test
    public void testDescription() throws Exception {
        DataStructures a = new DataStructures();
        a.setDescription("Quickly sorts an array");
        assertEquals(a.getDescription(), "Quickly sorts an array");
    }

    @Test
    public void testUserID() throws Exception {
        DataStructures a = new DataStructures();
        a.setUserID(-1);
        assertNotEquals(a.getUserID(), -1);
    }

    @Test
    public void testImage() throws Exception {
        DataStructures a = new DataStructures();
        a.setImage("");
        assertEquals(a.getImage(), "");
    }

    @Test
    public void testCategoryID() throws Exception {
        DataStructures a = new DataStructures();
        a.setCategoryID(1);
        assertEquals(a.getCategoryID(), 1);
    }

    @Test
    public void testHelpfulLink() throws Exception {
        DataStructures a = new DataStructures();
        a.setHelpfulLink("google.com");
        assertEquals(a.getHelpfulLink(), "google.com");
    }

    @Test
    public void testDateCreated() throws Exception {
        DataStructures a = new DataStructures();
        a.setDateCreated("");
        assertEquals(a.getDateCreated(), "");
    }

    @Test
    public void testDateUpdated() throws Exception {
        DataStructures a = new DataStructures();
        a.setDateUpdated("");
        assertEquals(a.getDateUpdated(), "");
    }

}