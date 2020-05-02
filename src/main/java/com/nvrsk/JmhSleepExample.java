package com.nvrsk;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
@Fork(value = 2, jvmArgs = {"-Xms2G", "-Xmx2G"})
@Warmup(iterations = 5)
@Measurement(iterations = 10)
public class JmhSleepExample {

    public static void main(String[] args) throws RunnerException {

        Options opt = new OptionsBuilder()
                .include(JmhSleepExample.class.getSimpleName())
                .build();

        new Runner(opt).run();
    }

    @Benchmark
    public void sleep() throws InterruptedException {
        Thread.sleep(500);
    }
}


