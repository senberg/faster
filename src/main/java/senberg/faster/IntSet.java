package senberg.faster;

import static senberg.faster.BitHacks.positiveModulo;

public class IntSet {
    private static final int MAX_CAPACITY = 1 << 30;
    private int[] data;
    private boolean[] set;
    private int capacity;
    private int resizeThreshold;
    private int size;

    public IntSet() {
        this(16);
    }

    public IntSet(int maxInitialCapacity) {
        this.capacity = maxInitialCapacity;
        this.resizeThreshold = capacity / 2;
        data = new int[this.capacity];
        set = new boolean[this.capacity];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean contains(int value) {
        int position = hash(value);

        while (set[position]) {
            if (data[position] == value) {
                return true;
            } else {
                position++;
                position %= capacity;
            }
        }

        return false;
    }

    public void add(int value) {
        int position = hash(value);

        while (set[position]) {
            if (data[position] == value) {
                return;
            } else {
                position++;
                position %= capacity;
            }
        }

        data[position] = value;
        set[position] = true;
        size++;
        ensureCapacity();
    }

    private void ensureCapacity() {
        if (size > resizeThreshold && capacity < MAX_CAPACITY) {
            int oldCapacity = capacity;
            int[] oldData = data;
            boolean[] oldSet = set;

            if (capacity * 2 > MAX_CAPACITY) {
                capacity = MAX_CAPACITY;
                resizeThreshold = MAX_CAPACITY;
            } else {
                capacity = capacity * 2;
                resizeThreshold = capacity * 2;
            }

            data = new int[capacity];
            set = new boolean[capacity];

            for (int i = 0; i < oldCapacity; i++) {
                if (oldSet[i]) {
                    int value = oldData[i];
                    int position = hash(value);

                    while (set[position]) {
                        position++;
                        position %= capacity;
                    }

                    data[position] = value;
                    set[position] = true;
                }
            }
        }
    }

    public void remove(int value) {
        int position = hash(value);

        while (set[position] && data[position] != value) {
            position++;
            position %= capacity;
        }

        if (set[position]) {
            size--;
        }

        int nextPosition = position;

        while (set[position]) {
            set[position] = false;
            int nextNaturalPositon;
            int nextPositionData;

            do {
                nextPosition++;
                nextPosition %= capacity;

                if (!set[nextPosition]) {
                    return;
                }

                nextPositionData = data[nextPosition];
                nextNaturalPositon = hash(nextPositionData);
            } while ((position <= nextPosition) ? ((position < nextNaturalPositon) && (nextNaturalPositon <= nextPosition)) : ((position < nextNaturalPositon) || (nextNaturalPositon <= nextPosition)));

            set[position] = true;
            data[position] = nextPositionData;
            position = nextPosition;
        }
    }

    private int hash(int value) {
        return positiveModulo(value, capacity);
    }

    public void set(int value, boolean contained) {
        if (contained) {
            add(value);
        } else {
            remove(value);
        }
    }

    public int[] toArray() {
        int[] result = new int[size];
        int resultIndex = 0;

        for (int i = 0; resultIndex < size; i++) {
            if (set[i]) {
                result[resultIndex] = data[i];
                resultIndex++;
            }
        }

        return result;
    }

    public void clear() {
        for (int i = 0; i < capacity; i++) {
            set[i] = false;
        }

        size = 0;
    }
}