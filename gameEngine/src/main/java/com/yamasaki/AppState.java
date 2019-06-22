package com.yamasaki;

import java.awt.event.ActionListener;

public class AppState {
  private ActionListener listener;
  private static int appWidth;
  private static int appHeight;
  private static int iScene;
  private static AppContent appContent;

  public AppState(ActionListener listener) {
    this.listener = listener;
    AppState.iScene = 0;
  }

  public static void setAppSize(int width, int height) {
    AppState.appWidth = width;
    AppState.appHeight = height;
  }

  public static int getAppWidth() {
    return AppState.appWidth;
  }

  public static int getAppHeight() {
    return AppState.appHeight;
  }

  public static void setAppContent(AppContent appContent) {
    AppState.appContent = appContent;
  }

  public static AppContent getAppContent() {
    return AppState.appContent;
  }

  public static void setSceneIndex(int i) {
    AppState.iScene = i;
    appContent.setScene(i);
  }

  public static int getSceneIndex() {
    return AppState.iScene;
  }
}