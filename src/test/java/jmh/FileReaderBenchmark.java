package jmh;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import senberg.faster.FileReader;
import senberg.faster.IntSet;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import static java.util.concurrent.TimeUnit.SECONDS;

@Warmup(iterations = 3, batchSize = 5, time = 2, timeUnit = SECONDS)
@Measurement(iterations = 3, time = 2, timeUnit = SECONDS)
@Fork(value = 3, warmups = 3)
@Timeout(time = 3, timeUnit = SECONDS)
public class FileReaderBenchmark {
    @Benchmark
    public String testReadString1() throws IOException {
        return FileReader.readString1("src/test/resources/test.xml");
    }
    @Benchmark
    public String testReadString2() throws IOException {
        return FileReader.readString2("src/test/resources/test.xml");
    }
    @Benchmark
    public String testReadString3() throws IOException {
        return FileReader.readString3("src/test/resources/test.xml");
    }
    @Benchmark
    public String testReadString4() throws IOException {
        return FileReader.readString4("src/test/resources/test.xml");
    }
    @Benchmark
    public String testReadString5() throws IOException {
        return FileReader.readString5("src/test/resources/test.xml");
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder().include(FileReaderBenchmark.class.getSimpleName()).build();
        new Runner(opt).run();
    }
}
