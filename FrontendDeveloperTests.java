import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * This class contains JUnit 5 tests for the FrontendDeveloper class.
 */
public class FrontendDeveloperTests {

    /**
     * This JUnit 5 test checks the functionality of the mainMenuPrompt() method.
     * The following scenarios are tested:
     * A. Entering valid commands
     * B. Entering an invalid command
     */
    @Test
    public void testMenu() {
        BackendInterface backend = new BackendFD();
        TextUITester test = new TextUITester("L\n\nT\n\nP\n\nY\n\n1\nQ\n");
        Scanner input = new Scanner(System.in);
        MovieSearchAppInterface movieSearchApp = new MovieSearchApp(input, backend);
        movieSearchApp.runCommandLoop();
        String output = test.checkOutput();
        { // Scenario A
            assertTrue(output.contains("Command List:"));
        }
        { // Scenario B
            assertTrue(output.contains("Invalid command. Please try again."));
        }
    }

    /**
     * This JUnit 5 test checks the functionality of the loadData() method.
     * The following scenarios are tested:
     * A. Loading a file that exists
     */
    @Test
    public void testLoad() {
        BackendInterface backend = new BackendFD();
        TextUITester test = new TextUITester("L\ntest.txt\nQ\n");
        Scanner input = new Scanner(System.in);
        MovieSearchAppInterface movieSearchApp = new MovieSearchApp(input, backend);
        movieSearchApp.runCommandLoop();
        String output = test.checkOutput();
        { // Scenario A
            assertTrue(output.contains("Data loaded successfully"));
        }
    }

    /**
     * This JUnit 5 test checks the functionality of the searchTitle() method.
     * The following scenarios are tested:
     * A. Searching by a single word title
     * B. Searching by a multi-word title
     * C. Searching by a title containing punctuation
     * D. Searching by a title that is a number
     */
    @Test
    public void testTitle() {
        BackendInterface backend = new BackendFD();
        TextUITester test = new TextUITester("L\nempty.txt\nT\nalpha\nT\nbeta gamma\nT\nDon't\nT\n1\nQ\n");
        Scanner input = new Scanner(System.in);
        MovieSearchAppInterface movieSearchApp = new MovieSearchApp(input, backend);
        movieSearchApp.runCommandLoop();
        String output = test.checkOutput();
        { // Scenario A
            assertTrue(output.contains("Found 1 movie(s) with the title alpha"));
        }
        { // Scenario B
            assertTrue(output.contains("Found 1 movie(s) with the title beta gamma"));
        }
        { // Scenario C
            assertTrue(output.contains("Found 1 movie(s) with the title Don't"));
        }
        { // Scenario D
            assertTrue(output.contains("Found 1 movie(s) with the title 1"));
        }
        assertTrue(output.contains("aaaa"));
    }

    /**
     * This JUnit 5 test checks the functionality of the searchYearCommand() and searchYearRange() methods.
     * The following scenarios are tested:
     * A. Searching by a single year
     * B. Searching by multiple years
     * C. Searching by a range of years
     * D. Searching by a year that is a not a valid number, such as "abc"
     * E. Searching by a range of years that is a not a valid number, such as "abc-def"
     */
    @Test
    public void testYear() {
        BackendInterface backend = new BackendFD();
        TextUITester test = new TextUITester("L\nempty.txt\nY\n2010\nY\n2011,2012\nY\n2013-2023\nY\nabc\nY\nabc-def\nQ\n");
        Scanner input = new Scanner(System.in);
        MovieSearchAppInterface movieSearchApp = new MovieSearchApp(input, backend);
        movieSearchApp.runCommandLoop();
        String output = test.checkOutput();
        { // Scenarios A and B
            assertTrue(output.contains("Found 2 movie(s) in 2010"));
            assertTrue(output.contains("Found 2 movie(s) in 2011"));
            assertTrue(output.contains("Found 2 movie(s) in 2012"));
            assertTrue(output.contains("dddd (4444) Popularity: 444"));
            assertTrue(output.contains("eeee (5555) Popularity: 555"));
        }
        { // Scenario C
            assertTrue(output.contains("Found 2 movie(s) in 2013 to 2023"));
            assertTrue(output.contains("ffff (6666) Popularity: 666"));
            assertTrue(output.contains("gggg (7777) Popularity: 777"));
        }
        { // Scenario D and E
            assertTrue(output.contains("Invalid year. Please try again."));
        }
    }

    /**
     * This JUnit 5 test checks the functionality of the searchPopularityCommand() and searchPopularityRange() methods.
     * The following scenarios are tested:
     * A. Searching by a single popularity
     * B. Searching by multiple popularities
     * C. Searching by a range of popularities
     * D. Searching by a popularity that is a not a valid number, such as "abc"
     * E. Searching by a range of years that is a not a valid number, such as "abc-def"
     */
    @Test
    public void testPopularity() {
        BackendInterface backend = new BackendFD();
        TextUITester test = new TextUITester("L\nempty.txt\nP\n1\nP\n2,3\nP\n4-5\nP\nabc\nP\nabc-def\nQ\n");
        Scanner input = new Scanner(System.in);
        MovieSearchAppInterface movieSearchApp = new MovieSearchApp(input, backend);
        movieSearchApp.runCommandLoop();
        String output = test.checkOutput();
        { // Scenarios A and B
            assertTrue(output.contains("Found 2 movie(s) with the popularity 1"));
            assertTrue(output.contains("Found 2 movie(s) with the popularity 2"));
            assertTrue(output.contains("Found 2 movie(s) with the popularity 3"));
            assertTrue(output.contains("bbbb (2222) Popularity: 222"));
            assertTrue(output.contains("cccc (3333) Popularity: 333"));
        }
        { // Scenario C
            assertTrue(output.contains("Found 2 movie(s) with the popularity 4 to 5"));
            assertTrue(output.contains("hhhh (8888) Popularity: 888"));
            assertTrue(output.contains("iiii (9999) Popularity: 999"));
        }
        { // Scenario D and E
            assertTrue(output.contains("Invalid popularity. Please try again."));
        }
    }

    /**
     * This JUnit 5 test checks the functionality of all methods in normal operation.
     * The following scenarios are tested:
     * A. Loading a file
     * B. Searching by a multiple keywords
     * C. Searching by a range of years
     * D. Searching by multiple popularities
     */
    @Test
    public void testGeneral() {
        BackendInterface backend = new BackendFD();
        TextUITester test = new TextUITester("L\nempty.txt\nT\nJames Bond\nY\n2021-2023\nP\n9,10\nQ\n");
        Scanner input = new Scanner(System.in);
        MovieSearchAppInterface movieSearchApp = new MovieSearchApp(input, backend);
        movieSearchApp.runCommandLoop();
        String output = test.checkOutput();
        System.out.println(output);
        { // Scenario A
            assertTrue(output.contains("Data loaded successfully"));
        }
        { // Scenario B
            assertTrue(output.contains("Found 1 movie(s) with the title James Bond:"));
        }
        { // Scenario C
            assertTrue(output.contains("Found 2 movie(s) in 2021 to 2023:"));
        }
        { // Scenario D
            assertTrue(output.contains("Found 2 movie(s) with the popularity 9"));
            assertTrue(output.contains("Found 2 movie(s) with the popularity 10"));
        }
    }
}