package org.example;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Task3 {
    public static String extractAndSortNumbers(String[] array) {
        StringBuffer resultBuffer = new StringBuffer();
        Arrays.stream(array)
                .flatMap(s -> Arrays.stream(s.split(", ")))
                .flatMapToInt(num -> IntStream.of(Integer.parseInt(num)))
                .sorted()
                .forEach(num ->
                        resultBuffer.append(num)
                        .append(", ")
                );
        //delete last ", ";
        resultBuffer.delete(resultBuffer.length()-2,resultBuffer.length());
        return resultBuffer.toString();
    }

    public static void main(String[] args) {
        String[] array = {"1, 2, 0", "4, 5", "9, 3, 6, 10, 299, 123"};
        String result = extractAndSortNumbers(array);
        System.out.println(result);
    }
}
