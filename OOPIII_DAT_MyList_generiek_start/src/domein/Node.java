package domein;

import java.io.Serializable;

public class Node<T extends Serializable> implements Serializable{

    private final T data;
    private Node next;

    public Node(T data) {
        this.data = data;
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
