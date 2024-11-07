package prov6;

import java.util.Arrays;

public class Vector<T extends Number> {
    private T[] elements;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;

    public Vector() {
        elements = (T[]) new Number[DEFAULT_CAPACITY];
        size = 0;
    }

    public void add(T element) {
        if (size == elements.length) {
            ensureCapacity();
        }
        elements[size++] = element;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        return elements[index];
    }

    public void removeLast() {
        if (size > 0) {
            size--;
        }
    }

    private void ensureCapacity() {
        int newCapacity = elements.length * 2;
        elements = Arrays.copyOf(elements, newCapacity);
    }
}
