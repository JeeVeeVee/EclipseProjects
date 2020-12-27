
package tricksproject.db;

import tricksproject.logic.Measurement;
import tricksproject.logic.Person;
import tricksproject.logic.TypeOfGas;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class DBReport {

    public static ArrayList<Measurement> getMeasurementsFromLocationFromTypeOfGas(int locationNr, String TypeGas)
    {
        Connection con = null;

        ArrayList<Integer> devices = DBDevice.getDevicesinRoom(locationNr);
        ArrayList<Measurement> metingen = new ArrayList<>();
        for(Integer i : devices)
        {

            try {
                con = DBConnector.getConnection();
                Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                String query = "SELECT *"
                        + "FROM MEASUREMENT "
                        + "WHERE SERIALNR= '"+ i +"' "+
                        "AND TYPEOFGAS = '"+TypeGas+"'";

                ResultSet srs = stmt.executeQuery(query);

                int measurementNR, serialNumber;
                double avg;
                String type;
                String Date;

                while (srs.next()) {
                    measurementNR = srs.getInt("MEASUREMENTNR");
                    Date = srs.getString("DATE");
                    avg = srs.getDouble("AVG");
                    serialNumber = srs.getInt("SERIALNR");
                    type = srs.getString("TYPEOFGAS");

                    Measurement m = new Measurement(measurementNR, Date, avg, serialNumber, type);
                    metingen.add(m);

                }
                    DBConnector.closeConnection(con);


            }
            catch (Exception ex) {
                ex.printStackTrace();
                DBConnector.closeConnection(con);

            }
        }
        return metingen;
        }


        public static HashMap<String, Double>  getAvgSameRoom(int LocationNR, String TypeOfGas )
        {
            ArrayList<Measurement> metingen = getMeasurementsFromLocationFromTypeOfGas(LocationNR, TypeOfGas);
            ArrayList<String> data = new ArrayList<>();
            HashMap<String, Double> result= new HashMap<>();

            for (Measurement m : metingen)
            {
                double som= 0;
                int aantal=0;
                double avg =0;
                String date = m.getDate();
                if (!data.contains(date))
                {
                    for(Measurement o: metingen){
                        if (o.getDate().equals(date))
                        {
                            som = som + o.getAverage();
                            aantal++;
                            data.add(date);

                        }
                    }
                    avg = som/aantal;
                    result.put(m.getDate(),avg);
                }
            }
            return result;
        }


    public static ArrayList<Measurement> getCO2Measurements()
    {
        Connection con = null;
        ArrayList<Measurement> metingen = new ArrayList<>();
            try {
                con = DBConnector.getConnection();
                Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                String query = "SELECT *"
                        + "FROM MEASUREMENT "
                        + "WHERE TYPEOFGAS= '"+ "CO2" +"' ";
                ResultSet srs = stmt.executeQuery(query);
                int measurementNR, serialNumber;
                double avg;
                String type;
                String Date;

                while (srs.next()) {
                    measurementNR = srs.getInt("MEASUREMENTNR");
                    Date = srs.getString("DATE");
                    avg = srs.getDouble("AVG");
                    serialNumber = srs.getInt("SERIALNR");
                    type = srs.getString("TYPEOFGAS");
                    Measurement m = new Measurement(measurementNR, Date, avg, serialNumber, type);
                    metingen.add(m);
                }
                DBConnector.closeConnection(con);
            }
            catch (Exception ex) {
                ex.printStackTrace();
                DBConnector.closeConnection(con);

            }

        return metingen;
    }

    public static HashMap<String, Double>  getCO2sameday()
    {
        ArrayList<Measurement> metingen = getCO2Measurements();
        ArrayList<String> data = new ArrayList<>();
        HashMap<String, Double> result= new HashMap<>();
        double som= 0;
        int aantal=0;
        double avg =0;
        for (Measurement m : metingen)
        {
             som= 0;
             aantal=0;
             avg =0;
            String date = m.getDate();
            if (!data.contains(date))
            {
                for(Measurement o: metingen){
                    if (o.getDate().equals(date))
                    {
                        som = som + o.getAverage();
                        aantal++;
                        data.add(date);
                    }

                }
                avg = som/aantal;
                result.put(m.getDate(),avg);
            }
        }
        return result;
    }

    public static void main(String[] args) throws DBException {

        DBMeasurement.save(new Measurement(DBMeasurement.genereereenMeasurementNr(), DBDevice.convertDate(DBMeasurement.getTodaysDate().toString()), 78.3, 1,"CO2"));
        System.out.println(getAvgSameRoom(1,"CO2"));


    }

}


