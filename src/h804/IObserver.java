package h804;

public interface IObserver {
  void updateClient(String message);
  void updateServer(String message);
}