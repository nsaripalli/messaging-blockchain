package network;

import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
  Socket socket;
  InetAddress ip;

  public Client(Socket socket, String ip) throws UnknownHostException {
    this.socket = socket;
    this.ip = InetAddress.getByName(ip);
  }
}
