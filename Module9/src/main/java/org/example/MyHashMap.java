package org.example;

public class MyHashMap {
    private Node firstNode = null;
    private int pointer = 0;
    private int size = 0;

    public void put(Object key, Object value) throws Exception {
        Node currentNode = firstNode;
        if (firstNode == null) {
            firstNode = new Node(key,value);
            size++;
        } else {
            for (int i = 0; i < size; i++) {
                currentNode = firstNode.getNext();
                if (currentNode.getKay() == key) {
                    currentNode.setData(key,value);
                    break;
                } else {
                    if (i == size - 1) {
                        currentNode.setNext(new Node(key,value));
                        size++;
                    }
                }
            }
        }
    }

    public void remove(Object key) {
        Node currentNode = firstNode;
        Node prevNode = null;
        for (int i = 0; i < size; i++) {
            prevNode = currentNode;
            currentNode = firstNode.getNext();
            if (currentNode.getKay() == key) {
                prevNode.setNext(currentNode.getNext());
                currentNode.setNext(null);
                currentNode.setData(null, null);
                size--;
                break;
            }
        }
    }

    public void clear() {
        Node currentNode = firstNode;
        Node prevNode = null;
        for (int i = 0; i < size; i++) {
            prevNode = currentNode;
            currentNode = firstNode.getNext();
            prevNode.setNext(null);
            prevNode.setData(null, null);
            size--;
        }
        firstNode = null;
        size = 0;
    }

    //return size
    public int size() {
        return size;
    }


    public Object get(Object key) {
        Node currentNode = firstNode;
        for (int i = 0; i < size; i++) {
            currentNode = firstNode.getNext();
            if (currentNode.getKay() == key) {
                return currentNode.getData();
            }
        }
        return null;
    }


    static class Node {
        private Object o;
        private Node next;
        private Object key;

        //to create first node object
        public Node(Object key, Object o) throws Exception {
            if (o != null) {
                this.o = o;
                this.key = key;
                next = null;
            } else {
                throw new Exception("Null element not allowed");
            }
        }

        public Object getData() {
            return o;
        }

        public Node getNext() {
            return next;
        }

        public Object getKay() {
            return key;
        }

        public void setData(Object key, Object o) {
            this.o = o;
            this.key = key;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }

}
