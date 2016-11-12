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
public class SoftwareDesignTest {
    @Test
    public void testCosts() throws Exception {
        SoftwareDesign a = new SoftwareDesign();
        a.setCosts("");
        assertEquals(a.getCosts(), "");
    }

    @Test
    public void testBenefits() throws Exception {
        SoftwareDesign a = new SoftwareDesign();
        a.setBenefits("");
        assertEquals(a.getBenefits(), "");
    }

    @Test
    public void testName() throws Exception {
        SoftwareDesign a = new SoftwareDesign();
        a.setName("Quick Sort");
        assertEquals(a.getName(), "Quick Sort");
    }

    @Test
    public void testDescription() throws Exception {
        SoftwareDesign a = new SoftwareDesign();
        a.setDescription("Quickly sorts an array");
        assertEquals(a.getDescription(), "Quickly sorts an array");
    }

    @Test
    public void testUserID() throws Exception {
        SoftwareDesign a = new SoftwareDesign();
        a.setUserID(-1);
        assertNotEquals(a.getUserID(), -1);
    }

    @Test
    public void testImage() throws Exception {
        SoftwareDesign a = new SoftwareDesign();
        a.setImage("");
        assertEquals(a.getImage(), "");
    }

    @Test
    public void testCategoryID() throws Exception {
        SoftwareDesign a = new SoftwareDesign();
        a.setCategoryID(1);
        assertEquals(a.getCategoryID(), 1);
    }

    @Test
    public void testHelpfulLink() throws Exception {
        SoftwareDesign a = new SoftwareDesign();
        a.setHelpfulLink("google.com");
        assertEquals(a.getHelpfulLink(), "google.com");
    }

    @Test
    public void testDateCreated() throws Exception {
        SoftwareDesign a = new SoftwareDesign();
        a.setDateCreated("");
        assertEquals(a.getDateCreated(), "");
    }

    @Test
    public void testDateUpdated() throws Exception {
        SoftwareDesign a = new SoftwareDesign();
        a.setDateUpdated("");
        assertEquals(a.getDateUpdated(), "");
    }

}