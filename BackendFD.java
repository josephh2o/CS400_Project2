import java.util.ArrayList;
import java.util.List;

public class BackendFD implements BackendInterface{
    @Override
    public List<MovieInterface> getMoviesByTitle(String title) {
        List<MovieInterface> movies = new ArrayList<>();
        movies.add(new MovieFD(1111, "aaaa", 111, "aaaa", "aaaa",
                "aaaa", "aaaa", 111, true));
        return movies;
    }

    @Override
    public List<MovieInterface> getMoviesByPopularity(int popularity) {
        List<MovieInterface> movies = new ArrayList<>();
        movies.add(new MovieFD(2222, "bbbb", 222, "bbbb", "bbbb",
                "bbbb", "bbbb", 222, true));
        movies.add(new MovieFD(3333, "cccc", 333, "cccc", "cccc",
                "cccc", "cccc", 333, true));
        return movies;
    }

    @Override
    public List<MovieInterface> getMoviesByYear(int year) {
        List<MovieInterface> movies = new ArrayList<>();
        movies.add(new MovieFD(4444, "dddd", 444, "dddd", "dddd",
                "dddd", "dddd", 444, true));
        movies.add(new MovieFD(5555, "eeee", 555, "eeee", "eeee",
                "eeee", "eeee", 555, true));
        return movies;
    }

    @Override
    public List<List<MovieInterface>> compareMoviesByYear(int year1, int year2) {
        return null;
    }

    @Override
    public List<MovieInterface> getMoviesByYearRange(int year1, int year2) {
        List<MovieInterface> movies = new ArrayList<>();
        movies.add(new MovieFD(6666, "ffff", 666, "ffff", "ffff",
                "ffff", "ffff", 666, true));
        movies.add(new MovieFD(7777, "gggg", 777, "gggg", "gggg",
                "gggg", "gggg", 777, true));
        return movies;
    }

    @Override
    public List<MovieInterface> getMoviesByPopularityRange(int year1, int year2) {
        List<MovieInterface> movies = new ArrayList<>();
        movies.add(new MovieFD(8888, "hhhh", 888, "hhhh", "hhhh",
                "hhhh", "hhhh", 888, true));
        movies.add(new MovieFD(9999, "iiii", 999, "iiii", "iiii",
                "iiii", "iiii", 999, true));
        return movies;
    }

    @Override
    public boolean loadData(List<MovieInterface> movies) {
        return false;
    }
}
