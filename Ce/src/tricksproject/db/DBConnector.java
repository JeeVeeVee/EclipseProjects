/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tricksproject.db;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.SQLException;

import static tricksproject.db.DBPerson.getAccountNumberFromAccount;


public class DBConnector {

    private static final String DB_NAME = "db2020_04";//vul hier uw databank naam in
    private static final String DB_PASS = "bhuajdns";//vul hier uw databank paswoord in
    private static final String SELECT_QUERY = "SELECT * FROM person WHERE AccountName = ? and Password = ?";
    private static final String SELECT_ACCOUNTNR = "SELECT ACCOUNTNR FROM person WHERE AccountName = ? and Password = ?";



    public static Connection getConnection() throws DBException {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String protocol = "jdbc";
            String subProtocol = "mysql";
            String myDatabase = "//pdbmbook.com/" + DB_NAME;
            String URL = protocol + ":" + subProtocol + ":" + myDatabase
                    + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            con = DriverManager.getConnection(URL, DB_NAME, DB_PASS);
            return con;
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            closeConnection(con);
            throw new DBException(sqle);
        } catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
            closeConnection(con);
            throw new DBException(cnfe);
        } catch (Exception ex) {
            ex.printStackTrace();
            closeConnection(con);
            throw new DBException(ex);
        }
    }

    public static void closeConnection(Connection con) {
        try {
            if(con != null)
                con.close();
        } catch (SQLException e) {
            //do nothing
        }
    }



    public boolean validate(String gebruikersnaam, String wachtwoord) throws SQLException {
        String protocol = "jdbc";
        String subProtocol = "mysql";
        String myDatabase = "//pdbmbook.com/" + DB_NAME;
        String URL = protocol + ":" + subProtocol + ":" + myDatabase
                + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        // Step 1: Establishing a Connection and
        // try-with-resource statement will auto close the connection.
        try (Connection connection = DriverManager
                .getConnection(URL, DB_NAME, DB_PASS);


             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUERY) ){
            preparedStatement.setString(1, gebruikersnaam);
            preparedStatement.setString(2, wachtwoord);

            System.out.println(preparedStatement);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return true;
            }


        } catch (SQLException e) {
            // print SQL exception information
            printSQLException(e);
        }
        return false;
    }

    public static void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}











