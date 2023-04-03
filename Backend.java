
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Backend implements BackendInterface {
    
    private MovieRedBlackTreeInterface RBT;
    private MovieReaderInterface reader;
    
    public Backend() {
        reader = new MovieReader();
        RBT = new MovieRedBlackTree();
    }
    
    // todo is it frontend or backend's job to make sure loadData isn't called twice?
    // O(NlogN)
    public boolean loadData(String filename) { // change to include argument in method
        List<MovieInterface> dataList;
        try {
            dataList = reader.readMovieData(filename);
        }
        catch (FileNotFoundException e){
            return false;
        }
        
        // load years
        RBTList<Integer>[] yearMap = new RBTList[80]; // first index is 1920, last might be 1997
        int index;
        for (MovieInterface movie: dataList) {
            index = movie.getYear()-1920;
            if (yearMap[index] == null) {
                yearMap[index] = new RBTList<Integer>(index+1920);
            }
            yearMap[index].add(movie);
        }
        for (RBTList<Integer> yearList: yearMap) {
            if (yearList != null)
                RBT.insertByYear(yearList);
        }
        
        // load titles
        RBTList<String> list;
        for (MovieInterface movie: dataList) {
            list = new RBTList<>(movie.getTitle());
            list.add(movie);
            RBT.insertByTitle(list);
        }
        
        // load popularity
        RBTList<Integer>[] popMap = new RBTList[101];
        for (MovieInterface movie: dataList) {
            index = movie.getPopularity();
            if (popMap[index] == null) {
                popMap[index] = new RBTList<Integer>(index);
            }
            popMap[index].add(movie);
        }
        for (RBTList<Integer> popList: popMap) {
            if (popList != null)
                RBT.insertByPopularity(popList);
        }
        
        return true;
    }
    
    public List<MovieInterface> getMoviesByTitle(String title) {
        List<MovieInterface> list = RBT.getDataByTitle(title.strip());
        System.out.println(list);
        if (list == null) {
            list = new ArrayList<>();
        }
        return list;
    }
    
    @Override
    public List<MovieInterface> getMoviesByPopularity(int popularity) {
        return RBT.getDataByPopularity(popularity);
    }
    
    public List<MovieInterface> getMoviesByYear(int year) {
        return RBT.getDataByYear(year);
    }
    public List<List<MovieInterface>> compareMoviesByYear(int year1, int year2) {
        ArrayList<List<MovieInterface>> output = new ArrayList<>();
        output.add(getMoviesByYear(year1));
        output.add(getMoviesByYear(year2));
        return output;
    }
    
    public List<MovieInterface> getMoviesByYearRange(int year1, int year2) {
        return RBT.getRangeData(year1, year2, true);
    }
    
    @Override
    public List<MovieInterface> getMoviesByPopularityRange(int pop1, int pop2) {
        return RBT.getRangeData(pop1, pop2, false);
    }
    
    // FOR TESTING
    
    public String RBTString() {
        return RBT.toString();
    }
    
}
