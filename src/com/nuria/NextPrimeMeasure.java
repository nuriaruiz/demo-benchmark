package com.nuria;

import org.openjdk.jmh.annotations.*;

import java.math.BigInteger;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

@SuppressWarnings("ALL")
@Warmup(iterations = 20, time =5, timeUnit = TimeUnit.SECONDS)
@Fork(5)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(java.util.concurrent.TimeUnit.MILLISECONDS)
public class NextPrimeMeasure {

    @State(Scope.Benchmark)
    public static class MyBenchmarkState {

        @Param({"10", "100", "1000"})
        public int size;

        @Param({"1", "5", "10"})
        public int count;
    }

    @Benchmark
    public BigInteger nextProbablePrimeUsingFor(MyBenchmarkState state) {
        BigInteger start = new BigInteger(state.size, new java.util.Random());
        for (int i = 0; i < state.count; i++) {
            start = start.nextProbablePrime();
        }
        return start;
    }

    @Benchmark
    public BigInteger nextProbablePrimeUsingStream(MyBenchmarkState state) {
        BigInteger start = new BigInteger(state.size, new java.util.Random());
        return IntStream.range(0, state.count)
                .mapToObj(i -> start)
                .reduce((prev, next) -> prev.nextProbablePrime())
                .orElse(start);
    }

    @Benchmark
    public BigInteger nextProbablePrimeUsingParallelStream(MyBenchmarkState state) {
        BigInteger start = new BigInteger(state.size, new java.util.Random());
        return IntStream.range(0, state.count)
                .parallel()
                .mapToObj(i -> start)
                .reduce((prev, next) -> prev.nextProbablePrime())
                .orElse(start);
    }

}
