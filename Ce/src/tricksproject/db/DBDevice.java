package tricksproject.db;

import tricksproject.logic.Device;
import tricksproject.logic.Location;
import tricksproject.logic.Person;
import tricksproject.logic.Student;

import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBDevice {
    public static Device getDevice(int SerialNr) throws DBException {
        Connection con = null;
        try {
            con = DBConnector.getConnection();
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT * "
                    + "FROM DEVICE "
                    + "WHERE SERIALNR = '" + SerialNr +"'";
            ResultSet srs = stmt.executeQuery(sql);
            String  code, instdate;
            int locationnr, serialNr;


            if (srs.next()) {
                serialNr = srs.getInt("SERIALNR");
                code = srs.getString("CODE");
                instdate = srs.getString("INSTDATE");

            } else {
                DBConnector.closeConnection(con);
                return null;
            }
            Device device = new Device(serialNr,code);
            DBConnector.closeConnection(con);
            return device;
        } catch (Exception ex) {
            ex.printStackTrace();
            DBConnector.closeConnection(con);
            throw new DBException(ex);
        }
    }



    public static ArrayList<Device> getDevices() throws DBException {
        Connection con = null;
        ArrayList<Device> nieuwelijst = new ArrayList<>();
        try {
            con = DBConnector.getConnection();
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String query = "SELECT * "
                    + "FROM DEVICE ";
            ResultSet srs = stmt.executeQuery(query);
            nieuwelijst = new ArrayList<>();
            while (srs.next())
                nieuwelijst.add(getDevice(srs.getInt("SERIALNR")));
            DBConnector.closeConnection(con);
        }
        catch(DBException e){
            e.printStackTrace();
            DBConnector.closeConnection(con);
        }
        catch(Exception e){
            e.printStackTrace();
            DBConnector.closeConnection(con);
            throw new DBException(e);
        }
        return nieuwelijst;

    }
    public static void save(Device s) throws DBException {
        boolean toegevoegd = false;
        Connection con = null;
        try {
            con = DBConnector.getConnection();
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

            String sql = "SELECT SERIALNR "
                    + "FROM DEVICE "
                    + "WHERE SERIALNR = '" + s.getSerialNumber() +"'";
            ResultSet srs = stmt.executeQuery(sql);
            if (srs.next()) {
                // UPDATE
                sql = "UPDATE DEVICE "
                        + "SET CODE = '" + s.getCode() + "'"
                        + ",INSTDATE = '" + s.getInstdate() +"'";
                stmt.executeUpdate(sql);
                toegevoegd = true;
            } else {
                // INSERT
                sql = "INSERT into DEVICE "
                        + "(SERIALNR, CODE) "
                        + "VALUES (" + s.getSerialNumber()
                        + ", '" + s.getCode() +"')";

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

    public static boolean deleteDevice (Device device) throws DBException {
        boolean verwijderd = false;
        Connection con = null;
        try {
            con = DBConnector.getConnection();
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT SERIALNR "
                    + "FROM DEVICE "
                    + "WHERE SERIALNR = '" + device.getSerialNumber() +"'";
            ResultSet srs = stmt.executeQuery(sql);

            if (srs.next()) {
                sql = "DELETE FROM DEVICE "
                        + "WHERE SERIALNR = '" + device.getSerialNumber() +"'";
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
    public static ArrayList<Integer> getDeviceSerialNumbers() throws DBException {
        ArrayList<Integer> lijstSerialNrs = new ArrayList<>();
        for(int i=0; i<= getDevices().size() ;i++)
            lijstSerialNrs.add(getDevices().get(i).getSerialNumber() );

        return lijstSerialNrs;
    }

    public static ArrayList<Integer> getDevicesinRoom(int locationNr)
    {
        ArrayList<Integer> resultaat= new ArrayList<>();
        Connection con=null;
        try
        {
            con = DBConnector.getConnection();
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT SERIALNR "
                    + "FROM DEVICE "
                    + "WHERE idLOCATIONNR = '" + locationNr +"'";
            ResultSet srs = stmt.executeQuery(sql);
            while (srs.next())
                resultaat.add(srs.getInt("SERIALNR"));
        } catch (DBException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return resultaat;
    }

    public static String getDateTime(String date){
        String a = Character.toString(date.charAt(0));
        String b = Character.toString(date.charAt(1));
        String c = Character.toString(date.charAt(3));
        String d = Character.toString(date.charAt(4));
        String e = Character.toString(date.charAt(6));
        String f = Character.toString(date.charAt(7));
        String g = Character.toString(date.charAt(8));
        String h = Character.toString(date.charAt(9));

        String dateTime = e+f+g+h + "-" + c+d + "-" + a+b;
        return dateTime;
    }

    public static String convertDate (String date){
        String newDate = "";
        String a = Character.toString(date.charAt(0));
        String b = Character.toString(date.charAt(1));
        String c = Character.toString(date.charAt(2));
        String d = Character.toString(date.charAt(3));
        String e = Character.toString(date.charAt(5));
        String f = Character.toString(date.charAt(6));
        String g = Character.toString(date.charAt(8));
        String h = Character.toString(date.charAt(9));
        newDate = g + h + "-" + e + f + "-" + a + b + c + d;
        return newDate;
    }


    public static void Install(int serialnumber, String date, int locationNr) {
        boolean gelukt = false;
        Connection con = null;
        try {
            con = DBConnector.getConnection();
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

            String sql = "SELECT SERIALNR "
                    + "FROM DEVICE "
                    + "WHERE SERIALNR = '" + serialnumber + "'";
            ResultSet srs = stmt.executeQuery(sql);
            if (srs.next()) {
                // UPDATE
                sql = "UPDATE DEVICE "
                        + "SET INSTDATE = '" + getDateTime(date) + "'"
                        + ",idLOCATIONNR= '" + locationNr + "'"
                        + "WHERE SERIALNR = '" + serialnumber + "'";
                stmt.executeUpdate(sql);
                gelukt = true;
            }
        } catch (DBException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if (gelukt) {
            System.out.println("Installatie gelukt!");
        }
        else {
            System.out.println("Installatie mislukt.");
        }
    }

    public static void DeInstall(int serialnumber) {
        boolean gelukt = false;
        Connection con = null;
        try {
            con = DBConnector.getConnection();
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

            String sql = "SELECT SERIALNR "
                    + "FROM DEVICE "
                    + "WHERE SERIALNR = '" + serialnumber + "'";
            ResultSet srs = stmt.executeQuery(sql);
            if (srs.next()) {
                // UPDATE
                sql = "UPDATE DEVICE "
                        + "SET INSTDATE = DEFAULT "
                        + ", idLOCATIONNR= DEFAULT"
                        + " WHERE SERIALNR = '" + serialnumber + "'";
                stmt.executeUpdate(sql);
                gelukt = true;
            }
        } catch (DBException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if (gelukt) {
            System.out.println("Deïnstallatie gelukt!");
        }
        else {
            System.out.println("Deïnstallatie mislukt.");
        }
    }

    public static boolean IsSerialNrAlAanwezig (int SerialNr) {
        Connection con = null;
        boolean aanwezig = false;
        try {
            con = DBConnector.getConnection();
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT * "
                    + "FROM DEVICE "
                    + " WHERE SERIALNR = '" + SerialNr + "'";
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

    public static int genereereenUniekSerialNr () throws DBException {
        int positie = 1;
        for (int i = 1; i <= getDevices().size(); i++) {
            if (IsSerialNrAlAanwezig(i)) {
                positie = (getDevices().size() +1);
            }
            else {
                positie = i;
                return positie;
            }

        } return positie;

    }

    public static String getCodeFromSerialNr(int SerialNr) throws DBException {
        Connection con = null;
        String code = "";
        try {
            con = DBConnector.getConnection();
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String query = "SELECT * "
                    + "FROM DEVICE "
                    + "WHERE SERIALNR = " + SerialNr;
            ResultSet srs = stmt.executeQuery(query);
            while (srs.next())
               code = srs.getString("CODE");
            DBConnector.closeConnection(con);
        }
        catch(DBException e){
            e.printStackTrace();
            DBConnector.closeConnection(con);
        }
        catch(Exception e){
            e.printStackTrace();
            DBConnector.closeConnection(con);
            throw new DBException(e);
        }
        return code;
    }

    public static void main(String[] args) throws DBException {
        Scanner keyboard = new Scanner(System.in);
        boolean check = false;
        boolean check1 = false;
        boolean check2 = false;
        boolean check3 = false;
        boolean check4 = false;

        while (!check) {
            System.out.println("Wil u een nieuw Device toevoegen aan de database?");
            String antwoord = keyboard.nextLine();
            if (antwoord.equalsIgnoreCase("ja"))
            {
                int n= genereereenUniekSerialNr();
                DBDevice.save(new Device(n));
                System.out.println("Is dit Device reeds op locatie?");
                String answer= keyboard.nextLine();
                if(answer.equalsIgnoreCase("ja"))
                {
                    System.out.println("Op welke datum werd het device geïnstalleerd?");
                    String date= keyboard.nextLine();
                    System.out.println("Waar is het device geïnstalleerd? (geeft Locatienummer)");
                    int lNr= keyboard.nextInt();
                    Install(n,date,lNr);
                }
                else {
                    System.out.println("Device aangemaakt zonder locatie in de database.");
                }

            }
            else
            {
              check = true;
            }
        }

        while (!check1) {
            System.out.println("Wil u een bestaand Device updaten?");
            String antwoord = keyboard.nextLine();

            if (antwoord.equalsIgnoreCase("ja")) {
                System.out.println("welk serialnummer heeft het device?");
                int serialnumber = Integer.parseInt(keyboard.nextLine());
                DBDevice.save(new Device(serialnumber));
            } else
                {
                check1 = true;
            }
        }

        while (!check2) {
            System.out.println("Wil u een device installeren?");
            String antwoord = keyboard.nextLine();

            if (antwoord.equalsIgnoreCase("ja")) {
                System.out.println("Wat is de installatiedatum ?");
                String datum = keyboard.nextLine();
                System.out.println("Waar installeer je het device, geef locatienummer.");
                int locationnummer = Integer.parseInt(keyboard.nextLine());
                System.out.println("Wat is het het serialnummer van de geïnstalleerde device?");
                int serialNr = Integer.parseInt(keyboard.nextLine());
                DBDevice.Install(serialNr, datum, locationnummer);
            } else {
                check2 = true;
            }
        }


        while (!check3) {
            System.out.println("Wil u een device deïnstalleren?");
            String antwoord = keyboard.nextLine();
            if (antwoord.equalsIgnoreCase("ja")) {
                System.out.println("Wat is het serialnummer van de device?");
                int serialNr = Integer.parseInt(keyboard.nextLine());
                DBDevice.DeInstall(serialNr);
            } else {
                check3 = true;
            }
        }
        while (!check4) {
            System.out.println("Wil u een  device verwijderen uit de database?");
            String antwoord2 = keyboard.nextLine();
            if (antwoord2.equalsIgnoreCase("ja")) {
                System.out.println("Wat is het serialnummer van de device?");
                int deviceNr = Integer.parseInt(keyboard.nextLine());
                DBDevice.deleteDevice(getDevice(deviceNr));
            } else
                check4 = true;
        }
        System.out.println(getCodeFromSerialNr(1));
    }

}
