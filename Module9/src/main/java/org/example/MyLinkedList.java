package org.example;

public class MyLinkedList<T> {
    private Node lastNode = null;
    private Node firstNode = null;
    private int pointer = 0;
    private int listSize = 0;

    public void add(T item) throws Exception {
        Node newNode;
        if ( firstNode == null && lastNode == null ) {
            newNode = new Node( item );
            firstNode = newNode;
        } else {
            newNode = new Node( item, lastNode.getPrev() );
        }
        lastNode = newNode;
        listSize++;
        pointer++;
    }

    public void remove(int index) {
        Node currentNode = firstNode;
        for (int i = 0; i<index; i++) {
            currentNode = currentNode.getNext();
        }
        Node prev = currentNode.getPrev();
        Node next = currentNode.getNext();
        next.setPrev(prev);
        prev.setNext(next);
        currentNode.setPrev(null);
        currentNode.setNext(null);
        listSize--;
    }

    public void clear() {
        listSize = 0;
        pointer = 0;
        lastNode = null;
        firstNode = null;
    }

    //return size
    public int size() {
        return listSize;
    }

    //convert and return element with same type
    public T get(int index) {
        Node currentNode = firstNode;
        if (index == 0) {
            return (T) currentNode.getData();
        }
        for (int i = 0; i<index; i++) {
            currentNode = currentNode.getNext();
        }
        return (T) currentNode.getData();
    }


//    private Node getPreLastNode(){
//        if (lastNode != null) {
//            return lastNode.getPrev();
//        } else {
//            return null;
//        }
//    }

    static class Node {
        private Object o;
        private Node next;
        private Node prev;

        //to create first node object
        public Node(Object o) throws Exception {
            if (o != null) {
                this.o = o;
                next = null;
                prev = null;
//                System.out.println("0= " + o + " +1= " + next + " -1= " + prev);
            } else {
                throw new Exception("Null element not allowed");
            }
        }

        //to create not first node object
        public Node(Object o, Node prev) throws Exception {
            if (o == null)
                throw new Exception("Null element not allowed");
            this.o = o;
            if (prev != null) {
                this.prev = prev;
                this.next = null;
                this.prev.setPrev(this);
//                System.out.println("0= " + o + " +1= " + next + " -1= " + prev);
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

        public Node getPrev() {
            return prev;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public void setPrev(Node prev) {
            this.prev = prev;
        }
    }

}
