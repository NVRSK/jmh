package com.nvrsk;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
@Warmup(iterations = 5)
@Measurement(iterations = 20)
public class JmhLoopExample {

    private static final int LENGTH = 100_000;

    private List<Integer> list;

    @Setup
    public void setup() {
        List<Integer> l = new ArrayList<>();
        for (int i = 0; i < LENGTH; i++) {
            l.add(i);
        }
        list = l;
    }

    public static void main(String[] args) throws RunnerException {

        Options opt = new OptionsBuilder()
                .forks(2)
                .include(JmhLoopExample.class.getSimpleName())
                .build();

        new Runner(opt).run();
    }

    @Benchmark
    public void loopFor(Blackhole bh) {
        for (int i = 0; i < LENGTH; i++) {
            bh.consume(list.get(i)); //bh.consume() - needs to prevent from the dead-code elimination
        }
    }

    @Benchmark
    public void loopForEach(Blackhole bh) {
        for (Integer i : list) {
            bh.consume(i); //bh.consume() - needs to prevent from the dead-code elimination
        }
    }
}


