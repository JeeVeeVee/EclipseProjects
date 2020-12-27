package tricksproject.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent loginParent =  FXMLLoader.load(getClass().getResource("main.fxml"));
        Scene dashboardScreen = new Scene(loginParent);
        stage.setScene(dashboardScreen);
        stage.show();
    }
}
