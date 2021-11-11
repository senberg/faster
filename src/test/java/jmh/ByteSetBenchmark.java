package jmh;

import org.openjdk.jmh.annotations.*;
import senberg.faster.ByteSet;
import senberg.faster.ByteSetSimple;

import java.util.HashSet;
import java.util.Set;

import static java.util.concurrent.TimeUnit.SECONDS;

@Warmup(iterations = 3, batchSize = 5, time = 2, timeUnit = SECONDS)
@Measurement(iterations = 3, time = 2, timeUnit = SECONDS)
@Fork(value = 3, warmups = 3)
@Timeout(time = 3, timeUnit = SECONDS)
public class ByteSetBenchmark {
    @Benchmark
    public byte[] fullTestByteSet() {
        ByteSet byteSet = new ByteSet();
        byteSet.add((byte) 123);
        byteSet.add((byte) 12);
        byteSet.add((byte) 1);
        byteSet.remove((byte) 2);
        byteSet.remove((byte) 1);
        int s1 = byteSet.size();
        boolean b1 = byteSet.contains(12);
        byteSet.clear();
        byteSet.add((byte) 123);
        byteSet.add((byte) 13);
        byteSet.add((byte) 1);
        byteSet.remove((byte) 2);
        byteSet.remove((byte) 1);
        int s2 = byteSet.size();
        boolean b2 = byteSet.contains(s1 + s2);
        byteSet.set((byte) 0, b1 && b2);
        return byteSet.toArray();
    }

    @Benchmark
    public Byte[] fullTestSetofByte() {
        Set<Byte> byteSet = new HashSet<>(16, 0.5f);
        byteSet.add((byte) 123);
        byteSet.add((byte) 12);
        byteSet.add((byte) 1);
        byteSet.remove((byte) 2);
        byteSet.remove((byte) 1);
        int s1 = byteSet.size();
        boolean b1 = byteSet.contains((byte) 12);
        byteSet.clear();
        byteSet.add((byte) 123);
        byteSet.add((byte) 13);
        byteSet.add((byte) 1);
        byteSet.remove((byte) 2);
        byteSet.remove((byte) 1);
        int s2 = byteSet.size();
        boolean b2 = byteSet.contains((byte) (s1 + s2));
        if (b1 && b2) {
            byteSet.add((byte) 0);
        } else {
            byteSet.remove((byte) 0);
        }
        return byteSet.toArray(new Byte[0]);
    }

    @Benchmark
    public boolean limitedTestByteSetSimple() {
        ByteSetSimple byteSet = new ByteSetSimple();
        byteSet.add((byte) 123);
        byteSet.add((byte) 12);
        byteSet.add((byte) 1);
        byteSet.remove((byte) 2);
        byteSet.remove((byte) 1);
        boolean b1 = byteSet.contains(12);
        byteSet.clear();
        byteSet.add((byte) 123);
        byteSet.add((byte) 13);
        byteSet.add((byte) 1);
        byteSet.remove((byte) 2);
        byteSet.remove((byte) 1);
        boolean b2 = byteSet.contains(0);
        byteSet.set((byte) 0, b1 && b2);
        return b1 && b2;
    }

    @Benchmark
    public boolean limitedTestByteSet() {
        ByteSet byteSet = new ByteSet();
        byteSet.add((byte) 123);
        byteSet.add((byte) 12);
        byteSet.add((byte) 1);
        byteSet.remove((byte) 2);
        byteSet.remove((byte) 1);
        boolean b1 = byteSet.contains(12);
        byteSet.clear();
        byteSet.add((byte) 123);
        byteSet.add((byte) 13);
        byteSet.add((byte) 1);
        byteSet.remove((byte) 2);
        byteSet.remove((byte) 1);
        boolean b2 = byteSet.contains(0);
        byteSet.set((byte) 0, b1 && b2);
        return b1 && b2;
    }

    @Benchmark
    public boolean limitedTestSetOfByte() {
        Set<Byte> byteSet = new HashSet<>();
        byteSet.add((byte) 123);
        byteSet.add((byte) 12);
        byteSet.add((byte) 1);
        byteSet.remove((byte) 2);
        byteSet.remove((byte) 1);
        boolean b1 = byteSet.contains((byte) 12);
        byteSet.clear();
        byteSet.add((byte) 123);
        byteSet.add((byte) 13);
        byteSet.add((byte) 1);
        byteSet.remove((byte) 2);
        byteSet.remove((byte) 1);
        boolean b2 = byteSet.contains((byte) 0);
        if (b1 && b2) {
            byteSet.add((byte) 0);
        } else {
            byteSet.remove((byte) 0);
        }
        return b1 && b2;
    }
}
