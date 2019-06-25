package com.yamasaki.scene;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import com.yamasaki.AppState;

public class GameOver extends Scene {
  private static final long serialVersionUID = 1L;
  private Font titleFont;

  public GameOver() {
    super();
    this.titleFont = new Font("Arial", Font.TRUETYPE_FONT, 30);
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D)g;
    g2.setColor(Color.black);
    g2.fillRect(0, 0, AppState.getAppWidth(), AppState.getAppHeight());
    g2.setColor(Color.white);
    g2.setFont(this.titleFont);
    g2.drawString("Game Over", AppState.getAppWidth() / 2 - 100, AppState.getAppHeight() / 2);
  }
}