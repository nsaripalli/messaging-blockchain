package common;

public interface IObserver {
  void clientMessage(String message);
  void serverMessage(String message);
}