package tricksproject.db;

import tricksproject.logic.Measurement;
import tricksproject.logic.MinMax;
import tricksproject.logic.Person;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class DBMinMax {

    public static MinMax getMinMax(int measurementNr) throws DBException {
        Connection con = null;
        try {
            con = DBConnector.getConnection();
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT * "
                    + "FROM MINMAX "
                    + "WHERE MEASUREMENTNR = '" + measurementNr + "'";
            ResultSet srs = stmt.executeQuery(sql);
            int measurementnumber;
            double min, max;

            if (srs.next()) {
                min = srs.getDouble("MIN");
                max = srs.getDouble("MAX");
                measurementnumber = srs.getInt("MEASUREMENTNR");

            } else {
                DBConnector.closeConnection(con);
                return null;
            }
            MinMax minmax = new MinMax(min, max, measurementnumber);
            DBConnector.closeConnection(con);
            return minmax;
        } catch (Exception ex) {
            ex.printStackTrace();
            DBConnector.closeConnection(con);
            throw new DBException(ex);
        }
    }

    public static ArrayList<MinMax> getAlleMinMax() throws DBException {
        Connection con = null;
        try {
            con = DBConnector.getConnection();
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String query = "SELECT * "
                    + "FROM MINMAX ";
            ResultSet srs = stmt.executeQuery(query);
            ArrayList<MinMax> nieuwelijst = new ArrayList<>();
            while (srs.next())
                nieuwelijst.add(getMinMax(srs.getInt("MEASUREMENTNR")));
            DBConnector.closeConnection(con);
            return nieuwelijst;
        } catch (DBException e) {
            e.printStackTrace();
            DBConnector.closeConnection(con);
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            DBConnector.closeConnection(con);
            throw new DBException(e);
        }
    }

    public static void save(MinMax s) throws DBException {
        boolean toegevoegd = false;
        Connection con = null;
        try {
            con = DBConnector.getConnection();
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

            String sql = "SELECT MEASUREMENTNR "
                    + "FROM MINMAX "
                    + "WHERE MEASUREMENTNR = '" + s.getMeasurementNr() + "'";
            ResultSet srs = stmt.executeQuery(sql);
            if (srs.next()) {
                // UPDATE
                sql = "UPDATE MINMAX "
                        + "SET MIN = '" + s.getMin() + "'"
                        + ", MAX = '" + s.getMax() + "'"
                        + ", MEASUREMENTNR = '" + s.getMeasurementNr() + "'";
                stmt.executeUpdate(sql);
                toegevoegd = true;
            } else {
                // INSERT
                sql = "INSERT into MINMAX "
                        + "(MIN, MAX, MEASUREMENTNR) "
                        + "VALUES (" + s.getMin()
                        + ", '" + s.getMax() + "'"
                        + ", '" + s.getMeasurementNr() +"')";
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

    public static boolean deleteMinMax(int MeasurementNr) throws DBException {
        Scanner keyboard = new Scanner (System.in);
        boolean verwijderd = false;
        Connection con = null;
        try {
            con = DBConnector.getConnection();
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT MEASUREMENTNR "
                    + "FROM MEASUREMENT ";
            ResultSet srs = stmt.executeQuery(sql);

            if (srs.next()) {
                sql = "DELETE FROM MEASUREMENT "
                        + "WHERE MEASUREMENTNR = " + MeasurementNr;
                stmt.executeUpdate(sql);
                verwijderd = true;
            } else {
                DBConnector.closeConnection(con);
            }

        }
        catch (Exception e) {
            e.printStackTrace();
            DBConnector.closeConnection(con);
            throw new DBException(e);
        }
        return verwijderd;
    }

    public static int getMeasurementNR (int serialNR, String typeOfGas){
        Connection con = null;
        String date = DBDevice.getDateTime("30/11/2020");
        int measurementNR = 0;
        try {
            con = DBConnector.getConnection();
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT MEASUREMENTNR "
                    + "FROM MEASUREMENT "
                    + "WHERE DATE = '" + date + "'"
                    + " AND SERIALNR = '" + serialNR + "'"
                    + " AND TYPEOFGAS = '" + typeOfGas + "'";
            ResultSet srs = stmt.executeQuery(sql);
            if (srs.next()) {
                measurementNR = srs.getInt("MEASUREMENTNR");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            DBConnector.closeConnection(con);
        }

        DBConnector.closeConnection(con);
        return measurementNR;
    }


    public static void main(String[] args) throws DBException {

        System.out.println(getMeasurementNR(7, "CO2"));

    }



}
