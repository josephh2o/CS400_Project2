import java.util.ArrayList;

public class RBTListFD<T extends Comparable<T>> extends ArrayList<MovieInterface> implements Comparable<RBTListFD<T>>{

    public T value; // either year or title

    public RBTListFD(T value) {
        super();
        this.value = value;
    }

    @Override
    public int compareTo(RBTListFD<T> otherValue) {
        return value.compareTo(otherValue.value);
    }
}