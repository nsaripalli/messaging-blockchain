package h804;

import common.IRelay;
import network.Client;
import network.NetworkController;
import network.Server;
import ui.Gui;

import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class Main implements IRelay {

	public static final int RSA_KEY_LENGTH = 2048;
	public static final int PORT = 13337;
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
	private Main() {
		generateKeys();
		Blockchain blockchain = new Blockchain();

		Gui gui = new Gui();

		Thread guiThread = new Thread(() -> gui.start(new NetworkController(PORT, gui)), "gui thread");
		guiThread.start();
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

	@Override
	public void receiveAbove(Object o) {

	}

	@Override
	public void receiveBelow(Object o) {

	}

	@Override
	public void sendAbove(Object o) {

	}

	@Override
	public void sendBelow(Object o) {

	}
}
