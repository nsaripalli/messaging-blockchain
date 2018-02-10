package network;

import h804.Blockchain;
import ui.Gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NetworkController {

  private int port;
  private Server server;
  private Blockchain blockchain;
  private List<Client> clients = new ArrayList<>();
  private Gui gui;

  public NetworkController(int port, Blockchain blockchain, Gui gui) {
    this.port = port;
    this.server = new Server(port, this);
    this.blockchain = blockchain;
    this.gui = gui;
    Thread curServerThread = new Thread(server::run, "Server thread");
    curServerThread.start();
  }

  public void createNewClient(String hostname) {
    try {
      clients.add(new Client(hostname, port, this));
    } catch (IOException e) {
      e.printStackTrace();
      System.err.println("Couldn't resolve name: " + hostname);
    }
  }

  public void removeClient(Client c) {
    clients.remove(c);
  }

  public void sendUIMessage(String s) {
    if (this.gui == null) {
      System.err.println("Gui has not been initialized in Network Controller!");
      return;
    }

    this.gui.appendToChat(s);
  }

  public void decodeMessage(String m) {
    gui.appendToChat(blockchain.decode(m));
  }

  public void sendMessage(String text) {
//    String encrypted = blockchain.encode(text);
    for (Client c : clients) {
      c.sendMessage(text);
//      c.sendMessage(encrypted);
    }
  }
}
