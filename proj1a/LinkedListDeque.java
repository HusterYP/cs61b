public class LinkedListDeque<T> {
    private class Item {
        T item;
        Item prev;
        Item next;

        Item(T item, Item prev, Item next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }

    private int size;
    private final Item sentinel;

    public LinkedListDeque() {
        size = 0;
        sentinel = new Item(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
    }

    public void addFirst(T item) {
        Item node = new Item(item, sentinel, sentinel.next);
        sentinel.next.prev = node;
        sentinel.next = node;
        size += 1;
    }

    public void addLast(T item) {
        Item node = new Item(item, sentinel.prev, sentinel);
        sentinel.prev.next = node;
        sentinel.prev = node;
        size += 1;
    }

    public boolean isEmpty() {
        return size <= 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        StringBuilder result = new StringBuilder();
        Item p = sentinel.next;
        while (p != sentinel) {
            result.append(p.item).append(" ");
            p = p.next;
        }
        System.out.println(result);
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        Item item = sentinel.next;
        sentinel.next.next.prev = sentinel;
        sentinel.next = sentinel.next.next;
        size -= 1;
        return item.item;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        Item item = sentinel.prev;
        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;
        size -= 1;
        return item.item;
    }

    public T get(int index) {
        if (index >= size || index < 0) {
            return null;
        }
        int currentIndex = 0;
        Item p = sentinel.next;
        while (currentIndex != index) {
            currentIndex += 1;
            p = p.next;
        }
        return p.item;
    }

    public T getRecursive(int index) {
        return getRecursiveHelper(index, sentinel.next);
    }

    private T getRecursiveHelper(int index, Item p) {
        if (p == sentinel || index >= size || index < 0) {
            return null;
        }
        if (index == 0) {
            return p.item;
        }
        return getRecursiveHelper(index - 1, p.next);
    }
}
