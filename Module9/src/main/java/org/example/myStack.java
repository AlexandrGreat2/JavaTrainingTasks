package org.example;

public class myStack<T> {
    MyLinkedList linkedList = new MyLinkedList<T>();
    private int size = 0;

    public void push(T item) throws Exception {
        linkedList.add(item);
        size++;
    }

    public void remove(int index) {
        linkedList.remove(index);
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
        return (T) linkedList.get(size-1);
    }


    public T pop() {
        T result = (T) linkedList.get(size-1);
        linkedList.remove(size-1);
        size--;
        return result;
    }
}
