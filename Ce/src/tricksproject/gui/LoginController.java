package tricksproject.gui;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import tricksproject.db.DBConnector;
import tricksproject.db.DBException;

import static tricksproject.db.DBPerson.getAccountNumberFromAccount;


public class LoginController {

    private static String gebruikersnaam;
    private static String password;

    @FXML
    private TextField gebruikersnaamField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button submitButton;

    @FXML
    public void login(ActionEvent event) throws SQLException, DBException, IOException {

        Window owner = submitButton.getScene().getWindow();

        System.out.println(gebruikersnaamField.getText());
        System.out.println(passwordField.getText());

        if (gebruikersnaamField.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Log in Error!",
                    "Gelieve uw gebruikersnaam in te voeren");
            return;
        }
        if (passwordField.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Log in Error!",
                    "Gelieve uw wachtwoord in te voeren");
            return;
        }

        gebruikersnaam = gebruikersnaamField.getText();
        password = passwordField.getText();

        DBConnector jdbcDao = new DBConnector();
        Connection con = null ;
        con = DBConnector.getConnection();

        boolean flag = jdbcDao.validate(gebruikersnaam, password);


        if (!flag) {
            infoBox("Gelieve een correct wachtwoord of gebruikersnaam in te voeren.", null, "Failed");
        } else {
            infoBox("Succesvol ingelogd ! ", null, "Failed");
            //dit is de code om het dashboardscherm te openen!
            Parent loginParent =  FXMLLoader.load(getClass().getResource("main.fxml"));
            Scene dashboardScreen = new Scene(loginParent);
            Stage dashboardStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            dashboardStage.setScene(dashboardScreen);
            dashboardStage.show();
        }
    }

    public static void infoBox(String infoMessage, String headerText, String title) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }

    private static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }

    public static String getGebruikersnaam() {
        return gebruikersnaam;
    }

    public static String getPassword() {
        return password;
    }


}