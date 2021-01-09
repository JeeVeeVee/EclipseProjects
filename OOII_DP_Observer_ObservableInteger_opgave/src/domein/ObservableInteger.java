package domein;

public class ObservableInteger {

    private int value = 1;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void add(int number) {
        setValue(value + number);
    }

    public void subtract(int number) {
        setValue(value - number);
    }

    public int getDoubleValue() {
        return value * 2;
    }
}
