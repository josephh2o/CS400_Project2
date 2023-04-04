public class MovieFD implements MovieInterface {

    private int year;
    private String title;
    private int runtime;
    private String category;
    private String leadActor;
    private String leadActress;
    private String director;
    private int popularity;
    private boolean wonAwards;

    public MovieFD(int year, String title, int runtime, String category, String leadActor, String leadActress, String director, int popularity, boolean wonAwards) {
        this.year = year;
        this.title = title;
        this.runtime = runtime;
        this.category = category;
        this.leadActor = leadActor;
        this.leadActress = leadActress;
        this.director = director;
        this.popularity = popularity;
        this.wonAwards = wonAwards;
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
    public int getRuntime() {
        return runtime;
    }

    @Override
    public String getCategory() {
        return category;
    }

    @Override
    public String getLeadActor() {
        return leadActor;
    }

    @Override
    public String getLeadActress() {
        return leadActress;
    }

    @Override
    public String getDirector() {
        return director;
    }

    @Override
    public int getPopularity() {
        return popularity;
    }

    @Override
    public boolean wonAwards() {
        return wonAwards;
    }
}
