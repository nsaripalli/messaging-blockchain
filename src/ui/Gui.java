package ui;

import java.io.IOException;

import h804.Blockchain;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import network.NetworkController;

public class Gui extends Application {

  private Stage primaryStage;
  private AnchorPane rootLayout;
  private static NetworkController networkController; //needs to be static?
  private static final int PORT = 13337;

  // FXML objects
  @FXML
  public TextArea chatHistory;
  @FXML
  public TextField hostNameEntry;
  @FXML
  public TextField chatInput;


  @Override
  public void start(Stage primaryStage) {

    this.primaryStage = primaryStage;
    this.primaryStage.setTitle("Hello world!");
    this.primaryStage.setAlwaysOnTop(true);
    this.primaryStage.setX(1920 * 3/5);
    this.primaryStage.setY(1080 / 2);

    initRootLayout();
  }

  public void initialize() {
    Blockchain blockchain = new Blockchain();
    networkController = new NetworkController(PORT, blockchain,);
  }
  private void initRootLayout() {
    try {
      // Load root layout from fxml file.
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(Gui.class.getResource("Gui.fxml"));
      rootLayout = loader.load();

      // Show the scene containing the root layout.
      Scene scene = new Scene(rootLayout);
      primaryStage.setScene(scene);
      primaryStage.show();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void handleConnectButtonAction(ActionEvent actionEvent) {
    networkController.createNewClient(this.hostNameEntry.getText());
    this.hostNameEntry.clear();
  }

  public void handleKeyInput(KeyEvent keyEvent) {
    if (chatInput.isFocused()) {
      switch (keyEvent.getCode()) {
        case ENTER:
          System.out.println(chatInput.getText());
          networkController.sendMessage(chatInput.getText());
          chatInput.clear();
      }
    }
  }

  public void appendToChat(String s) {
    chatHistory.appendText(s);
  }
}
