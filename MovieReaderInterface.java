import java.util.List;
import java.io.FileNotFoundException;


/**
 * This interface decsribes a way to read 
 * movie data (in csv form) into new Movie
 * objects.
 * @author Cameron
 */
public interface MovieReaderInterface {
    // public MovieReaderInterface();



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
    public List<MovieInterface> readMovieData(String filename) throws FileNotFoundException; 
}
