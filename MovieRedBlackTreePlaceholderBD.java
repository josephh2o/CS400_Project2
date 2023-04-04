import java.util.ArrayList;

public class MovieRedBlackTreePlaceholderBD<T> implements MovieRedBlackTreeInterface {

    public ArrayList<RBTList<Integer>> moviesByYear;
    public ArrayList<RBTList<String>> moviesByTitle;
    public ArrayList<RBTList<Integer>> moviesByPopularity;
    
    // IN USE
    
    public MovieRedBlackTreePlaceholderBD() {
        moviesByYear = new ArrayList<>();
        moviesByTitle = new ArrayList<>();
        moviesByPopularity = new ArrayList<>();
    }
    
    @Override
    public boolean insertByYear(RBTList<Integer> movies) {
        moviesByYear.add(movies);
        return true;
    }
    
    @Override
    public boolean insertByTitle(RBTList<String> movies) {
        moviesByTitle.add(movies);
        return true;
    }
    
    @Override
    public boolean insertByPopularity(RBTList<Integer> movies) {
        moviesByPopularity.add(movies);
        return true;
    }
    
    @Override
    public RBTList<Integer> getRangeData(int min, int max, boolean mode) {
        if (mode) { // year, return random thing
            return moviesByYear.get(0);
        }
        else { // popularity, another random thing
            return moviesByPopularity.get(0);
        }
    }
    
    @Override
    public RBTList<String> getDataByTitle(String title) {
        return moviesByTitle.get(0);
    }
    
    @Override
    public RBTList<Integer> getDataByYear(int year) {
        if (year == 1920) {
            return moviesByYear.get(0);
        }
        else {
            return moviesByYear.get(1);
        }
    }
    
    @Override
    public RBTList<Integer> getDataByPopularity(int popularity) {
        return moviesByPopularity.get(0);
    }
    
    @Override
    public String toString() {
        String output = "";
        output += "year: " + moviesByYear + "\n";
        output += "popularity: " + moviesByPopularity + "\n";
        output += "title: " + moviesByTitle + "\n";
        return output;
    }
    
    // NOT IN USE
    
    @Override
    public RedBlackTree<RBTList<Integer>> getYearRBT() {
        return null;
    }
    
    @Override
    public RedBlackTree<RBTList<String>> getTitleRBT() {
        return null;
    }
    
    @Override
    public RedBlackTree<RBTList<Integer>> getPopularityRBT() {
        return null;
    }
}
