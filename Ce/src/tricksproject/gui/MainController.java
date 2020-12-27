package tricksproject.gui;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private BorderPane mainPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    public void MetingToevoegen(javafx.event.ActionEvent actionEvent) throws IOException {
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("measure");
        mainPane.setCenter(view);
    }

    @FXML
    public void Forecast(javafx.event.ActionEvent actionEvent) throws IOException {
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("Forecast");
        mainPane.setCenter(view);
    }

    @FXML
    public void Report(javafx.event.ActionEvent actionEvent) throws IOException{
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("Report");
        mainPane.setCenter(view);

    }
}

