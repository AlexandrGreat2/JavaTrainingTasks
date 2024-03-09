package org.example;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneNumberValidator {
    public static void main(String[] args) {
//        System.out.println("Hello world!");
        validatePhoneNumbers("F:\\java\\javaTrainingTasks\\JavaTrainingTasks\\Module10\\src\\main\\java\\org\\example\\file.txt");
    }


    private static void validatePhoneNumbers(String fileName) {
        //FileReader filereader = new FileReader(fileName);
        //BufferedReader reader = new BufferedReader(filereader);
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (isValidPhoneNumber(line)) {
                    System.out.println(line);
                }
            }
        } catch (Exception ignored) {
        }
    }

    private static boolean isValidPhoneNumber(String phoneNumber) {
        String regex = "(\\d{3}-\\d{3}-\\d{4})|(\\(\\d{3}\\) \\d{3}-\\d{4})";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }



}