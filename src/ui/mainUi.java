package ui;

import java.util.Observer;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

public class MessageObserver implements Observer {
  private String clientMessage;
  private String serverMessage;

  private static String obser
}

public class mainUi extends Application {

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) {
    Button connect = new Button();
    connect.setText("Connect");
    connect.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        System.out.
      }
    });
  }
}
