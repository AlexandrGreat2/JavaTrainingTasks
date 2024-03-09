package org.example;

import java.util.Objects;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class FizzBuzz {
    private int n;
    private LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>();

    public FizzBuzz(int n) {
        this.n = n;
    }

    public void fizz() {
        for (int i = 1; i <= n; i++) {
            if (i % 5 == 0) {
                queue.offer("fizz");
            }
        }
    }

    public void buzz() {
        for (int i = 1; i <= n; i++) {
            if (i % 3 == 0) {
                queue.offer("buzz");
            }
        }
    }

    public void fizzbuzz() {
        for (int i = 1; i <= n; i++) {
            if (i % 15 == 0) {
                queue.offer("fizzbuzz");
            }
        }
    }

    public void number() {
        for (int i = 1; i <= n; i++) {
            try {
                String output = queue.poll(100, TimeUnit.MILLISECONDS); // Wait for 100 milliseconds
                if (Objects.equals(output, "fizz") || Objects.equals(output, "buzz") || Objects.equals(output, "fizzbuzz")) {
                    System.out.println(output);
                } else {
                    System.out.println(i);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
