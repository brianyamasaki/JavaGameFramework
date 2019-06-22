package com.yamasaki.scene;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.yamasaki.App;
import com.yamasaki.AppState;

public class Intro extends Scene {
  private static final long serialVersionUID = 1L;
  private App app;
  private Font titleFont;

  public Intro(App app) {
    super(app);
    this.app = app;
    this.titleFont = new Font("Arial", Font.TRUETYPE_FONT, 30);
  }

  @Override
  protected void addListeners() {
    addMouseListener(new IntroMAdapter());
  }

  @Override
  protected void paintComponent(Graphics g) {
    int width = AppState.getAppWidth();
    int height = AppState.getAppHeight();
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D)g;
    g2.setColor(Color.gray);
    g2.fillRect(0, 0, width, height);
    Font oldFont = g2.getFont();
    g2.setFont(this.titleFont);
    g2.setColor(Color.BLACK);
    g2.drawString("Introduction screen", width / 2 - 150, 40);
    g2.setFont(oldFont);
    g2.drawString("Click anywhere to continue", width / 2 - 50, 100);
  }

  private class IntroMAdapter extends MouseAdapter {
    @Override
    public void mouseClicked(MouseEvent e) {
      super.mouseClicked(e);
      AppState.setSceneIndex(AppState.getSceneIndex()+1);
    }

  }

}