import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assumptions.assumeTrue;

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
    public void test1() {
        BackendInterface backend1 = new Backend();
        MovieReaderInterface movieReader1 = new MovieReader();
        TextUITester test1 = new TextUITester("L\n\nT\n\nP\n\nY\n\n1\nQ\n");
        Scanner input1 = new Scanner(System.in);
        MovieSearchAppInterface movieSearchApp = new MovieSearchApp(input1, backend1, movieReader1);
        movieSearchApp.runCommandLoop();
        String output1 = test1.checkOutput();
        { // Scenario A
            assumeTrue(output1.contains("Command List:"));
        }
        { // Scenario B
            assumeTrue(output1.contains("Invalid command. Please try again."));
        }
    }

    /**
     * This JUnit 5 test checks the functionality of the loadData() method.
     * The following scenarios are tested:
     * A. Loading a file that exists
     */
    @Test
    public void test2() {
        BackendInterface backend2 = new Backend();
        MovieReaderInterface movieReader2 = new MovieReader();
        TextUITester test2 = new TextUITester("L\ntest.txt\nQ\n");
        Scanner input2 = new Scanner(System.in);
        MovieSearchAppInterface movieSearchApp = new MovieSearchApp(input2, backend2, movieReader2);
        movieSearchApp.runCommandLoop();
        String output2 = test2.checkOutput();
        assumeTrue(output2.contains("Loaded 3 movies from test.txt."));
    }

    /**
     * This JUnit 5 test checks the functionality of the searchTitle() method.
     * The following scenarios are tested:
     * A. Searching by a single keyword
     * B. Searching by two keywords
     * C. Searching by a keyword containing punctuation
     * D. Searching by a keyword that is a number
     */
    @Test
    public void test3() {
        BackendInterface backend3 = new Backend();
        MovieReaderInterface movieReader3 = new MovieReader();
        TextUITester test3 = new TextUITester("T\nalpha\nT\nbeta gamma\nT\nDon't\nT\n1\nQ\n");
        Scanner input3 = new Scanner(System.in);
        MovieSearchAppInterface movieSearchApp = new MovieSearchApp(input3, backend3, movieReader3);
        movieSearchApp.runCommandLoop();
        String output3 = test3.checkOutput();
        { // Scenario A
            assumeTrue(output3.contains("Movies found with the keyword alpha"));
        }
        { // Scenario B
            assumeTrue(output3.contains("Movies found with the keyword beta"));
            assumeTrue(output3.contains("Movies found with the keyword gamma"));
        }
        { // Scenario C
            assumeTrue(output3.contains("Movies found with the keyword Don't"));
        }
        { // Scenario D
            assumeTrue(output3.contains("Movies found with the keyword 1"));
        }
        assumeTrue(output3.contains("aaaa"));
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
    public void test4() {
        BackendInterface backend4 = new Backend();
        MovieReaderInterface movieReader4 = new MovieReader();
        TextUITester test4 = new TextUITester("Y\n2010\nY\n2011,2012\nY\n2013-2023\nY\nabc\nY\nabc-def\nQ\n");
        Scanner input4 = new Scanner(System.in);
        MovieSearchAppInterface movieSearchApp = new MovieSearchApp(input4, backend4, movieReader4);
        movieSearchApp.runCommandLoop();
        String output4 = test4.checkOutput();
        { // Scenarios A and B
            assumeTrue(output4.contains("Movies found in 2010"));
            assumeTrue(output4.contains("Movies found in 2011"));
            assumeTrue(output4.contains("Movies found in 2012"));
            assumeTrue(output4.contains("dddd (4444)"));
            assumeTrue(output4.contains("eeee (5555)"));
        }
        { // Scenario C
            assumeTrue(output4.contains("Movies found in 2013 to 2023:"));
            assumeTrue(output4.contains("ffff (6666)"));
            assumeTrue(output4.contains("gggg (7777)"));
        }
        { // Scenario D and E
            assumeTrue(output4.contains("Invalid year. Please try again."));
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
    public void test5() {
        BackendInterface backend5 = new Backend();
        MovieReaderInterface movieReader5 = new MovieReader();
        TextUITester test5 = new TextUITester("P\n1\nP\n2,3\nP\n4-5\nP\nabc\nP\nabc-def\nQ\n");
        Scanner input5 = new Scanner(System.in);
        MovieSearchAppInterface movieSearchApp = new MovieSearchApp(input5, backend5, movieReader5);
        movieSearchApp.runCommandLoop();
        String output5 = test5.checkOutput();
        { // Scenarios A and B
            assumeTrue(output5.contains("Movies found with the popularity 1"));
            assumeTrue(output5.contains("Movies found with the popularity 2"));
            assumeTrue(output5.contains("Movies found with the popularity 3"));
            assumeTrue(output5.contains("bbbb (Rating: 222)"));
            assumeTrue(output5.contains("cccc (Rating: 333)"));
        }
        { // Scenario C
            assumeTrue(output5.contains("Movies found with the popularity 4 to 5"));
            assumeTrue(output5.contains("hhhh (Rating: 888)"));
            assumeTrue(output5.contains("iiii (Rating: 999)"));
        }
        { // Scenario D and E
            assumeTrue(output5.contains("Invalid popularity. Please try again."));
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
    public void test6() {
        BackendInterface backend6 = new Backend();
        MovieReaderInterface movieReader6 = new MovieReader();
        TextUITester test6 = new TextUITester("L\ntest.txt\nT\nJames Bond\nY\n2021-2023\nP\n9,10\nQ\n");
        Scanner input6 = new Scanner(System.in);
        MovieSearchAppInterface movieSearchApp = new MovieSearchApp(input6, backend6, movieReader6);
        movieSearchApp.runCommandLoop();
        String output = test6.checkOutput();
        { // Scenario A
            assumeTrue(output.contains("Loaded 3 movies from test.txt"));
        }
        { // Scenario B
            assumeTrue(output.contains("Movies found with the keyword James"));
            assumeTrue(output.contains("Movies found with the keyword Bond"));
        }
        { // Scenario C
            assumeTrue(output.contains("Movies found in 2021 to 2023:"));
        }
        { // Scenario D
            assumeTrue(output.contains("Movies found with the popularity 9"));
            assumeTrue(output.contains("Movies found with the popularity 10"));
        }
    }
}