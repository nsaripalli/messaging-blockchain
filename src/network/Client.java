package network;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
  Socket socket;
  InetAddress ip;

  public Client(Socket socket, String ip) {
    this.socket = socket;
    this.ip = InetAddress.getByName();
  }
}
