package main;

import domein.ChatBodyUDP;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import gui.ChatFrame;

public class StartUp extends Application {

    @Override
    public void start(Stage stage) {
        String status = getParameters().getRaw().isEmpty()?"SERVER":getParameters().getRaw().get(0);
        String host= getParameters().getRaw().size()<2?"localhost":getParameters().getRaw().get(1);
        ChatBodyUDP chatUDPbody = new ChatBodyUDP(status, host);
        Scene scene = new Scene(new ChatFrame(chatUDPbody));
        chatUDPbody.start();
        stage.setTitle("Chat " + status);
        stage.setScene(scene);
        // The stage will not get smaller than its preferred (initial) size.
        stage.setOnShown((WindowEvent t) -> {
            stage.setMinWidth(stage.getWidth());
            stage.setMinHeight(stage.getHeight());
        });
        stage.addEventHandler(WindowEvent.WINDOW_CLOSE_REQUEST, e -> System.exit(0));
        stage.show();
    }

    public static void main(String... args) {
        Application.launch(StartUp.class, args);
    }
}