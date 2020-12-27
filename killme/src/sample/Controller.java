package sample;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    public Button chat;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image image = new Image("");
        ImageView view = new ImageView(image);
        chat.setGraphic(view);
    }
}
