package network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
  private InetAddress host;
  private Socket socket;
  private int port;
  private NetworkController networkController;

  public Client(String host, int port, NetworkController networkController) throws IOException {
    this.port = port;
    this.networkController = networkController;
    this.host = InetAddress.getByName(host);
  }

  void sendMessage(String message) {
    try {
      this.socket = new Socket(this.host, port);
      ObjectOutputStream oos = new ObjectOutputStream(this.socket.getOutputStream());
      oos.writeObject(message);
      ObjectInputStream ois = new ObjectInputStream(this.socket.getInputStream());
      String serverMessage = (String) ois.readObject();
      System.out.println("Client: " + serverMessage);
      Thread.sleep(50); // why this?
      this.socket.close();
    } catch (IOException | ClassNotFoundException | InterruptedException e) {
      String errMsg = "Client: Send message error" + e.getClass();
      System.err.println(errMsg);
      e.printStackTrace();
    }
  }

}
