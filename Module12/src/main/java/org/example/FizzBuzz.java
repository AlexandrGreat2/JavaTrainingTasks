package org.example;

import java.util.Objects;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class FizzBuzz {
    private int n;
    private int currentNumber = 1;
    private Object lock = new Object();

    private LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>();

    public FizzBuzz(int n) {
        this.n = n;
    }

    public void fizz() {
        while (currentNumber <= n) {
            synchronized (lock) {
                if (currentNumber % 3 == 0 && currentNumber % 5 != 0) {
                    queue.offer("fizz");
                    currentNumber++;
                    lock.notifyAll();
                }
            }
        }
    }

    public void buzz() {
        while (currentNumber <= n) {
            synchronized (lock) {
                if (currentNumber % 5 == 0 && currentNumber % 3 != 0) {
                    queue.offer("buzz");
                    currentNumber++;
                    lock.notifyAll();
                }
            }
        }
    }

    public void fizzbuzz() {
        while (currentNumber <= n) {
            synchronized (lock) {
                if (currentNumber % 3 == 0 && currentNumber % 5 == 0) {
                    queue.offer("fizzbuzz");
                    currentNumber++;
                    lock.notifyAll();
                }
            }
        }
    }

    public void number() {


        while (currentNumber <= n) {
            synchronized (lock) {
                try {
                    if (currentNumber % 3 != 0 && currentNumber % 5 != 0) {
                        queue.offer(String.valueOf(currentNumber));
                        currentNumber++;
                        lock.notifyAll();
                    }
                } catch (Exception ignored) {

                }
            }
        }

    }

    public void printQueue() throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            System.out.println(queue.take());
        }
    }

    public static void main(String[] args) {
        FizzBuzz fizzBuzz = new FizzBuzz(15);
        Thread threadA = new Thread(fizzBuzz::fizz);
        Thread threadB = new Thread(fizzBuzz::buzz);
        Thread threadC = new Thread(fizzBuzz::fizzbuzz);
        Thread threadD = new Thread(fizzBuzz::number);

        threadA.start();
        threadB.start();
        threadC.start();
        threadD.start();
        try {
            fizzBuzz.printQueue();
        } catch (InterruptedException ignored) {

        }

    }
}
