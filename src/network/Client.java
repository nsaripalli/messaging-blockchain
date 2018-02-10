package network;

import h804.IObserver;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Client implements ISubject {

  InetAddress host;
  int port;
  Socket socket;
  IObserver obs;

  public Client(String host, int port, IObserver obs) throws IOException {
    this.host = InetAddress.getByName(host);
    this.port = port;
    this.obs = obs;
    try {
      this.socket = new Socket(this.host, port);
    } catch (IOException e) {
      this.socket = new Socket();
      System.out.println(e.getMessage());
    }
  }

  public void notifyObserver() {
    return;
  }

  void sendMessage(String message) throws IOException, ClassNotFoundException, InterruptedException {
    try {
      ObjectOutputStream oos = new ObjectOutputStream(this.socket.getOutputStream());
      oos.writeObject(message);
      ObjectInputStream ois = new ObjectInputStream(this.socket.getInputStream());
      String serverMessage = (String) ois.readObject();
      System.out.println("Reply: " + serverMessage);
      Thread.sleep(50);
    } catch (EOFException e) {
      System.out.println(e.getMessage());
    } catch (SocketException e) {
      System.out.println("socket error: " + e.getMessage());
    }
  }
}