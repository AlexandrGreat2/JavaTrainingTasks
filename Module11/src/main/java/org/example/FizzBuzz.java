package org.example;

import java.util.concurrent.LinkedBlockingQueue;

public class FizzBuzz {
    private final int n;
    private final LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>();

    public FizzBuzz(int n) {
        this.n = n;
    }

    public void fizz()  {
        for (int i = 3; i <= n; i += 3) {
            if (i % 5 != 0) {
                try {
                    queue.offer("fizz");
                    String output = queue.take();
                    System.out.println(output);
                } catch (Exception ignored) {

                }
            }
        }
    }

    public void buzz()  {
        for (int i = 5; i <= n; i += 5) {
            if (i % 3 != 0) {
                try {
                    queue.offer("buzz");
                    String output = queue.take();
                    System.out.println(output);
                } catch (Exception ignored) {

                }
            }
        }
    }

    public void fizzbuzz()  {
        for (int i = 15; i <= n; i += 15) {
            try {
                queue.offer("fizzbuzz");
                String output = queue.take();
                System.out.println(output);
            } catch (Exception ignored) {

            }
        }
    }

    public void number() {
        for (int i = 1; i <= n; i++) {
            try {
                queue.offer("" + i);
                String output = queue.take();
                System.out.println(output);
            } catch (Exception ignored) {

            }
        }
    }
}
