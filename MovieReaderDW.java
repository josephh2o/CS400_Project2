import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.lang.String;
import java.lang.Character;



/**
 * This class is used to create Movie objects using data from
 * a.csv file
 * @author Cameron
 */
public class MovieReaderDW implements MovieReaderInterface {  
  
  /**
   * Reads movie data from a csv file given by filename.
   * csv format is as follows:
   * "[year]","[runtime]","[title]","[category]","[leadActor]","[leadActress]",
   * "[director]","[popularity]","[wonAwards]","[IMAGE -- note: not used]"
   * 
   * the csv file has two header files that will be skipped when reading data
   * 
   * @return a list of Movie objects that are specified by the csv file
   */
  @Override
  public List<MovieInterface> readMovieData(String filename) throws FileNotFoundException {
    Scanner csv = new Scanner(new File(filename)); // open file; throws exception if not found
    List<MovieInterface> movieList = new ArrayList<MovieInterface>();

    if (!csv.hasNextLine()) {
      // blank file =>
      csv.close();
      return movieList;
    }
    
    // skip two lines for header
    csv.nextLine();
    
    if (!csv.hasNextLine()) {
      // blank file =>
      csv.close();
      return movieList;
    }
    csv.nextLine();
    
    // add movies to list
    movieList = addMovies(csv);
    
    
    // close file
    csv.close();   
    
    return movieList;
  }


  /**
   * Helper method to read a .csv file containing movie data into
   * a list of Movie objects.
   * @param csv - a Scanner object to read the .csv file
   * @return a list of newly-created MovieInterfaces
   */
    private List<MovieInterface> addMovies(Scanner csv) {
	// create list for movies
	List<MovieInterface> movieList = new ArrayList<MovieInterface>();

	List<String> params = null; // stores fields for each movie
	String line;		    // stores each line read in file

	// go through each line
	while (csv.hasNextLine()) {
		line = csv.nextLine();
		if (line.isBlank()) continue; // skip blank lines
		
		// parse out params
		params = getParams(line);
		
		// create new movie object using params, add to list
		movieList.add( createMovie(params) );

		// goto next line
	}

	// return movie list
	return movieList;

    }

	/**
	 * Helper method that parses out the fields
	 * of a Movie object from a single .csv line
	 * @param line - a single line of a .csv file containing
	 * 		the data for a Movie object
	 * @return a list of String representations of the Movie object's
	 * 		data fields
	 */
	private List<String> getParams(String line) {

		// create list for params
		List<String> params = new ArrayList<String>();
	
		
		// parse through each character of line, 
		while ( line.length() > 0  ) {
			// add params until line is done
			line = getParam(line, params);	
		}
		// return list of params
		return params;
	}

	/**
	 * Helper method to parse out a single data field for a Movie
	 * object from a line in a .csv file.
	 * @param line - a line in a .csv file containing data for a Movie object
	 * @param params - a list to store the paramters in; will be modified
	 * 			to hold the newly-parsed data field
	 * @return the same line from the parameters, but without the characters
	 * 			processed for the data field
	 */
	private String getParam(String line, List<String> params) {
		String param = ""; // param to add to list
		char current; // char being processed
		int quoteCount = 0; // counts the number of quote marks processed

		int i=0; // start at first character

		while ( i < line.length()  ) {
			// get char
			current = line.charAt(i);
			
			// if line is blank, return ""
			if (i == 0 && current == '\0') return "";
		
			// if reached end of param, break
			if ( (current == ',' && quoteCount % 2 == 0) ) {
				// remove last quote mark
				param = param.substring(0, param.length() - 1);
				// adjust i
				i++;
				break;
		      }
		      
		      // if char is a quote, increment counter
		      if (current == '\"') {
			quoteCount++; 
			
			// don't add first quote to string
			if (quoteCount == 1 || i == (line.length() - 1)  ) {
			       i++;
		       	       continue;
			}
		      }
		
			// add char to string
		      param += Character.toString(  current  );
			// update i
			i++;
		}

		// fix quote marks and add to parameter list
		param = param.replaceAll( "\"\"", "\"");	
		params.add(param);
		
		// return line without processed chars
		if (i == line.length()) return "";
		return line.substring(i);
	}


	/**
	 * Takes in a list of data fields and creates a movie object with them.
	 * @param paramList - a list containing String representations of a Movie
	 * 			object's data fields. Parameters are as follows:
	 * 			- index 0: release year
	 * 			- index 1: runtime (minutes)
	 * 			- index 2: title
	 * 			- index 3: genre
	 * 			- index 4: lead actor's name
	 * 			- index 5: lead actress's name
	 * 			- index 6: director's name
	 * 			- index 7: popularity of movie within [0, 100]
	 * 			- index 8: whether the movie has won awards ("Yes"/"No")
	 * 			- index 9: an image somehow associated with the movie (unused)
	 * @return the newly-created Movie object
	 */
	private MovieInterface createMovie( List<String> paramList ) {
		if (paramList.size() != 10 ) {
			System.out.println("error creating movie");
			return null;
		}

		int year = Integer.parseInt( paramList.get(0).isBlank() ? "0" : paramList.get(0));
		int runtime = Integer.parseInt( paramList.get(1).isBlank() ? "0" : paramList.get(1));
      		String title = paramList.get(2);
      		String category = paramList.get(3);
		String leadActor = paramList.get(4);
		String leadActress = paramList.get(5);
		String director = paramList.get(6);
		int popularity = Integer.parseInt( paramList.get(7).isBlank() ? "0" : paramList.get(7));
		boolean wonAwards = paramList.get(8).equals("Yes");

		return new MovieDW( year, title, runtime, category, leadActor, leadActress, 
				director, popularity, wonAwards);
	}
  
}
