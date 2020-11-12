package domein;

import java.io.Serializable;

public class Node<T extends Serializable> {

    private final T data;
    private Node<T> next;

    public Node(T data2) {
    	super();
        this.data = data2;
    }

    public T getData() {
        return data;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Node getNext() {
        return next;
    }
}
