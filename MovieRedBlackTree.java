public class MovieRedBlackTree extends RedBlackTree implements MovieRedBlackTreeInterface {
    private RedBlackTree<RBTList<Integer>> yearRBT = new RedBlackTree<>();
    private RedBlackTree<RBTList<String>> titleRBT = new RedBlackTree<>();
    private RedBlackTree<RBTList<Integer>> popularityRBT = new RedBlackTree<>();

    /**
     * Insert a RBTList into a RBT that is sorted by year. The value of the RBTList
     * sholud be the year the movie was released
     *
     * @param movies A list of movies that where released in the year value of RBTList
     * @return true if insert was successful and false otherwise
     * @see RBTList
     */
	public boolean insertByYear(RBTList<Integer> movies) throws IllegalArgumentException, NullPointerException {
        return yearRBT.insert(movies);
    }

    /**
     * Insert a RBTList into a RBT that is sorted by title. The value of the RBTList
     * should be the title of the movie.
     *
     * @param movies A list of movies that have a title value of RBTList
     * @return true if insert was successful and false otherwise
     * @see RBTList
     */
	public boolean insertByTitle(RBTList<String> movies) throws IllegalArgumentException, NullPointerException {
        // System.out.println(movies.value.getTitle());
        return titleRBT.insert(movies);
    }

    /**
     * Insert a RBTList into a RBT that is sorted by popularity. The value of the RBTList
     * should be the popularity of the movie.
     *
     * @param movies A list of movies that have a popularity of value of RBTList
     * @return true if insert was successful and false otherwise
     * @see RBTList
     */
	public boolean insertByPopularity(RBTList<Integer> movies) throws IllegalArgumentException, NullPointerException {
        return popularityRBT.insert(movies);

    }

    /**
     * Gets the lists between min node and the max node traversing the tree
     * corresponding to the mode (year if mode is true and popularity if mode
     * is false).
     *
     * @param min the minimum value of the range
     * @param max the maximum value of the range
     * @param mode true if min and max are years and false if min and max are popularities
     * @return null if min or max is not in the tree
     */
	public RBTList<Integer> getRangeData(int min, int max, boolean mode) {
        RBTList<Integer> minList = new RBTList<Integer>(min);
        RBTList<Integer> maxList = new RBTList<Integer>(max);
        // If max or min is not in yearRBT
        // if(mode && (!yearRBT.contains(minList) ||
        //             !yearRBT.contains(maxList)))
        //     return null;
        //
        // // If max or min is not in popularity RBT
        // if(!mode && (!popularityRBT.contains(minList) ||
        //             !popularityRBT.contains(maxList)))
        //     return null;

        if(mode) { // in year mode
            // Find the common parent of max and min
            Node<RBTList<Integer>> commonParent = findCommonParent(minList, yearRBT.root, maxList);
            // return result of helper
            return rangeHelper(minList, 
                    commonParent,
                    maxList);
        }

        // in popularity mode
        // Find the common parent of max and min
        Node<RBTList<Integer>> commonParent = findCommonParent(minList, popularityRBT.root, maxList);
        // return result of helper
        return rangeHelper(minList, 
                commonParent,
                maxList);
    }

    /**
     * Helper for getRangeData that traverses the tree looking for a "middle" node
     * between the min and the max. Sort of like binary search.
     *
     * @param min node corresponding to the min of the range
     * @param curr current node that the algorithm has traversed to
     * @param max node corresponding to the max of the range
     */
    private Node<RBTList<Integer>> findCommonParent(RBTList<Integer> min, Node<RBTList<Integer>> curr, RBTList<Integer> max) {
        // current node too small
        if(curr.data.compareTo(min) < 0)
            return findCommonParent(min, curr.context[2], max);
        // current node too large
        if(curr.data.compareTo(max) > 0)
            return findCommonParent(min, curr.context[1], max);
        // current node just right
        return curr;
    }

    /**
     * Helper for getRangeData that traverses the tree to get the list of all the
     * nodes in the range between min and max
     * @param min node corresponding to the min of the range
     * @param curr current node that the algorithm has traversed to
     * @param max node corresponding to the max of the range
     */
    private RBTList<Integer> rangeHelper(RBTList<Integer> min,
            Node<RBTList<Integer>> curr, RBTList<Integer> max) {
        RBTList<Integer> data = new RBTList<Integer>(0); // data to return
        RBTList<Integer> child; // data of the next node traversed
        if(curr == null) // reached end of the tree
            return null;
        child = rangeHelper(min, curr.context[1], max); // recurse to left child
        if(child != null) // append elements in child list
            data.addAll(child);
        // Only add if node is in range
        if(curr.data.compareTo(min) >= 0 && curr.data.compareTo(max) <= 0) {
            data.addAll(curr.data);
        }

        child = rangeHelper(min, curr.context[2], max); // recures to right chlid
        if(child != null) // append elements in child list
            data.addAll(child);
        return data;
    }

    /**
     * Finds the list of movies that were released in a specific year
     * @param year the year the list of movies were released
     * @return list of movies released in year year.
     */
    public RBTList<Integer> getDataByYear(int year) {
        RBTList<Integer> data = new RBTList<>(year);
        if(yearRBT.findNodeWithData(data) == null) // if there are no movies released in a certain year
            return null;
        return yearRBT.findNodeWithData(data).data;
    }

    /**
     * Finds the list of movies that have a specific title.
     * @param title of the list of movies
     * @return list of movies with title title.
     */

    public RBTList<String> getDataByTitle(String title) {
        RBTList<String> data = new RBTList<>(title);
        if(titleRBT.findNodeWithData(data) == null) // if there are no movies with a certain title
            return null;
        return titleRBT.findNodeWithData(data).data;
    }

    /**
     * Finds the list of movies that were released in a specific popularity
     * @param popularity the popularity of movie being searched
     * @return list of movies with popularity popularity.
     */
    public RBTList<Integer> getDataByPopularity(int popularity) {
        RBTList<Integer> data = new RBTList<>(popularity);
        if(popularityRBT.findNodeWithData(data) == null) // if there are no movies with a certain popularity
            return null;
        return popularityRBT.findNodeWithData(data).data;
    }

    /**
     * Getter for yearRBT
     * @return yearRBT
     */
	public RedBlackTree<RBTList<Integer>> getYearRBT() {
        return yearRBT;
    }

    /**
     * Getter for titleRBT
     * @return titlRBT
     */
	public RedBlackTree<RBTList<String>> getTitleRBT() {
        return titleRBT;
    }

    /**
     * Getter for popularityRBT
     * @return popularityRBT
     */
	public RedBlackTree<RBTList<Integer>> getPopularityRBT() {
        return popularityRBT;
    }
}
