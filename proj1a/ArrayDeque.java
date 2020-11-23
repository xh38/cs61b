public class ArrayDeque<T> {
    private int size;
    private int nextFirst;
    private int nextLast;
    private T[] array;

    public ArrayDeque(){
        size = 0;
        nextFirst = 0;
        nextLast = 1;
        array = (T[]) new Object[8];
    }

    private int getrightindex(int index) {
        if (index < 0) {
            index = index + array.length;
        }
        if (index > array.length - 1) {
            index = index - array.length;
        }
        return index;
    }

    private void resize() {
        T[] temp = (T[]) new Object[array.length * 2];
        if (nextFirst < array.length - 1){
            System.arraycopy(array, nextFirst + 1, temp, 0, array.length - nextFirst - 1);
            System.arraycopy(array, 0, temp, array.length - nextFirst - 1, nextLast);
        }
        else {
            System.arraycopy(array, 0, temp, 0, array.length);
        }
        nextFirst = temp.length - 1;
        nextLast = size;
        array = temp;
    }

    private void reducesize() {
        T[] temp = (T[]) new Object[array.length / 2];
        if (nextFirst > nextLast) {
            System.arraycopy(array, getrightindex(nextFirst + 1), temp, 0, array.length - nextFirst - 1);
            System.arraycopy(array, 0, temp, array.length-nextFirst-1, nextLast);
        }
        else {
            System.arraycopy(array, nextFirst + 1, temp, 0, size);
        }
        nextFirst = temp.length - 1;
        nextLast = size;
        array = temp;
    }

    public void addFirst(T item) {
        if (size == array.length) {
            resize();
        }
        array[nextFirst] = item;
        nextFirst = getrightindex(nextFirst - 1);
        size++;
    }
    public void addLast(T item) {
        if (size == array.length) {
            resize();
        }
        array[nextLast] = item;
        nextLast = getrightindex(nextLast + 1);
        size++;
    }
    public boolean isEmpty() {
        boolean flag = false;
        if (size == 0) {
            flag = true;
        }
        return flag;
    }
    public int size() {
        return size;
    }
    public void printDeque() {
        int i = nextFirst;
        int number = size;
        while (number > 0) {
            i = getrightindex(i + 1);
            System.out.print(array[i] + " ");
            number--;
        }
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        if (size < array.length / 2) {
            reducesize();
        }
        nextFirst = getrightindex(nextFirst + 1);
        T first = array[nextFirst];
        size--;
        return first;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        if (size < array.length / 2) {
            reducesize();
        }
        nextLast = getrightindex(nextLast - 1);
        T Last = array[nextLast];
        size--;
        return Last;
    }

    public T get(int index) {
        T item = array[getrightindex(index + nextFirst + 1)];
        return  item;
    }
}
