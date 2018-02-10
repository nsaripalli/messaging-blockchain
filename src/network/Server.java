package network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

  private int port;
  private NetworkController controller;

  Server(int port, NetworkController controller) {
    this.port = port;
    this.controller = controller;
  }

  public void run() {
    try {
      ServerSocket serverSocket = new ServerSocket(port);
      while (true) {
        System.out.println("Server: Waiting for client request");
        Socket socket = serverSocket.accept(); // waits for client request

        // Get message
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        String message = (String) ois.readObject();

        // Pass the message to the controller
        controller.decodeMessage(message, socket.getInetAddress());

        // Reply to client
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        oos.writeObject("received");

        // close everything
        ois.close();
        oos.close();
        socket.close();
      }
    } catch (IOException | ClassNotFoundException e) {
      e.printStackTrace();
      controller.sendUIMessage(e.getMessage());
    }


  }
}
