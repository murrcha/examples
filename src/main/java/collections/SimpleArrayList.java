package collections;

import java.util.Arrays;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("unchecked")
public class SimpleArrayList<T> implements Iterable<T> {

    private static final int DEFAULT_SIZE = 10;

    private int position;

    private int modCount;

    private Object[] elements;

    public SimpleArrayList(int size) {
        if (size > 0) {
            elements = new Object[size];
        } else if (size == 0) {
            elements = new Object[] {};
        } else {
            elements = new Object[DEFAULT_SIZE];
        }
    }

    public int getSize() {
        return elements.length;
    }

    public void add(T value) {
        checkPosition(position);
        this.elements[position++] = value;
        modCount++;
    }

    public void set(int index, T value) {
        checkIndex(index);
        elements[index] = value;
        modCount++;
    }

    public void delete(int index) {
        checkIndex(index);
        int numMoved = elements.length - index - 1;
        if (numMoved > 0) {
            System.arraycopy(elements, index + 1, elements, index, numMoved);
        }
        this.position--;
        modCount++;
    }

    public void sort(Comparator<T> comparator) {
        Object[] sortElements = Arrays.copyOf(elements, this.position);
        Arrays.sort((T[]) sortElements, comparator);
        elements = Arrays.copyOf(sortElements, elements.length);
    }

    public T get(int index) {
        checkIndex(index);
        return (T) elements[index];
    }

    public boolean contains(T value) {
        for (Object element : elements) {
            if (element.equals(value)) {
                return true;
            }
        }
        return false;
    }

    @NotNull
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            int cursor = 0;
            final int expectedModCount = getModCount();

            @Override
            public boolean hasNext() {
                return cursor < getSize();
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (getModCount() != expectedModCount) {
                    throw new ConcurrentModificationException();
                }
                return getValue(cursor++);
            }
        };
    }

    private void checkPosition(int position) {
        if (position >= elements.length) {
            elements = Arrays.copyOf(elements, this.position + DEFAULT_SIZE);
        }
    }

    private void checkIndex(int index) {
        if (index >= elements.length || index < 0) {
            throw new IndexOutOfBoundsException(String.format("Index: %s", index));
        }
    }

    private int getModCount() {
        return modCount;
    }

    private T getValue(int cursor) {
        return (T) elements[cursor];
    }
}
