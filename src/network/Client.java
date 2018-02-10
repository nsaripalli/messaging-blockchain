package network;

import org.bouncycastle.util.encoders.Hex;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
  private InetAddress host;
  public static byte[] publicKey;
  private Socket socket;
  private int port;
  private NetworkController controller;

  public Client(String host, int port, NetworkController networkController) throws IOException {
    this.port = port;
    this.controller = networkController;
    this.host = InetAddress.getByName(host);
  }

  void sendMessage(String message) {
    try {
      this.socket = new Socket(this.host, port);
      ObjectOutputStream oos = new ObjectOutputStream(this.socket.getOutputStream());
      oos.writeObject(message);
      ObjectInputStream ois = new ObjectInputStream(this.socket.getInputStream());
      String serverMessage = (String) ois.readObject();
      if (!serverMessage.equals(message)) {
        if (message.equals("give me your rsa please")) {
          System.out.println("Acquired key " + serverMessage + " for server " + this.host);
          publicKey = Hex.decode(serverMessage);
        } else {
          System.out.println(serverMessage);
          System.out.println(message);
        }
      } else {

      }
      Thread.sleep(50); // why this?
      this.socket.close();
    } catch (IOException | ClassNotFoundException | InterruptedException e) {
      String errMsg = "Client: Send message error" + e.getClass();
      System.err.println(errMsg);
      e.printStackTrace();
    }
  }

}
