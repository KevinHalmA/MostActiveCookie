import org.junit.Test;
import org.junit.Assert;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

public class Tests {
    @Test
    public void testMostActiveCookieSingleResult() throws FileNotFoundException {
        List<String> expected = Arrays.asList("AtY0laUfhglK3lC7");
        List<String> actual = Main.findMostActiveCookies("csvFiles/cookieTest.csv", "2018-12-09");
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testMostActiveCookieMultipleResults() throws FileNotFoundException {
        List<String> expected = Arrays.asList("SAZuXPGUrfbcn5UA", "4sMM2LxV07bPJzwf", "fbcn5UAVanZf6UtG");
        List<String> actual = Main.findMostActiveCookies("csvFiles/cookieTest.csv", "2018-12-08");
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testStopReadingCSVFile() throws FileNotFoundException {
        //Tests to see if after the date has passed that the file stops being read
        //Unordered csv file contains an extra addition of date 2018-12-09 at the end of the file (shouldn't be read)
        List<String> expected = Arrays.asList("AtY0laUfhglK3lC7");
        List<String> actual = Main.findMostActiveCookies("csvFiles/cookieTestUnordered.csv", "2018-12-09");
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testNoActiveCookie() throws FileNotFoundException {
        List<String> expected = Arrays.asList();
        // Date with no entries
        List<String> actual = Main.findMostActiveCookies("csvFiles/cookieTest.csv", "2018-12-01");
        Assert.assertEquals(expected, actual);
    }

    @Test(expected = FileNotFoundException.class)
    public void testFileNotFound() throws FileNotFoundException {
        Main.findMostActiveCookies("csvFiles/nonexistent.csv", "2018-12-09");
    }

    @Test
    public void testValidDate() {
        Assert.assertTrue("2022-11-09 should be a valid date", Main.isValidDate("2022-11-09"));
    }

    @Test
    public void testInvalidDate() {
        Assert.assertFalse("2022-02-30 should be an invalid date", Main.isValidDate("2022-02-30"));
    }
}
