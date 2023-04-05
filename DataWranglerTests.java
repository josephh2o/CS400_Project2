import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;


/**
 * Tester class for the MovieDW and MovieReaderDW
 * classes. Uses junit5 tests.
 * @author Cameron
 */
public class DataWranglerTests {
   

	/**
	 * Tests the MovieDW class on its own (without reader class).
	 * Makes sure no errors are found when constructing/accessing data.
	 */
	@Test
	public void test1() {

		MovieInterface movie = new MovieDW(2001, "Movie Title", 178, "Drama",
			       	"Lead actor", "Lead actress", "Director",
				90,true );


		// make sure year is correct
		assertEquals(2001, movie.getYear());

		// make sure runtime is correct
		assertEquals(178, movie.getRuntime());

		// make sure movie's title is correct
		assertEquals("Movie Title", movie.getTitle());
	
		// make sure category is correct
		assertEquals("Drama", movie.getCategory());
	
		// make sure leadActor is correct
		assertEquals("Lead actor", movie.getLeadActor());


		// make sure leadActress is correct
		assertEquals("Lead actress", movie.getLeadActress());


		// make sure director is correct
		assertEquals("Director", movie.getDirector());

		// make sure popularity is correct
		assertEquals(90, movie.getPopularity());

		// make sure wonAwards is correct
		assertEquals(true, movie.wonAwards());
	}


	/**
	 * Tests the MovieDW class (getters) while processing one line of a csv file
	 * using the MovieReaderDW. Uses standard (i.e. likely not error-prone) data.
	 */
	@Test
	public void test2() {
		MovieReaderInterface reader = new MovieReaderDW();

		List<MovieInterface> list = null; 
		try {
			list = reader.readMovieData("data/dummy.csv");
		} catch (FileNotFoundException e) {
			System.out.println("FileNotFoundException thrown");
		}

		// make sure list is not empty
		assertEquals(false, list.isEmpty());

		// amke sure list has exactly one item
		assertEquals(1, list.size());

		// make sure movie's year is 2001
		assertEquals(2001, list.get(0).getYear());


		// make sure movie's title is "title"
		assertEquals("title", list.get(0).getTitle());
	}

	

	/**
	 * Tests the MovieReaderDW class with data that contains blank entries,
	 * ensuring the reader handles those cases correctly.
	 */
	@Test
	public void test3() {
		MovieReaderInterface reader = new MovieReaderDW();

		List<MovieInterface> list = null; 
		try {
			list = reader.readMovieData("data/data_with_blanks.csv");
		} catch (FileNotFoundException e) {
			System.out.println("FileNotFoundException thrown");
		}

		// make sure list has exactly three items
		assertEquals(3, list.size());


		// make sure movie 0's title is ""
		assertEquals("", list.get(0).getTitle());
	
		// make sure movie 1's runtime is 0
		assertEquals(0, list.get(1).getRuntime());
	
		// make sure movie 2's popularity is 0
		assertEquals(0, list.get(2).getPopularity());

	}



	/**
	 * Tests the MovieReaderDW class with data that contains commas and quote marks in string data,
	 * making sure that commas and quotation marks do not cause breaks within data fields.
	 */
	@Test
	public void test4() {
		MovieReaderInterface reader = new MovieReaderDW();

		List<MovieInterface> list = null; 
		try {
			list = reader.readMovieData("data/commas_and_quotes.csv");
		} catch (FileNotFoundException e) {
			System.out.println("FileNotFoundException thrown");
		}

		// make sure list contains exactly five items
		assertEquals(5, list.size());


		// make sure movie 0's title is "The "Quotes" in the Middle"
		assertEquals("The \"Quotes\" in the Middle", list.get(0).getTitle());

		// make sure movie 1's lead actor is "lastname, firstname"
		assertEquals("lastname, firstname", list.get(1).getLeadActor());

		// make sure movie 2's lead actress is "lastname, firstname "nickname""
		assertEquals("lastname, firstname \"nickname\"", list.get(2).getLeadActress());

		// make sure movie 3's title is "commas "in," quotes"
		assertEquals("commas \"in,\" quotes", list.get(3).getTitle());

		// make sure movie 4's title is """quotes on ends""
		assertEquals("\"\"quotes on ends\"", list.get(4).getTitle());


	}


	/**
	 * Tests the MovieReaderDW on a significantly larger dataset (>1500 lines).
	 * Ensures the reader processes the larger file correctly.
	 */
	@Test
	public void test5() {
		MovieReaderInterface reader = new MovieReaderDW();

		List<MovieInterface> list = null; 
		try {
			list = reader.readMovieData("data/GOOD_DATA.csv");
		} catch (FileNotFoundException e) {
			System.out.println("FileNotFoundException thrown");
		}

		// make sure list contains exactly 1659 items
		assertEquals(1659, list.size());


		// -------------------- check first movie -------------------------------------
		// make sure movie 0's title is 
		assertEquals("Tie Me Up! Tie Me Down!", list.get(0).getTitle());

		// make sure movie 1's lead actor is 
		assertEquals("Banderas, Antonio", list.get(0).getLeadActor());

		// make sure movie 0's director is 
		assertEquals("Almodóvar, Pedro", list.get(0).getDirector());

		// make sure movie 0 did not win awards
		assertEquals(false, list.get(0).wonAwards());

		// ---------------------- check another movie further down--------------------

		// make sure movie 1269's runtime is 0
		assertEquals(0, list.get(1269).getRuntime());

		// make sure movie 1269's title is "Mr. & Mrs. Bridge"
		assertEquals("Mr. & Mrs. Bridge", list.get(1269).getTitle());

		// make sure movie 1269's lead actress is "Woodward, Joanne" 
		assertEquals("Woodward, Joanne", list.get(1269).getLeadActress());

		// make sure movie 1269's director is ""
		assertEquals("", list.get(1269).getDirector());

		// make sure movie 1269 did not win awards
		assertEquals(false, list.get(1269).wonAwards());

		}
	
	// -----------------------Code review tests----------------------------------------------
	
	
	/**
	 * This tests out the searchTitleCommand() method from MovieSearchApp (frontend)
	 * We test with:
	 *  1. Results not found
	 *  2. Results found
	 *  and ensure the output matches expected
	 */
	@Test
	public void CodeReviewOfFDTest1() {
	  // redirect system out
	  final ByteArrayOutputStream newOut = new ByteArrayOutputStream();
      final PrintStream consoleOut = System.out;
      System.setOut(new PrintStream(newOut));
      
      // set up frontend
	  MovieSearchAppInterface fntend = new MovieSearchApp(new Scanner(System.in), new BackendDW());
	  ArrayList<String> titles = new ArrayList<String>();
	 
	  // 1. test w/ no results
	  titles.add("no match");
	  fntend.searchTitleCommand(titles);
	  assertEquals("No movies found with the title no match.",newOut.toString().trim());
	  if (titles.size() != 0) titles.remove(0);
	  
	  
	  // 2. test w/ results found
	  newOut.reset();
	  titles.add("match");
	  fntend.searchTitleCommand(titles);
	  assertEquals("Found 1 movie(s) with the title match:\r\nmatch (0) Popularity: 0",newOut.toString().trim());
	  
	  
	  // other note: it seems the runCommandLoop() doesn't allow for multiple titles,
      // but searchTitleCommand() takes into account multiple titles. These should agree with
	  // one another.
	  
	  // reset system out
	  System.setOut(consoleOut);
	}
	
	
	/**
	 * Tests the searchPopularityRange() method, ensuring that each test case's output matches
	 * the expected behavior:
	 * 1. invalid input (non-numeric)
	 * 2. valid input, movies found in range
	 * 3. valid input, no movies found in range
	 */
	@Test
    public void CodeReviewOfFDTest2() {
	  // redirect system out
	  final ByteArrayOutputStream newOut = new ByteArrayOutputStream();
      final PrintStream consoleOut = System.out;
      System.setOut(new PrintStream(newOut));
      
      // set up frontend
	  List<String> min = new ArrayList<String>();
	  min.add("0dsfdds");
	  List<String> max = new ArrayList<String>();
	  max.add("100");
	  MovieSearchAppInterface fntend = new MovieSearchApp(new Scanner(System.in), new BackendDW());
	  
	  // ---------------------------1. invalid input-------------------------------------------
	  fntend.searchPopularityRange(min, max);
	  assertEquals("Invalid popularity. Please try again.",newOut.toString().trim());
	  
	  
	  newOut.reset();
      if (min.size() != 0) min.remove(0);
      if (max.size() != 0) max.remove(0);
	  // ----------------------------2. valid input, within range----------------------------------
	  min.add("30");
	  max.add("70");
	  fntend.searchPopularityRange(min, max);
	  
	  
	  assertEquals("Found 1 movie(s) with the popularity 30 to 70:\r\n" +
	              "match (0) Popularity: 50",newOut.toString().trim());
	  
	  
	  
	  newOut.reset();
      if (min.size() != 0) min.remove(0);
      if (max.size() != 0) max.remove(0);
	  // ------------------------------3. valid input, not within range------------------------
      min.add("10");
      max.add("20");
      fntend.searchPopularityRange(min, max);      
      assertEquals("No movies found with the popularity 10 to 20.",newOut.toString().trim());
      
	  
	 // reset system out
	  System.setOut(consoleOut);
    }
	
	// --------------------------------Integration tests-----------------------------------------
	
	@Test
	public void integrationTest1() {
	  
	  
	  
	 // System.setOut(consoleOut);
	}

	@Test
    public void integrationTest2() {
      
      
    }


	
	
	class BackendDW implements BackendInterface {

    @Override
    public boolean loadData(String filename) {
      // TODO Auto-generated method stub
      return false;
    }

    @Override
    public List<MovieInterface> getMoviesByTitle(String title) {
      ArrayList<MovieInterface> list = new ArrayList<MovieInterface>();
      if (title.equals("no match")) return list;
      list.add(new MovieDW(0, "match", 0, "","","","",0, false));      
      return list;
    }

    @Override
    public List<MovieInterface> getMoviesByPopularity(int popularity) {
      
      return null;
    }

    @Override
    public List<MovieInterface> getMoviesByYear(int year) {
      // TODO Auto-generated method stub
      return null;
    }

    

    @Override
    public List<MovieInterface> getMoviesByYearRange(int year1, int year2) {
      // TODO Auto-generated method stub
      return null;
    }

    @Override
    public List<MovieInterface> getMoviesByPopularityRange(int year1, int year2) {
      ArrayList<MovieInterface> list = new ArrayList<MovieInterface>();
      int pop = 50;
      list.add(new MovieDW(0, "match", 0, "","","","",pop, false));
      if (year1 <= pop && year2 >= pop) return list;
      return new ArrayList<MovieInterface>();
    }
	  
	}
  
}
