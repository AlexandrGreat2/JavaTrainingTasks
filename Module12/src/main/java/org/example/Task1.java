package org.example;

import java.util.List;
import java.util.stream.IntStream;

public class Task1 {
    public static String filterNames(List<String> names) {
        StringBuffer result = new StringBuffer();

        IntStream.range(0, names.size())
                .filter(i -> i % 2 == 0)
                .forEach(i -> //add to result string number + ". " + names.get(i) + ", "
                        result.append(i + 1)
                              .append(". ")
                              .append(names.get(i))
                              .append(", "));

        return result.delete( result.length() - 2, result.length() ).toString();
    }

    public static void main(String[] args) {
        List<String> names = List.of(
                "Ivan",
                "Peter",
                "John",
                "Mary",
                "Bob",
                "Peter",
                "John",
                "Mary",
                "Bob"
        );
        String result = filterNames(names);
        System.out.print(result);
    }
}