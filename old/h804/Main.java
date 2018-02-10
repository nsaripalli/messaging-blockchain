package h804;

import common.IRelay;
import network.Client;
import network.NetworkController;
import network.Server;
//import ui.Gui;

import java.util.ArrayList;
import java.util.List;

public class Main implements IRelay {

	public static final int RSA_KEY_LENGTH = 2048;
	public static final int PORT = 13337;


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
		Blockchain blockchain = new Blockchain();

		//Gui gui = new Gui();
		new NetworkController(PORT)

		//Thread guiThread = new Thread(() -> gui.start(new NetworkController(PORT, gui)), "gui thread");
		//guiThread.start();
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
