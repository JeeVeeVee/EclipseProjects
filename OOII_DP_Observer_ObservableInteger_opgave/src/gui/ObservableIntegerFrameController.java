package gui;

import domein.DomeinController;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class ObservableIntegerFrameController extends VBox {

    @FXML
    private Label lblValue;
    
    @FXML
    private Label lblDoubleValue;
    
    @FXML
    private Button btnUp;
    
    @FXML
    private Button btnDown;
    
    private DomeinController domeinController;
    
    public ObservableIntegerFrameController(DomeinController domeinController) {
        this.domeinController = domeinController;
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ObservableIntegerFrame.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        
        
    }

    @FXML
    private void up(ActionEvent event) {
        domeinController.up();
    }
    
    @FXML
    private void down(ActionEvent event) {
        domeinController.down();
    }

}
