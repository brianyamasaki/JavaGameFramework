package com.yamasaki.scene;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;

import com.yamasaki.AppState;

public class Intro extends Scene {
  private static final long serialVersionUID = 1L;
  private Image backgroundImage;
  private final String filename = "gameEngine/assets/intro-screen.jpg";
  private final int fileWidth = 1600;
  private final int fileHeight = 1200;
  private boolean isTransformRecorded = false;

  @Override
  public void loadAssets() {
    Toolkit toolkit = Toolkit.getDefaultToolkit();
    this.backgroundImage = toolkit.createImage(filename);
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    int width = AppState.getAppWidth();
    int height = AppState.getAppHeight();
    Graphics2D g2 = (Graphics2D)g;
    if (!isTransformRecorded) {
      AppState.setInitialTransform(g2);
      isTransformRecorded = true;
    }
    g2.drawImage(this.backgroundImage, 0, 0, width, height, 0, 0, fileWidth, fileHeight, this);
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    super.mouseClicked(e);
    AppState.setSceneIndex(AppState.getSceneIndex()+1);    
  }
}