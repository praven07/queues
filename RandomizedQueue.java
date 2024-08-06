/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private static final int INITIAL_CAPACITY = 10;

    private Item[] queue;

    private int size;

    public RandomizedQueue() {

        queue = (Item[]) new Object[INITIAL_CAPACITY];
        size = 0;
    }

    public void enqueue(Item item) {

        if (item == null) {
            throw new IllegalArgumentException();
        }

        if (size == queue.length) {
            resize();
        }

        queue[size++] = item;
    }

    public Item dequeue() {

        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        if (size() < queue.length / 4) {
            shrink();
        }

        // Swaps the last item with the randomly selected item
        int randomIndex = StdRandom.uniformInt(size);
        Item selected = queue[randomIndex];
        queue[randomIndex] = queue[--size];

        return selected;
    }

    public Item sample() {

        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        return queue[StdRandom.uniformInt(size)];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public Iterator<Item> iterator() {
        return new QueueIterator();
    }

    private void resize() {

        Item[] newCopy = (Item[]) new Object[queue.length * 2];

        for (int i = 0; i < queue.length; i++) {
            newCopy[i] = queue[i];
        }

        queue = newCopy;
    }

    private void shrink() {

        Item[] newCopy = (Item[]) new Object[queue.length / 2];

        for (int i = 0; i < size; i++) {
            newCopy[i] = queue[i];
        }

        queue = newCopy;
    }

    private class QueueIterator implements Iterator<Item> {

        private int[] selection;

        private int index;

        public QueueIterator() {

            selection = new int[size];
            index = 0;

            for (int i = 0; i < selection.length; i++) {
                selection[i] = i;
            }

            StdRandom.shuffle(selection);
        }

        public boolean hasNext() {
            return index < selection.length;
        }

        public Item next() {

            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            return queue[selection[index++]];
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public static void main(String[] args) {

        RandomizedQueue<Integer> queue = new RandomizedQueue<>();

        for (int i = 0; i < 10; i++) {
            queue.enqueue(i);
        }

        while (!queue.isEmpty()) {
            StdOut.println(queue.dequeue());
        }
    }
}
