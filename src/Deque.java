import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private class Node {
        private Item item;
        private Node next;
        private Node before;
    }

    private Node first;
    private Node last;
    private int n;

    public Deque() {
        // construct an empty deque
        first = null;
        last = null;
        n = 0;
    }

    public boolean isEmpty() {
        // is the deque empty?
        return first == null;
    }

    public int size() {
        // return the number of items on the deque
        return n;
    }

    public void addFirst(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
        // add the item to the front
        if (first == null) {
            first = new Node();
            first.item = item;
            first.before = null;
            first.next = null;
            last = first;
        } else {
            Node oldFirst = first;
            first = new Node();
            first.item = item;
            first.next = oldFirst;
            first.before = null;
            oldFirst.before = first;
        }
        n++;
    }

    public void addLast(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
        // add the item to the end
        if (last == null) {
            last = new Node();
            last.item = item;
            last.next = null;
            last.before = null;
            first = last;
        } else {
            Node oldLast = last;
            last = new Node();
            last.item = item;
            last.next = null;
            last.before = oldLast;
            oldLast.next = last;
        }
        n++;
    }

    public Item removeFirst() {
        if (n == 0) {
            throw new NoSuchElementException();
        }
        // remove and return the item from the front
        Item item = first.item;
        first = first.next;
        if (first == null)
            last = null;
        else
            first.before = null;
        n--;
        return item;
    }

    public Item removeLast() {
        if (n == 0) {
            throw new NoSuchElementException();
        } else {
            // remove and return the item from the end
            Item item = last.item;
            last = last.before;
            if (last == null) {
                first = null;
            } else {
                last.next = null;
            }
            n--;
            return item;
        }
    }

    public Iterator<Item> iterator() {
        // return an iterator over items in order from front to end
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public static void main(String[] args) {
        // unit testing
    }
}
