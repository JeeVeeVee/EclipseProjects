package tricksproject.db;

import tricksproject.logic.Device;
import tricksproject.logic.Measurement;
import tricksproject.logic.MinMax;
import tricksproject.logic.TypeOfGas;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class DBMeasurement {

    public static Measurement getMeasurement(int measurementNR) throws DBException {
        Connection con = null;
        String date;
        String typeOfGas;
        Double average;
        int serialNr;
        try {
            con = DBConnector.getConnection();
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT * "
                    + "FROM MEASUREMENT "
                    + "WHERE MEASUREMENTNR = " + measurementNR;
            ResultSet srs = stmt.executeQuery(sql);


            if (srs.next()) {
                date = srs.getString("DATE");
                average = srs.getDouble("AVG");
                typeOfGas = srs.getString("TYPEOFGAS");
                serialNr = srs.getInt("SERIALNR");

            } else {
                DBConnector.closeConnection(con);
                return null;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            DBConnector.closeConnection(con);
            throw new DBException(ex);
        }
        Measurement measurement = new Measurement(measurementNR, date, average,serialNr, typeOfGas);
        DBConnector.closeConnection(con);
        return measurement;
    }

    public static ArrayList<Measurement> getMeasurements() throws DBException {
        Connection con = null;
        ArrayList<Measurement> nieuwelijst = new ArrayList<>();
        try {
            con = DBConnector.getConnection();
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String query = "SELECT *"
                    + "FROM MEASUREMENT ";
            ResultSet srs = stmt.executeQuery(query);
            nieuwelijst = new ArrayList<>();
            while (srs.next())
                nieuwelijst.add(getMeasurement(srs.getInt("MEASUREMENTNR")));
            DBConnector.closeConnection(con);
        } catch (DBException e) {
            e.printStackTrace();
            DBConnector.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
            DBConnector.closeConnection(con);
            throw new DBException(e);
        }
        return nieuwelijst;
    }

    public static String getTodaysDate()

    {

        return LocalDate.now().toString();

    }

    public static void save(Measurement s) throws DBException {
        boolean toegevoegd = false;
        Connection con = null;
        try {
            con = DBConnector.getConnection();
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

            String sql = "SELECT * "
                    + "FROM MEASUREMENT "
                    + "WHERE DATE = '"+ DBDevice.getDateTime(s.getDate()) +"'"
                    + "AND SERIALNR = '"+ s.getSerialNr() +"'"
                    + "AND TYPEOFGAS = '"+ s.getTypeOfGas() +"'";
            ResultSet srs = stmt.executeQuery(sql);

            if (srs.next()) {
                if(DBDevice.getDateTime(s.getDate()).equals(getTodaysDate())) {
                    sql = "UPDATE MEASUREMENT "
                            + "SET AVG = '" + s.getAverage() + "'"
                            + "WHERE DATE = '"+ DBDevice.getDateTime(s.getDate()) +"'"
                            + "AND SERIALNR = '"+ s.getSerialNr() +"'"
                            + "AND TYPEOFGAS = '"+ s.getTypeOfGas() +"'";

                    stmt.executeUpdate(sql);
                    toegevoegd = true;
                }
            } else {
                // INSERT
                sql = "INSERT into MEASUREMENT "
                        + "(MEASUREMENTNR, DATE, AVG, SERIALNR, TYPEOFGAS) "
                        + "VALUES ('" + s.getMeasurementNR() + "'"
                        + ", '" + DBDevice.getDateTime(s.getDate()) + "'"
                        + ", '" + s.getAverage() + "'"
                        + ", '" + s.getSerialNr() + "'"
                        + ", '" + s.getTypeOfGas() + "')";

                stmt.executeUpdate(sql);
                toegevoegd = true;
            }
            DBConnector.closeConnection(con);
        } catch (Exception ex) {
            ex.printStackTrace();
            DBConnector.closeConnection(con);
            throw new DBException(ex);
        }
    }

    public static boolean deleteMeasurement(Measurement measurement) throws DBException {
        boolean verwijderd = false;
        Connection con = null;
        try {
            con = DBConnector.getConnection();
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT MEASUREMENTNR "
                    + "FROM MEASUREMENT "
                    + "WHERE MEASUREMENTNR = " + measurement.getMeasurementNR();
            ResultSet srs = stmt.executeQuery(sql);

            if (srs.next()) {
                sql = "DELETE FROM MEASUREMENT "
                        + "WHERE MEASUREMENTNR = " + measurement.getMeasurementNR();
                stmt.executeUpdate(sql);
                verwijderd = true;
            } else {
                DBConnector.closeConnection(con);
            }
        } catch (Exception e) {
            e.printStackTrace();
            DBConnector.closeConnection(con);
            throw new DBException(e);
        }
        return verwijderd;
    }

    public static ArrayList<Integer> getMeasurementNumbers() throws DBException {
        ArrayList<Integer> lijstMetNamen = new ArrayList<>();
        for (int i = 0; i <= getMeasurements().size(); i++)
            lijstMetNamen.add(getMeasurements().get(i).getMeasurementNR());

        return lijstMetNamen;
    }

    public static boolean IsMeasurementNrAlAanwezig(int measurementNR) {
        Connection con = null;
        boolean aanwezig = false;
        try {
            con = DBConnector.getConnection();
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT * "
                    + "FROM MEASUREMENT "
                    + " WHERE MEASUREMENTNR = '" + measurementNR + "'";
            ResultSet srs = stmt.executeQuery(sql);
            if (srs.next()) {
                aanwezig = true;
            }

        } catch (DBException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return aanwezig;
    }

    public static int genereereenMeasurementNr() throws DBException {
        return getMeasurements().size()+1;
    }

    public static void main(String[] args) throws DBException {
        save(new Measurement(1, "30/11/2020", 423.5,1, "CO2"));
        save(new Measurement(2, "30/11/2020", 780400,1, "N2"));
        save(new Measurement(3, "30/11/2020", 23.67,1, "PM10"));
        save(new Measurement(4, "30/11/2020", 410.56,2, "CO2"));
        save(new Measurement(5, "30/11/2020", 21.56,2, "PM10"));
        save(new Measurement(6, "30/11/2020", 413.12,3, "CO2"));
        save(new Measurement(7, "30/11/2020", 779500.22,3, "N2"));
        save(new Measurement(8, "30/11/2020", 18.67,3, "PM2.5"));
        save(new Measurement(9, "30/11/2020", 20.15,3, "PM5"));
        save(new Measurement(10, "30/11/2020", 421.06,5, "CO2"));
        save(new Measurement(11, "30/11/2020", 18.12,5, "PM2.5"));
        save(new Measurement(12, "30/11/2020", 22.62,5, "PM10"));
        save(new Measurement(13, "30/11/2020", 407.6,6, "CO2"));
        save(new Measurement(14, "30/11/2020", 779980.32,6, "N2"));
        save(new Measurement(15, "30/11/2020", 18.98,6, "PM2.5"));
        save(new Measurement(16, "30/11/2020", 400.36,7, "CO2"));
        save(new Measurement(17, "30/11/2020", 782630.50,7, "N2"));
        save(new Measurement(18, "30/11/2020", 18.53,7, "PM2.5"));
        save(new Measurement(19, "30/11/2020", 20.91,7, "PM5"));
        save(new Measurement(20, "30/11/2020", 20.53,7, "PM10"));
        save(new Measurement(21, "30/11/2020", 430.5,8, "CO2"));
        save(new Measurement(22, "30/11/2020", 779407.23,8, "N2"));
        save(new Measurement(23, "30/11/2020", 21.01,8, "PM5"));
        save(new Measurement(24, "30/11/2020", 23.17,8, "PM10"));
        save(new Measurement(25, "30/11/2020", 417.07,9, "CO2"));
        save(new Measurement(26, "30/11/2020", 779754.98,9, "N2"));
        save(new Measurement(27, "30/11/2020", 411,10, "CO2"));
        save(new Measurement(28, "30/11/2020", 17.99,10, "PM2.5"));
        save(new Measurement(29, "30/11/2020", 412.96,11, "CO2"));
        save(new Measurement(30, "30/11/2020", 779820.32,11, "N2"));
        save(new Measurement(31, "30/11/2020", 19.01,11, "PM2.5"));
        save(new Measurement(32, "30/11/2020", 20.36,11, "PM5"));
        save(new Measurement(33, "30/11/2020", 419.26,12, "CO2"));
        save(new Measurement(34, "30/11/2020", 779630.52,12, "N2"));
        save(new Measurement(35, "30/11/2020", 412.63,13, "CO2"));
        save(new Measurement(36, "30/11/2020", 778100.83,13, "N2"));
        save(new Measurement(37, "30/11/2020", 18.25,13, "PM2.5"));
        save(new Measurement(38, "30/11/2020", 20.35,13, "PM5"));
        save(new Measurement(39, "30/11/2020", 420.12,14, "CO2"));
        save(new Measurement(40, "30/11/2020", 782130,14, "N2"));
        save(new Measurement(41, "30/11/2020", 18.46,14, "PM2.5"));
        save(new Measurement(42, "30/11/2020", 19.95,14, "PM5"));
        save(new Measurement(43, "30/11/2020", 22.10,14, "PM10"));
        save(new Measurement(44, "30/11/2020", 415.33,15, "CO2"));
        save(new Measurement(45, "30/11/2020", 780201.32,15, "N2"));
        save(new Measurement(46, "30/11/2020", 410.96,16, "CO2"));
        save(new Measurement(47, "30/11/2020", 780300.62,16, "N2"));
        save(new Measurement(48, "30/11/2020", 18.11,16, "PM2.5"));
        save(new Measurement(49, "30/11/2020", 20.32,16, "PM5"));
        save(new Measurement(50, "30/11/2020", 413.36,17, "CO2"));
        save(new Measurement(51, "30/11/2020", 18.83,17, "PM2.5"));
        save(new Measurement(52, "30/11/2020", 20.12,17, "PM5"));
        save(new Measurement(53, "30/11/2020", 23.06,17, "PM10"));    }
}
