package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserToJson {
    public static void main(String[] args) {
        usersToJson("F:\\java\\javaTrainingTasks\\JavaTrainingTasks\\Module10\\src\\main\\java\\org\\example\\file2.txt", "F:\\java\\javaTrainingTasks\\JavaTrainingTasks\\Module10\\src\\main\\java\\org\\example\\user.json");
    }

    private static void usersToJson(String inputFileName, String outputFileName) {
        List<User> users = readUsersFromFile(inputFileName);
        writeUsersToJsonFile(users, outputFileName);
    }

    private static List<User> readUsersFromFile(String fileName) {
        List<User> users = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            // Пропустимо перший рядок, який є заголовком
            reader.readLine();

            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(" ");
                String name = data[0];
                int age = Integer.parseInt(data[1]);
                users.add(new User(name, age));
            }
        } catch (Exception ignored) {

        }

        return users;
    }

    private static void writeUsersToJsonFile(List<User> users, String outputFileName) {
        try (FileWriter writer = new FileWriter(outputFileName)) {
            writer.write("[\n");
            int iteration = 0;
            String commaText = ",%n";
            for (User user : users) {
                if(iteration != 0) {
                    writer.write(String.format(",%n"));
                }
                iteration++;
                writer.write(String.format("    {\n        \"name\": \"%s\",\n        \"age\": %d\n    }",
                        user.getName(), user.getAge()));
            }

            writer.write("\n]");
        } catch (Exception ignored) {

        }
    }


    //try to do immutable class
    static class User {
        private final String name;
        private final int age;

        public User(String name, int age) {
            this.name = name;
            this.age = age;
        }

        // Getters
        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }
    }
}
