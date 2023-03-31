package unused;

public class MovieAE implements MovieInterface {
    int year;
    String title;
    int popularity;
    public MovieAE(int year, String title, int runtime, String category, String
            leadActor, String leadActress, String director, int popularity,
                   boolean wonAwards) {}

    // Not going to be in the final implementation just assigning what I need
    // need for tests
    public MovieAE(int year, String title, int popularity) {
        this.year = year;
        this.title = title;
        this.popularity = popularity;
    }

    public int getYear() {
        return year;
    }

    public String getTitle() {
        return title;
    }
    public int getRuntime() {
        return 0;
    }
    public String getCategory(){
        return "";
    }
    public String getLeadActor(){
        return "";
    }
    public String getLeadActress(){
        return "";
    }
    public String getDirector(){
        return "";
    }
    public int getPopularity(){
        return popularity;
    }
    public boolean wonAwards(){
        return false;
    }

    public int compareToYear(MovieAE movie2){
        return 1;
    }
    public int compareToTitle(MovieAE movie2){
        return 1;
    }
    public int compareToPopularity(MovieAE movie2){
        return 1;
    }

    public String toString() {
        return Integer.toString(year);
    }

}

