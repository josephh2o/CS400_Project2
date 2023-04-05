
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.*;

public class BackendDeveloperTests {
    
    Backend backend;
    
    @BeforeEach
    public void makeBackend() {
        backend = new Backend(true);
    }
    
    // JUST BACKEND
    
    /*
    test that load data returns true and properly loads the data for all three red black trees
     */
    @Test
    public void testLoadData() {
        assertTrue(backend.loadData("noduplicates.csv"));
        assertEquals("year: [[1920:  {Bad Old Movie, 1920, 0},  {Good Old Movie, 1920, 100}], [1997:  {Good New Movie, 1997, 100}]]\n" +
                "popularity: [[0:  {Bad Old Movie, 1920, 0}], [100:  {Good Old Movie, 1920, 100},  {Good New Movie, 1997, 100}]]\n" +
                "title: [[Bad Old Movie:  {Bad Old Movie, 1920, 0}], [Good Old Movie:  {Good Old Movie, 1920, 100}], [Good New Movie:  {Good New Movie, 1997, 100}]]\n",
            backend.RBTString());
    }
    
    /*
    test that getMoviesByTitle outputs the proper movies
     */
    @Test
    public void testGetMoviesByTitle() {
        backend.loadData("noduplicates.csv");
        MovieInterface bad = backend.getMoviesByTitle("Bad Old Movie").get(0);
        assertEquals(0, bad.getPopularity());
        assertEquals(1920, bad.getYear());
        assertEquals("Bad Old Movie", bad.getTitle());
    }
    
    /*
    test that getMoviesByYear outputs the proper movies
     */
    @Test
    public void testGetMoviesByYear() {
        backend.loadData("noduplicates.csv");
        MovieInterface good = backend.getMoviesByYear(1997).get(0);
        assertEquals(100, good.getPopularity());
        assertEquals(1997, good.getYear());
        assertEquals("Good New Movie", good.getTitle());
    }
    
    /*
    test that getMoviesByPopularity outputs the proper movies
     */
    @Test
    public void testGetMoviesByPopularity() {
        backend.loadData("noduplicates.csv");
        MovieInterface bad = backend.getMoviesByPopularity(0).get(0);
        assertEquals(0, bad.getPopularity());
        assertEquals(1920, bad.getYear());
        assertEquals("Bad Old Movie", bad.getTitle());
    }
    
    /*
    Test that compareMoviesByYear produces the results produced by the algorithm dummy class
    Edit: no longer relevant since frontend is no longer using this test
     */
//    @Test
//    public void testCompareMoviesByYear() {
//        backend.loadData("noduplicates.csv");
//        List<List<MovieInterface>> comparison = backend.compareMoviesByYear(1979, 1920);
//        assertEquals("<1997: [{Good New Movie, 1997, 100}]>", comparison.get(0).toString());
//        assertEquals("<1920: [{Bad Old Movie, 1920, 0}, {Good Old Movie, 1920, 100}]>", comparison.get(1).toString());
//    }
    
    /*
    Test that getMoviesByYearRange and getMoviesByYearPopularityRnage produces the results produced by the algorithm dummy class
     */
    @Test
    public void testGetMoviesByRange() {
        backend.loadData("noduplicates.csv");
        System.out.println(backend.getMoviesByYearRange(1920, 1920).toString());
        System.out.println(backend.getMoviesByPopularityRange(0, 0).toString());
        assertEquals("[1920:  {Bad Old Movie, 1920, 0},  {Good Old Movie, 1920, 100}]", backend.getMoviesByYearRange(1920, 1920).toString());
        assertEquals("[0:  {Bad Old Movie, 1920, 0}]", backend.getMoviesByPopularityRange(0, 0).toString());
    }
    
    // INTEGRATION
    
    /*
    Test integration for finding single values of title, population, and year when they exist and don't exist in the data
     */
    @Test
    public void integrationTest1() {
        TextUITester tester = new TextUITester("l\nfake.csv\nl\nnoduplicates.csv\nt\nHunting\nt\nThe Fake Movie\np\n42\np\n97\ny\n1967\ny\n2001\nq");
        
        // from runProgram main
        BackendInterface backend = new Backend();
        Scanner input = new Scanner(System.in);
        MovieSearchAppInterface movieSearchApp = new MovieSearchApp(input, backend);
        movieSearchApp.runCommandLoop();
        
        String output = tester.checkOutput();
        System.out.println(output);
        
        assertTrue(output.contains("Error loading file."));
        assertTrue(output.contains("Data loaded successfully."));
        assertTrue(output.contains("Hunting (1992) Popularity: 68"));
        assertTrue(output.contains("No movies found with the title The Fake Movie."));
        assertTrue(output.contains("Wild Strawberries (1957) Popularity: 42"));
        assertTrue(output.contains("World's Greatest Lover, The (1977) Popularity: 42"));
        assertTrue(output.contains("No movies found with the popularity 97."));
        assertTrue(output.contains("Elvira Madigan (1967) Popularity: 28"));
        assertTrue(output.contains("Cool Hand Luke (1967) Popularity: 49"));
        assertTrue(output.contains("No movies found in 2001."));
        assertFalse(output.contains("No movies found with the keyword Hunting."));
        assertFalse(output.contains("Dangerous When Wet"));
        assertFalse(output.contains("No movies found with the popularity 42."));
        assertFalse(output.contains("No movies found in 1967."));
    }
    
    /*
    Test integration for finding ranges of data
     */
    @Test
    public void integrationTest2() {
        TextUITester tester = new TextUITester("l\nnoduplicates.csv\np\n30-32\ny\n1940-1942\nq");
        
        // from runProgram main
        BackendInterface backend = new Backend();
        Scanner input = new Scanner(System.in);
        MovieSearchAppInterface movieSearchApp = new MovieSearchApp(input, backend);
        movieSearchApp.runCommandLoop();
        
        String output = tester.checkOutput();
        System.out.println(output);
        
        assertTrue(output.contains("Missing (1982) Popularity: 30"));
        assertTrue(output.contains("Running Man, The (1987) Popularity: 31"));
        assertTrue(output.contains("Shining, The (1980) Popularity: 32"));
        assertFalse(output.contains("French Lesson (1986) Popularity: 29"));
        assertFalse(output.contains("All New Tales from the Crypt, A Trilogy (1990) Popularity: 33"));
        
        assertTrue(output.contains("Long Voyage Home, The (1940) Popularity: 88"));
        assertTrue(output.contains("Mr. & Mrs. Smith (1941) Popularity: 3"));
        assertTrue(output.contains("Lady for a Night (1942) Popularity: 12"));
        assertFalse(output.contains("Allegheny Uprising (1939) Popularity: 53"));
        assertFalse(output.contains("Sanshiro Sugata (1943) Popularity: 85"));
        
        
    }
    
    // CODE REVIEW
    
    /*
    tests inserting and getting data
     */
    @Test
    public void CodeReviewOfAlgorithmEngineer1() {
    
        // setting up movie RBTs
        ArrayList<MovieInterface> movies = new ArrayList<>();
        movies.add(new Movie(1920, "Bad Old Movie", 0, "", "", "",
            "", 0, false));
        movies.add(new Movie(1920, "Good Old Movie", 0, "", "", "",
            "", 100, false));
        movies.add(new Movie(1997, "Good New Movie", 0, "", "", "",
            "", 100, false));
    
        // year
        RBTList<Integer>[] year = new RBTList[2];
        year[0] = new RBTList<>(1920);
        year[0].add(movies.get(0));
        year[0].add(movies.get(1));
        year[1] = new RBTList<>(1997);
        year[1].add(movies.get(2));
    
        // by title
        RBTList<String>[] title = new RBTList[3];
        title[0] = new RBTList<>("Bad Old Movie");
        title[0].add(movies.get(0));
        title[1] = new RBTList<>("Good Old Movie");
        title[1].add(movies.get(1));
        title[2] = new RBTList<>("Good New Movie");
        title[2].add(movies.get(2));
    
        // popularity
        RBTList<Integer>[] pop = new RBTList[2];
        pop[0] = new RBTList<>(0);
        pop[0].add(movies.get(0));
        pop[1] = new RBTList<>(100);
        pop[1].add(movies.get(1));
        pop[1].add(movies.get(2));
    
        MovieRedBlackTree movieRBT = new MovieRedBlackTree();
    
        // insert into red black trees
        for (int i=0; i<title.length; i++) {
            movieRBT.insertByTitle(title[i]);
        }
        for (int i=0; i<pop.length; i++) {
            movieRBT.insertByPopularity(pop[i]);
        }
        for (int i=0; i<year.length; i++) {
            movieRBT.insertByYear(year[i]);
        }
        
        // test that it inserted correctly
        assertEquals("level order: [ [Good New Movie:  Good New Movie (1997) Popularity: 100], [Bad Old Movie:  Bad Old Movie (1920) Popularity: 0], [Good Old Movie:  Good Old Movie (1920) Popularity: 100] ]\n" +
            "in order: [ [Bad Old Movie:  Bad Old Movie (1920) Popularity: 0], [Good New Movie:  Good New Movie (1997) Popularity: 100], [Good Old Movie:  Good Old Movie (1920) Popularity: 100] ]",
            movieRBT.getTitleRBT().toString());
        assertEquals("level order: [ [0:  Bad Old Movie (1920) Popularity: 0], [100:  Good Old Movie (1920) Popularity: 100,  Good New Movie (1997) Popularity: 100] ]\n" +
                "in order: [ [0:  Bad Old Movie (1920) Popularity: 0], [100:  Good Old Movie (1920) Popularity: 100,  Good New Movie (1997) Popularity: 100] ]",
            movieRBT.getPopularityRBT().toString());
        assertEquals("level order: [ [1920:  Bad Old Movie (1920) Popularity: 0,  Good Old Movie (1920) Popularity: 100], [1997:  Good New Movie (1997) Popularity: 100] ]\n" +
                "in order: [ [1920:  Bad Old Movie (1920) Popularity: 0,  Good Old Movie (1920) Popularity: 100], [1997:  Good New Movie (1997) Popularity: 100] ]",
            movieRBT.getYearRBT().toString());
    
        assertEquals(movieRBT.getDataByYear(1920), year[0]);
        assertEquals(movieRBT.getDataByYear(1921), null);
        assertEquals(movieRBT.getDataByPopularity(0), pop[0]);
        assertEquals(movieRBT.getDataByYear(5), null);
        assertEquals(movieRBT.getDataByTitle("Bad Old Movie"), title[0]);
        assertEquals(movieRBT.getDataByTitle("Fake Movie"), null);
        
    }
    
    /*
    test range of data
     */
    @Test
    public void CodeReviewOfAlgorithmEngineer2() {
    
        // setting up movie RBTs
        ArrayList<MovieInterface> movies = new ArrayList<>();
        movies.add(new Movie(1920, "Bad Old Movie", 0, "", "", "",
            "", 0, false));
        movies.add(new Movie(1920, "Good Old Movie", 0, "", "", "",
            "", 100, false));
        movies.add(new Movie(1997, "Good New Movie", 0, "", "", "",
            "", 100, false));
    
        // year
        RBTList<Integer>[] year = new RBTList[2];
        year[0] = new RBTList<>(1920);
        year[0].add(movies.get(0));
        year[0].add(movies.get(1));
        year[1] = new RBTList<>(1997);
        year[1].add(movies.get(2));
    
        // by title
        RBTList<String>[] title = new RBTList[3];
        title[0] = new RBTList<>("Bad Old Movie");
        title[0].add(movies.get(0));
        title[1] = new RBTList<>("Good Old Movie");
        title[1].add(movies.get(1));
        title[2] = new RBTList<>("Good New Movie");
        title[2].add(movies.get(2));
    
        // popularity
        RBTList<Integer>[] pop = new RBTList[2];
        pop[0] = new RBTList<>(0);
        pop[0].add(movies.get(0));
        pop[1] = new RBTList<>(100);
        pop[1].add(movies.get(1));
        pop[1].add(movies.get(2));
    
        MovieRedBlackTree movieRBT = new MovieRedBlackTree();
    
        // insert the data into red black trees
        for (int i=0; i<title.length; i++) {
            movieRBT.insertByTitle(title[i]);
        }
        for (int i=0; i<pop.length; i++) {
            movieRBT.insertByPopularity(pop[i]);
        }
        for (int i=0; i<year.length; i++) {
            movieRBT.insertByYear(year[i]);
        }
    
        assertEquals("[0:  Bad Old Movie (1920) Popularity: 0,  Good Old Movie (1920) Popularity: 100,  Good New Movie (1997) Popularity: 100]",
            movieRBT.getRangeData(1920, 1997, true).toString()); // tests normal year range
        assertEquals("[0:  Bad Old Movie (1920) Popularity: 0,  Good Old Movie (1920) Popularity: 100,  Good New Movie (1997) Popularity: 100]",
            movieRBT.getRangeData(0, 100, false).toString()); // normal popularity range
        assertEquals(null, movieRBT.getRangeData(1997, 1920, true)); // backwards range
        assertEquals(null, movieRBT.getRangeData(1950, 1956, true)); // no movies in range
        assertEquals("[0:  Good New Movie (1997) Popularity: 100]",
            movieRBT.getRangeData(1980, 2000, true).toString()); // movies in range but not in bounds
        
        
    
    }
    
    
    
}
