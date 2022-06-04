package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T> {
    @SuppressWarnings("unchecked")
    private T[] items = (T[]) new Object[8];
    private int size;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
        size = 0;
        nextFirst = 3;
        nextLast = 4;
    }
    public ArrayDeque(T item) {
        items[3] = item;
        size = 1;
        nextFirst = 2;
        nextLast = 4;
    }
    private void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        System.arraycopy(items, 0, a, 0, size);
        items = a;
    }

    @Override
    public void addFirst(T item) {
        if (size == items.length) {
            resize((int) (size * 1.25));
        }
        T[] a = (T[]) new Object[size];
        System.arraycopy(items, 0, a, 1, size);
        items[0] = item;
        items = a;
        size++;
    }

    @Override
    public void addLast(T item) {
        if (size == items.length) {
            resize((int) (size * 1.25));
        }
        items[size] = item;
        size++;
    }

    @Override
    public boolean isEmpty() {
        return items[0] == null;
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
        if (size > items.length * 0.25) {
            resize((int) (size * 0.5));
        }

        T b = items[0];
        T[] a = (T[]) new Object[size];
        System.arraycopy(items, 1, a, 0, size);
        items = a;
        size--;
        return b;
    }

    private T getLast() {
        return items[size - 1];
    }

    @Override
    public T removeLast() {
        if (size > items.length * 0.25) {
            resize((int) (size * 0.5));
        }
        T b = getLast();
        T[] a = (T[]) new Object[size];
        System.arraycopy(items, 0, a, 0, size - 1);
        size--;
        return b;
    }

    @Override
    public T get(int index) {
        return items[index];
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (o == this) {
            return true;
        }
        if (!(o instanceof ArrayDeque)) {
            return false;
        }
        ArrayDeque<?> ad = (ArrayDeque<?>) o;
        if (ad.size() != size) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (ad.get(i) != get(i)) {
                return false;
            }
        }
        return true;
    }

    public Iterator<T> iterator() {
        return new ArrayDeque.ArrayDequeIterator();
    }

    private class ArrayDequeIterator implements Iterator<T> {
        private int index;

        ArrayDequeIterator() {
            index = 0;
        }

        public boolean hasNext() {
            return index < size;
        }

        public T next() {
            T returnItem = get(index);
            index += 1;
            return returnItem;
        }
    }

}
