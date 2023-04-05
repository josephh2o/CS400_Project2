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

    /**
     * This JUnit 5 test checks the functionality of all methods in normal operation once integrated.
     * The following scenarios are tested:
     * A. Loading a file
     * B. Searching by a single word title
     * C. Searching by a single year
     * D. Searching by a single popularity
     * E. Searching by a multiple word title
     * F. Searching by a multiple years
     * G. Searching by a multiple popularities
     * H. Searching by a range of years
     * I. Searching by a range of popularities
     * J. Quitting the program
     */
    @Test
    public void testIntegration1() {
        BackendInterface backend = new Backend();
        TextUITester test = new TextUITester("L\nnoduplicates.csv\nT\nLoulou\nY\n1978\nP\n0\n" +
                "T\nDesert Rider\nY\n1972,1974\nP\n5,10\nY\n1980-1990\nP\n80-100\nQ\n");
        Scanner input = new Scanner(System.in);
        MovieSearchAppInterface movieSearchApp = new MovieSearchApp(input, backend);
        movieSearchApp.runCommandLoop();
        String output = test.checkOutput();
        { // Scenario A
            assertTrue(output.contains("Data loaded successfully"));
        }
        { // Scenario B
            assertTrue(output.contains("Found 1 movie(s) with the title Loulou"));
            assertTrue(output.contains("Loulou (1980) Popularity: 65"));
        }
        { // Scenario C
            assertTrue(output.contains("Found 18 movie(s) in 1978"));
            assertTrue(output.contains("Days of Heaven (1978) Popularity: 14"));
            assertTrue(output.contains("Eraserhead (1978) Popularity: 2"));
        }
        { // Scenario D
            assertTrue(output.contains("Found 18 movie(s) with the popularity 0"));
            assertTrue(output.contains("Shalako (1968) Popularity: 0"));
            assertTrue(output.contains("Tales of Tomorrow (1953) Popularity: 0"));
        }
        { // Scenario E
            assertTrue(output.contains("Found 1 movie(s) with the title Desert Rider"));
            assertTrue(output.contains("Desert Rider (1923) Popularity: 0"));
        }
        { // Scenario F
            assertTrue(output.contains("Found 24 movie(s) in 1972"));
            assertTrue(output.contains("Cries & Whispers (1972) Popularity: 18"));
            assertTrue(output.contains("My Country Right or Wrong (1972) Popularity: 21"));
            assertTrue(output.contains("Found 23 movie(s) in 1974:"));
            assertTrue(output.contains("Murder on the Orient Express (1974) Popularity: 8"));
            assertTrue(output.contains("Steve Martin, The Funnier Side of Eastern Canada (1974) Popularity: 34"));
        }
        { // Scenario G
            assertTrue(output.contains("Found 22 movie(s) with the popularity 5"));
            assertTrue(output.contains("Tommy (1992) Popularity: 5"));
            assertTrue(output.contains("McQ (1992) Popularity: 5"));
            assertTrue(output.contains("Found 18 movie(s) with the popularity 10:"));
            assertTrue(output.contains("Carnal Knowledge (1971) Popularity: 10"));
            assertTrue(output.contains("Final Alliance, The (1990) Popularity: 10"));
        }
        { // Scenario H
            assertTrue(output.contains("Found 712 movie(s) in 1980 to 1990"));
            assertTrue(output.contains("Happy Birthday to Me (1980) Popularity: 88"));
            assertTrue(output.contains("Sagebrush Trail (1990) Popularity: 23"));
        }
        { // Scenario I
            assertTrue(output.contains("Found 168 movie(s) with the popularity 80 to 100"));
            assertTrue(output.contains("Zandalee (1990) Popularity: 80"));
            assertTrue(output.contains("Jeremiah Johnson (1972) Popularity: 88"));
        }
        { // Scenario J
            assertTrue(output.contains("Thank you for using the Movie Search App & enjoy the movie!"));
        }
    }

    /**
     * This JUnit 5 test checks the functionality of all methods in abnormal operation once integrated.
     * The following scenarios are tested:
     * A. Not loading a file before entering other commands
     * B. Loading incorrect file
     * C. User enters invalid command
     * D. Searching by a title that does not exist
     * E. Searching by a year that does not exist
     * F. Searching by a popularity that does not exist
     */
    @Test
    public void testIntegration2() {
        BackendInterface backend = new Backend();
        TextUITester test = new TextUITester("T\nL\nfake.txt\nL\nnoduplicates.csv\nU\nT\naaaa\n" +
                "Y\n2021\nP\n101\nQ\n");
        Scanner input = new Scanner(System.in);
        MovieSearchAppInterface movieSearchApp = new MovieSearchApp(input, backend);
        movieSearchApp.runCommandLoop();
        String output = test.checkOutput();
        { // Scenario A
            assertTrue(output.contains("Please load data first"));
        }
        { // Scenario B
            assertTrue(output.contains("Error loading file"));
        }
        { // Scenario C
            assertTrue(output.contains("Invalid command"));
        }
        { // Scenario D
            assertTrue(output.contains("No movies found with the title aaaa"));
        }
        { // Scenario E
            assertTrue(output.contains("No movies found in 2021"));
        }
        { // Scenario F
            assertTrue(output.contains("No movies found with the popularity 101"));
        }
    }
}