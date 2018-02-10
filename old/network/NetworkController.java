package network;

import common.IObserver;
import common.IRelay;
import ui.Gui;
import h804.Blockchain;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NetworkController implements IRelay<String> {

  private List<Client> clientList = new ArrayList<>();
  private Server server;
  private Blockchain blockchain;

  private int port;

  public NetworkController(int port, Blockchain blockchain) {
    this.port = port;
    this.server = new Server(port, this);
    this.blockchain = blockchain;
    server.start();
  }

  public void createNewClient(String hostname) {
    try {
      clientList.add(new Client(hostname, port, this));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void receiveAbove(String s) {
  }

  @Override
  public void receiveBelow(String s) {

  }

  @Override
  public void sendAbove(String s) {
    blockchain.receiveBelow(s);
  }

  @Override
  public void sendBelow(String s) {

  }
}
