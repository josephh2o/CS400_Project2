
public class MovieBD implements MovieInterface {
    
    int year;
    String title;
    int pop;
    
    public MovieBD(int year, String title, int runtime, String category, String leadActor, String leadActress,
                   String director, int popularity, boolean wonAwards) {
        this.year = year;
        this.title = title;
        this.pop = popularity;
    }
    
    @Override
    public int getYear() {
        return year;
    }
    
    @Override
    public String getTitle() {
        return title;
    }
    
    @Override
    public String toString() {
        return "{" + title + ", " + year + ", " + pop + "}";
    }
    
    @Override
    public int getRuntime() {
        return 0;
    }
    
    @Override
    public String getCategory() {
        return null;
    }
    
    @Override
    public String getLeadActor() {
        return null;
    }
    
    @Override
    public String getLeadActress() {
        return null;
    }
    
    @Override
    public String getDirector() {
        return null;
    }
    
    @Override
    public int getPopularity() {
        return pop;
    }
    
    @Override
    public boolean wonAwards() {
        return false;
    }
}
