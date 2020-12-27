package tricksproject.logic;

import java.util.Scanner;

public class Device {

    private int serialNumber;
    private String code;
    private String instDate;
    private int locationNR;

    public Device(int serialNumber) {
        this.serialNumber = serialNumber;
        this.code = generateCode();
    }

    public Device(int serialNumber, String code) {
        this.serialNumber = serialNumber;
        this.code = code;
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getInstdate() {
        return instDate;
    }

    public int getLocationNR() {
        return locationNR;
    }

    public void setInstdate(String instdate) {
        instDate = instdate;
    }

    public void setLocationNR(int locationNR) {
        this.locationNR = locationNR;
    }

    public String generateCode() {
        //volgorde CO2, N2, PM2.5, PM5, PM10
        String code = "";
        Scanner keyboard = new Scanner(System.in);


            code = code + "1";
            System.out.println("Meet de device een minimum- en maximumwaarde voor CO2?");
            if (keyboard.nextLine().equalsIgnoreCase("ja")) {
                code = code + "1";
            } else {
                code = code + "0";
            }

        System.out.println("Meet de device N2?");
        if (keyboard.nextLine().equalsIgnoreCase("ja")) {
            code = code + "1";
            System.out.println("Meet de device een minimum- en maximumwaarde voor N2?");
            if (keyboard.nextLine().equalsIgnoreCase("ja")) {
                code = code + "1";
            } else {
                code = code + "0";
            }
        } else {
            code = code + "00";
        }
        System.out.println("Meet de device PM2,5?");
        if (keyboard.nextLine().equalsIgnoreCase("ja")) {
            code = code + "1";
            System.out.println("Meet de device een minimum- en maximumwaarde voor PM2,5?");
            if (keyboard.nextLine().equalsIgnoreCase("ja")) {
                code = code + "1";
            } else {
                code = code + "0";
            }
        } else {
            code = code + "00";
        }
        System.out.println("Meet de device PM5?");
        if (keyboard.nextLine().equalsIgnoreCase("ja")) {
            code = code + "1";
            System.out.println("Meet de device een minimum- en maximumwaarde voor PM5?");
            if (keyboard.nextLine().equalsIgnoreCase("ja")) {
                code = code + "1";
            } else {
                code = code + "0";
            }
        } else {
            code = code + "00";
        }
        System.out.println("Meet de device PM10?");
        if (keyboard.nextLine().equalsIgnoreCase("ja")) {
            code = code + "1";
            System.out.println("Meet de device een minimum- en maximumwaarde voor PM10?");
            if (keyboard.nextLine().equalsIgnoreCase("ja")) {
                code = code + "1";
            } else {
                code = code + "0";
            }
        } else {
            code = code + "00";
        }
        System.out.println("Code gegenereerd!");
        return code;
    }
}

