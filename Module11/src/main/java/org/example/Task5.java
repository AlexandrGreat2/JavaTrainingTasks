package org.example;

import java.util.Iterator;
import java.util.Objects;
import java.util.stream.Stream;

public class Task5 {

    public static <T> Stream<T> mix(Stream<T> first, Stream<T> second) {
        Iterator<T> iterator1 = first.iterator();
        Iterator<T> iterator2 = second.iterator();

        return Stream.generate(() -> {
            //generate logic
            if (iterator1.hasNext() && iterator2.hasNext()) {
                return Stream.of(iterator1.next(), iterator2.next());
            } else {
                return null;
            }
        })
        //stop condition. Objects::nonNull - it is IntelliJ IDEA's idea :)
        .takeWhile(Objects::nonNull)
        .flatMap(o -> o);
    }

    public static void main(String[] args) {
        Stream<Integer> stream1 = Stream.of(11, 12, 13, 14, 15, 16, 17, 18, 19);
        Stream<Integer> stream2 = Stream.of(21, 22, 23, 24, 25, 26, 27);

        mix(stream1, stream2)
                .limit(20)
                .forEach(System.out::println);
    }
}
