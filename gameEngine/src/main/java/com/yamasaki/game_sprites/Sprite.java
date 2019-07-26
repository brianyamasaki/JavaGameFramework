package com.yamasaki.game_sprites;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Polygon;
import java.awt.geom.AffineTransform;
import java.awt.geom.PathIterator;

import javax.swing.JPanel;

public class Sprite {
  protected int x;
  protected int y;
  protected double dx;
  protected double dy;
  protected double theta;
  protected AffineTransform transform;
  protected SpriteImage spriteImage;
  protected int frameDelay = 0;
  protected long timeCreated;
  protected boolean isAnimation = false;
  protected boolean hasCollisions = false;
  protected Polygon polyCollsions;

/**
 * Constructor to use when custom drawing sprite (not an image)
 * @param x
 * @param y
 * @param theta
 */
  public Sprite(int x, int y, double theta) {
    this.x = x;
    this.y = y;
    this.dx = 0;
    this.dy = 0;
    this.theta = theta;
    this.transform = this.createTransform(x, y, theta);
  }
/**
 * Constructor to use when using a PNG or JPG file
 * @param spriteImage
 * @param x
 * @param y
 * @param theta
 */
  public Sprite(SpriteImage spriteImage, int x, int y, double theta) {
    this.spriteImage = spriteImage;
    this.x = x;
    this.y = y;
    this.dx = 0;
    this.dy = 0;
    this.theta = theta;
    this.frameDelay = this.spriteImage.getFrameDelay();
    this.transform = this.createTransform(x, y, theta);
    this.timeCreated = System.currentTimeMillis();
    this.isAnimation = this.spriteImage.xImageFrames * this.spriteImage.yImageFrames > 1;
    this.hasCollisions = spriteImage.hasCollisions;
    if (this.hasCollisions) {
      this.polyCollsions = spriteImage.polyCollisions;
    }
  }

  protected AffineTransform createTransform(int x, int y, double theta) {
    AffineTransform transform = new AffineTransform();
    transform.translate(x, y);
    transform.rotate(theta, 0, 0);
    return transform;
  }

  protected Polygon transformCollisionPolygon() {
    Polygon polyTransformed = new Polygon();
    if (!this.hasCollisions)
      return polyTransformed;
    PathIterator pathIterator = this.polyCollsions.getPathIterator(this.transform);
    double[] coords = new double[6];
    while (!pathIterator.isDone()) {
      int type = pathIterator.currentSegment(coords);
      System.out.println(type);
      polyTransformed.addPoint((int)Math.round(coords[0]), (int)Math.round(coords[1]));
    }
    return polyTransformed;
  }

  /** returns an integer of which frame in the animation to display - starting from 0
  * Get modulus of total length of animation, then find the integer dividend to get the frame to display
  */
  protected int chooseFrame() {
    // casting from long to double is safe because the time difference from timeCreated to current time
    // is less than 32 bits in milliseconds
    int timeSinceCreated = (int)(System.currentTimeMillis() - this.timeCreated);
    
    return timeSinceCreated % this.spriteImage.getTotalAnimation() / this.spriteImage.getFrameDelay();
  }

  /** 
   * draw function for images - we need the actionListener for images
   */
  public void draw(Graphics2D g2, JPanel panel) {
    int width = this.spriteImage.getImageWidth();
    int height = this.spriteImage.getImageHeight();
    int halfWidth =  width / 2;
    int halfHeight = height / 2;
  
    AffineTransform transformSave = g2.getTransform();
    g2.setTransform(this.transform);
    if (this.isAnimation) {
      Rectangle rect = this.spriteImage.getFrame(this.chooseFrame(), 0);

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
    } else {
      g2.drawImage(this.spriteImage.getImage(), -halfWidth, -halfHeight, panel);
    }
    g2.setTransform(transformSave);

  }

  /* draw function for anything other than images
  */
  public void draw(Graphics2D g2) {
    // draw it
  }

  public void update() {
    // physics and movement
  }
}