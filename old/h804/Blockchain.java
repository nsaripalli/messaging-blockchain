package h804;

import common.IRelay;
import org.bouncycastle.util.encoders.Hex;
import java.util.ArrayList;
import java.util.List;

public class Blockchain implements IRelay<String> {

  // TODO: DO we need this?
  private List<Message> messages = new ArrayList<>();
  private byte[] currentHash = new byte[0];

  Blockchain() {}

  Blockchain(byte[] initialHash) {
    this.currentHash = initialHash;
  }

  /**
   * Sets the current hash.
   *
   * @param hash the new hash to set
   */
  void setCurrentHash(byte[] hash) {
    this.currentHash = hash;
  }

  /**
   * Generates a String meant for sending to the other party(ies)s.
   *
   * @param message The current String to be encrypted
   * @return The TwoFish encrypted string.
   */
  String generateMessage(String message) {
    return new Message(message).getHash(currentHash);
  }

  /**
   * SHOULD ONLY BE CALLED ON SUCCESSFUL MESSAGE RECEIVED.
   *
   * "Adds" a new message to the current blockchain. Since we don't really need to keep the history
   * (History should really be only temporarily cached in the UI), we only need to keep the current
   * the latest hash. Since this function is only called on receiving a success message from the
   * other party, we can simply set the received message's hash to be the current hash.
   *
   * @param message The successful message sent.
   */
  void addToHistory(Message message) {
    currentHash = Hex.decode(message.getHash(currentHash));
  }

  @Override
  public void receiveAbove(String s) {

  }

  @Override
  public void receiveBelow(String s) {

  }

  @Override
  public void sendAbove(String s) {

  }

  @Override
  public void sendBelow(String s) {

  }
}
