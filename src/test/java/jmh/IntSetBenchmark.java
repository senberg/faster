package jmh;

import org.openjdk.jmh.annotations.*;
import senberg.faster.IntSet;

import java.util.HashSet;
import java.util.Set;

import static java.util.concurrent.TimeUnit.SECONDS;

@Warmup(iterations = 3, batchSize = 5, time = 2, timeUnit = SECONDS)
@Measurement(iterations = 3, time = 2, timeUnit = SECONDS)
@Fork(value = 3, warmups = 3)
@Timeout(time = 3, timeUnit = SECONDS)
public class IntSetBenchmark {
    @Benchmark
    public int[] fullTestIntSet() {
        IntSet intSet = new IntSet();
        intSet.add(123);
        intSet.add(12);
        intSet.add(1);
        intSet.remove(2);
        intSet.remove(1);
        int s1 = intSet.size();
        boolean b1 = intSet.contains(12);
        intSet.clear();
        intSet.add(123);
        intSet.add(13);
        intSet.add(1);
        intSet.remove(2);
        intSet.remove(1);
        int s2 = intSet.size();
        boolean b2 = intSet.contains(s1 + s2);
        intSet.set(0, b1 && b2);
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
        return intSet.toArray();
    }

    @Benchmark
    public Integer[] fullTestSetofInteger() {
        Set<Integer> intSet = new HashSet<>();
        intSet.add(123);
        intSet.add(12);
        intSet.add(1);
        intSet.remove(2);
        intSet.remove(1);
        int s1 = intSet.size();
        boolean b1 = intSet.contains(12);
        intSet.clear();
        intSet.add(123);
        intSet.add(13);
        intSet.add(1);
        intSet.remove(2);
        intSet.remove(1);
        int s2 = intSet.size();
        boolean b2 = intSet.contains((s1 + s2));
        if (b1 && b2) {
            intSet.add(0);
        } else {
            intSet.remove(0);
        }
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
        return intSet.toArray(new Integer[0]);
    }
}
