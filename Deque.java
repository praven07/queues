import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private int size;

    private Node first;

    private Node last;

    public Deque() {

        size = 0;
        first = null;
        last = null;
    }

    public void addFirst(Item item) {

        if (item == null) {
            throw new IllegalArgumentException();
        }

        Node newNode = new Node(item, null, first);

        if (isEmpty()) {
            last = newNode;
        }
        else {
            first.prev = newNode;
        }

        first = newNode;

        size++;
    }

    public void addLast(Item item) {

        if (item == null) {
            throw new IllegalArgumentException();
        }

        Node newNode = new Node(item, last, null);

        if (isEmpty()) {
            first = newNode;
        }
        else {
            last.next = newNode;
        }

        last = newNode;

        size++;
    }

    public Item removeFirst() {

        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        Item item = first.item;

        first = first.next;

        size--;

        if (isEmpty()) {
            last = null;
        }
        else {
            first.prev = null;
        }

        return item;
    }

    public Item removeLast() {

        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        Item item = last.item;

        last = last.prev;

        size--;

        if (isEmpty()) {
            first = null;
        }
        else {
            last.next = null;
        }

        return item;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size() == 0;
    }


    public Iterator<Item> iterator() {
        return new QueueIterator();
    }

    private class Node {

        private Item item;

        private Node prev;

        private Node next;

        public Node(Item item, Node prev, Node next) {

            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }

    private class QueueIterator implements Iterator<Item> {

        private Node pointer;

        public QueueIterator() {
            pointer = first;
        }

        public boolean hasNext() {
            return pointer != null;
        }

        public Item next() {

            if (pointer == null) {
                throw new NoSuchElementException();
            }

            Item item = pointer.item;
            pointer = pointer.next;
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public static void main(String[] args) {

        Deque<Integer> deque = new Deque<>();

        StdOut.println(deque.isEmpty());
        deque.addLast(3);
        deque.addLast(2);
        deque.addLast(1);
        deque.addFirst(4);
        deque.addFirst(5);
        StdOut.println(deque.isEmpty());
    }
}