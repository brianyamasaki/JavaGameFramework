package com.yamasaki.scene;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import com.yamasaki.AppState;
import com.yamasaki.game_sprites.BackgroundStar;
import com.yamasaki.game_sprites.BackgroundStarImage;
import com.yamasaki.game_sprites.Ship2;
import com.yamasaki.game_sprites.ShipImage;
import com.yamasaki.game_sprites.Sprite;
import com.yamasaki.game_sprites.VerticalWall;
import com.yamasaki.game_sprites.VerticalWallImage;

public class Game extends Scene {
  private static final long serialVersionUID = 1L;
  private Ship2 playerObject;
  private final String backgroundFilename = "gameEngine/assets/gameBackground.jpg";
  private final int backgroundWidth = 1600;
  private final int backgroundHeight = 1200;
  private Image backgroundImage;
  private ShipImage shipImage;
  private BackgroundStarImage backgroundStarImage;
  private VerticalWallImage verticalWallImage;
  private ArrayList<Sprite> staticSprites;
  private ArrayList<Sprite> dynamicSprites;
  private boolean thrustSound = true;

  @Override
  public void loadAssets() {
    this.shipImage = new ShipImage("gameEngine/assets/Ship-Animation.png");
    this.backgroundStarImage = new BackgroundStarImage("gameEngine/assets/star-animation.png");
    this.verticalWallImage = new VerticalWallImage("gameEngine/assets/vertical-wall.png");
    Toolkit toolkit = Toolkit.getDefaultToolkit();
    this.backgroundImage = toolkit.createImage(backgroundFilename);
  }

  @Override
  public void initialize() {
    super.initialize();
    this.playerObject = new Ship2(this.shipImage, 100, 70, 0.3);
    staticSprites = new ArrayList<Sprite>();
    staticSprites.add(new VerticalWall(this.verticalWallImage, 10, 80, 0));
    staticSprites.add(new BackgroundStar(this.backgroundStarImage, 400, 400, 0));
    AppState.addToSpriteList(this.staticSprites);
    dynamicSprites = new ArrayList<Sprite>();
    dynamicSprites.add(this.playerObject);
    AppState.addToSpriteList(this.dynamicSprites);
  }
  
  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D)g; 
    g2.drawImage(this.backgroundImage, 0, 0, AppState.getAppWidth(), AppState.getAppHeight(), 0, 0, backgroundWidth, backgroundHeight, this);
    for (Sprite sprite : this.staticSprites) {
      sprite.draw(g2, this);
    }
    for (Sprite sprite : this.dynamicSprites) {
      sprite.draw(g2, this);
    }
    Toolkit.getDefaultToolkit().sync();
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    for(int keyCode : this.keyList) {
      this.handleKeyDown(keyCode);
    }

    // update positions
    for(Sprite sprite : this.dynamicSprites) {
      sprite.update();
    }

    // check collisions
    for(Sprite sprite : this.dynamicSprites) {
      sprite.collisionDetect();
    }

    // remove if marked for removal
    this.dynamicSprites.removeIf((sprite) -> sprite.toRemove());

    // redraw the screen
    repaint();
  }

  private void handleKeyDown(int keyCode) {
    switch(keyCode) {
      case 37: // left
      case 65: // A
        this.playerObject.turnLeft();
        break;
      case 39: // right
      case 68: // D
        this.playerObject.turnRight();
        break;
      case 38: // up
      case 87: // W
        this.playerObject.thrust();
        break;
      case 40: // down
      case 83: // S
        // this.playerObject.brake();
        break;
      default:
        break;
    }
  }

  @Override
  public void keyPressed(KeyEvent e) {
    super.keyPressed(e);
    if (e.getKeyCode() == 32) {
      this.playerObject.fire();
    }
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    // super.mouseClicked(e);
  }

}