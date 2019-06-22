package com.yamasaki.scene;

import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import com.yamasaki.App;

public class Scene extends JPanel implements ActionListener {
  private static final long serialVersionUID = 1L;
  private App app;

  public Scene(App app) {
    this.app = app;
    this.addListeners();
  }

  protected void addListeners() {
    MAdapter mouseAdapter;
    addKeyListener(new TAdapter());
    mouseAdapter = new MAdapter();
    addMouseListener(mouseAdapter);
    addMouseMotionListener(mouseAdapter);
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

  private class TAdapter extends KeyAdapter {

    @Override
    public void keyReleased(KeyEvent e) {
        // spaceShip.keyReleased(e);
        // System.out.println("key released " + KeyEvent.getKeyText(e.getKeyCode()));
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("key pressed " + KeyEvent.getKeyText(e.getKeyCode()));
    }
  }

  private class MAdapter extends MouseAdapter {

    @Override
    public void mouseClicked(MouseEvent e) {
      super.mouseClicked(e);
    }

  }

}