package tricksproject.gui;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import tricksproject.db.DBException;
import tricksproject.logic.Location;
import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;
import static tricksproject.db.DBLocation.getAlleLocaties;

public class Report implements Initializable {

    @FXML
    private ChoiceBox<String> LocationMeasurmentCB;

    @FXML
    void LaunchChart(ActionEvent event) {

        getChart();
    }

    private void getChart(){

        HBox root = new HBox();
        Scene scene = new Scene(root, 450, 330);

        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Date");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Air pollution Measurements");

        LineChart<String, Number> lineChart = new LineChart<String, Number>(xAxis, yAxis);
        lineChart.setTitle("AirForTheFuture");

        XYChart.Series<String, Number> dataC02 = new XYChart.Series<>();
        dataC02.getData().add(new XYChart.Data<String, Number>("Jan", 1800));
        dataC02.getData().add(new XYChart.Data<String, Number>("Feb", 2000));
        dataC02.getData().add(new XYChart.Data<String, Number>("Apr", 2100));
        dataC02.getData().add(new XYChart.Data<String, Number>("May", 1900));
        dataC02.getData().add(new XYChart.Data<String, Number>("June", 2400));
        dataC02.getData().add(new XYChart.Data<String, Number>("Aug", 2200));
        dataC02.setName("CO2");


        XYChart.Series<String, Number> dataN2 = new XYChart.Series<>();
        dataN2.getData().add(new XYChart.Data<String, Number>("Jan", 1000));
        dataN2.getData().add(new XYChart.Data<String, Number>("Feb", 1500));
        dataN2.getData().add(new XYChart.Data<String, Number>("Apr", 1800));
        dataN2.getData().add(new XYChart.Data<String, Number>("May", 1500));
        dataN2.getData().add(new XYChart.Data<String, Number>("June", 1600));
        dataN2.getData().add(new XYChart.Data<String, Number>("Aug", 1700));
        dataN2.setName("N2");

        XYChart.Series<String, Number> dataPM25 = new XYChart.Series<>();
        dataPM25.getData().add(new XYChart.Data<String, Number>("Jan", 100));
        dataPM25.getData().add(new XYChart.Data<String, Number>("Feb", 150));
        dataPM25.getData().add(new XYChart.Data<String, Number>("Apr", 180));
        dataPM25.getData().add(new XYChart.Data<String, Number>("May", 150));
        dataPM25.getData().add(new XYChart.Data<String, Number>("June", 160));
        dataPM25.getData().add(new XYChart.Data<String, Number>("Aug", 170));
        dataPM25.setName("PM2.5");

        XYChart.Series<String, Number> dataPM5 = new XYChart.Series<>();
        dataPM5.getData().add(new XYChart.Data<String, Number>("Jan", 1050));
        dataPM5.getData().add(new XYChart.Data<String, Number>("Feb", 1550));
        dataPM5.getData().add(new XYChart.Data<String, Number>("Apr", 1850));
        dataPM5.getData().add(new XYChart.Data<String, Number>("May", 1500));
        dataPM5.getData().add(new XYChart.Data<String, Number>("June", 1400));
        dataPM5.getData().add(new XYChart.Data<String, Number>("Aug", 1600));
        dataPM5.setName("PM5");

        XYChart.Series<String, Number> dataPM10 = new XYChart.Series<>();
        dataPM10.getData().add(new XYChart.Data<String, Number>("Jan", 2000));
        dataPM10.getData().add(new XYChart.Data<String, Number>("Feb", 2500));
        dataPM10.getData().add(new XYChart.Data<String, Number>("Apr", 3300));
        dataPM10.getData().add(new XYChart.Data<String, Number>("May", 3000));
        dataPM10.getData().add(new XYChart.Data<String, Number>("June", 3200));
        dataPM10.getData().add(new XYChart.Data<String, Number>("Aug", 3000));
        dataPM10.setName("PM10");

        lineChart.getData().addAll(dataC02, dataN2, dataPM25, dataPM5, dataPM10);
        root.getChildren().add(lineChart);

        Stage stage = new Stage();
        stage.setTitle("REPORT");
        stage.setScene(scene);
        stage.show();
    }

    public HashSet<String> getUniqueLocaties () {
        HashSet<String> uniqueLocForGraph = new HashSet<>();
        try {
            for(Location l : getAlleLocaties()) {
                for (int i = 0; i < getAlleLocaties().size(); i++)
                    uniqueLocForGraph.add(getAlleLocaties().get(i).getAdres());
            }
        } catch (DBException e) {
            e.printStackTrace();
        }
        return uniqueLocForGraph;
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        for(String s: getUniqueLocaties())
            LocationMeasurmentCB.getItems().add(s);
    }
}
