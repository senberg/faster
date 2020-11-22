package senberg.faster;

public abstract class BitHacks {
    private static final int MAX_VALUE_16_BIT = 65536;
    private static final int[] LOOKUP_LOG_16_BIT;

    static {
        LOOKUP_LOG_16_BIT = new int[MAX_VALUE_16_BIT];
        LOOKUP_LOG_16_BIT[0] = -1;

        for (int i = 1; i < MAX_VALUE_16_BIT; i++) {
            LOOKUP_LOG_16_BIT[i] = 1 + LOOKUP_LOG_16_BIT[i / 2];
        }
    }

    /**
     * @param input should be not be negative
     */
    public static int roundUpToPowerOfTwo(int input) {
        input--;
        input |= input >> 1;
        input |= input >> 2;
        input |= input >> 4;
        input |= input >> 8;
        input |= input >> 16;
        return ++input;
    }

    /**
     * @param input should be not be negative
     */
    public static int logBaseTwo(int input) {
        if (input < MAX_VALUE_16_BIT) {
            return LOOKUP_LOG_16_BIT[input];
        } else {
            return LOOKUP_LOG_16_BIT[input >> 16] + 16;
        }
    }

    /**
     * @param modulo should be not be negative
     */
    public static int positiveModulo(int input, int modulo){
        int result = input % modulo;
        return (result >= 0) ? result : result + modulo;
    }
}
