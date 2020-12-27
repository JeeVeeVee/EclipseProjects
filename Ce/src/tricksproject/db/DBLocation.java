package tricksproject.db;

import tricksproject.logic.Location;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class DBLocation {


    public static Location getLocation (int locationNR) throws DBException {
        Connection con = null;
        try {
            con = DBConnector.getConnection();
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT * "
                    + "FROM LOCATION "
                    + "WHERE idLOCATIONNR = '" + locationNR + "'";
            ResultSet srs = stmt.executeQuery(sql);
            int idLocationNR,accountnr;
            String locationType;
            String adres, categorie;
            if (srs.next()) {
                idLocationNR = srs.getInt("idLOCATIONNR");
                locationType = srs.getString("LOCATIONTYPE");
                adres = srs.getString("ADDRESS");
                accountnr=srs.getInt("FK_PERSON_ACCOUNTNR");
                categorie= srs.getString("CATEGORY");
            } else {
                DBConnector.closeConnection(con);
                return null;
            }
            Location locatie = new Location(idLocationNR, locationType, adres,categorie, accountnr);
            DBConnector.closeConnection(con);
            return locatie;
        } catch (Exception ex) {
            ex.printStackTrace();
            DBConnector.closeConnection(con);
            throw new DBException(ex);
        }
    }
    public static ArrayList<Location> getAlleLocaties() throws DBException {
        Connection con = null;
        ArrayList<Location> nieuwelijst = null;
        try
        {
            con = DBConnector.getConnection();
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String query = "SELECT * "
                    + "FROM LOCATION ";
            ResultSet srs = stmt.executeQuery(query);
            nieuwelijst = new ArrayList<>();
            while (srs.next())
                nieuwelijst.add(getLocation(srs.getInt("idLOCATIONNR")));
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

    public static ArrayList<Location> getLocatiesFromAccNR(int accountnr) throws DBException {
        Connection con = null;
        ArrayList<Location> nieuwelijst = new ArrayList<>();
        try {
            con = DBConnector.getConnection();
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String query = "SELECT * "
                    + "FROM LOCATION "
                    + "WHERE FK_PERSON_ACCOUNTNR = " + accountnr;
            ResultSet srs = stmt.executeQuery(query);
            while (srs.next())
                nieuwelijst.add(getLocation(srs.getInt("idLOCATIONNR")));
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

    public static ArrayList<String> getAdressenFromAccNR(int accountnr) throws DBException {
        ArrayList<String> adressen  = new ArrayList<>();
        for(Location l : getLocatiesFromAccNR(accountnr)) {
            if (!adressen.contains(l.getAdres())) {
                adressen.add(l.getAdres());
            }
        }
        return adressen;
    }
    public static ArrayList<String> getLocationTypeFromAdres (String adres, int accountnr) throws DBException {
        ArrayList<String> adressen = getAdressenFromAccNR(accountnr);
        ArrayList<String>  types = new ArrayList<>();
        for(Location l : getLocatiesFromAccNR(accountnr)){
            if(l.getAdres().equals(adres)){
                types.add(l.getLocationType());
            }
        }
        return types;
    }
    public static void save(Location s) throws DBException {
        boolean toegevoegd = false;
        Connection con = null;
        try {
            con = DBConnector.getConnection();
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

            String sql = "SELECT idLOCATIONNR "
                    + "FROM LOCATION "
                    + "WHERE idLOCATIONNR = '" + s.getIdLocationNR() + "'";
            ResultSet srs = stmt.executeQuery(sql);
            if (srs.next()) {
                sql = "UPDATE LOCATION "
                        + "SET LOCATIONTYPE = '" + s.getLocationType() + "'"
                        + ", ADDRESS = '" + s.getAdres() + "'"
                        + ", CATEGORY = '" + s.getCategory() + "'"
                        + ", FK_PERSON_ACCOUNTNR = " + s.getAccountnr()
                        + " WHERE idLOCATIONNR = '" + s.getIdLocationNR() + "'";
                stmt.executeUpdate(sql);
                toegevoegd = true;
            } else {
                sql = "INSERT into LOCATION "
                        + "(idLOCATIONNR, LOCATIONTYPE, ADDRESS, CATEGORY, FK_PERSON_ACCOUNTNR) "
                        + "VALUES (" + s.getIdLocationNR()
                        + ", '" + s.getLocationType() + "'"
                        + ", '" + s.getAdres() + "'"
                        + ", '" + s.getCategory() + "'"
                        + ", '" + s.getAccountnr() + "')";
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

    public static boolean deleteLocation (int locationNR) throws DBException {
        boolean verwijderd = false;
        Connection con = null;
        try {
            con = DBConnector.getConnection();
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT idLOCATIONNR "
                    + "FROM LOCATION ";
            ResultSet srs = stmt.executeQuery(sql);

            if (srs.next()) {
                sql = "DELETE FROM LOCATION "
                        + "WHERE idLOCATIONNR = " + locationNR;
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

    public static boolean IsLocationNRAanwezig (int locationNR) {
        Connection con = null;
        boolean aanwezig = false;
        try {
            con = DBConnector.getConnection();
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT * "
                    + "FROM LOCATION "
                    + " WHERE idLOCATIONNR = '" + locationNR + "'";
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

    public static int genereereenUniekLocationNR () throws DBException {
        int positie = 1;
        for (int i = 1; i <= getAlleLocaties().size(); i++) {
            if (IsLocationNRAanwezig(i)) {
                positie = (getAlleLocaties().size() +1);
            }
            else {
                positie = i;
                return positie;
            }
        } return positie;
    }

    public static void changeResponsability (int locationNR, int accountNR){
        Connection con = null;
        try {
            con = DBConnector.getConnection();
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT idLOCATIONNR "
                    + "FROM LOCATION "
                    + "WHERE idLOCATIONNR = '" + locationNR +"'";
            ResultSet srs = stmt.executeQuery(sql);
            if (srs.next()) {
                sql = "UPDATE LOCATION "
                        + "SET FK_PERSON_ACCOUNTNR = '" + accountNR + "'"
                        + "WHERE idLOCATIONNR = '" + locationNR + "'";
                stmt.executeUpdate(sql);
            }
        } catch (DBException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static int getLocatieID(int accountnr, String adress, String locatieType) throws DBException {
        Connection con = null;
        int id = 0;
        try {
            con = DBConnector.getConnection();
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String query = "SELECT * "
                    + " FROM LOCATION "
                    + " WHERE FK_PERSON_ACCOUNTNR = " + accountnr
                    + " AND LOCATIONTYPE = '" + locatieType + "'"
                    + " AND ADDRESS = '" + adress + "'";

            ResultSet srs = stmt.executeQuery(query);
            if(srs.next()){
                id = srs.getInt("idLOCATIONNR");
            }
            else {
                DBConnector.closeConnection(con);
                return 0;
            }
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
        return id;
    }
    

    public static void main(String[] args) throws DBException {
        Scanner keyboard = new Scanner(System.in);
        boolean check = false;
        boolean check1 = false;
        boolean check2 = false;
        boolean check3 = false;

        while (!check) {
            System.out.println("Wil u een nieuwe locatie toevoegen aan de database?");
            String antwoord = keyboard.nextLine();
            if (antwoord.equalsIgnoreCase("ja")) {
                System.out.println("Wat is de naam van de ruimte (opgeslaan als LocationType) van de locatie?");
                String locatietype = keyboard.nextLine();
                System.out.println("Wat is de adres van de locatie?");
                String adres = keyboard.nextLine();
                System.out.println("Wat is het accountnr van de verantwoordelijke?");
                int accountnr = Integer.parseInt(keyboard.nextLine());
                System.out.println("Welke soort locatie is het? (in hoofdletters)");
                String categorie= keyboard.nextLine();
                DBLocation.save(new Location(genereereenUniekLocationNR(), locatietype, adres, categorie,accountnr));
            } else {
                check = true;
            }
        }

        while (!check1) {
            System.out.println("Wil u een bestaande verantwoordelijkheid updaten?");
            String antwoord1 = keyboard.nextLine();
            if (antwoord1.equalsIgnoreCase("ja")) {
                System.out.println("Op welk locatienummer wil u van verantwoordelijke veranderen?");
                int locationnr = Integer.parseInt(keyboard.nextLine());
                System.out.println("Wie wordt de nieuwe verantwoordelijke?");
                int accountnr = Integer.parseInt(keyboard.nextLine());
                DBLocation.changeResponsability(locationnr,accountnr);
            } else {
                check1 = true;
            }
        }

        while (!check2) {
            System.out.println("Wil u de gegevens van de locatie veranderen?");
            String antwoord2 = keyboard.nextLine();
            if (antwoord2.equalsIgnoreCase("ja")) {
                System.out.println("Wat is het locatienummer van de locatie?");
                int locatienummer = Integer.parseInt(keyboard.nextLine());
                System.out.println("Wat is de nieuwe naam van de ruimte (opgeslaan in LocationType) van de locatie?");
                String locatietype = keyboard.nextLine();
                System.out.println("Wat is het nieuwe adres van de locatie?");
                String adres = keyboard.nextLine();
                System.out.println("Welke soort locatie is het? (in hoofdletters)");
                String categorie= keyboard.nextLine();
                DBLocation.save(new Location(locatienummer,locatietype,adres, categorie, getLocation(locatienummer).getAccountnr()));
            }else {
                check2 = true;
            }
        }
        while(!check3) {
            System.out.println("Wil u een  locatie verwijderen uit de database?");
            String antwoord = keyboard.nextLine();
            if (antwoord.equalsIgnoreCase("ja")) {
                System.out.println("Wat is het locatienummer van de locatie?");
                int locatienr = Integer.parseInt(keyboard.nextLine());
                DBLocation.deleteLocation(locatienr);
            } else {
                check3 = true;
            }
        }

    }


}

