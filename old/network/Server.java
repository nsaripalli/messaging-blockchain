package network;

import common.IObserver;
import common.IRelay;
import common.ISender;
import common.ISubject;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.ClassNotFoundException;
import java.net.ServerSocket;
import java.net.Socket;


/*

example is

    network.Server server = new network.Server(8000);
    server.start();

    Thread.sleep(30000);

    server.stop();
 */


public class Server implements Runnable, ISender<String> {
  private ServerSocket server;
  private NetworkController networkController;

  public Server(int port, NetworkController networkController) {
    this.networkController = networkController;

    try {
      server = new ServerSocket(port);
    } catch (IOException e) {
      System.err.println("Server: Error while initializing Server Socket with port " + port);
      e.printStackTrace();
    }
  }

  public void run() {
    //create the socket server object
    //keep listens indefinitely until receives 'exit' call or program terminates
    try {
      while (true) { // Runs once per request

        System.out.println("Server: Waiting for client request");
        Socket socket = this.server.accept(); // waits for client request
        // FIXME: this doesn't work, accept() immediately fails and DOES NOT wait!!

        // Get message
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        String message = (String) ois.readObject();
        System.out.println("Server Message Received: " + message + " from " + socket.getInetAddress());

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
    }

  }
}