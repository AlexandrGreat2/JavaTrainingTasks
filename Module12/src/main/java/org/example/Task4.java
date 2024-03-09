package org.example;

import java.util.stream.Stream;

public class Task4 {
    public static Stream<Long> randomGenerator(long seed, long a, long c, long m) {
        return Stream.iterate(seed, x -> (a * x + c) % m);
    }

    public static void main(String[] args) {
        long a = 25214903917L;
        long c = 11;
        long m = (long) Math.pow(2, 48);
        long seed = 0L;

        randomGenerator(seed, a, c, m)
                .limit(20)
                .forEach(System.out::println);
    }
}
