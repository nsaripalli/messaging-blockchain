package h804;

import network.Client;
import network.Server;
import ui.Gui;

import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class Main {

	private static final int RSA_KEY_LENGTH = 2048;
	private static final int PORT = 13337;
	private byte[] pubKey;
	private byte[] privKey;

	public List<Client> clients = new ArrayList<>();

	/**
	 * Entry point to our program.
	 * @param args Ignored.
	 */
	public static void main(String[] args)  {
		new Main();
	}

	/**
	 * Main initialization of our program. Generates our RSA keys and starts our multi-threaded
	 * application.
	 */
	Main() {
		generateKeys();
		Blockchain blockchain = new Blockchain();

		Gui gui = new Gui();

		Server server = new Server(PORT, gui);
		server.start();

		Thread guiThread = new Thread(() -> gui.start(this));
		guiThread.start();

		// this initialization should really be called elsewhere.
		Client client = new Client("localhost", PORT, gui);
	}

	/**
	 * Generates the RSA passphrase that will be used throughout the protocol.
	 */
	private void generateKeys() {
		try {
			KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");

			keyGen.initialize(RSA_KEY_LENGTH);

			this.pubKey = keyGen.generateKeyPair().getPublic().getEncoded();
			this.privKey = keyGen.generateKeyPair().getPublic().getEncoded();

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			System.err.println("Unable to create public and private key!");
			System.exit(-1);
		}

	}

}
