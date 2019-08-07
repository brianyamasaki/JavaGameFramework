package com.yamasaki.game_sprites;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Polygon;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import javax.swing.JPanel;

import com.yamasaki.AppState;

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
  protected ArrayList<Point> pointsCollisions;
  protected int[] collidesWith;
  protected ArrayList<Point> pointsCollisionsInGame;
  protected Rectangle rectBoundsInGame;
  protected long timeToDie;
  protected boolean toRemove = false;

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
    if (spriteImage.lifetimeInMilliseconds > 0) {
      this.timeToDie = this.timeCreated + spriteImage.lifetimeInMilliseconds;
    } else {
      this.timeToDie = 0;
    }
    this.isAnimation = this.spriteImage.xImageFrames * this.spriteImage.yImageFrames > 1;
    this.hasCollisions = spriteImage.hasCollisions;
    if (this.hasCollisions) {
      this.pointsCollisions = this.convertCollisionPoints(spriteImage);
      this.collidesWith = spriteImage.collidesWith;
    }
  }

  protected AffineTransform createTransform(int x, int y, double theta) {
    AffineTransform transform = new AffineTransform(AppState.getInitialTransform());
    transform.translate(x, y);
    transform.rotate(theta, 0, 0);
    return transform;
  }

  protected ArrayList<Point> convertCollisionPoints(SpriteImage spriteImage) {
    ArrayList<Point> pointsOut = new ArrayList<Point>();
    int halfHeight = spriteImage.height / 2;
    int halfWidth = spriteImage.width / 2;
    for (Point point : spriteImage.pointsCollisions) {
      pointsOut.add(new Point(point.x - halfWidth, point.y - halfHeight));
    }
    return pointsOut;
  }

  protected Point transformPoint(int x, int y) {
    double[] src = new double[2];
    double[] dst = new double[2];
    src[0] = x;
    src[1] = y;
    this.transform.transform(src, 0, dst, 0, 1);
    return new Point((int) dst[0], (int) dst[1]);
  }

  protected ArrayList<Point> transformPoints(ArrayList<Point> points) {
    ArrayList<Point> newPoints = new ArrayList<Point>();
    int arraySize = points.size() * 2;
    double[] src = new double[arraySize];
    double[] dst = new double[arraySize];
    int i = 0;
    for (Point pt : points) {
      src[i++] = pt.x;
      src[i++] = pt.y;
    }
    this.transform.transform(src, 0, dst, 0, points.size());
    for (i = 0; i < arraySize; ) {
      newPoints.add(new Point((int)Math.round(dst[i++]), (int)Math.round(dst[i++])));
    }
    return newPoints;
  }

  protected Rectangle boundingRectangle(ArrayList<Point> points) {
    int xMin, xMax, yMin, yMax;
    Point pt = points.get(0);
    xMin = xMax = pt.x;
    yMin = yMax = pt.y;
    for(int i = 1; i < points.size(); i++) {
      pt = points.get(i);
      if (pt.x < xMin) {
        xMin = pt.x;
      } else if (pt.x > xMax) {
        xMax = pt.x;
      } 
      if (pt.y < yMin) {
        yMin = pt.y;
      } else if (pt.y > yMax) {
        yMax = pt.y;
      }
    }
    return new Rectangle(xMin, yMin, xMax - xMin, yMax - yMin);
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
    if (this.spriteImage != null) {
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
    } else {
      this.draw(g2);
    }

  }

  /* draw function for anything other than images
  */
  public void draw(Graphics2D g2) {
    // draw it
  }

  /**
   * updates the position and attitude
   * Not used if unmoving
   */
  public void update(long timeNow) {

    if (this.timeToDie > 0 && timeNow > this.timeToDie) {
      this.toRemove = true;
    }
    if (this.pointsCollisions != null) {
      this.pointsCollisionsInGame = this.transformPoints(this.pointsCollisions);
      this.rectBoundsInGame = this.boundingRectangle(this.pointsCollisionsInGame);  
    }
  }

  /**
   * Called during the Collision Detection phase 
   */
  public void collisionDetect(long timeNow) {
    
  }
  /**
   * called to see if this sprite has collided with otherSprite
   * naive implementation for collision detection
   * @return boolean
   */
  protected boolean hasCollidedWith(Sprite otherSprite) {
    // first check if bounding rectangles intersect, then check if polygon points are inside given otherSprite
    if (otherSprite.hasCollisions && this.rectBoundsInGame.intersects(otherSprite.rectBoundsInGame)) {
      Polygon poly = new Polygon();
      for (Point pt : otherSprite.pointsCollisionsInGame) {
        poly.addPoint(pt.x, pt.y);
      }
      for (Point pt : this.pointsCollisionsInGame) {
        if (poly.contains(pt)) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * Called by game to remove objects from game
   * @return
   */
  public boolean toRemove() {
    return this.toRemove;
  }

}