public class SLLists<Item> {
    private class Node {
        Item item;
        Node next;

        Node(Item item, Node next) {
            this.item = item;
            this.next = next;
        }
    }
    private final Node sentinel;
    private int size;

    public SLLists() {
        sentinel = new Node(null, null);
        size = 0;
    }

    public SLLists(Item item) {
        Node first = new Node(item, null);
        sentinel = new Node(null, first);
        size = 1;
    }

    public int size() {
        return size;
    }

    /** Return the size of the list using no recursion! */
    public int iterativeSize() {
        int size = 0;
        Node p = sentinel;
        while (p.next != null) {
            size += 1;
            p = p.next;
        }
        return size;
    }

    private int size(Node item) {
        if (item == null) {
            return 0;
        }
        if (item.next == null) {
            return 1;
        }
        return 1 + size(item.next);
    }

    public int recursiveSize() {
        return size(sentinel.next);
    }

    public Item getFirst() {
        if (sentinel.next == null) {
            return null;
        }
        return sentinel.next.item;
    }

    public void addFirst(Item item) {
        size += 1;
        sentinel.next = new Node(item, sentinel.next);
    }

    public Item removeFirst() {
        if (sentinel.next == null) {
            return null;
        }
        size -= 1;
        Item item = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        return item;
    }

    public Item getLast() {
        Node p = sentinel;
        while (p.next != null) {
            p = p.next;
        }
        return p.item;
    }

    public void addLast(Item item) {
        size += 1;
        Node p = sentinel;
        while (p.next != null) {
            p = p.next;
        }
        p.next = new Node(item, null);
    }

    public Item removeLast() {
        Node pre = sentinel;
        Node next = pre.next;
        if (next == null) {
            return null;
        }
        size -= 1;
        while (next.next != null) {
            pre = next;
            next = next.next;
        }
        pre.next = null;
        return next.item;
    }

    public static void main(String[] args) {
        SLLists<String> lists = new SLLists<>();
        int size1 = lists.size();
        int size2 = lists.iterativeSize();
        int size3 = lists.recursiveSize();
        String item1 = lists.removeFirst();
        String item2 = lists.removeLast();
        System.out.println(size1);
        System.out.println(size2);
        System.out.println(size3);
        System.out.println(item1);
        System.out.println(item2);

        lists.addFirst("2");
        lists.addFirst("1");
        lists.addLast("3");
        lists.addLast("4");
        System.out.println(lists.size());
        System.out.println(lists.iterativeSize());
        System.out.println(lists.recursiveSize());
    }
}
