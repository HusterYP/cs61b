public class ArrayDeque<T> implements Deque<T> {
    private static final double FACTOR = 0.25;
    private static final int MIN_SIZE = 8;

    private int size;
    private Object[] items;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
        size = 0;
        items = new Object[MIN_SIZE];
        nextFirst = 3;
        nextLast = 4;
    }

    private void resizeIfNeed() {
        if (size < items.length && size >= items.length / (1 + FACTOR)) {
            return;
        }
        if (items.length <= MIN_SIZE) {
            return;
        }
        int newSize;
        if (size >= items.length) {
            newSize = (int) (items.length * (1 + FACTOR));
        } else {
            newSize = (int) (items.length / (1 + FACTOR));
        }
        Object[] newItems = new Object[newSize];
        int currentIndex = Math.abs(newSize - size) / 2;
        for (int index = nextFirst + 1; index < size; index++) {
            newItems[currentIndex] = items[index];
            currentIndex++;
        }
        for (int index = 0; index < nextLast; index++) {
            newItems[currentIndex] = items[index];
            currentIndex++;
        }
        nextLast = currentIndex;
        nextFirst = (newSize - size) / 2 - 1;
        items = newItems;
    }

    @Override
    public void addFirst(T item) {
        items[nextFirst] = item;
        size++;
        if (nextFirst == 0) {
            nextFirst = items.length - 1;
        } else {
            nextFirst -= 1;
        }
        resizeIfNeed();
    }

    @Override
    public void addLast(T item) {
        items[nextLast] = item;
        size++;
        if (nextLast == items.length - 1) {
            nextLast = 0;
        } else {
            nextLast += 1;
        }
        resizeIfNeed();
    }

    @Override
    public boolean isEmpty() {
        return size <= 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        if (isEmpty()) {
            return;
        }
        StringBuilder builder = new StringBuilder();
        if (nextFirst < nextLast) {
            for (int index = nextFirst + 1; index < nextLast; index++) {
                builder.append(items[index]).append(" ");
            }
        } else {
            for (int index = nextFirst + 1; index < items.length; index++) {
                builder.append(items[index]).append(" ");
            }
            for (int index = 0; index < nextLast; index++) {
                builder.append(items[index]).append(" ");
            }
        }
        System.out.println(builder);
    }

    @Override
    public T removeFirst() {
        if (size <= 0) {
            return null;
        }
        size--;
        if (nextFirst == items.length - 1) {
            nextFirst = 0;
        } else {
            nextFirst++;
        }
        Object item = items[nextFirst];
        items[nextFirst] = null;
        resizeIfNeed();
        return (T) item;
    }

    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        size--;
        if (nextLast == 0) {
            nextLast = items.length - 1;
        } else {
            nextLast--;
        }
        Object item = items[nextLast];
        items[nextLast] = null;
        resizeIfNeed();
        return (T) item;
    }

    @Override
    public T get(int index) {
        if (index >= size) {
            return null;
        }
        if (nextFirst + index + 1 < items.length) {
            return (T) items[nextFirst + index + 1];
        } else {
            return (T) items[nextFirst + index + 1 - items.length];
        }
    }
}
