package com.yamasaki.scene;

import java.awt.Graphics;
import java.awt.Graphics2D;

import com.yamasaki.App;

public class GameOver extends Scene {
  private static final long serialVersionUID = 1L;

  public GameOver(App app) {
    super(app);
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D)g;

    g2.drawString("Game Over", 200, 200);
  }
}