package ui;

import common.IRelay;

// The GuiAdapter is primarily responsible for formatting and outputting text to the Gui.
public class GuiAdapter implements IRelay {
  Gui gui;

  GuiAdapter(Gui gui, ) {
    this.gui = gui;
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
