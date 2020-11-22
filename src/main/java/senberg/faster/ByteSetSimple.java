package senberg.faster;

public class ByteSetSimple {
    public static final boolean[] EMPTY_SET = new boolean[256];
    private static final int BYTE_MASK = 0xFF;

    private final boolean[] data = new boolean[256];

    public boolean contains(byte b) {
        return data[b & BYTE_MASK];
    }

    /**
     * @param b should be 0-254
     */
    public boolean contains(int b) {
        return data[b];
    }

    public void add(byte b) {
        data[b & BYTE_MASK] = true;
    }

    /**
     * @param b should be 0-254
     */
    public void add(int b) {
        data[b] = true;
    }

    public void remove(byte b) {
        data[b & BYTE_MASK] = false;
    }

    /**
     * @param b should be 0-254
     */
    public void remove(int b) {
        data[b] = false;
    }

    public void set(byte b, boolean value) {
        data[b & BYTE_MASK] = value;
    }

    /**
     * @param b should be 0-254
     */
    public void set(int b, boolean value) {
        data[b] = value;
    }

    public void clear() {
        System.arraycopy(EMPTY_SET, 0, data, 0, 256);
    }
}