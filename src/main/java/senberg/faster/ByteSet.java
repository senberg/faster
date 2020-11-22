package senberg.faster;

public class ByteSet {
    public static final byte[] EMPTY_SET = new byte[256];
    public static final byte SET = 1;
    public static final byte NOT_SET = 0;
    private static final int BYTE_MASK = 0xFF;

    private final byte[] data = new byte[256];
    private int size = 0;

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean contains(byte b) {
        return data[b & BYTE_MASK] == SET;
    }

    /**
     * @param b should be 0-254
     */
    public boolean contains(int b) {
        return data[b] == SET;
    }

    public void add(byte b) {
        int index = b & BYTE_MASK;
        size += SET ^ data[index];
        data[index] = SET;
    }

    /**
     * @param b should be 0-254
     */
    public void add(int b) {
        size += SET ^ data[b];
        data[b] = SET;
    }

    public void remove(byte b) {
        int index = b & BYTE_MASK;
        size -= NOT_SET ^ data[index];
        data[index] = NOT_SET;
    }

    /**
     * @param b should be 0-254
     */
    public void remove(int b) {
        size -= NOT_SET ^ data[b];
        data[b] = NOT_SET;
    }

    public void set(byte b, byte newValue) {
        int index = b & BYTE_MASK;
        int currentValue = data[index];
        int changed = (currentValue ^ newValue);
        size += newValue & changed;
        size -= currentValue & changed;
        data[index] = newValue;
    }

    public void set(int b, byte newValue) {
        int currentValue = data[b];
        int changed = (currentValue ^ newValue);
        size += newValue & changed;
        size -= currentValue & changed;
        data[b] = newValue;
    }

    public void set(byte b, boolean newValue) {
        set(b, newValue ? SET : NOT_SET);
    }

    /**
     * @param b should be 0-254
     */
    public void set(int b, boolean newValue) {
        set(b, newValue ? SET : NOT_SET);
    }

    public byte[] toArray() {
        byte[] result = new byte[size];
        int resultIndex = 0;

        for (int i = 0; resultIndex < size; i++) {
            if (data[i] == SET) {
                result[resultIndex] = (byte) i;
                resultIndex++;
            }
        }

        return result;
    }

    public void clear() {
        size = 0;
        System.arraycopy(EMPTY_SET, 0, data, 0, 256);
    }
}