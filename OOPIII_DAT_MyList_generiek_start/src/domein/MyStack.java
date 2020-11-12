package domein;

public class MyStack<T> {

    private MyListIterable<T> list;

    public MyStack() {
        this("Stack");
    }

    public MyStack(String name) {
        list = new MyListIterable(name);
    }

    public void push(T data) {
        list.insertAtFront(data);
    }

    public T pop() {
        return list.removeFromFront();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public String toString() {
        return list.toString();
    }

}
