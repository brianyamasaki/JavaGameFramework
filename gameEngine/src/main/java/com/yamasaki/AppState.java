package com.yamasaki;

import java.awt.Graphics2D;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import com.yamasaki.game_sprites.Sprite;

public class AppState {
  private static int appWidth;
  private static int appHeight;
  private static int iScene;
  private static AppContent appContent;
  private static AffineTransform initialTransform;
  private static ArrayList<ArrayList<Sprite>> spriteLists;

  public AppState(ActionListener listener) {
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
  /**
   * Store initial transform to detect screen scaling by some windowing environments
   * Windows will double the screen size by pixels on high res devices and this is the way we know
   * @param g2
   */
  public static void setInitialTransform(Graphics2D g2) {
    AppState.initialTransform = g2.getTransform();
  }
  /**
   * get the initial transform to detect screen scaling
   * @return
   */
  public static AffineTransform getInitialTransform() {
    return AppState.initialTransform;
  }

  public static void addToSpriteList(ArrayList<Sprite> spriteList) {
    AppState.spriteLists.add(spriteList);
  }

  public static ArrayList<Sprite> getSpriteList(int i) {
    return AppState.spriteLists.get(i);
  }

  public static void addDynamicSprite(Sprite sprite) {
    AppState.spriteLists.get(1).add(sprite);
  }

  public static void clearSpriteLists() {
    AppState.spriteLists = new ArrayList<ArrayList<Sprite>>();
  }
}