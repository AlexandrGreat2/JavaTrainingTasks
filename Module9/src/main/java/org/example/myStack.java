package org.example;

public class myStack<T> extends MyArrayList {

    public void push(T item) throws Exception {
        super.add(item);

    }

    //convert and return element with same type
    public T peek() {
        return (T) super.get(super.size()-1);
    }


    public T pop() {
        T result = (T) super.get(super.size()-1);
        super.remove(super.size()-1);
        return result;
    }
}
