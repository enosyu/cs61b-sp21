package deque;

public class ArrayDeque<T> implements Deque<T> {
    private T[] items;
    private int size;
    public ArrayDeque() {
        T[] a = (T[]) new Object[8];
        size = 0;
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

}
