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

        MyLinkedList<String> linkedList = new MyLinkedList<String>();
        linkedList.add('1');
        linkedList.add('2');
        linkedList.add('3');
        System.out.println(linkedList.get(0));
        System.out.println(linkedList.get(1));
        System.out.println(linkedList.get(2));
        linkedList.remove(2);
        // trow planed exception uncomment to test
        //System.out.println(linkedList.get(2));
        linkedList.clear();
        // trow planed exception uncomment to test
        //System.out.println(linkedList.get(2));

        MyQueue queue = new MyQueue<String>();
        queue.add('1');
        queue.add('2');
        queue.add('3');

        System.out.println(queue.peek());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());

        MyHashMap hashMap = new MyHashMap();
        hashMap.put(1,'1');
        System.out.println(hashMap.get(1));
        hashMap.put(1,"11");
        System.out.println(hashMap.get(1));
        hashMap.put(3,'3');
        System.out.println(hashMap.get(3));
        System.out.println(hashMap.size());
        hashMap.remove(3);
        hashMap.clear();


        MyHashMap myMap = new MyHashMap();

        myMap.put("key1", "value1");
        myMap.put("key2", "value2");
        myMap.put("key3", "value3");

        System.out.println("Size: " + myMap.size());

        System.out.println("Values:");
        System.out.println("key1: " + myMap.get("key1"));
        System.out.println("key2: " + myMap.get("key2"));
        System.out.println("key3: " + myMap.get("key3"));

        myMap.remove("key2");

        System.out.println("Size after removing key2: " + myMap.size());

        System.out.println("Values after removal:");
        System.out.println("key1: " + myMap.get("key1"));
        System.out.println("key2: " + myMap.get("key2"));
        System.out.println("key3: " + myMap.get("key3"));

        myMap.clear();

        System.out.println("Size after clearing: " + myMap.size());
    }
}