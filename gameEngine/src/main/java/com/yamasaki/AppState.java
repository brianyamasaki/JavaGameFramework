package com.yamasaki;

import java.awt.event.ActionListener;

public class AppState {
  private ActionListener listener;
  private static AppState self;

  public AppState(ActionListener listener) {
    this.listener = listener;
    AppState.self = this;
  }

}