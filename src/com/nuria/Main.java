package com.nuria;

import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class Main {

    public static void main(String[] args) {
        Options options = new OptionsBuilder()
                .include(NextPrimeMeasure.class.getSimpleName())
                .build();
        try {
            new Runner(options).run();
        } catch (RunnerException e) {
            e.printStackTrace();
        }
    }
}
