import java.util.ArrayList;

public class RBTList<T extends Comparable<T>> extends ArrayList<MovieInterface> implements Comparable<RBTList<T>>{

    public T value; // either year or title

    public RBTList(T value) {
        super();
        this.value = value;
    }

    @Override
    public int compareTo(RBTList<T> otherValue) {
        return value.compareTo(otherValue.value);
    }
    
    @Override
    public String toString() {
        String output = "[";
        for (MovieInterface movie: this) {
            output += " " + movie.toString() + ", ";
        }
        output = output.substring(0, output.length()-2);
        return output;
    }
}
