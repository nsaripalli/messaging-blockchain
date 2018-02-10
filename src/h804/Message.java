package h804;

import org.bouncycastle.crypto.BufferedBlockCipher;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.engines.TwofishEngine;
import org.bouncycastle.crypto.modes.CBCBlockCipher;
import org.bouncycastle.crypto.paddings.PaddedBufferedBlockCipher;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.util.encoders.Hex;

public class Message {
  private String message;

  Message(String message) {
    this.message = message;
  }

  /**
   * Initialize a message with a sender and message. This is primarily used with group communication
   *
   * @param message
   */
  Message(String message, String sender) {
    this.message = message;
  }

  String getMessage() {
    return message;
  }

  /**
   * Returns the hash of the current
   *
   * @return
   */
  String getHash(byte[] prevHash) {
    TwofishEngine tfEngine = new TwofishEngine();
    BufferedBlockCipher cipher = new PaddedBufferedBlockCipher(new CBCBlockCipher(tfEngine));
    tfEngine.init(true, new KeyParameter(prevHash));

    byte[] cipherText = new byte[cipher.getOutputSize(message.length())];

    int outputLen = cipher.processBytes(message.getBytes(), 0, message.length(), cipherText, 0);

    try {
      cipher.doFinal(cipherText, outputLen);
    } catch (InvalidCipherTextException e) {
      e.printStackTrace();
      System.err.println("Ciphertext failed!");
    }

    return Hex.toHexString(cipherText);
  }
}
