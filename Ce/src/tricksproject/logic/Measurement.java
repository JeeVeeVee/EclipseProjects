package tricksproject.logic;

public class Measurement {

    private int measurementNR;
    private String date;
    private double average;
    private String typeOfGas;
    private int serialNr;

    public Measurement(int measurementNR, String date, double average,  int serialNr, String typeOfGas) {
        this.measurementNR = measurementNR;
        this.date = date;
        this.average = average;
        this.typeOfGas = typeOfGas;
        this.serialNr = serialNr;
    }

    public int getMeasurementNR() {
        return measurementNR;
    }

    public void setMeasurementNR(int measurementNR) {
        this.measurementNR = measurementNR;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(int average) {
        this.average = average;
    }

    public String getTypeOfGas() {
        return typeOfGas;
    }

    public void setTypeOfGas(String typeOfGas) {
        this.typeOfGas = typeOfGas;
    }

    public int getSerialNr() {
        return serialNr;
    }

    public void setSerialNr(int serialNr) {
        this.serialNr = serialNr;
    }

    @Override
    public String toString() {
        return "Measurement{" +
                "measurementNR=" + measurementNR +
                ", date=" + date +
                ", average=" + average +
                ", typeOfGas=" + typeOfGas +
                ", serialNr=" + serialNr+
                '}';
    }
}
