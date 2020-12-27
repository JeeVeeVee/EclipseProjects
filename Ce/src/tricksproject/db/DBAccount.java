/*package tricksproject.db;
import tricksproject.logic.Account;
import tricksproject.logic.Person;
import tricksproject.logic.Student;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DBAccount {



    public static ArrayList<Account> getAccounts() throws DBException {
        Connection con = null;
        ArrayList<Account> nieuwelijst = new ArrayList<>();
        try {
            con = DBConnector.getConnection();
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String query = "SELECT * "
                    + "FROM ACCOUNT ";
            ResultSet srs = stmt.executeQuery(query);
            nieuwelijst = new ArrayList<>();
            while (srs.next())
                nieuwelijst.add(getAccount(srs.getString("ACCOUNTNAME")));
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

    public static void save(Account s) throws DBException {
        boolean toegevoegd = false;
        Connection con = null;
        try {
            con = DBConnector.getConnection();
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

            String sql = "SELECT ACCOUNTNR_FK "
                    + "FROM ACCOUNT "
                    + " WHERE ACCOUNTNR_FK = '" + s.getAccountNr() + "'";
            ResultSet srs = stmt.executeQuery(sql);
            if (srs.next()) {
                // UPDATE
                sql = "UPDATE ACCOUNT "
                        + "SET ACCOUNTNAME = '" + s.getAccountNaam() + "'"
                        + ", PASSWORD = '" + s.getWachtwoord() + "'"
                        + " WHERE ACCOUNTNR_FK = '" + s.getAccountNr() + "'";
                stmt.executeUpdate(sql);
                toegevoegd = true;
            } else {
                // INSERT
                sql = "INSERT into ACCOUNT "
                        + "(ACCOUNTNAME, PASSWORD, ACCOUNTNR_FK) "
                        + "VALUES ('" + s.getAccountNaam() +"'"
                        + ", '" + s.getWachtwoord() + "'"
                        + ", '" + s.getAccountNr() + "')";

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

    public static boolean deleteAccount (String accountname) throws DBException {
        boolean verwijderd = false;
        Connection con = null;
        try {
            con = DBConnector.getConnection();
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT ACCOUNTNAME "
                    + "FROM ACCOUNT "
                    + "WHERE ACCOUNTNAME = '" + accountname +"'";
            ResultSet srs = stmt.executeQuery(sql);

            if (srs.next()) {
                sql = "DELETE FROM ACCOUNT "
                        + "WHERE ACCOUNTNAME = '" + accountname +"'";
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

    public static ArrayList<String> getAccountNamen() throws DBException {
        ArrayList<String> lijstMetNamen = new ArrayList<>();
        for(int i=0;i<=getAccounts().size();i++)
            lijstMetNamen.add(getAccounts().get(i).getAccountNaam() + getAccounts().get(i).getWachtwoord());

        return lijstMetNamen;
    }

    public static String getAccountNameFromNr (int accountnr) {
        Connection con = null;
        String accountName = "";
        try {
            con = DBConnector.getConnection();
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT ACCOUNTNAME "
                    + "FROM ACCOUNT "
                    + "WHERE ACCOUNTNR_FK = '" + accountnr + "'";

            ResultSet srs = stmt.executeQuery(sql);
            if (srs.next()) {
                accountName = srs.getString("ACCOUNTNAME");
            }
        } catch (DBException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return accountName;
    }
    public static String getPasswordFromNr (int accountnr) {
        Connection con = null;
        String password = "";
        try {
            con = DBConnector.getConnection();
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT PASSWORD "
                    + "FROM ACCOUNT "
                    + "WHERE ACCOUNTNR_FK = '" + accountnr + "'";

            ResultSet srs = stmt.executeQuery(sql);
            if (srs.next()) {
                password = srs.getString("PASSWORD");
            }
        } catch (DBException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return password;
    }

    public static void main(String[] args) throws DBException {
    }

}
*/