package collections;

import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class SimpleArrayListTest {
    @Test
    public void whenCreateSimpleArrayWith0SizeThenUpSizeTo10() {
        SimpleArrayList<String> testArray = new SimpleArrayList<>(0);
        assertEquals(0, testArray.getSize());
        testArray.add("test");
        assertEquals(10, testArray.getSize());
    }

    @Test
    public void whenCreateSimpleArrayWithInvalidSizeThenSetDefaultSize10() {
        SimpleArrayList<Integer> testArray = new SimpleArrayList<>(-1);
        assertEquals(10, testArray.getSize());
    }

    @Test
    public void whenAddElementAndGetElementThenReturnAddedElement() {
        SimpleArrayList<Integer> integerArray = new SimpleArrayList<>(2);
        integerArray.add(3);
        integerArray.add(5);
        assertEquals(3, integerArray.get(0));
        assertEquals(5, integerArray.get(1));
    }

    @Test
    public void whenAddElementAndOverflowSizeThenUpSizeTo2Plus10() {
        SimpleArrayList<Integer> integerArray = new SimpleArrayList<>(2);
        integerArray.add(3);
        integerArray.add(5);
        integerArray.add(8);
        assertEquals(2 + 10, integerArray.getSize());
    }

    @Test
    public void whenGetElementByInvalidIndexThenReturnException() {
        SimpleArrayList<Integer> integerArray = new SimpleArrayList<>(2);
        integerArray.add(3);
        integerArray.add(5);
        assertThrows(IndexOutOfBoundsException.class, () -> {
            integerArray.get(2);
        });
    }

    @Test
    public void whenSetNewValueAndGetThisElementThenReturnElementWithNewValue() {
        SimpleArrayList<Integer> integerArray = new SimpleArrayList<>(2);
        integerArray.add(3);
        integerArray.add(5);
        integerArray.set(0, 33);
        integerArray.set(1, 55);
        assertEquals(33, integerArray.get(0));
        assertEquals(55, integerArray.get(1));
    }

    @Test
    public void whenSetNewValueByInvalidIndexThenReturnException() {
        SimpleArrayList<Integer> integerArray = new SimpleArrayList<>(1);
        integerArray.add(3);
        assertThrows(IndexOutOfBoundsException.class, () -> {
            integerArray.set(1, 5);
        });
    }

    @Test
    public void whenDeleteElementByValidIndexThenDeletingSuccess() {
        SimpleArrayList<Double> doubleArray = new SimpleArrayList<>(2);
        doubleArray.add(3.0);
        doubleArray.add(5.0);
        doubleArray.delete(0);
        assertEquals(5.0, doubleArray.get(0));
    }

    @Test
    public void whenDeleteElementByInvalidIndexThenReturnException() {
        SimpleArrayList<Double> doubleArray = new SimpleArrayList<>(1);
        assertThrows(IndexOutOfBoundsException.class, () -> {
            doubleArray.delete(2);
        });
    }

    @Test
    public void whenGetIteratorThenReturnIterator() {
        SimpleArrayList<Integer> integerArray = new SimpleArrayList<>(2);
        integerArray.add(3);
        integerArray.add(5);
        Iterator<Integer> it = integerArray.iterator();
        assertTrue(it.hasNext());
        assertTrue(it.hasNext());
        assertEquals(3, it.next());
        assertTrue(it.hasNext());
        assertEquals(5, it.next());
        assertFalse(it.hasNext());
    }

    @Test
    public void whenHasNextFalseAndCallNextThenReturnException() {
        SimpleArrayList<Integer> integerArray = new SimpleArrayList<>(1);
        Iterator<Integer> it = integerArray.iterator();
        it.next();
        assertThrows(NoSuchElementException.class, () -> {
            it.next();
        });
    }

    @Test
    public void whenValueIsContains() {
        SimpleArrayList<Integer> integerArray = new SimpleArrayList<>(2);
        integerArray.add(2);
        integerArray.add(5);
        assertTrue(integerArray.contains(5));
        assertFalse(integerArray.contains(51));
        SimpleArrayList<String> stringArray = new SimpleArrayList<>(2);
        stringArray.add("23");
        stringArray.add("15");
        assertTrue(stringArray.contains("15"));
        assertFalse(stringArray.contains("25"));
    }

    @Test
    public void whenSortArrayList() {
        SimpleArrayList<Integer> integerArray = new SimpleArrayList<>(3);
        integerArray.add(7);
        integerArray.add(2);
        integerArray.add(5);
        integerArray.add(4);
        assertEquals(13, integerArray.getSize());
        integerArray.sort(Integer::compareTo);
        assertEquals(2, integerArray.get(0));
        assertEquals(4, integerArray.get(1));
        assertEquals(5, integerArray.get(2));
        assertEquals(7, integerArray.get(3));
        assertEquals(13, integerArray.getSize());
    }
}
