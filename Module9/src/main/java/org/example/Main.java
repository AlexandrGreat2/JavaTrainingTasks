package org.example;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        //todo: Why next sting mark as "Raw use of parameterized class 'MyArrayList' " ?
        MyArrayList myArrayList = new MyArrayList<String>();
        //todo: Unchecked call to 'add(T)' as a member of raw type 'org.example.MyArrayList' " ?
        myArrayList.add("John");
        myArrayList.add("Bill");

        System.out.println(myArrayList.get(0));
        System.out.println(myArrayList.get(1));
    }
}