package org.example;

import java.util.Objects;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class FizzBuzz {
    private int n;
    private int currentNumber = 1;
    private Object lock = new Object();

    public FizzBuzz(int n) {
        this.n = n;
    }

    public void fizz() {
        while (currentNumber <= n) {
            synchronized (lock) {
                if (currentNumber % 3 == 0 && currentNumber % 5 != 0) {
                    System.out.println("fizz");
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
                    System.out.println("buzz");
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
                    System.out.println("fizzbuzz");
                    currentNumber++;
                    lock.notifyAll();
                }
            }
        }
    }

    public void number() {
        while (currentNumber <= n) {
            synchronized (lock) {
                if (currentNumber % 3 != 0 && currentNumber % 5 != 0) {
                    System.out.println(currentNumber);
                    currentNumber++;
                    lock.notifyAll();
                }
            }
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
    }
}
