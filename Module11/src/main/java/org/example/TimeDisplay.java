package org.example;

public class TimeDisplay {
    public static void main(String[] args) {
        Thread timeThread = new Thread(() -> {
            long startTime = System.currentTimeMillis();
            while (true) {
                long currentTime = System.currentTimeMillis();
                long elapsedTime = (currentTime - startTime) / 1000;
                System.out.println("Секунд зі старту: " + elapsedTime);
                try {
                    Thread.sleep(1000); // Sleep for 1 second
                } catch (Exception ignored) {

                }
            }
        });

        Thread messageThread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(5000); // Sleep for 5 seconds
                    System.out.println("Минуло 5 секунд");
                } catch (Exception ignored) {
                }
            }
        });

        timeThread.start();
        messageThread.start();
    }
}
