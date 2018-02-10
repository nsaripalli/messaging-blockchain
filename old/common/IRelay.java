package common;

public interface IRelay<T> {
  void receiveAbove(T t);
  void receiveBelow(T t);
  void sendAbove(T t);
  void sendBelow(T t);
}
