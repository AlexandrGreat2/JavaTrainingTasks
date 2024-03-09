package org.example;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class WordFrequencyCounter {
    public static void main(String[] args) throws Exception {
        Map<String, Integer> wordFrequency = countWordFrequency("F:\\java\\javaTrainingTasks\\JavaTrainingTasks\\Module10\\src\\main\\java\\org\\example\\words.txt");
        printWordFrequency(wordFrequency);
    }

    private static Map<String, Integer> countWordFrequency(String fileName) throws Exception {
        //store words and frequencies
        Map<String, Integer> wordFrequency = new HashMap<>();

        //FileReader filereader = new FileReader(fileName);
        //BufferedReader reader = new BufferedReader(filereader);
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] words = line.split("\\s+");
                for (String word : words) {
                    wordFrequency.put(word, wordFrequency.getOrDefault(word, 0) + 1);
                }
            }
        } catch (Exception ignored) {

        }

        return wordFrequency;
    }

    private static void printWordFrequency(Map<String, Integer> wordFrequency) {
        wordFrequency.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .forEach(entry -> System.out.printf("%s %d%n", entry.getKey(), entry.getValue()));
    }
}
