public class OffByN implements CharacterComparator {
    private int N;
    public OffByN(int i) {
        N = i;
    }
    @Override
    public boolean equalChars(char x, char y) {
        boolean flag = false;
        if (x - y == N || y - x == N) {
            flag = true;
        }
        return flag;
    }
}
