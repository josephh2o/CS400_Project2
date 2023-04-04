public class MovieRedBlackTreeFD implements MovieRedBlackTreeInterface {

    @Override
    public boolean insertByYear(RBTListFD<Integer> movies) throws IllegalArgumentException, NullPointerException {
        return false;
    }

    @Override
    public boolean insertByTitle(RBTListFD<String> movies) throws IllegalArgumentException, NullPointerException {
        return false;
    }

    @Override
    public boolean insertByPopularity(RBTListFD<Integer> movies) throws IllegalArgumentException, NullPointerException {
        return false;
    }

    @Override
    public RBTListFD<Integer> getRangeData(int min, int max, boolean mode) {
        return null;
    }

    @Override
    public RBTListFD<Integer> getDataByYear(int year) {
        return null;
    }

    @Override
    public RBTListFD<String> getDataByTitle(String title) {
        return null;
    }

    @Override
    public RBTListFD<Integer> getDataByPopularity(int popularity) {
        return null;
    }

    @Override
    public RedBlackTree<RBTListFD<Integer>> getYearRBT() {
        return null;
    }

    @Override
    public RedBlackTree<RBTListFD<String>> getTitleRBT() {
        return null;
    }

    @Override
    public RedBlackTree<RBTListFD<Integer>> getPopularityRBT() {
        return null;
    }
}
