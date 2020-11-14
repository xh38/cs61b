public class ArrayDeque<T> {
    private int size;
    private int first;
    private int last;
    private T[] ts;

    public ArrayDeque() {
        ts = (T[]) new Object[100];
        size = 0;
        first = 0;
        last = 0;
    }

    private int getrightnumber(int index) {
        if (index < 0) {
            index += ts.length;
        }
        if (index > ts.length - 1) {
            index -= ts.length;
        }
        return index;
    }

    public void addFirst(T item) {
        ts[first] = item;
        size++;
        first--;
        first = getrightnumber(first);
    }

    public void addLast(T item) {
        ts[last] = item;
        size++;
        last++;
        last = getrightnumber(last);
    }

    public boolean isEmpty() {
        boolean isempty = false;
        if (size == 0) {
            isempty = true;
        }
        return isempty;
    }

    public int size() {
        return size;
    }

    public T removeFirst() {
        first = getrightnumber(first + 1);
        T removedfirst = ts[first];
        size--;
        return removedfirst;
    }

    public T removeLast() {
        last = getrightnumber(last + 1);
        T removedlast = ts[last];
        size--;
        return removedlast;
    }

    public T get(int index) {
        index = getrightnumber(index + first + 1);
        T getitem = ts[index];
        return getitem;
    }

    public void printDeque() {
        int index = 0;
        while (index < size - 1) {
            System.out.print(get(index) + " ");
            index++;
        }
    }
}
