package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private final Comparator<T> comparator;
    public MaxArrayDeque(Comparator<T> c) {
        super();
        comparator = c;
    }
    public T max() {
        return max(comparator);
    }
    public T max(Comparator<T> c) {
        int maxDex = 0;
        for (int i = 0; i < size(); i++) {
            if (c.compare(get(i), get(maxDex)) > 0) {
                maxDex = i;
            }
        }
        return get(maxDex);
    }
    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (this == o) {
            return true;
        }
        if (!(o instanceof MaxArrayDeque)) {
            return false;
        }
        return ((MaxArrayDeque<?>) o).max() == max();
    }
}
