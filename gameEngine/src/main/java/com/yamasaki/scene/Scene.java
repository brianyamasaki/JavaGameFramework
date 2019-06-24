package com.yamasaki.scene;

import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.TreeSet;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Scene extends JPanel implements ActionListener {
  private static final long serialVersionUID = 1L;
  private Timer timer;
  private static int DELAY = 10;
  protected TreeSet<Integer> keyList;

  public Scene() {
    timer = new Timer(DELAY, this);
    timer.start();
    this.keyList = new TreeSet<Integer>();
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

  protected Boolean isKeyDown(int keyCode) {
    return this.keyList.contains(keyCode);
  }

  public void keyPressed(KeyEvent e) {
    this.keyList.add(e.getKeyCode());
    // System.out.println(e.getKeyCode() + " Key Pressed");
  }

  public void keyReleased(KeyEvent e) {
    this.keyList.remove(e.getKeyCode());
    // System.out.println(e.getKeyCode() + " Key Released");
  }
}