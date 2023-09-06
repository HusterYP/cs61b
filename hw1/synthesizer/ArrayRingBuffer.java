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
            return null;
        }
        return (T) rb[first];
    }

//    @Override
//    public Iterator<T> iterator() {
//        return new ArrayRingBufferIterator<>();
//    }
//
//    private class ArrayRingBufferIterator<R> implements Iterator<R> {
//        private int index;
//
//        ArrayRingBufferIterator() {
//            index = first;
//        }
//
//        @Override
//        public boolean hasNext() {
//            return index < fillCount;
//        }
//
//        @Override
//        public R next() {
//            R r = (R) rb[index];
//            if (index < capacity - 1) {
//                index++;
//            } else {
//                index = 0;
//            }
//            return r;
//        }
//    }
}