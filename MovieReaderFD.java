import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class MovieReaderFD implements MovieReaderInterface {

    @Override
    public List<MovieInterface> readMovieData(String filename) throws FileNotFoundException {
        List<MovieInterface> myMovies = new ArrayList<>();
        myMovies.add(new MovieFD(2012, "Skyfall", 143, "Action", "Daniel Craig",
                "Naomie Harris", "Sam Mendes", 86, true));
        myMovies.add(new MovieFD(2015, "Spectre", 148, "Action", "Daniel Craig",
                "Naomie Harris", "Sam Mendes", 65, true));
        myMovies.add(new MovieFD(2019, "No Time to Die", 163, "Action", "Daniel Craig",
                "Naomie Harris", "Cary Joji Fukunaga", 84, true));
        return myMovies;
    }
}