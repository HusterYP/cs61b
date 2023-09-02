public class ArrayDeque<T> {
    private static final double FACTOR = 0.25;
    private static final int MIN_SIZE = 8;

    private int size;
    private Object[] items;
    private int head;
    private int tail;

    public ArrayDeque() {
        size = 0;
        items = new Object[MIN_SIZE];
        head = 3;
        tail = 3;
    }

    private void expandIfNeed() {
        if (size < items.length) {
            return;
        }
        int newSize = (int) (items.length * (1 + FACTOR));
        Object[] newItems = new Object[newSize];
        System.arraycopy(items, head, newItems, 0, items.length - head);
        System.arraycopy(items, 0, newItems, items.length - head, tail);
        items = newItems;
        head = 0;
        tail = size;
    }

    private void reduceIfNeed() {
        if (items.length <= MIN_SIZE) {
            return;
        }
        int newSize = Math.max((int) (items.length / (1 + FACTOR)), MIN_SIZE);
        if (size >= newSize) {
            return;
        }
        Object[] newItems = new Object[newSize];
        if (head < tail) {
            System.arraycopy(items, head, newItems, 0, tail - head);
        } else {
            System.arraycopy(items, head, newItems, 0, items.length - head);
            System.arraycopy(items, 0, newItems, items.length - head, tail);
        }
        items = newItems;
        head = 0;
        tail = size;
    }

    public void addFirst(T item) {
        if (head == 0) {
            head = items.length - 1;
        } else {
            head -= 1;
        }
        items[head] = item;
        size++;
        expandIfNeed();
    }

    public void addLast(T item) {
        items[tail] = item;
        size++;
        if (tail == items.length - 1) {
            tail = 0;
        } else {
            tail += 1;
        }
        expandIfNeed();
    }

    public boolean isEmpty() {
        return size <= 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        if (isEmpty()) {
            return;
        }
        StringBuilder builder = new StringBuilder();
        if (head < tail) {
            for (int index = head; index < tail; index++) {
                builder.append(items[index]).append(" ");
            }
        } else {
            for (int index = head; index < items.length; index++) {
                builder.append(items[index]).append(" ");
            }
            for (int index = 0; index < tail; index++) {
                builder.append(items[index]).append(" ");
            }
        }
        System.out.println(builder);
    }

    public T removeFirst() {
        if (size <= 0) {
            return null;
        }
        Object item = items[head];
        items[head] = null;
        size--;
        if (head == items.length - 1) {
            head = 0;
        } else {
            head++;
        }
        reduceIfNeed();
        return (T) item;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        size--;
        if (tail == 0) {
            tail = items.length - 1;
        } else {
            tail--;
        }
        Object item = items[tail];
        items[tail] = null;
        reduceIfNeed();
        return (T) item;
    }

    public T get(int index) {
        if (index >= size) {
            return null;
        }
        if (head + index < items.length) {
            return (T) items[head + index];
        } else {
            return (T) items[head + index - items.length];
        }
    }
}
