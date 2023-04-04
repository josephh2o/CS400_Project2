import java.util.ArrayList;

public interface RedBlackTreeInterface<T extends Comparable<T>> extends SortedCollectionInterface<T> {
    public boolean insert(T data) throws NullPointerException, IllegalArgumentException;
    public boolean remove(T data) throws NullPointerException, IllegalArgumentException;
    public boolean contains(T data);
    public int size();
    public boolean isEmpty();
    public ArrayList<T> subTreeData(T data1, T data2) throws NullPointerException;
}
