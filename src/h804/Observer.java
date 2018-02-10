package h804;

public interface Observer {
  void updateClient(String message);
  void updateServer(String message);
}