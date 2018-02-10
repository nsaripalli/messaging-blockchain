package ui;

import java.io.IOException;

import common.*;
import h804.Main;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import network.Client;
import network.NetworkController;

public class Gui extends Application implements ISender<String>, IReceiver<String> {

  private Stage primaryStage;
  private AnchorPane rootLayout;
  private GuiAdapter adapter;

  // FXML objects
  public TextArea chatHistory;
  public TextField hostNameEntry;
  public TextField chatInput;

  public void start(GuiAdapter adapter) {
    this.adapter = adapter;
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
    chatHistory.appendText("Client: " + message + "\n");
  }

  @Override
  public void serverMessage(String message) {
    chatHistory.appendText("Server: " + message + "\n");
  }

  public void handleConnectButtonAction(ActionEvent actionEvent) {
    try {
      hostNameEntry.setText("");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void handleKeyInput(KeyEvent keyEvent) {
    if (chatInput.isFocused()) {
      switch (keyEvent.getCode()) {
        case ENTER:
          System.out.println("Test");
      }
    }
  }
}
