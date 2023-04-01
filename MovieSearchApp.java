import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This class provides a text-based user interface for the MovieSearchApp.
 */
public class MovieSearchApp implements MovieSearchAppInterface {

    private Scanner input; // For user input
    private BackendInterface backend; // For accessing the backend
    private boolean loadedData = false;

    /**
     * Constructor for the MovieSearchApp class.
     * @param input Scanner for user input
     * @param backend BackendInterface for accessing the backend
     */
    public MovieSearchApp (Scanner input, BackendInterface backend) {
        this.input = input;
        this.backend = backend;
    }

    /**
     * This method prompts the user for a command and returns the command.
     */
    @Override
    public void runCommandLoop() {
        System.out.println("Welcome to the Movie Search App.");

        char command = '\0';
        while (command != 'Q') {
            command = this.mainMenuPrompt();
            switch (command) {
                case 'L' -> {
                    loadData();
                }
                case 'T' -> {
                    if (!loadedData) { // if data has not been loaded
                        System.out.println("Please load data first.");
                        break;
                    }
                    System.out.println("Enter a keyword(s) to search by: ");
                    String titleInput = input.nextLine();
                    List<String> title = new ArrayList<>();
                    title.add(titleInput);
                    searchTitleCommand(title);
                }
                case 'Y' -> {
                    if (!loadedData) { // if data has not been loaded
                        System.out.println("Please load data first.");
                        break;
                    }
                    System.out.println("Enter a year to search by: ");
                    System.out.println("(To search for multiple years, " +
                            "please include a \",\" separating the years.)");
                    System.out.println("(To search by a range of years, " +
                            "please include a \"-\" separating the years.)");
                    String yearInput = input.nextLine().trim();
                    if (yearInput.contains("-")) { // Search by single year
                        String[] yearRange = yearInput.split("-");
                        List<String> minYear = new ArrayList<>();
                        minYear.add(yearRange[0].trim());
                        List<String> maxYear = new ArrayList<>();
                        maxYear.add(yearRange[1]);
                        searchYearRange(minYear, maxYear);
                    }
                    else if (yearInput.contains(",")) { // Search by multiple years
                        String[] yearList = yearInput.split(",");
                        for (String year : yearList) {
                            List<String> years = new ArrayList<>();
                            years.add(year.trim());
                            searchYearCommand(years);
                        }
                    }
                    else { // Search by range of years
                        List<String> year = new ArrayList<>();
                        year.add(yearInput);
                        searchYearCommand(year);
                    }
                }
                case 'P' -> {
                    if (!loadedData) { // if data has not been loaded
                        System.out.println("Please load data first.");
                        break;
                    }
                    System.out.println("Enter a popularity to search for: ");
                    System.out.println("(To search for multiple popularities, " +
                            "please include a \",\" separating the popularities.)");
                    System.out.println("(To search for a range of popularities, " +
                            "please include a \"-\" separating the popularities.)");
                    String popularityInput = input.nextLine().trim();
                    if (popularityInput.contains("-")) { // Search by single popularity
                        String[] popularityRange = popularityInput.split("-");
                        List<String> minPopularity = new ArrayList<>();
                        minPopularity.add(popularityRange[0]);
                        List<String> maxPopularity = new ArrayList<>();
                        maxPopularity.add(popularityRange[1]);
                        searchPopularityRange(minPopularity, maxPopularity);
                    }
                    else if (popularityInput.contains(",")) { // Search by multiple popularities
                        String[] popularityList = popularityInput.split(",");
                        for (String popularity : popularityList) {
                            List<String> popularityA = new ArrayList<>();
                            popularityA.add(popularity.trim());
                            searchPopularityCommand(popularityA);
                        }
                    }
                    else { // Search by range of popularities
                        List<String> popularity = new ArrayList<>();
                        popularity.add(popularityInput);
                        searchPopularityCommand(popularity);
                    }
                }
                case 'Q' -> System.out.println("Goodbye!");
                default -> System.out.println("Invalid command. Please try again.");
            }
        }
        System.out.println("Thank you for using the Movie Search App & enjoy the movie!");
    }

    /**
     * This method prompts the user for a command and returns the command.
     * @return The command entered by the user
     */
    @Override
    public char mainMenuPrompt() {
        System.out.println("--------------------");
        System.out.println("Command List:");
        System.out.println("    [L]oad data");
        System.out.println("    [T]itle search");
        System.out.println("    [P]opularity search");
        System.out.println("    [Y]ear search");
        System.out.println("    [Q]uit");
        System.out.println("--------------------");

        System.out.println("Enter a command: ");
        String command = input.nextLine().trim().toUpperCase();
        if (command.length() != 1) {
            return '\0';
        }
        return Character.toUpperCase(command.charAt(0));
    }

    /**
     * This method loads data from a file.
     */
    @Override
    public void loadData() {
        System.out.println("Enter the name of the file to load: ");
        String filename = input.nextLine().trim();
        if (backend.loadData(filename)) {
            System.out.println("Data loaded successfully.");
            loadedData = true;
        }
        else {
            System.out.println("Error loading file.");
        }
    }

    /**
     * This method searches for movies by title.
     * @param title The title to search by
     */
    @Override
    public void searchTitleCommand(List<String> title) {
        backend.getMoviesByTitle(title.get(0));
        while (title.size() > 0) {
            List<MovieInterface> movies = backend.getMoviesByTitle(title.get(0));
            if (movies.size() == 0) { // No movies found
                System.out.println("No movies found with the keyword " + title.get(0) + ".");
            } else { // Movies found
                System.out.println("Movies found with the keyword " + title.get(0) + ":");
                for (MovieInterface movie : movies) {
                    System.out.println(movie.getTitle() + " (" + movie.getYear() + ")" + " Popularity: " + movie.getPopularity());
                }
            }
            title.remove(0);
        }
    }

    /**
     * This method searches for movies by year.
     * @param year The year to search by
     */
    @Override
    public void searchYearCommand(List<String> year) {
        try {
            int moviesYear = Integer.parseInt(year.get(0).trim());
            List<MovieInterface> movies = backend.getMoviesByYear(moviesYear);
            if (movies.size() == 0) { // No movies found
                System.out.println("No movies found in " + moviesYear + ".");
            } else { // Movies found
                System.out.println("Movies found in " + moviesYear + ":");
                for (MovieInterface movie : movies) {
                    System.out.println(movie.getTitle() + " (" + movie.getYear() + ")" + " Popularity: " + movie.getPopularity());
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid year. Please try again.");
        }
    }

    /**
     * This method searches for movies by popularity.
     * @param popularity The popularity to search by
     */
    @Override
    public void searchPopularityCommand(List<String> popularity) {
        try {
            int moviesPopularity = Integer.parseInt(popularity.get(0).trim());
            List<MovieInterface> movies = backend.getMoviesByPopularity(moviesPopularity);
            if (movies.size() == 0) { // No movies found
                System.out.println("No movies found with the popularity " + moviesPopularity + ".");
            } else { // Movies found
                System.out.println("Movies found with the popularity " + moviesPopularity + ":");
                for (MovieInterface movie : movies) {
                    System.out.println(movie.getTitle() + " (" + movie.getYear() + ")" + " Popularity: " + movie.getPopularity());
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid popularity. Please try again.");
        }
    }

    /**
     * This method searches for movies by a range of popularity.
     * @param rangeMin The minimum popularity to search by
     * @param rangeMax The maximum popularity to search by
     */
    @Override
    public void searchYearRange(List<String> rangeMin, List<String> rangeMax) {
        try {
            int moviesYearMin = Integer.parseInt(rangeMin.get(0).trim());
            int moviesYearMax = Integer.parseInt(rangeMax.get(0).trim());
            List<MovieInterface> movies = backend.getMoviesByYearRange(moviesYearMin,
                    moviesYearMax);
            if (movies.size() == 0) { // No movies found
                System.out.println("No movies found in " + moviesYearMin + " to "
                        + moviesYearMax + ".");
            } else { // Movies found
                System.out.println("Movies found in " + moviesYearMin + " to "
                        + moviesYearMax + ":");
                for (MovieInterface movie : movies) {
                    System.out.println(movie.getTitle() + " (" + movie.getYear() + ")" + " Popularity: " + movie.getPopularity());
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid year. Please try again.");
        }
    }

    /**
     * This method searches for movies by a range of popularity.
     * @param rangeMin The minimum popularity to search by
     * @param rangeMax The maximum popularity to search by
     */
    @Override
    public void searchPopularityRange(List<String> rangeMin, List<String> rangeMax) {
        try {
            int moviesPopularityMin = Integer.parseInt(rangeMin.get(0).trim());
            int moviesPopularityMax = Integer.parseInt(rangeMax.get(0).trim());
            List<MovieInterface> movies = backend.getMoviesByPopularityRange(moviesPopularityMin,
                    moviesPopularityMax);
            if (movies.size() == 0) { // No movies found
                System.out.println("No movies found with the popularity " + moviesPopularityMin
                        + " to " + moviesPopularityMax + ".");
            } else { // Movies found
                System.out.println("Movies found with the popularity " + moviesPopularityMin
                        + " to " + moviesPopularityMax + ":");
                for (MovieInterface movie : movies) {
                    System.out.println(movie.getTitle() + " (" + movie.getYear() + ")" + " Popularity: " + movie.getPopularity());
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid popularity. Please try again.");
        }
    }
}