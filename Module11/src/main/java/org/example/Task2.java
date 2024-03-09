package org.example;

import java.util.List;
import java.util.stream.Collectors;

public class Task2 {
    public static List<String> sort(List<String> names) {
//        List<String> result = Stream.of(names.stream()
//                .map(String::toUpperCase) //to UpperCase
//                .sorted()).toList();
//        return names.stream()
//                .map(String::toUpperCase) //to UpperCase
//                .sorted()
//                .toList();
//        List<String> result = names.stream()
//                .map(String::toUpperCase)
//                //to UpperCase
//                .sorted()
//                .collect(Collectors.toList());

        //todo Why Stream.toList() not allowed???? It is Java 17.0.4
        return names.stream()
                        .map(String::toUpperCase)
                        .sorted((s1, s2) -> s2.compareTo(s1))
                        .collect(Collectors.toList());
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
        List<String> result = sort(names);
        System.out.println(result);
    }
}
