package edu.csumb.moli9479.applicationpocketcs;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(MockitoJUnitRunner.class)
public class AlgorithmsTest {
    @Test
    public void testRunTime() throws Exception {
        Algorithms a = new Algorithms();
        a.setRuntime("O(n*log(n))");
        assertEquals(a.getRuntime(), "O(n*log(n))");
    }

    @Test
    public void testName() throws Exception {
        Algorithms a = new Algorithms();
        a.setName("Quick Sort");
        assertEquals(a.getName(), "Quick Sort");
    }

    @Test
    public void testDescription() throws Exception {
        Algorithms a = new Algorithms();
        a.setDescription("Quickly sorts an array");
        assertEquals(a.getDescription(), "Quickly sorts an array");
    }

    @Test
    public void testUserID() throws Exception {
        Algorithms a = new Algorithms();
        a.setUserID(-1);
        assertNotEquals(a.getUserID(), -1);
    }

    @Test
    public void testImage() throws Exception {
        Algorithms a = new Algorithms();
        a.setImage("");
        assertEquals(a.getImage(), "");
    }

    @Test
    public void testCategoryID() throws Exception {
        Algorithms a = new Algorithms();
        a.setCategoryID(1);
        assertEquals(a.getCategoryID(), 0);
    }

    @Test
    public void testHelpfulLink() throws Exception {
        Algorithms a = new Algorithms();
        a.setHelpfulLink("google.com");
        assertEquals(a.getHelpfulLink(), "google.com");
    }

    @Test
    public void testDateCreated() throws Exception {
        Algorithms a = new Algorithms();
        a.setDateCreated("");
        assertEquals(a.getDateCreated(), "");
    }

    @Test
    public void testDateUpdated() throws Exception {
        Algorithms a = new Algorithms();
        a.setDateUpdated("");
        assertEquals(a.getDateUpdated(), "");
    }

}