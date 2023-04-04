import java.util.List;

public interface BackendInterface {
    boolean loadData(String filename);
    List<MovieInterface> getMoviesByTitle(String title);
    List<MovieInterface> getMoviesByPopularity(int popularity);
    List<MovieInterface> getMoviesByYear(int year);
    //List<List<MovieInterface>> compareMoviesByYear(int year1, int year2);
    List<MovieInterface> getMoviesByYearRange(int year1, int year2);
    
    public List<MovieInterface> getMoviesByPopularityRange(int year1, int year2);
    
    
}
