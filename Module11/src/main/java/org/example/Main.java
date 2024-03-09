package org.example;

public class Main {
    //Start FizzBuzz Here
    public static void main(String[] args) {
        FizzBuzz fizzBuzz = new FizzBuzz(15);

        Thread thread1 = new Thread(fizzBuzz::fizz);
        Thread thread2 = new Thread(fizzBuzz::buzz);
        Thread thread3 = new Thread(fizzBuzz::fizzbuzz);
        Thread thread4 = new Thread(fizzBuzz::number);

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
    }
}