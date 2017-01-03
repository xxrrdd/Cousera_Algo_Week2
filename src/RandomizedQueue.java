import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] items;
    private int last;
    private int first;
    private int n;

    public RandomizedQueue() {
        // construct an empty randomized queue
        n = 0;
        last = 0;
        first = 0;
        items = (Item[]) new Object[1];
    }

    private void resize(int capacity) {
        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            temp[i] = items[(first + i) % items.length];
        }
        items = temp;
        first = 0;
        last = n;
    }

    public boolean isEmpty() {
        // is the queue empty?
        return n == 0;
    }

    public int size() {
        // return the number of items on the queue
        return n;
    }

    public void enqueue(Item item) {
        if (item == null) {
            throw new NullPointerException();
        } else {
            // add the item
            if (n == items.length) {
                resize(items.length * 2);
            }
            if (last < items.length) {
                items[last] = item;
                last++;
            } else {
                last = 0;
                items[last] = item;
                last++;
            }
            n++;
        }
    }

    public Item dequeue() {
        if (n == 0) {
            throw new NoSuchElementException();
        } else {
            int rand = StdRandom.uniform(n);
            Item item = items[(first + rand) % items.length];
            items[(first + rand) % items.length] = items[first % items.length];
            items[first % items.length] = null;
            first++;
            n--;
            if (n < items.length / 3) {
                resize(items.length / 2);
            }
            return item;
        }
        // remove and return a random item
    }

    public Item sample() {
        if (n == 0) {
            throw new NoSuchElementException();
        }
        // return (but do not remove) a random item
        int rand = StdRandom.uniform(n);
        Item item = items[(first + rand) % items.length];
        return item;
    }

    public Iterator<Item> iterator() {
        // return an independent iterator over items in random order
        return new RQIterator();
    }

    private class RQIterator implements Iterator<Item> {
        private Item[] temp = items.clone();
        private int num = n;
        private int cFirst = first;

        public boolean hasNext() {
            return num != 0;
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            } else {
                int randN = StdRandom.uniform(num);
                Item item = temp[(cFirst + randN) % temp.length];
                temp[(cFirst + randN) % temp.length] = temp[cFirst % temp.length];
                temp[cFirst % temp.length] = null;
                cFirst++;
                num--;
                return item;
            }
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public static void main(String[] args) {
        /**RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();
        rq.enqueue(1);
        rq.enqueue(2);
        rq.enqueue(3);
        rq.enqueue(4);
        rq.enqueue(15);
        System.out.println("dequeue+"+rq.dequeue());
        System.out.println("sample+"+rq.sample());
        System.out.println("dequeue+"+rq.dequeue());
        System.out.println("sample+"+rq.sample());
        System.out.println("dequeue+"+rq.dequeue());
        System.out.println("sample+"+rq.sample());
        System.out.println("dequeue+"+rq.dequeue());
        System.out.println("sample+"+rq.sample());*/

    }
}