import java.util.List;

public interface MovieSearchAppInterface {

	//runCommandLoop will run the mainMenuPrompt and use it to execute searches
	public void runCommandLoop();

	// prompt for main menu
	public char mainMenuPrompt();

	// prompt to load data, calls the backend loadData() method
	public void loadData();

	// search by title, calls the backend getMoviesByTitle() method
	public void searchTitleCommand(List<String> words);

	//search by year, calls the backend getMoviesByYear() method
	public void searchYearCommand(List<String> year);

	// search by popularity, calls the backend getMoviesByPopularity() method
	public void searchPopularityCommand(List<String> words);

	// search by year range, calls the backend getMoviesByYearRange() method
	public void searchYearRange(List<String> rangeMin, List<String>rangeMax);

// search by popularity range, calls the backend getMoviesByPopularityRange() method
	public void searchPopularityRange(List<String>rangeMin, List<String>rangeMax);
}
