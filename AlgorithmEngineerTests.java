import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AlgorithmEngineerTests {
    MovieRedBlackTree _rbt;

    /**
     * Initialize _rbt and insert values into MovieRedBlackTreeAE
     * @see MovieRedBlackTreeAE
     */
    @BeforeEach
    public void init() { 
        // Initialize the RBT and insert the lists into the RBT
        _rbt = new MovieRedBlackTree();

        MovieAE movie1 = new MovieAE(1, "h", 11);
        MovieAE movie2 = new MovieAE(2, "g", 12);
        MovieAE movie3 = new MovieAE(3, "f", 13);
        MovieAE movie4 = new MovieAE(4, "e", 14);
        MovieAE movie5 = new MovieAE(5, "d", 15);
        MovieAE movie6 = new MovieAE(6, "c", 16);
        MovieAE movie7 = new MovieAE(7, "b", 17);
        MovieAE movie8 = new MovieAE(8, "a", 18);
       
        // Insert First Movie
        RBTList<Integer> yearList = new RBTList<>(movie1.getYear()); // Create a new RBTList to insert into the yearRBT
        yearList.add(movie1); // add the movie into the RBTList arraylist
        _rbt.insertByYear(yearList); // insert into yearRBT
        RBTList<String> titleList = new RBTList<>(movie1.getTitle()); // Create a new RBTList to insert into the titleRBT
        titleList.add(movie1); // add the movie int other RBTList arraylist
        _rbt.insertByTitle(titleList); // insert into titleRBT
        RBTList<Integer> popularityList = new RBTList<>(movie1.getPopularity()); // Create a new RBTList to insert into the popularityRBT
        popularityList.add(movie1); // add the movie into the RBTList arraylist
        _rbt.insertByPopularity(popularityList); // insert into popularityRBT

        // Insert Second Movie
        yearList = new RBTList<>(movie2.getYear());
        yearList.add(movie2);
        _rbt.insertByYear(yearList);
        titleList = new RBTList<>(movie2.getTitle());
        titleList.add(movie2);
        _rbt.insertByTitle(titleList);
        popularityList = new RBTList<>(movie2.getPopularity());
        popularityList.add(movie2);
        _rbt.insertByPopularity(popularityList);

        // Insert Third Movie
        yearList = new RBTList<>(movie3.getYear());
        yearList.add(movie3);
        _rbt.insertByYear(yearList);
        popularityList = new RBTList<>(movie3.getPopularity());
        popularityList.add(movie3);
        _rbt.insertByPopularity(popularityList);
        titleList = new RBTList<>(movie3.getTitle());
        titleList.add(movie3);
        _rbt.insertByTitle(titleList);
   
        // Insert Fourth Movie
        yearList = new RBTList<>(movie4.getYear());
        yearList.add(movie4);
        _rbt.insertByYear(yearList);
        popularityList = new RBTList<>(movie4.getPopularity());
        popularityList.add(movie4);
        _rbt.insertByPopularity(popularityList);
        titleList = new RBTList<>(movie4.getTitle());
        titleList.add(movie4);
        _rbt.insertByTitle(titleList);
   
        // Insert Fifth Movie
        yearList = new RBTList<>(movie5.getYear());
        yearList.add(movie5);
        _rbt.insertByYear(yearList);
        popularityList = new RBTList<>(movie5.getPopularity());
        popularityList.add(movie5);
        _rbt.insertByPopularity(popularityList);
        titleList = new RBTList<>(movie5.getTitle());
        titleList.add(movie5);
        _rbt.insertByTitle(titleList);
   
        // Insert Sixth Movie
        yearList = new RBTList<>(movie6.getYear());
        yearList.add(movie6);
        _rbt.insertByYear(yearList);
        popularityList = new RBTList<>(movie6.getPopularity());
        popularityList.add(movie6);
        _rbt.insertByPopularity(popularityList);
        titleList = new RBTList<>(movie6.getTitle());
        titleList.add(movie6);
        _rbt.insertByTitle(titleList);
   
        // Insert Seventh Movie
        yearList = new RBTList<>(movie7.getYear());
        yearList.add(movie7);
        _rbt.insertByYear(yearList);
        popularityList = new RBTList<>(movie7.getPopularity());
        popularityList.add(movie7);
        _rbt.insertByPopularity(popularityList);
        titleList = new RBTList<>(movie7.getTitle());
        titleList.add(movie7);
        _rbt.insertByTitle(titleList);
   
        // Insert Eighth Movie
        yearList = new RBTList<>(movie8.getYear());
        yearList.add(movie8);
        _rbt.insertByYear(yearList);
        popularityList = new RBTList<>(movie8.getPopularity());
        popularityList.add(movie8);
        _rbt.insertByPopularity(popularityList);
        titleList = new RBTList<>(movie8.getTitle());
        titleList.add(movie8);
        _rbt.insertByTitle(titleList);
    }

    @Test
    public void testInsert() { // test the insert methods and RBT getters to ensure lists are inserted correctly
        // Test that the yearRBT is correct
        assertEquals( "[ [4:  4], [2:  2], [6:  6], [1:  1], [3:  3], [5:  5], [7:  7], [8:  8] ]", _rbt.getYearRBT().toLevelOrderString());
        // Test that the popularityRBT is correct
        assertEquals("[ [14:  4], [12:  2], [16:  6], [11:  1], [13:  3], [15:  5], [17:  7], [18:  8] ]", _rbt.getPopularityRBT().toLevelOrderString());
        // Test that the titleRBT is correct
        assertEquals("[ [e:  4], [c:  6], [g:  2], [b:  7], [d:  5], [f:  3], [h:  1], [a:  8] ]", _rbt.getTitleRBT().toLevelOrderString());
        // Test inserting invalid node to yearRBT
        assertThrows(NullPointerException.class, () -> {
            _rbt.insertByYear(null);
        });
        // Test inserting invalid node to popularityRBT
        assertThrows(NullPointerException.class, () -> {
            _rbt.insertByPopularity(null);
        });
        // Test inserting invalid node to titlRBT
        assertThrows(NullPointerException.class, () -> {
            _rbt.insertByTitle(null);
        });
    }
    
    @Test
    public void testRangeYear() { // Test getting movies between certain years
        // Test full range
        assertEquals("[0:  1,  2,  3,  4,  5,  6,  7,  8]", _rbt.getRangeData(1, 8, true).toString());
        // Range that includes root
        assertEquals("[0:  3,  4,  5,  6,  7]", _rbt.getRangeData(3, 7, true).toString());
        // Range that does not include root
        assertEquals("[0:  5,  6,  7]", _rbt.getRangeData(5, 7, true).toString());
    }

    @Test
    public void testRangePopularity() { // Test getting movies between certain popularities
        // Test full range
        assertEquals("[0:  1,  2,  3,  4,  5,  6,  7,  8]", _rbt.getRangeData(11, 18, false).toString());
        // Range that includes root
        assertEquals("[0:  3,  4,  5,  6,  7]", _rbt.getRangeData(13, 17, false).toString());
        // Range that does not include root
        assertEquals("[0:  5,  6,  7]", _rbt.getRangeData(15, 17, false).toString());
    }

    @Test
    public void testGetDataByTitle() { // Test getDataByTitle method
        // Test Root Node
        assertEquals("[e:  4]", _rbt.getDataByTitle("e").toString());
        // Test Leaf Node
        assertEquals("[f:  3]", _rbt.getDataByTitle("f").toString());
        // // Test Node in the Center of the Tree
        assertEquals("[g:  2]", _rbt.getDataByTitle("g").toString());
        // // Test Non-Existing Node
        assertEquals(null, _rbt.getDataByTitle("z"));
    }

    @Test
    public void testGetDataByPopularity() { // Test getDataByPopularity method
        // Test Root Node
        assertEquals("[14:  4]", _rbt.getDataByPopularity(14).toString());
        // Test Leaf Node
        assertEquals("[13:  3]", _rbt.getDataByPopularity(13).toString());
        // // Test Node in the Center of the Tree
        assertEquals("[12:  2]", _rbt.getDataByPopularity(12).toString());
        // // Test Non-Existing Node
        assertEquals(null, _rbt.getDataByPopularity(0));
    }

    @Test
    public void testGetDataByYear() { // Test getDataByPopularity method
        // Test Root Node
        assertEquals("[4:  4]", _rbt.getDataByYear(4).toString());
        // Test Leaf Node
        assertEquals("[3:  3]", _rbt.getDataByYear(3).toString());
        // // Test Node in the Center of the Tree
        assertEquals("[2:  2]", _rbt.getDataByYear(2).toString());
        // // Test Non-Existing Node
        assertEquals(null, _rbt.getDataByYear(11));
    }

    @Test
    public void testContainsIntegration() {
        Backend bd = new Backend();
        bd.loadData("noduplicates.csv");
        RBTList<Integer> listData= new RBTList<Integer>(1990);
        assertEquals(true, bd.getRBT().getYearRBT().contains(listData));
        listData = new RBTList<Integer>(0);
        assertEquals(false, bd.getRBT().getYearRBT().contains(listData));

        listData= new RBTList<Integer>(5);
        assertEquals(true, bd.getRBT().getPopularityRBT().contains(listData));
        listData = new RBTList<Integer>(-1);
        assertEquals(false, bd.getRBT().getYearRBT().contains(listData));

        RBTList<String> listDataString = new RBTList<String>("Z");
        assertEquals(true, bd.getRBT().getTitleRBT().contains(listDataString));
        listDataString = new RBTList<String>("Foo Bar");
        assertEquals(false, bd.getRBT().getTitleRBT().contains(listDataString));
    }
    
    @Test
    public void testRangeIntegration() {
        TextUITester tester = new TextUITester("L\nnoduplicates.csv\nY\n1995-2000\nQ\n");
        String expected = "Found 2 movie(s) in 1995 to 2000:\n" +
                "Island of Dr. Moreau, The (1996) Popularity: 39\n" +
                "Alien: resurrection (1997) Popularity: 60";
        Scanner scan = new Scanner(System.in);
        Backend bd = new Backend();

        MovieSearchApp app = new MovieSearchApp(scan, bd);
        app.runCommandLoop();
        String output = tester.checkOutput();
        assertEquals(true, output.contains(expected));
    }

    @Test
    public void testLoadDataCodeReviewOfBackendDeveloper() {
        Backend bd = new Backend();
        assertEquals(true ,bd.loadData("noduplicates.csv"));
        assertEquals(1657, bd.getRBT().getTitleRBT().size());
        assertEquals(false, bd.loadData(null));
        assertEquals(false, bd.loadData("Foo Bar"));
    }

    @Test
    public void testGetRangeCodeReviewOfBackendDeveloper() {
        Backend bd = new Backend();
        bd.loadData("noduplicates.csv");
        String[] expected = new String[] {"Spiders (1920) Popularity: 29",
            "Desert Rider (1923) Popularity: 0",
            "Siegfried, The Nibelungenlied (1924) Popularity: 79",
            "Kriemhild's Revenge, The Nibelungenlied (1924) Popularity: 74",
            "Gosta Berling's Saga (1924) Popularity: 63",
            "Joyless Street (1925) Popularity: 73"};
        List<MovieInterface> movies = bd.getMoviesByYearRange(1920, 1925);
        assertEquals(movies.size(), expected.length);
        for(int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], movies.get(i).toString());
        }

        assertEquals(new ArrayList<MovieInterface>(), bd.getMoviesByYearRange(2050, 3000));
        assertEquals(new ArrayList<MovieInterface>(), bd.getMoviesByYearRange(-10, 10));
        assertEquals(new ArrayList<MovieInterface>(), bd.getMoviesByYearRange(10, 0));
    }
}
