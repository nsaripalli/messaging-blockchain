package ui;

import java.io.IOException;

import common.IObserver;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.control.Button;

public class Gui extends Application implements IObserver {

  private Stage primaryStage;
  private AnchorPane rootLayout;

  @FXML
  private TextField chatHistory;

  public void start() {
    launch();
  }

  @Override
  public void start(Stage primaryStage) {
    this.primaryStage = primaryStage;
    this.primaryStage.setTitle("Hello world!");
    this.primaryStage.setAlwaysOnTop(true);
    this.primaryStage.setX(1920 * 3/5);
    this.primaryStage.setY(1080 / 2);

    initRootLayout();
  }

  private void initRootLayout() {
    try {
      // Load root layout from fxml file.
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(Gui.class.getResource("Gui.fxml"));
      rootLayout =  loader.load();

      // Show the scene containing the root layout.
      Scene scene = new Scene(rootLayout);
      primaryStage.setScene(scene);
      primaryStage.show();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void clientMessage(String message) {

  }

  @Override
  public void serverMessage(String message) {

  }

  public void handleConnectButtonAction(ActionEvent actionEvent) {
    System.out.println("clicked!");
    chatHistory.appendText("hello");
  }
}
