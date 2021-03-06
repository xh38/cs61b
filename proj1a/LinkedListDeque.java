public class LinkedListDeque<T> {

    private class Node {
        private Node prev;
        private Node next;
        private T item;

        public Node(T i) {
            item = i;
            prev = null;
            next = null;
        }

        private T recget(int index) {
            if (index == 0) {
                return item;
            }
            return this.next.recget(index - 1);
        }

    }

    private Node sentinel;
    private int size;

    public LinkedListDeque() {
        sentinel = new Node(null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    public void addFirst(T item) {
        Node newnode = new Node(item);
        /** Newnode's prev should be the sentinel and it's next shou-
         * ld be the node that usd to be the head
         */
        newnode.prev = sentinel;
        newnode.next = sentinel.next;
        /** Sentinel's next change to the new node and the old head's
         * prev should be the newnode.
         */
        sentinel.next.prev = newnode;
        sentinel.next = newnode;
        /** size increase by one
         */
        size++;
    }

    public void addLast(T item) {
        Node newnode = new Node(item);
        /** Newnode's prev should be the node that used to be the
         * last which is sentienl.prev, and it's next should be the
         * sentinel.
         */
        newnode.prev = sentinel.prev;
        newnode.next = sentinel;
        /** The old end of the Deque's next should be the new node
         * and the sentinel.prev should be the newnode too.
         */
        sentinel.prev.next = newnode;
        sentinel.prev = newnode;
        /** size increase by one
         */
        size++;
    }

    public boolean isEmpty() {
        boolean isempty = false;
        if (size == 0) {
            isempty = true;
        }
        return isempty;
    }

    public void printDeque() {
        Node pointer = sentinel;
        while (!pointer.next.equals(sentinel)) {
            System.out.print(pointer.item + " ");
            pointer = pointer.next;
        }
        System.out.println();
    }

    public T removeFirst() {
        if (this.isEmpty()) {
            return null;
        }
        T item = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size--;
        return item;
    }

    public T removeLast() {
        if (this.isEmpty()) {
            return null;
        }
        T item = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size--;
        return item;
    }

    public T get(int index) {
        int i = 0;
        Node tempnode = sentinel;
        while (i < index) {
            tempnode = tempnode.next;
            i++;
        }
        return  tempnode.next.item;
    }

    public int size() {
        return size;
    }

    public T getRecursive(int index) {
        Node nownode = sentinel.next;
        if (index == 0) {
            return nownode.item;
        }
        return nownode.next.recget(index - 1);
    }
}
