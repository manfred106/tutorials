package com.baeldung.performance;

import java.util.StringJoiner;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@Warmup(iterations = 1, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 3, time = 1, timeUnit = TimeUnit.SECONDS)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Fork(value = 1)
public class BatchConcatBenchmark {

    private static final String TOKEN = "string";
    private static final String[] DATA_ONE_THOUSAND = prepareData(1000);
    private static final String[] DATA_TEN_THOUSAND = prepareData(10000);
    private static final String[] DATA_ONE_HUNDRED_THOUSAND = prepareData(100000);

    private static final String FORMAT_STR_1000 = getFormatStr(1000);
    private static final String FORMAT_STR_10000 = getFormatStr(10000);
    private static final String FORMAT_STR_100000 = getFormatStr(100000);

    private static String[] prepareData(int size) {
        String[] data = new String[size];
        for (int n=0;n<size;n++) data[n] = TOKEN;
        return data;
    }

    private static String getFormatStr(int iterations) {
        StringBuilder builder = new StringBuilder();
        for (int n=0;n<iterations;n++) builder.append("%s");
        return builder.toString();
    }

    @Benchmark
    public static void concatByPlusBy1000(Blackhole blackhole) {
        concatByPlus(DATA_ONE_THOUSAND, blackhole);
    }

    @Benchmark
    public static void concatByPlusBy10000(Blackhole blackhole) {
        concatByPlus(DATA_TEN_THOUSAND, blackhole);
    }

    @Benchmark
    public static void concatByPlusBy100000(Blackhole blackhole) {
        concatByPlus(DATA_ONE_HUNDRED_THOUSAND, blackhole);
    }

    public static void concatByPlus(String[] data, Blackhole blackhole) {
        String concatString = String.join("", data);
        blackhole.consume(concatString);
    }

    //    @Benchmark
    //    public static void concatByConcatBy1000(Blackhole blackhole) {
    //        concatByConcat(DATA_ONE_THOUSAND, blackhole);
    //    }
    //
    //    @Benchmark
    //    public static void concatByConcatBy10000(Blackhole blackhole) {
    //        concatByConcat(DATA_TEN_THOUSAND, blackhole);
    //    }
    //
    //    @Benchmark
    //    public static void concatByConcatBy100000(Blackhole blackhole) {
    //        concatByConcat(DATA_ONE_HUNDRED_THOUSAND, blackhole);
    //    }
    //
    //    public static void concatByConcat(int iterations, Blackhole blackhole) {
    //        String concatString = String.concat("", data);
    //        blackhole.consume(concatString);
    //    }

    @Benchmark
    public static void concatByJoinBy1000(Blackhole blackhole) {
        concatByJoin(DATA_ONE_THOUSAND, blackhole);
    }

    @Benchmark
    public static void concatByJoinBy10000(Blackhole blackhole) {
        concatByJoin(DATA_TEN_THOUSAND, blackhole);
    }

    @Benchmark
    public static void concatByJoinBy100000(Blackhole blackhole) {
        concatByJoin(DATA_ONE_HUNDRED_THOUSAND, blackhole);
    }

    public static void concatByJoin(String[] data, Blackhole blackhole) {
        String concatString = String.join("", data);
        blackhole.consume(concatString);
    }

    @Benchmark
    public static void concatByStringJoinerrBy1000(Blackhole blackhole) {
        concatByStringJoiner(DATA_ONE_THOUSAND, blackhole);
    }

    @Benchmark
    public static void concatByStringJoinerBy10000(Blackhole blackhole) {
        concatByStringJoiner(DATA_TEN_THOUSAND, blackhole);
    }

    @Benchmark
    public static void concatByStringJoinerBy100000(Blackhole blackhole) {
        concatByStringJoiner(DATA_ONE_HUNDRED_THOUSAND, blackhole);
    }

    public static void concatByStringJoiner(String[] data, Blackhole blackhole) {
        StringJoiner concatBuf = new StringJoiner("");
        for (int n=0; n<data.length; n++) concatBuf.add(data[n]);
        blackhole.consume(concatBuf);
    }

    @Benchmark
    public static void concatByStringBufferBy1000(Blackhole blackhole) {
        concatByStringBuffer(DATA_ONE_THOUSAND, blackhole);
    }

    @Benchmark
    public static void concatByStringBufferBy10000(Blackhole blackhole) {
        concatByStringBuffer(DATA_TEN_THOUSAND, blackhole);
    }

    @Benchmark
    public static void concatByStringBufferBy100000(Blackhole blackhole) {
        concatByStringBuffer(DATA_ONE_HUNDRED_THOUSAND, blackhole);
    }

    public static void concatByStringBuffer(String[] data, Blackhole blackhole) {
        StringBuffer concatBuf = new StringBuffer("");
        for (int n=0; n<data.length; n++) concatBuf.append(data[n]);
        blackhole.consume(concatBuf);
    }

    @Benchmark
    public static void concatByStringBuilderBy1000(Blackhole blackhole) {
        concatByStringBuilder(DATA_ONE_THOUSAND, blackhole);
    }

    @Benchmark
    public static void concatByStringBuilderBy10000(Blackhole blackhole) {
        concatByStringBuilder(DATA_TEN_THOUSAND, blackhole);
    }

    @Benchmark
    public static void concatByStringBuilderBy100000(Blackhole blackhole) {
        concatByStringBuilder(DATA_ONE_HUNDRED_THOUSAND, blackhole);
    }

    public static void concatByStringBuilder(String[] data, Blackhole blackhole) {
        StringBuilder concatBuf = new StringBuilder("");
        for (int n=0; n<data.length; n++) concatBuf.append(data[n]);
        blackhole.consume(concatBuf);
    }


    public static void main(String[] args) throws Exception {
        Options options = new OptionsBuilder()
            .include(BatchConcatBenchmark.class.getSimpleName()).threads(1)
            .shouldFailOnError(true)
            .shouldDoGC(true)
            .jvmArgs("-server").build();
        new Runner(options).run();
    }

}