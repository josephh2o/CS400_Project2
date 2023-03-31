



/**
 * This class implements a MovieInterface which contains
 * data related to a movie, including: title, release year, 
 * runtime (in minutes), category, lead actor, lead actress, director,
 * popularity (from 0 to 100), and if it has won any awards
 * @author Cameron
 */
public class Movie implements MovieInterface {
  private int year;
  private String title;
  private int runtime;
  private String category;
  private String leadActor;
  private String leadActress;
  private String director;
  private int popularity;
  private boolean wonAwards;
  
 /**
  * default constructor; initializes fields to 0-like
  */ 
  public Movie() {
    this(0, "", 0, "", "", "", "", 0, false);
  }
  
  /**
   * 
   * @param year	- year the movie was released
   * @param title	- title of movie
   * @param runtime	- running time in minutes
   * @param category	- movie's genre
   * @param leadActor	- movie's lead actor
   * @param leadActress	- movie's lead actress
   * @param director	- director of the movie
   * @param popularity	- int from 0 to 100 describing how popular the movie is
   * @param wonAwards	- true if the movie has won awards; false if not
   */
  public Movie(int year, String title, int runtime, String category, String leadActor, 
      String leadActress, String director, int popularity, boolean wonAwards) {
    
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
  
  // getters
  
  /**
   * getter for the movie's relase year
   * @return the year the movie was released
   */
  @Override
  public int getYear() { return year; }
  
  
  /**
   * getter for the movie's title
   * @return the title of the movie
   */
  @Override
  public String getTitle() { return title; }
  
  
  /**
   * getter for the movie's runtime
   * @return the runtime of the movie in minutes
   */
  @Override
  public int getRuntime() { return runtime; }
  
  
  /**
   * getter for the movie's category
   * @return the category (genre) of the movie
   */
  @Override
  public String getCategory() { return category; }
  
  
  /**
   * getter for the movie's leadActor
   * @return the movie's lead actor in the form "[lastname], [firstname]"
   */
  @Override
  public String getLeadActor() { return leadActor; }
  
  
  /**
   * getter for the movie's leadActress
   * @return the movie's lead actress in the form "[lastname], [firstname]"
   */
  @Override
  public String getLeadActress() { return leadActress; }
  
  
  /**
   * getter for the movie's director
   * @return the movie's director in the form "[lastname], [firstname]"
   */
  @Override
  public String getDirector() { return director; }
  
  
  /**
   * getter for the movie's popularity
   * @return an integer describing the movie's popularity on a scale 
   *    from 0 (least popular) to 100 (most popular)
   */
  @Override
  public int getPopularity() { return popularity; }
  
  
  /**
   * gets whether the movie has won awards or not
   * @return true if the movie has won awards; else false
   */
  @Override
  public boolean wonAwards() { return wonAwards; }
  
}
