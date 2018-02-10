package h804;

//import javafx.application.Application;
import network.NetworkController;
//import ui.Gui;

public class Main {
  public static final int RSA_KEY_LENGTH = 2048;
  public static final int PORT = 13337;
  public static NetworkController nc;

  public static void main(String[] args) {
    nc = new NetworkController(PORT,RSA_KEY_LENGTH, new Blockchain());
    nc.createNewClient("Nithin");
  }
}
