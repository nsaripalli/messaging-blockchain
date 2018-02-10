package network;

import common.IObserver;
import common.ISubject;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Client implements ISubject {

  private InetAddress host;
  private Socket socket;
  private IObserver obs;

  public Client(String host, int port, IObserver obs) {
    this.obs = obs;

    try {
      this.host = InetAddress.getByName(host);
    } catch (UnknownHostException e) {
      e.printStackTrace();
    }

    try {
      this.socket = new Socket(this.host, port);
      this.sendMessage("request public key");
    } catch (IOException e) {
      this.socket = new Socket();
      e.printStackTrace();
    }
  }

  private void sendMessage(String message) {
    try {
      ObjectOutputStream oos = new ObjectOutputStream(this.socket.getOutputStream());
      oos.writeObject(message);
      ObjectInputStream ois = new ObjectInputStream(this.socket.getInputStream());
      String serverMessage = (String) ois.readObject();
      System.out.println("Reply: " + serverMessage);
      Thread.sleep(50);
    } catch (IOException|ClassNotFoundException|InterruptedException e) {
      String errMsg = "Client: Send message error" + e.getClass();
      System.err.println(errMsg);
      notifyObserver(errMsg);
      e.printStackTrace();
    }
  }

  public void notifyObserver(String str) {
    obs.clientMessage(str);
  }
}