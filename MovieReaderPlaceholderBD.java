import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class MovieReaderPlaceholderBD implements MovieReaderInterface {
    
    public List<MovieInterface> readMovieData(String filename) throws FileNotFoundException {
        
        ArrayList<MovieInterface> movies = new ArrayList<>();
        movies.add(new MovieBD(1920, "Bad Old Movie", 0, "", "", "",
            "", 0, false));
        movies.add(new MovieBD(1920, "Good Old Movie", 0, "", "", "",
            "", 100, false));
        movies.add(new MovieBD(1997, "Good New Movie", 0, "", "", "",
            "", 100, false));
        return movies;
    }
    
}
