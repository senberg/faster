package senberg.faster;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class IntSetTests {
    @Test
    void testEmptySizeClear() {
        IntSet intSet = new IntSet();
        assertTrue(intSet.isEmpty());
        assertEquals(0, intSet.size());
        intSet.add(11);
        assertEquals(1, intSet.size());
        intSet.add(66);
        assertEquals(2, intSet.size());
        intSet.add(11);
        assertEquals(2, intSet.size());
        intSet.clear();
        assertTrue(intSet.isEmpty());
        assertEquals(0, intSet.size());
        intSet.add(33);
        assertEquals(1, intSet.size());
        intSet.add(11);
        assertEquals(2, intSet.size());
        intSet.add(11);
        assertEquals(2, intSet.size());
    }

    @Test
    void testContainsAddRemove() {
        IntSet intSet = new IntSet();
        assertFalse(intSet.contains(66));
        intSet.add(66);
        assertTrue(intSet.contains(66));
        intSet.add(66);
        assertTrue(intSet.contains(66));
        assertFalse(intSet.contains(33));
        intSet.add(33);
        assertTrue(intSet.contains(33));
        assertTrue(intSet.contains(66));
        intSet.remove(66);
        assertFalse(intSet.contains(66));
        intSet.remove(66);
        assertFalse(intSet.contains(66));
        intSet.remove(33);
        assertFalse(intSet.contains(33));
        assertFalse(intSet.contains(66));
    }

    @Test
    void testSet() {
        IntSet intSet = new IntSet();
        assertTrue(intSet.isEmpty());
        assertFalse(intSet.contains(66));
        assertFalse(intSet.contains(0));
        intSet.set(66, false);
        assertTrue(intSet.isEmpty());
        intSet.set(66, false);
        assertTrue(intSet.isEmpty());
        intSet.set(0, false);
        assertTrue(intSet.isEmpty());
        assertFalse(intSet.contains(66));
        assertFalse(intSet.contains(0));
        intSet.set(66, true);
        assertEquals(1, intSet.size());
        intSet.set(0, true);
        assertEquals(2, intSet.size());
        intSet.set(0, true);
        assertEquals(2, intSet.size());
        assertTrue(intSet.contains(66));
        assertTrue(intSet.contains(0));
        intSet.set(66, false);
        assertEquals(1, intSet.size());
        intSet.set(0, false);
        assertTrue(intSet.isEmpty());
        assertFalse(intSet.contains(66));
        assertFalse(intSet.contains(0));
    }

    @Test
    void testToArray() {
        IntSet intSet = new IntSet();
        intSet.set(Integer.MAX_VALUE, true);
        intSet.set(Integer.MIN_VALUE, true);
        intSet.set(22, true);
        intSet.set(33, true);
        intSet.set(33, false);
        intSet.set(0, true);
        int[] result = intSet.toArray();
        assertEquals(4, result.length);
        assertEquals(Integer.MIN_VALUE, result[0]);
        assertEquals(0, result[1]);
        assertEquals(22, result[2]);
        assertEquals(Integer.MAX_VALUE, result[3]);
    }

    @Test
    void testComplexRemoval() {
        IntSet intSet = new IntSet();
        intSet.add(0);
        intSet.add(16);
        intSet.remove(16);
        int[] result = intSet.toArray();
        assertEquals(1, result.length);
        assertEquals(0, result[0]);
        intSet.add(16);
        intSet.remove(0);
        result = intSet.toArray();
        assertEquals(1, result.length);
        assertEquals(16, result[0]);
        intSet.add(0);
        intSet.add(32);
        intSet.remove(0);
        result = intSet.toArray();
        assertEquals(2, result.length);
        assertEquals(16, result[0]);
        assertEquals(32, result[1]);
        intSet.add(0);
        intSet.remove(16);
        result = intSet.toArray();
        assertEquals(2, result.length);
        assertEquals(32, result[0]);
        assertEquals(0, result[1]);
        intSet.clear();
        intSet.add(15);
        intSet.add(31);
        intSet.add(47);
        intSet.remove(15);
        result = intSet.toArray();
        assertEquals(2, result.length);
        assertEquals(47, result[0]);
        assertEquals(31, result[1]);
        intSet.add(15);
        intSet.add(63);
        intSet.remove(47);
        result = intSet.toArray();
        assertEquals(3, result.length);
        assertEquals(15, result[0]);
        assertEquals(63, result[1]);
        assertEquals(31, result[2]);
        intSet.add(47);
        intSet.remove(31);
        result = intSet.toArray();
        assertEquals(3, result.length);
        assertEquals(63, result[0]);
        assertEquals(47, result[1]);
        assertEquals(15, result[2]);
    }

    @Test
    void testGrowing() {
        IntSet intSet = new IntSet();
        intSet.add(1);
        intSet.add(2);
        intSet.add(3);
        intSet.add(4);
        intSet.add(5);
        intSet.add(6);
        intSet.add(7);
        intSet.add(8);
        intSet.add(9);
        intSet.add(10);
        intSet.add(11);
        intSet.add(12);
        intSet.add(13);
        intSet.add(14);
        intSet.add(15);
        intSet.add(16);
        intSet.add(17);
        int[] result = intSet.toArray();
        assertEquals(17, result.length);
    }
}
