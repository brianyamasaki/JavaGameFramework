package com.yamasaki.scene;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import com.yamasaki.AppState;
import com.yamasaki.game_sprites.BackgroundStar;
import com.yamasaki.game_sprites.BackgroundStarImage;
import com.yamasaki.game_sprites.Ship2;
import com.yamasaki.game_sprites.ShipImage;


public class Game extends Scene {
  private static final long serialVersionUID = 1L;
  private Ship2 ship2;
  private final String backgroundFilename = "gameEngine/assets/gameBackground.jpg";
  private final int backgroundWidth = 1600;
  private final int backgroundHeight = 1200;
  private Image backgroundImage;
  private ShipImage shipImage;
  private BackgroundStarImage backgroundStarImage;
  private BackgroundStar backgroundStar;

  @Override
  public void loadAssets() {
    this.shipImage = new ShipImage("gameEngine/assets/Ship-Animation.png");
    this.backgroundStarImage = new BackgroundStarImage("gameEngine/assets/star-animation.png");
    Toolkit toolkit = Toolkit.getDefaultToolkit();
    this.backgroundImage = toolkit.createImage(backgroundFilename);
  }

  @Override
  public void initialize() {
    super.initialize();
    this.ship2 = new Ship2(this.shipImage, 100, 70, 0.3);
    this.backgroundStar = new BackgroundStar(this.backgroundStarImage, 400, 400, 0);
  }
  
  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D)g; 
    g2.drawImage(this.backgroundImage, 0, 0, AppState.getAppWidth(), AppState.getAppHeight(), 0, 0, backgroundWidth, backgroundHeight, this);
    this.backgroundStar.draw(g2, this);
    this.ship2.draw(g2, this);
    Toolkit.getDefaultToolkit().sync();
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    for(int keyCode : this.keyList) {
      this.handleKeyDown(keyCode);
    }
    ship2.update();
    repaint();
  }

  private void handleKeyDown(int keyCode) {
    switch(keyCode) {
      case 37: // left
      case 65: // A
        ship2.turnLeft();
        break;
      case 39: // right
      case 68: // D
        ship2.turnRight();
        break;
      case 38: // up
      case 87: // W
        ship2.thrust();
        break;
      case 40: // down
      case 83: // S
        ship2.brake();
        break;
      default:
        break;
    }
  }

  @Override
  public void keyPressed(KeyEvent e) {
    super.keyPressed(e);
    if (e.getKeyCode() == 32) {
      ship2.fire();
    }
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    // super.mouseClicked(e);
    AppState.setSceneIndex(AppState.getSceneIndex()+1);
  }

}