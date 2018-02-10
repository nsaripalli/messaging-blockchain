package h804;

import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

public class Main {

	private static final int RSA_KEY_LENGTH = 2048;

	public static void main(String[] args)  {
		System.out.println("Hello world!");

		KeyPairGenerator keyGen = null;
		try {
			keyGen = KeyPairGenerator.getInstance("RSA");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			System.err.println("Something fucked up greatly.");
			System.exit(-1);
		}

		keyGen.initialize(RSA_KEY_LENGTH);

		byte[] pubKey = keyGen.generateKeyPair().getPublic().getEncoded();
		byte[] privKey = keyGen.generateKeyPair().getPublic().getEncoded();

		Blockchain blockchain = new Blockchain();

		Message testMsg = new Message("HElo world");
//		System.out.println(testMsg.getHash());
	}

}
