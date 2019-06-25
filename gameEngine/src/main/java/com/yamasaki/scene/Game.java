package com.yamasaki.scene;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import com.yamasaki.AppState;
import com.yamasaki.objs.Ship;


public class Game extends Scene {
  private static final long serialVersionUID = 1L;
  private Ship ship;
  private final String backgroundFilename = "gameEngine/assets/gameBackground.jpg";
  private final int backgroundWidth = 1600;
  private final int backgroundHeight = 1200;
  private Image backgroundImage;

  public Game() {
    super();
    this.ship = new Ship(250, 250, 0.2);
    Toolkit toolkit = Toolkit.getDefaultToolkit();
    this.backgroundImage = toolkit.createImage(backgroundFilename);
  }
  
  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D)g; 
    g2.drawImage(this.backgroundImage, 0, 0, AppState.getAppWidth(), AppState.getAppHeight(), 0, 0, backgroundWidth, backgroundHeight, this);
    this.ship.draw(g2);
    Toolkit.getDefaultToolkit().sync();
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    for(int keyCode : this.keyList) {
      this.handleKeyDown(keyCode);
    }
    ship.update();
    repaint();
  }

  private void handleKeyDown(int keyCode) {
    switch(keyCode) {
      case 37: // left
      case 65: // A
        ship.turnLeft();
        break;
      case 39: // right
      case 68: // D
        ship.turnRight();
        break;
      case 38: // up
      case 87: // W
        ship.thrust();
        break;
      case 40: // down
      case 83: // S
        ship.brake();
        break;
      default:
        break;
    }
  }

  @Override
  public void keyPressed(KeyEvent e) {
    super.keyPressed(e);
    if (e.getKeyCode() == 32) {
      ship.fire();
    }
  }

}