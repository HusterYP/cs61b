public class DLList<Item> {
    private class Node {
        Item item;
        Node prev;
        Node next;

        Node(Item item, Node prev, Node next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }

    private int size;
    private final Node sentinel;

    public DLList() {
        size = 0;
        sentinel = new Node(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }

    public DLList(Item item) {
        size = 1;
        sentinel = new Node(null, null, null);
        Node node = new Node(item, sentinel, sentinel);
        sentinel.next = node;
        sentinel.prev = node;
    }

    public void addFirst(Item item) {
        size += 1;
        Node node = new Node(item, sentinel, sentinel.next);
        sentinel.next.prev = node;
        sentinel.next = node;
    }

    public Item getFirst() {
        return sentinel.next.item;
    }

    public Item removeFirst() {
        if (sentinel.next != sentinel) {
            size -= 1;
        }
        Item item = getFirst();
        sentinel.next.next.prev = sentinel;
        sentinel.next = sentinel.next.next;
        return item;
    }

    public void addLast(Item item) {
        size += 1;
        Node node = new Node(item, sentinel.prev, sentinel);
        sentinel.prev.next = node;
        sentinel.prev = node;
    }

    public Item getLast() {
        return sentinel.prev.item;
    }

    public Item removeLast() {
        if (sentinel.prev != sentinel) {
            size -= 1;
        }
        Item item = getLast();
        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;
        return item;
    }

    public int size() {
        return size;
    }

    private int size(Node node) {
        if (node == sentinel && node.next == sentinel) {
            return 0;
        }
        if (node.next == sentinel) {
            return 1;
        }
        return 1 + size(node.next);
    }

    public int recursiveSize() {
        return size(sentinel.next);
    }

    public int iterativeSize() {
        Node p = sentinel;
        int size = 0;
        while (p.next != sentinel) {
            size += 1;
            p = p.next;
        }
        return size;
    }

    public static void main(String[] args) {
        DLList<String> list = new DLList<>();
        System.out.println(list.size());
        System.out.println(list.recursiveSize());
        System.out.println(list.iterativeSize());
        System.out.println(list.getFirst());
        System.out.println(list.removeFirst());
        System.out.println(list.getLast());
        System.out.println(list.removeLast());

        System.out.println("<---------------------->");

        list.addFirst("2");
        list.addFirst("1");
        list.addLast("3");
        list.addLast("4");
        System.out.println(list.size());
        System.out.println(list.recursiveSize());
        System.out.println(list.iterativeSize());
        System.out.println(list.getFirst());
        System.out.println(list.getLast());

        System.out.println("<---------------------->");

        System.out.println(list.removeFirst());
        System.out.println(list.removeFirst());
        System.out.println(list.removeLast());
        System.out.println(list.removeLast());
        System.out.println(list.size());
    }
}
