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
    ServerSocket serverSocket;
    try {
      serverSocket = new ServerSocket(port);
    } catch (IOException e) {
      e.printStackTrace();
      return;
    }

    try {
      while (true) { // Runs once per request

        System.out.println("Server: Waiting for client request");
        Socket socket = serverSocket.accept(); // waits for client request
        // FIXME: this doesn't work, accept() immediately fails and DOES NOT wait!!

        // Get message
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        String message = (String) ois.readObject();
        System.out.println("Server Message Received: " + message + " from " + socket.getInetAddress());

        // Pass the message to the controller
        controller.decodeMessage(message);

        if (message.equals("heldlo!")) {
          ois.close();
          socket.close();
          return;
        }

        System.out.println("FOWEIJHFOIWEJFOIWEJFOIWEJFOIWEJFOIWJEEFOIJWEOIFJWEOIFj");

        // Reply to client
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        oos.writeObject("Message Received. Your IP is" + socket.getInetAddress());

        // close everything
        ois.close();
        oos.close();
        socket.close();
      }

    } catch (IOException|ClassNotFoundException e) {
      e.printStackTrace();
      controller.sendUIMessage(e.getMessage());
    }
  }
}
