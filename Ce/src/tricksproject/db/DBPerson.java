package tricksproject.db;

import tricksproject.logic.Person;
import tricksproject.logic.Account;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBPerson {


    public static int getAccountNumberFromAccount(String account, String password) {
        Connection con = null;
        int accountNR = 0;
        try {
            con = DBConnector.getConnection();
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT ACCOUNTNR "
                    + "FROM PERSON "
                    + "WHERE AccountName = '" + account + "'"
                    + " AND Password = '" + password + "'";
            ResultSet srs = stmt.executeQuery(sql);
            if (srs.next()) {
                accountNR = srs.getInt("ACCOUNTNR");
            }
        } catch (DBException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return accountNR;
    }

    public static Person getPerson(int AccountNR) throws DBException {
        Connection con = null;
        try {
            con = DBConnector.getConnection();
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT * "
                    + "FROM PERSON "
                    + "WHERE ACCOUNTNR = '" + AccountNR + "'";
            ResultSet srs = stmt.executeQuery(sql);
            int accountnr;
            String firstName, lastName, accountName, password;

            if (srs.next()) {
                accountnr = srs.getInt("ACCOUNTNR");
                firstName = srs.getString("FNAME");
                lastName = srs.getString("LNAME");
                accountName = srs.getString("AccountName");
                password = srs.getString("Password");

            } else {
                DBConnector.closeConnection(con);
                return null;
            }
            Person persoon = new Person(accountnr, firstName, lastName, accountName, password);
            DBConnector.closeConnection(con);
            return persoon;
        } catch (Exception ex) {
            ex.printStackTrace();
            DBConnector.closeConnection(con);
            throw new DBException(ex);
        }
    }

    public static ArrayList<Person> getPersonen() throws DBException {
        Connection con = null;
        try {
            con = DBConnector.getConnection();
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String query = "SELECT * "
                    + "FROM PERSON ";
            ResultSet srs = stmt.executeQuery(query);
            ArrayList<Person> nieuwelijst = new ArrayList<>();
            while (srs.next())
                nieuwelijst.add(getPerson(srs.getInt("ACCOUNTNR")));
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

    public static String[] WeergaveAlleNamen() throws DBException {
        Person[] arraymetPersonen = getPersonen().toArray(new Person[getPersonen().size()]);
        String[] arrayMetNamen = new String[arraymetPersonen.length];
        for (int i = 0; i < getPersonen().size(); i++) {
            arrayMetNamen[i] = arraymetPersonen[i].getFirstName() + " " + arraymetPersonen[i].getLastName();
        }
        return arrayMetNamen;
    }


    public static void save(Person s) throws DBException {
        boolean toegevoegd = false;
        Connection con = null;
        try {
            con = DBConnector.getConnection();
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

            String sql = "SELECT ACCOUNTNR "
                    + "FROM PERSON "
                    + "WHERE ACCOUNTNR = '" + s.getAccountNr() + "'";
            ResultSet srs = stmt.executeQuery(sql);
            if (srs.next()) {
                // UPDATE
                sql = "UPDATE PERSON "
                        + "SET FNAME = '" + s.getFirstName() + "'"
                        + ", LNAME = '" + s.getLastName() + "'"
                        + ", AccountName = '" + s.getAccountName() + "'"
                        + ", Password = '" + s.getPassword() + "'"
                        + "WHERE ACCOUNTNR = '" + s.getAccountNr() + "'";
                stmt.executeUpdate(sql);
                toegevoegd = true;
            } else {
                // INSERT
                sql = "INSERT into PERSON "
                        + "(ACCOUNTNR, FNAME, LNAME, AccountName, Password) "
                        + "VALUES (" + s.getAccountNr()
                        + ", '" + s.getFirstName() + "'"
                        + ", '" + s.getLastName() + "'"
                        + ", '" + s.getAccountName() + "'"
                        + ", '" + s.getPassword() + "')";
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

    public static boolean deletePerson(int accountnr) throws DBException {
        Scanner keyboard = new Scanner (System.in);
        boolean verwijderd = false;
        Connection con = null;
        try {
            con = DBConnector.getConnection();
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT ACCOUNTNR "
                    + "FROM PERSON ";
            ResultSet srs = stmt.executeQuery(sql);

            if (srs.next()) {
                sql = "DELETE FROM PERSON "
                        + "WHERE ACCOUNTNR = " + accountnr;
                stmt.executeUpdate(sql);
                verwijderd = true;
            } else {
                DBConnector.closeConnection(con);
            }

        }
        catch(SQLIntegrityConstraintViolationException e){
            System.out.println("Kan geen persoon verwijderen als deze nog verantwoordelijk is voor locaties, eerst dit aanpassen");
            System.out.println("De volgende locaties zijn aan deze persoon toegewezen: ");
            for (int i =0; i<DBLocation.getLocatiesFromAccNR(accountnr).size();i++) {
                System.out.println(DBLocation.getLocatiesFromAccNR(accountnr).get(i).toString());
                System.out.println("Wie wil je verantwoordelijk maken?");
                int accountnummer = Integer.parseInt(keyboard.nextLine());
                DBLocation.changeResponsability(DBLocation.getLocatiesFromAccNR(accountnr).get(i).getIdLocationNR(),accountnummer);
            }
            DBPerson.deletePerson(accountnr);
        }
        catch (Exception e) {
            e.printStackTrace();
            DBConnector.closeConnection(con);
            throw new DBException(e);
        }
        return verwijderd;
    }

    public static Account getAccount(int Accountnr) throws DBException {
        Connection con = null;
        try {
            con = DBConnector.getConnection();
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT * "
                    + "FROM PERSON "
                    + "WHERE ACCOUNTNR = '" + Accountnr + "'";
            ResultSet srs = stmt.executeQuery(sql);
            String accountname, password;
            int accountnr;

            if (srs.next()) {

                accountname = srs.getString("AccountName");
                password = srs.getString("Password");
                accountnr = srs.getInt("ACCOUNTNR");

            } else {
                DBConnector.closeConnection(con);
                return null;
            }
            Account account = new Account(accountname, password, accountnr);
            DBConnector.closeConnection(con);
            return account;
        } catch (Exception ex) {
            ex.printStackTrace();
            DBConnector.closeConnection(con);
            throw new DBException(ex);
        }
    }

    public static ArrayList<Account> getAccounts() throws DBException {
        Connection con = null;
        ArrayList<Account> nieuwelijst = null;
        try {
            con = DBConnector.getConnection();
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String query = "SELECT * "
                    + "FROM PERSON ";
            ResultSet srs = stmt.executeQuery(query);
            nieuwelijst = new ArrayList<>();
            while (srs.next())
                nieuwelijst.add(getAccount(srs.getInt("ACCOUNTNR")));
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

    public static ArrayList<String> getAccountNamen() throws DBException {
        ArrayList<String> lijstMetNamen = new ArrayList<>();
        for (int i = 1; i < getAccounts().size(); i++)
            lijstMetNamen.add(getAccounts().get(i).getAccountNaam());

        return lijstMetNamen;
    }

    public static boolean IsAccounNrAlAanwezig(int accountnr) {
        Connection con = null;
        boolean aanwezig = false;
        try {
            con = DBConnector.getConnection();
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT * "
                    + "FROM PERSON "
                    + " WHERE ACCOUNTNR = '" + accountnr + "'";
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

    public static int genereereenUniekAccountNr() throws DBException {
        int positie = 1;
        for (int i = 1; i <= getPersonen().size(); i++) {
            if (IsAccounNrAlAanwezig(i)) {
                positie = (getPersonen().size() + 1);
            } else {
                positie = i;
                return positie;
            }

        }
        return positie;

    }

    public static void main(String[] args) throws DBException {

        Scanner keyboard = new Scanner(System.in);
        boolean check = false;
        boolean check1 = false;
        boolean check2 = false;

        while (!check) {
            System.out.println("Wil u een nieuwe persoon toevoegen aan de database?");
            String antwoord = keyboard.nextLine();
            if (antwoord.equalsIgnoreCase("ja")) {
                System.out.println("Wat is de voornaam van de persoon?");
                String voornaam = keyboard.nextLine();
                System.out.println("Wat is de achternaam van de persoon?");
                String achternaam = keyboard.nextLine();
                System.out.println("Wat is de accountnaam van de persoon?");
                String accountnaam = keyboard.nextLine();
                System.out.println("Geef het paswoord in dat bij dit account hoort");
                String paswoord = keyboard.nextLine();
                DBPerson.save(new Person(genereereenUniekAccountNr(), voornaam, achternaam, accountnaam, paswoord));
            }
            check = true;
        }
        while (!check1) {
            System.out.println("Wil u een bestaande naam updaten aan de database?");
            String antwoord = keyboard.nextLine();
            if (antwoord.equalsIgnoreCase("ja")) {
                System.out.println("Wat is het accountnummer van de persoon?");
                int accountnr = Integer.parseInt(keyboard.nextLine());
                System.out.println("Wat is de nieuwe voornaam van de persoon?");
                String voornaam = keyboard.nextLine();
                System.out.println("Wat is de nieuwe achternaam van de persoon?");
                String achternaam = keyboard.nextLine();
                System.out.println("Wat is de nieuwe accountnaam vand de persoon?");
                String accountnaam = keyboard.nextLine();
                System.out.println("Geef het nieuw paswoord in dat bij dit account hoort");
                String paswoord = keyboard.nextLine();
                DBPerson.save(new Person(accountnr, voornaam, achternaam, accountnaam, paswoord));
            }
            check1 = true;
        }
            System.out.println("Wil u een  naam verwijderen uit de database?");
            String antwoord = keyboard.nextLine();
            if (antwoord.equalsIgnoreCase("ja")) {
                System.out.println("Wat is het accountnummer van de persoon?");
                int accountnr = keyboard.nextInt();
                DBPerson.deletePerson(accountnr);
            } else
                check2 = true;
            }
    }
