package network;

import common.IObserver;
import common.ISubject;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.ClassNotFoundException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.function.Function;


/*

example is

    network.Server server = new network.Server(8000);
    server.start();

    Thread.sleep(30000);

    server.stop();
 */


public class Server implements Runnable, ISubject {
  private ServerSocket server;
  private Thread t;
  private String threadName = "server";
  private IObserver obs;

  public Server(int port, IObserver obs) {
    this.obs = obs;

    try {
      server = new ServerSocket(port);
    } catch (IOException e) {
      System.err.println("Server: Error while initializing Server Socket with port " + port);
      e.printStackTrace();
    }
  }

  public void start() {
    System.out.println("Server: Starting server!");
    if (t == null) {
      t = new Thread(this, threadName);
      t.start();
    }
  }

  public void stop() {
    System.out.println("Server: Shutting down Socket server!");
    //close the ServerSocket object
    try {
      this.server.close();
    } catch (IOException e) {
      System.out.println(e.getMessage());
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

        // Inform the UI about the message
        notifyObserver(message);

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

  @Override
  public void notifyObserver(String str) {
    obs.serverMessage(str);
  }
}