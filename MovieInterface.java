import unused.MovieAE; // Change to Movie.java

public interface MovieInterface {
    // public Movie(int year, String title, int runtime, String category, String leadActor, String leadActress, String director, int popularity, boolean wonAwards)

    public int getYear();
    public String getTitle();
    public int getRuntime(); // in minutes
    public String getCategory();
    public String getLeadActor();
    public String getLeadActress();
    public String getDirector();
    public int getPopularity();
    public boolean wonAwards();

    public int compareToYear(MovieAE movie2);
    public int compareToTitle(MovieAE movie2);
    public int compareToPopularity(MovieAE movie2);
}
