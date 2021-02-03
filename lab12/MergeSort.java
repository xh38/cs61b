import edu.princeton.cs.algs4.Queue;

public class MergeSort {
    /**
     * Removes and returns the smallest item that is in q1 or q2.
     *
     * The method assumes that both q1 and q2 are in sorted order, with the smallest item first. At
     * most one of q1 or q2 can be empty (but both cannot be empty).
     *
     * @param   q1  A Queue in sorted order from least to greatest.
     * @param   q2  A Queue in sorted order from least to greatest.
     * @return      The smallest item that is in q1 or q2.
     */
    private static <Item extends Comparable> Item getMin(
            Queue<Item> q1, Queue<Item> q2) {
        if (q1.isEmpty()) {
            return q2.dequeue();
        } else if (q2.isEmpty()) {
            return q1.dequeue();
        } else {
            // Peek at the minimum item in each queue (which will be at the front, since the
            // queues are sorted) to determine which is smaller.
            Comparable q1Min = q1.peek();
            Comparable q2Min = q2.peek();
            if (q1Min.compareTo(q2Min) <= 0) {
                // Make sure to call dequeue, so that the minimum item gets removed.
                return q1.dequeue();
            } else {
                return q2.dequeue();
            }
        }
    }

    /** Returns a queue of queues that each contain one item from items. */
    private static <Item extends Comparable> Queue<Queue<Item>>
            makeSingleItemQueues(Queue<Item> items) {
        // Your code here!
        Queue<Queue<Item>> itemQueue = new Queue<Queue<Item>>();
        for (Item i : items) {
            Queue<Item> perQueue = new Queue<Item>();
            perQueue.enqueue(i);
            itemQueue.enqueue(perQueue);
        }
        return itemQueue;
    }

    /**
     * Returns a new queue that contains the items in q1 and q2 in sorted order.
     *
     * This method should take time linear in the total number of items in q1 and q2.  After
     * running this method, q1 and q2 will be empty, and all of their items will be in the
     * returned queue.
     *
     * @param   q1  A Queue in sorted order from least to greatest.
     * @param   q2  A Queue in sorted order from least to greatest.
     * @return      A Queue containing all of the q1 and q2 in sorted order, from least to
     *              greatest.
     *
     */
    private static <Item extends Comparable> Queue<Item> mergeSortedQueues(
            Queue<Item> q1, Queue<Item> q2) {
        // Your code here!
        Queue<Item> sortQueue = new Queue<Item>();
        while(!q1.isEmpty() || !q2.isEmpty()) {
            sortQueue.enqueue(getMin(q1, q2));
        }
        return sortQueue;
    }


    /** Returns a Queue that contains the given items sorted from least to greatest. */
    public static <Item extends Comparable> Queue<Item> mergeSort(
            Queue<Item> items) {
        // Your code here!
        if (items.size() == 0) {
            return items;
        }
        Queue<Queue<Item>> beforesort = makeSingleItemQueues(items);
        while(beforesort.size() > 1) {
            beforesort.enqueue(mergeSortedQueues(beforesort.dequeue(), beforesort.dequeue()));
        }
        items = beforesort.dequeue();
        return items;
    }

    public static void main(String[] args) {
        Queue<String> unsorted1 = new Queue<String>();
        unsorted1.enqueue("xuhao");
        unsorted1.enqueue("zhugexiangwen");
        unsorted1.enqueue("xuye");
        unsorted1.enqueue("tongzhengren");
        unsorted1.enqueue("zhengzhiyuan");
        Queue<String> sorted1 = MergeSort.mergeSort(unsorted1);
        System.out.println(unsorted1);
        System.out.println(sorted1);

        Queue<Integer> unsorted2 = new Queue<Integer>();
        unsorted2.enqueue(100);
        unsorted2.enqueue(78);
        unsorted2.enqueue(38);
        unsorted2.enqueue(900);
        unsorted2.enqueue(121238);
        Queue<Integer> sorted2 = MergeSort.mergeSort(unsorted2);
        System.out.println(unsorted2);
        System.out.println(sorted2);
    }
}
