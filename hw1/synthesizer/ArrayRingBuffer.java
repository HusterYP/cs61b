// package <package name>;
package synthesizer;
import java.util.Iterator;

public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;            // index for the next dequeue or peek
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    private Object[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        this.rb = new Object[capacity];
        this.first = 0;
        this.last = 0;
        this.fillCount = 0;
        this.capacity = capacity;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */
    public void enqueue(T x) {
        if (isFull()) {
            throw new RuntimeException("Ring buffer overflow");
        }
        rb[last] = x;
        if (last < capacity - 1) {
            last++;
        } else {
            last = 0;
        }
        fillCount++;
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    public T dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        }
        T item = (T) rb[first];
        rb[first] = null;
        if (first < capacity - 1) {
            first++;
        } else {
            first = 0;
        }
        fillCount--;
        return item;
    }

    /**
     * Return oldest item, but don't remove it.
     */
    public T peek() {
        if (isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        }
        return (T) rb[first];
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayRingBufferIterator<>();
    }

    private class ArrayRingBufferIterator<R> implements Iterator<R> {
        // 已经遍历的个数
        private int count;

        ArrayRingBufferIterator() {
            count = 0;
        }

        @Override
        public boolean hasNext() {
            return count < fillCount;
        }

        @Override
        public R next() {
            int index = first + count;
            if (index >= capacity) {
                index = count - capacity + first;
            }
            count++;
            return (R) rb[index];
        }
    }

//    public static void main(String[] args) {
//        ArrayRingBuffer<Integer> buffer = new ArrayRingBuffer<>(5);
//        for (int i = 0; i < 5; i++) {
//            buffer.enqueue(i);
//        }
//
//        for (Integer index : buffer) {
//            System.out.println(index);
//        }
//    }
}
