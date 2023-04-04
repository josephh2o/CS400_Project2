public class MovieRedBlackTreeFD implements MovieRedBlackTreeInterface {

    @Override
    public boolean insertByYear(RBTList<Integer> movies) throws IllegalArgumentException, NullPointerException {
        return false;
    }

    @Override
    public boolean insertByTitle(RBTList<String> movies) throws IllegalArgumentException, NullPointerException {
        return false;
    }

    @Override
    public boolean insertByPopularity(RBTList<Integer> movies) throws IllegalArgumentException, NullPointerException {
        return false;
    }

    @Override
    public RBTList<Integer> getRangeData(int min, int max, boolean mode) {
        return null;
    }

    @Override
    public RBTList<Integer> getDataByYear(int year) {
        return null;
    }

    @Override
    public RBTList<String> getDataByTitle(String title) {
        return null;
    }

    @Override
    public RBTList<Integer> getDataByPopularity(int popularity) {
        return null;
    }

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
