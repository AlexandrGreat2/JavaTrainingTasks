package org.example;

public class MyQueue<T> extends MyArrayList {

    //convert and return element with same type
    public T peek() {
        return (T) super.get(0);
    }

    public T poll() {
        T result = (T) super.get(0);
        if (result == null) {
            return null;
        }
        super.remove(0);
        return result;
    }
}
