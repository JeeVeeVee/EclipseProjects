package gui;

import domein.Dier;
import domein.Verzoek;
import domein.Verzorger;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;

public class QueryPanelController extends GridPane {

    @FXML
    private ToggleGroup query;

    @FXML
    private RadioButton query1;

    @FXML
    private RadioButton query2;
    @FXML
    private RadioButton query3;
    @FXML
    private RadioButton query4;

    @FXML
    private ListView resultatenListView;
    @FXML
    private ProgressIndicator spinner;
    @FXML
    private TextField parameter;

    private Socket socket;
    private ObjectOutputStream connectionOutput;
    private ObjectInputStream connectionInput;

    public QueryPanelController() {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("QueryPanel.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        spinner.setVisible(false);
    }

    //generieke methode om een lijst van bepaalde willekeurige objecten
    //aan de listview resultatenListView te koppelen
    //(aanroepen vanuit de methode doeQuery)
    private <T> void showValue(List<T> values){
    	spinner.setVisible(false);
       //TODO... resultatenListView
    	resultatenListView.setItems(FXCollections.observableList(values));
       
    }

    //
    //hulpmethode om de query door te geven aan de server,
    //en het resultaat terug op te vragen
    //(aanroep vanuit methode doeQuery 
    public <T extends Serializable> void contactServer(Verzoek<T> verzoek){
    	verzendVerzoek(verzoek);
        verzoek = ontvangResultaat();
        showValue(verzoek.getResult());
    }

    public void doeQuery() {
        initConnection();
        spinner.setVisible(true);
        resultatenListView.setItems(null);
        if (query1.isSelected()) {              //een lijst van Dier objecten
            contactServer(new Verzoek<Dier>("query1", parameter.getText()));
        } else if (query2.isSelected()) {    //enkel één double in een lijst
            contactServer(new Verzoek<Double>("query2", parameter.getText()));
        } else if (query3.isSelected()) {     // een lijst van String
            contactServer(new Verzoek<String>("query3", parameter.getText()));
        } else if (query4.isSelected()) {     // een  lijst van Verzorger objecten
            contactServer(new Verzoek<Verzorger>("query4", parameter.getText()));
        }
        closeConnection();
    }

    public <T extends Serializable> void verzendVerzoek(Verzoek<T> verzoek){
    	try {
			connectionOutput.writeObject(verzoek);
			connectionOutput.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

   public <T extends Serializable> Verzoek<T> ontvangResultaat() {
	   try {
		   return (Verzoek<T>) connectionInput.readObject();
	   } catch (ClassNotFoundException | IOException e) {
		   e.printStackTrace();
	   }
	   return null;
   }

    private void initConnection() {
        final int PORT = 3456;
        final String HOST = "localhost";
        try {
			socket = new Socket(HOST, PORT);
			connectionOutput = new ObjectOutputStream(socket.getOutputStream());
			connectionOutput.flush();
			connectionInput =  new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
        	System.out.println("get fucked");
			e.printStackTrace();
		}
   
   
    }

    private void closeConnection() {
        try {
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(QueryPanelController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
