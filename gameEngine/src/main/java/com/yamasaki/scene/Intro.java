package com.yamasaki.scene;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.yamasaki.App;

public class Intro extends Scene {
  private static final long serialVersionUID = 1L;

  public Intro(App app) {
    super(app);
    
  }

  @Override
  protected void addListeners() {
    addMouseListener(new IntroMAdapter());
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D)g;

    g2.drawString("Introduction", 200, 200);

  }

  private class IntroMAdapter extends MouseAdapter {
    @Override
    public void mouseClicked(MouseEvent e) {
      super.mouseClicked(e);
      System.out.println("Mouse Clicked");
    }

  }

}