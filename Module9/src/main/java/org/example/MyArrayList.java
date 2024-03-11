package org.example;

public class MyArrayList<T> {
    private final int startArraySize = 8;
    private final int resizeArrayMultiply = 2;
    private final int resizeArrayRate = 2;
    private T[] array = (T[]) new Object[startArraySize];
    private int pointer = 0;

    public void add(T item) {
        if(pointer >= array.length-1)
            resize(array.length * this.resizeArrayMultiply);
        array[pointer++] = item;
    }

    //convert and return element with same type
    public T get(int index) {
        if (array[index] == null) {
            return null;
        }
        //todo: Why next sting mark as "Unchecked cast: 'java.lang.Object' to 'T' " ?
        return (T) array[index];
    }

    //remove element from list
    public void remove(int index) {
        if (array[index] != null) {

            for (int i = index; i < pointer; i++)
                array[i] = array[i + 1];
            array[pointer] = null;
            pointer--;
            if (array.length > startArraySize && pointer < array.length / resizeArrayRate)
                resize(array.length / this.resizeArrayMultiply);
        }
    }

    //return size
    public int size() {
        return pointer;
    }

    //resize to bigger or smaller array to storage data
    private void resize(int length) {
        Object[] resizedArray = new Object[length];
        System.arraycopy(array, 0, resizedArray, 0, pointer);
        array = (T[]) resizedArray;
    }

    private void clear() {
        resize(startArraySize);
        for (int i = 0; i<startArraySize; i++) {
            array[i] = null;
        }
        pointer = 0;
    }

}
