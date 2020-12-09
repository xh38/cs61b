public class OffByOne implements CharacterComparator {
    @Override
    public boolean equalChars(char x, char y) {
        boolean flag = false;
        if (x - y == 1 || y - x == 1) {
            flag = true;
        }
        return flag;
    }
}
