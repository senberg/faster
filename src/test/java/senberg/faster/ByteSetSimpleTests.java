package senberg.faster;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static senberg.faster.Bytes.*;

public class ByteSetSimpleTests {

    @Test
    void testContainsAddRemove() {
        ByteSetSimple byteSet = new ByteSetSimple();
        assertFalse(byteSet.contains(BxFF));
        byteSet.add(BxFF);
        assertTrue(byteSet.contains(BxFF));
        byteSet.add(BxFF);
        assertTrue(byteSet.contains(BxFF));
        assertFalse(byteSet.contains(Bx33));
        byteSet.add(Bx33);
        assertTrue(byteSet.contains(Bx33));
        assertTrue(byteSet.contains(BxFF));
        byteSet.remove(BxFF);
        assertFalse(byteSet.contains(BxFF));
        byteSet.remove(BxFF);
        assertFalse(byteSet.contains(BxFF));
        byteSet.remove(Bx33);
        assertFalse(byteSet.contains(Bx33));
        assertFalse(byteSet.contains(BxFF));
    }

    @Test
    void testSet() {
        ByteSet byteSet = new ByteSet();
        assertTrue(byteSet.isEmpty());
        assertFalse(byteSet.contains(BxFF));
        assertFalse(byteSet.contains(Bx00));
        byteSet.set(BxFF, ByteSet.NOT_SET);
        assertTrue(byteSet.isEmpty());
        byteSet.set(BxFF, ByteSet.NOT_SET);
        assertTrue(byteSet.isEmpty());
        byteSet.set(Bx00, ByteSet.NOT_SET);
        assertTrue(byteSet.isEmpty());
        assertFalse(byteSet.contains(BxFF));
        assertFalse(byteSet.contains(Bx00));
        byteSet.set(BxFF, ByteSet.SET);
        assertEquals(1, byteSet.size());
        byteSet.set(Bx00, ByteSet.SET);
        assertEquals(2, byteSet.size());
        byteSet.set(Bx00, ByteSet.SET);
        assertEquals(2, byteSet.size());
        assertTrue(byteSet.contains(BxFF));
        assertTrue(byteSet.contains(Bx00));
        byteSet.set(BxFF, ByteSet.NOT_SET);
        assertEquals(1, byteSet.size());
        byteSet.set(Bx00, ByteSet.NOT_SET);
        assertTrue(byteSet.isEmpty());
        assertFalse(byteSet.contains(BxFF));
        assertFalse(byteSet.contains(Bx00));
    }

    @Test
    void testToArray() {
        ByteSet byteSet = new ByteSet();
        byteSet.set(BxFF, ByteSet.SET);
        byteSet.set(Bx22, ByteSet.SET);
        byteSet.set(Bx33, ByteSet.SET);
        byteSet.set(Bx33, ByteSet.NOT_SET);
        byteSet.set(Bx00, ByteSet.SET);
        byte[] result = byteSet.toArray();
        assertEquals(3, result.length);
        assertEquals(Bx00, result[0]);
        assertEquals(Bx22, result[1]);
        assertEquals(BxFF, result[2]);
    }
}
