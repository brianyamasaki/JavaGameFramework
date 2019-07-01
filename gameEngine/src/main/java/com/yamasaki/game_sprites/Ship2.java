package com.yamasaki.game_sprites;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;

import javax.swing.JPanel;

public class Ship2 extends Sprite {
  private long timeCreated;

  public Ship2(SpriteImage spriteImage, int x, int y, double theta) {
    super(spriteImage, x, y, theta);
    this.timeCreated = System.currentTimeMillis();
  }
  
  /* returns an integer of which frame in the animation to display - starting from 0
  * Get modulus of total length of animation, then find the integer dividend to get the frame to display
  */
  private int chooseFrame() {
    int timeSinceCreated = (int)(System.currentTimeMillis() - this.timeCreated);
    
    return timeSinceCreated % this.spriteImage.getTotalAnimation() / this.spriteImage.getFrameDelay();
  }

  @Override
  public void draw(Graphics2D g2, JPanel panel) {
    super.draw(g2, panel);
    int width = this.spriteImage.getImageWidth();
    int height = this.spriteImage.getImageHeight();
    int halfWidth =  width / 2;
    int halfHeight = height / 2;
    
    Rectangle rect = this.spriteImage.getFrame(this.chooseFrame(), 0);
    AffineTransform transformSave = g2.getTransform();
    g2.setTransform(this.transform);
    g2.drawImage(
      this.spriteImage.getImage(), 
      -halfWidth, 
      -halfHeight, 
      width - halfWidth, 
      height - halfHeight, 
      rect.x, 
      rect.y, 
      rect.x + width, 
      rect.y + height, 
      panel);
    g2.setTransform(transformSave);
  }
}