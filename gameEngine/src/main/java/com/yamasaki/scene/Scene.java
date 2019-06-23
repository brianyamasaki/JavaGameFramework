package com.yamasaki.scene;

import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Scene extends JPanel implements ActionListener {
  private static final long serialVersionUID = 1L;
  private Timer timer;
  private static int DELAY = 10;

  public Scene() {
    timer = new Timer(DELAY, this);
    timer.start();
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    Toolkit.getDefaultToolkit().sync();
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    // physics and movement here
    repaint();
  }

  public void mouseClicked(MouseEvent e) {

  }

  public void mouseMoved(MouseEvent e) {
    
  }

  public void keyPressed(KeyEvent e) {
    
  }

  public void keyReleased(KeyEvent e) {

  }
}