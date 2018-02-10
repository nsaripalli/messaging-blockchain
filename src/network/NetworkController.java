package network;

import h804.Blockchain;
import org.bouncycastle.util.encoders.Hex;
//import ui.Gui;

import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

public class NetworkController {

  private int port;
  private int keylength;
  private byte[] pubKey;
  private byte[] privKey;
  private Server server;
  private Blockchain blockchain;
  private List<Client> clients = new ArrayList<>();
  //private Gui gui;

  public NetworkController(int port, int keylength, Blockchain blockchain) {
    this.port = port;
    this.keylength = keylength;
    this.server = new Server(port, this);
    this.blockchain = blockchain;
    //this.gui = gui;
    generateKeys();
    Thread curServerThread = new Thread(server::run, "Server thread");
    curServerThread.start();
    // server.close() to exit thread
  }

  public void createNewClient(String hostname) {
    try {
      Client client = new Client(hostname, port, this);
      client.sendMessage("give me your rsa please");
      clients.add(client);
    } catch (IOException e) {
      e.printStackTrace();
      System.err.println("Couldn't resolve name: " + hostname);
    }
  }

  public String getPublicKey() {
    return Hex.toHexString(pubKey);
  }

  public void removeClient(Client c) {
    clients.remove(c);
  }

  public void sendUIMessage(String s) {
    System.out.println(s);
    /*if (this.gui == null) {
      System.err.println("Gui has not been initialized in Network Controller!");
      return;
    }*/

    //this.gui.appendToChat(s);
  }

  public void decodeMessage(String m, InetAddress ipaddr) {
    //gui.appendToChat(ipaddr + "> " + blockchain.decode(m));
  }

  public void sendMessage(String text) {
//    String encrypted = blockchain.encode(text);
    for (Client c : clients) {
      c.sendMessage(text);
//      c.sendMessage(encrypted);
    }
  }
  /**
   * Generates the RSA passphrase that will be used throughout the protocol.
   */
  private void generateKeys() {
    try {
      KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");

      keyGen.initialize(this.keylength);

      this.pubKey = keyGen.generateKeyPair().getPublic().getEncoded();
      this.privKey = keyGen.generateKeyPair().getPublic().getEncoded();

    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
      System.err.println("Unable to create public and private key!");
      System.exit(-1);
    }

  }

}
