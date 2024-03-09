package org.example;

public class MyQueue<T> {

    MyLinkedList linkedList = new MyLinkedList<T>();
    private int size = 0;

    public void add(T item) throws Exception {
        linkedList.add(item);
        size++;
    }

    private void clear() {
        linkedList.clear();
        size = 0;
    }

    //return size
    public int size() {
        return size;
    }

    //convert and return element with same type
    public T peek() {
        return (T) linkedList.get(0);
    }


    public T poll() {
        T result = (T) linkedList.get(0);
        linkedList.remove(0);
        return result;
    }
}
