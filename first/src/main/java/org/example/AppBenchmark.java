package org.example;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.random.RandomGenerator;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.Warmup;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Warmup(iterations = 10, time = 2, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(iterations = 20, time = 2, timeUnit = TimeUnit.MILLISECONDS)
@Fork(value = 1)
public class AppBenchmark {
    private List<Integer> intsList;
    private StreamAPITasks streamAPITasks;
    private ParallelStreamAPITasks parallelStreamAPITasks;

    @Setup
    public void setup() {
        streamAPITasks = new StreamAPITasks();
        parallelStreamAPITasks = new ParallelStreamAPITasks();
        intsList = RandomGenerator.getDefault().ints(10000000, 0, 100).boxed().toList();
    }

    @Benchmark
    public int benchmarkStreamAPIFindSum() {
        return streamAPITasks.sumInts(intsList);
    }
    @Benchmark
    public double benchmarkStreamAPIAverage() {
        return streamAPITasks.findAverage(intsList);
    }
    @Benchmark
    public double benchmarkStreamAPIStandardDeviation() {
        return streamAPITasks.findStandardDeviation(intsList);
    }
    @Benchmark
    public List<Integer> benchmarkStreamAPIMultiplyBy2() {
        return streamAPITasks.multiplyBy2(intsList);
    }
    @Benchmark
    public List<Integer> benchmarkStreamAPIDivisibleBy3() {
        return streamAPITasks.filterDivisibleBy3(intsList);
    }

    @Benchmark
    public int benchmarkParallelStreamAPIFindSum() {
        return parallelStreamAPITasks.sumInts(intsList);
    }
    @Benchmark
    public double benchmarkParallelStreamAPIAverage() {
        return parallelStreamAPITasks.findAverage(intsList);
    }
    @Benchmark
    public double benchmarkParallelStreamAPIStandardDeviation() {
        return parallelStreamAPITasks.findStandardDeviation(intsList);
    }
    @Benchmark
    public List<Integer> benchmarkParallelStreamAPIMultiplyBy2() {
        return parallelStreamAPITasks.multiplyBy2(intsList);
    }
    @Benchmark
    public List<Integer> benchmarkParallelStreamAPIDivisibleBy3() {
        return parallelStreamAPITasks.filterDivisibleBy3(intsList);
    }
}
