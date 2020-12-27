package tricksproject.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import tricksproject.db.DBException;
import tricksproject.db.DBMeasurement;
import tricksproject.db.DBMinMax;
import tricksproject.logic.Location;
import tricksproject.logic.Measurement;
import tricksproject.logic.MinMax;
import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;

import static tricksproject.db.DBDevice.*;
import static tricksproject.db.DBLocation.*;
import static tricksproject.db.DBMeasurement.genereereenMeasurementNr;
import static tricksproject.db.DBMeasurement.getTodaysDate;
import static tricksproject.db.DBPerson.getAccountNumberFromAccount;


public class MeasureController implements Initializable {

    private String adress, locationType,device, code;

    private String CO2, CO2MIN, CO2MAX, N2, N2MIN, N2MAX, PM25, PM25MIN, PM25MAX, PM5, PM5MIN, PM5MAX, PM10, PM10MIN,PM10MAX;

    @FXML
    public ChoiceBox<String> AdressCB;

    @FXML
    private ChoiceBox<String> locationTypeCB;

    @FXML
    private ChoiceBox<String> devicesCB;

    @FXML
    private TextField TFCO2,TFMINCO2,TFMAXCO2,TFN2,TFMINN2,TFMAXN2,TFPM25,TFMINPM25,TFMAXPM25,TFPM5,TFMINPM5,TFMAXPM5,TFPM10,TFMINPM10,TFMAXPM10;


    @FXML
    public void uploadAdress(ActionEvent event) {
        adress = AdressCB.getValue();
        try {
            for (String s : getLocationTypeFromAdres(getAdress(), returnAccountnr())) {
                locationTypeCB.getItems().add(s);
            }
            System.out.println(getAdress());
        } catch (DBException e) {
            e.printStackTrace();
        }
    }


    @FXML
    public void uploadLocationType(ActionEvent event) {
        locationType = locationTypeCB.getValue();
        try {
            int i = getLocatieID(returnAccountnr(),getAdress(),getLocationType());
            for(int j = 0; j < getDevicesinRoom(i).size(); j++){
                 devicesCB.getItems().add(Integer.toString(getDevicesinRoom(i).get(j)));
            }
            System.out.println(getLocationType());
        } catch (DBException e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void uploadDevice(ActionEvent event) {
        device = devicesCB.getValue();
        try {
            code = getCodeFromSerialNr(Integer.parseInt(device));

            if(code.charAt(0) == '1')
                TFCO2.setDisable(false);
            if(code.charAt(1) == '1') {
                TFMINCO2.setDisable(false);
                TFMAXCO2.setDisable(false);
            }
            if(code.charAt(2) == '1')
                TFN2.setDisable(false);
            if(code.charAt(3) == '1') {
                TFMINN2.setDisable(false);
                TFMAXN2.setDisable(false);
            }
            if(code.charAt(4) == '1')
                TFPM25.setDisable(false);
            if(code.charAt(5) == '1') {
                TFMINPM25.setDisable(false);
                TFMINPM25.setDisable(false);
            }
            if(code.charAt(6) == '1')
                TFPM5.setDisable(false);
            if(code.charAt(7) == '1') {
                TFMINPM5.setDisable(false);
                TFMINPM5.setDisable(false);
            }
            if(code.charAt(8) == '1')
                TFPM10.setDisable(false);
            if(code.charAt(9) == '1') {
                TFMINPM10.setDisable(false);
                TFMINPM10.setDisable(false);
            }
        } catch (DBException e) {
            e.printStackTrace();
        }
    }

    public int returnAccountnr() {
        return getAccountNumberFromAccount(LoginController.getGebruikersnaam(), LoginController.getPassword());
    }

    public String getAdress() {
        return adress;
    }

    public String getLocationType() {
        return locationType;
    }

    public String getDevice() {
        return device;
    }

    @FXML
    public void uploadMeasurment(ActionEvent event) throws DBException {
        CO2 = TFCO2.getText();
        Measurement m1 = new Measurement(genereereenMeasurementNr(),convertDate(getTodaysDate()), Integer.parseInt(CO2), Integer.parseInt(getDevice()),"CO2");
        DBMeasurement.save(m1);
        CO2MIN = TFMINCO2.getText();
        CO2MAX = TFMAXCO2.getText();
        MinMax CO2 = new MinMax(Integer.parseInt(CO2MIN), Integer.parseInt(CO2MAX), genereereenMeasurementNr());
        DBMinMax.save(CO2);
        N2 = TFN2.getText();
        Measurement m2 = new Measurement(genereereenMeasurementNr(), convertDate(getTodaysDate()), Integer.parseInt(N2), Integer.parseInt(getDevice()), "N2");
        DBMeasurement.save(m2);
        N2MIN = TFMINN2.getText();
        N2MAX = TFMAXN2.getText();
        MinMax N2 = new MinMax(Integer.parseInt(N2MIN), Integer.parseInt(N2MAX), genereereenMeasurementNr());
        DBMinMax.save(N2);
        PM25 = TFPM25.getText();
        Measurement m3 = new Measurement(genereereenMeasurementNr(), convertDate(getTodaysDate()), Integer.parseInt(PM25), Integer.parseInt(getDevice()), "PM2.5");
        DBMeasurement.save(m3);
        PM25MIN = TFMINPM25.getText();
        PM25MAX = TFMAXPM25.getText();
        MinMax PM25 = new MinMax(Integer.parseInt(PM25MIN), Integer.parseInt(PM25MAX), genereereenMeasurementNr());
        DBMinMax.save(PM25);
        PM5 = TFPM5.getText();
        Measurement m4 = new Measurement(genereereenMeasurementNr(), convertDate(getTodaysDate()), Integer.parseInt(PM5), Integer.parseInt(getDevice()), "PM5");
        DBMeasurement.save(m4);
        PM5MIN = TFMINPM5.getText();
        PM5MAX = TFMAXPM5.getText();
        MinMax PM5 = new MinMax(Integer.parseInt(PM5MIN), Integer.parseInt(PM5MAX), genereereenMeasurementNr());
        DBMinMax.save(PM5);
        PM10 = TFPM10.getText();
        Measurement m5 = new Measurement(genereereenMeasurementNr(), convertDate(getTodaysDate()), Integer.parseInt(PM10), Integer.parseInt(getDevice()), "PM10");
        DBMeasurement.save(m5);
        PM10MIN= TFMINPM10.getText();
        PM10MAX = TFMAXPM10.getText();
        MinMax PM10 = new MinMax(Integer.parseInt(PM10MIN), Integer.parseInt(PM10MAX), genereereenMeasurementNr());
        DBMinMax.save(PM10);
        System.out.println(CO2 +" "+ N2 +" "+ PM25 +" "+ PM5 +" "+ PM10);
    }

    public HashSet<String> getUniqueLocatiesFromAccNR() throws DBException {
        HashSet<String> uniqueLoc = new HashSet<>();
        for(Location l : getLocatiesFromAccNR(returnAccountnr())) {
            for (int i = 0; i < getLocatiesFromAccNR(returnAccountnr()).size(); i++)
                uniqueLoc.add(getLocatiesFromAccNR(returnAccountnr()).get(i).getAdres());
        }
        return uniqueLoc;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            for (String l : getUniqueLocatiesFromAccNR())
                AdressCB.getItems().add(l);

            } catch(DBException e){
                e.printStackTrace();
            }
    }
}













