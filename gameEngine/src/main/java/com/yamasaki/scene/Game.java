package com.yamasaki.scene;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import com.yamasaki.objs.Ship;


public class Game extends Scene {
  private static final long serialVersionUID = 1L;
  private Ship ship;

  public Game() {
    super();
    this.ship = new Ship(250, 250, 0.2);
  }
  
  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    this.ship.draw((Graphics2D)g);
    Toolkit.getDefaultToolkit().sync();
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    ship.update();
    repaint();
  }

  @Override
  public void keyPressed(KeyEvent e) {
    super.keyPressed(e);
    int keyCode = e.getKeyCode();
    if (keyCode == 37) { // left
      ship.turnLeft();
    } else if (keyCode == 39) { // right
      ship.turnRight();
    } else if (keyCode == 38) { // up
      ship.thrust();
    } else if (keyCode == 40) { // down
      ship.brake();
    }
    // System.out.println("key pressed " + e.getKeyCode());
}


}