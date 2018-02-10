package network;

import common.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class Client implements ISender<String>, IReceiver<String> {

  private InetAddress host;
  private Socket socket;
  private NetworkController networkController;

  public Client(String host, int port, NetworkController networkController) throws IOException {
    this.networkController = networkController;
    this.host = InetAddress.getByName(host);
    this.socket = new Socket(this.host, port);
    this.sendMessage("request public key");
  }

  void sendMessage(String message) {
    try {
      ObjectOutputStream oos = new ObjectOutputStream(this.socket.getOutputStream());
      oos.writeObject(message);
      ObjectInputStream ois = new ObjectInputStream(this.socket.getInputStream());
      String serverMessage = (String) ois.readObject();
      send(serverMessage);
      Thread.sleep(50); // why this?
    } catch (IOException|ClassNotFoundException|InterruptedException e) {
      String errMsg = "Client: Send message error" + e.getClass();
      send(errMsg);
      System.err.println(errMsg);
      e.printStackTrace();
    }
  }

  @Override
  public void send(String serverResponse) {
    networkController.receive(serverResponse);
  }

  @Override
  public void receive(String msgToSendOut) {
    sendMessage(msgToSendOut);
  }
}