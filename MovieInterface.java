/**
 * This interface provides a way to access data
 * related to a movie, including: title, release year, 
 * runtime (in minutes), category, lead actor, lead actress, director,
 * popularity (from 0 to 100), and if it has won any awards
 * @author Cameron
 */
public interface MovieInterface {
    // public Movie(int year, String title, int runtime, String category, String leadActor, String leadActress, String director, int popularity, boolean wonAwards)


	// accessors
    public int getYear();
    public String getTitle();
    public int getRuntime(); // in minutes
    public String getCategory();
    public String getLeadActor();
    public String getLeadActress();
    public String getDirector();
    public int getPopularity();
    public boolean wonAwards();

}
