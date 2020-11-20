public class ArrayDeque<T> {
    private int size;
    private int first;
    private int last;
    private T[] thing;

    public ArrayDeque() {
        thing = (T[]) new Object[100];
        size = 0;
        first = 0;
        last = 0;
    }

    private int getrightnumber(int index) {
        if (index < 0) {
            index += thing.length;
        }
        if (index > thing.length - 1) {
            index -= thing.length;
        }
        return index;
    }

    private void resize(){
        T[] temp = (T[]) new Object[100];
        if (last > first) {
            System.arraycopy(thing, 0, temp, 0, size);
        }
        else {
            System.arraycopy(thing, first, temp,0, thing.length - first);
            System.arraycopy(thing, 0, temp, thing.length - first, first);
        }
        first = 0;
        last = size - 1;
        thing = temp;
    }

    public void addFirst(T item) {
        if (size == thing.length) {
            resize();
        }
        thing[first] = item;
        size++;
        first--;
        first = getrightnumber(first);
    }

    public void addLast(T item) {
        if (size == thing.length) {
            resize();
        }
        thing[last] = item;
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
        T removedfirst = thing[first];
        size--;
        return removedfirst;
    }

    public T removeLast() {
        last = getrightnumber(last + 1);
        T removedlast = thing[last];
        size--;
        return removedlast;
    }

    public T get(int index) {
        index = getrightnumber(index + first + 1);
        T getitem = thing[index];
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
