package tricksproject.logic;

public class MinMax {

    private double min;
    private double max;
    private int measurementNr;

    public MinMax( double min, double max, int measurementNR) {
        this.min = min;
        this.max = max;
        this.measurementNr= measurementNR;

    }

    public int getMeasurementNr() {
        return measurementNr;
    }

    public void setMeasurementNr(int measurementNr) {
        this.measurementNr = measurementNr;
    }

    public double getMin() {
        return min;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }
}

