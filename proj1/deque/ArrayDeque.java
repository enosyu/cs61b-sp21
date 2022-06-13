package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>, Iterable<T> {
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

    @Override
    public void addFirst(T item) {
        items[nextFirst] = item;
        nextFirst -= 1;
        size++;
        if (nextFirst == -1) {
            resize(size * 2);
        }
    }

    @Override
    public void addLast(T item) {
        items[nextLast] = item;
        nextLast += 1;
        size++;
        if (nextLast == items.length) {
            resize(size * 2);
        }
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
        if (isEmpty()) {
            return null;
        }
        nextFirst += 1;
        T item = items[nextFirst];
        items[nextFirst] = null;
        size -= 1;
        shrinkSize();
        return item;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        nextLast -= 1;
        T item = items[nextLast];
        items[nextLast] = null;
        size -= 1;
        shrinkSize();
        return item;
    }
    @SuppressWarnings("unchecked")
    private void resize(int s) {
        T[] newItems = (T[]) new Object[s];
        int firstPos = Math.abs(s - size) / 2;
        System.arraycopy(items, nextFirst + 1, newItems, firstPos, size);
        items = newItems;
        nextFirst = firstPos - 1;
        nextLast = firstPos + size;
    }
    private void shrinkSize() {
        if (isEmpty()) {
            resize(8);
        } else if (items.length / 4 >= size && size >= 4) {
            resize(size * 2);
        }
    }
    @Override
    public T get(int index) {
        if (index < 0 || index > size - 1) {
            return null;
        }
        int itemIndex = nextFirst + 1 + index;
        return items[itemIndex];
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
        return new ArrayDequeIterator();
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
