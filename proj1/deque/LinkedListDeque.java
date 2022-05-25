package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T>, Iterable<T> {
    private class Node {
        private Node prev;
        private final T item;
        private Node next;

        private Node(T i, Node n, Node p) {
            item = i;
            next = n;
            prev = p;
        }
    }

    private final Node sentinel;
    private int size;

    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    public LinkedListDeque(T item) {
        sentinel = new Node(null, null, null);
        sentinel.next = new Node(item, null, null);
        sentinel.prev = sentinel.next;
        size = 1;
    }
/**
    public T getRecursive(int index) {
        if (index < 0 || index > size - 1) {
            return null;
        }
        return getRecursiveHelper(index - 1, );
    }
    public T getRecursiveHelper(int index , Node p) {

    }
*/
    @Override
    public void addFirst(T item) {
        Node n = sentinel.next;
        sentinel.next = new Node(item, n, sentinel);
        n.prev = sentinel.next;
        size = size + 1;
    }

    @Override
    public void addLast(T item) {
        Node p = sentinel.prev;
        sentinel.prev = new Node(item, sentinel, p);
        p.next = sentinel.prev;
        size = size + 1;
    }

    @Override
    public boolean isEmpty() {
        return sentinel.next.item == null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        for (int n = 0; n < size; n++) {
            System.out.println(get(n).toString());
        }
    }

    @Override
    public T removeFirst() {
        Node f = sentinel.next;
        if (f == null) {
            return null;
        }
        f.next.prev = sentinel;
        sentinel.next = f.next;
        size = size - 1;
        if (size < 0) {
            size = 0;
        }
        return f.item;
    }

    @Override
    public T removeLast() {
        Node p = sentinel.prev;
        if (p == null) {
            return null;
        }
        p.prev.next = sentinel;
        sentinel.prev = p.prev;
        size = size - 1;
        if (size < 0) {
            size = 0;
        }
        return p.item;
    }

    @Override
    public T get(int index) {
        Node p = sentinel.next;
        for (int i = 0; i < index; i++) {
            if (isEmpty()) {
                return null;
            }
            p = p.next;
        }
        return p.item;
    }

    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();
    }

    private class LinkedListDequeIterator implements Iterator<T> {
        private int wizPos;

        LinkedListDequeIterator() {
            wizPos = 0;
        }

        public boolean hasNext() {
            return wizPos < size;
        }

        public T next() {
            T returnItem = get(wizPos);
            wizPos += 1;
            return returnItem;
        }
    }
    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (this == o) {
            return true;
        }
        if (!(o instanceof Deque)) {
            return false;
        }
        LinkedListDeque<?> lld = (LinkedListDeque<?>) o;
        if (lld.size != size) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (lld.get(i) != get(i)) {
                return false;
            }
        }
        return true;

    }
    @Override
    public int hashCode() {
        int hashCode = 1;
        for (Object o : this) {
            hashCode = hashCode * 31;
            hashCode = hashCode + o.hashCode();
        }
        return hashCode;
    }

}
