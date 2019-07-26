package com.yamasaki.game_sprites;

import java.awt.Image;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.Toolkit;

public class SpriteImage {
  protected String filename;
  protected Image image;
  protected int xImageFrames;
  protected int yImageFrames;

  protected int width;
  protected int height;
  protected int msFrameDelay;
  protected int msTotalAnimation;

  protected boolean hasCollisions;
  protected Polygon polyCollisions;

  public SpriteImage(String filename) {
    this.filename = filename;
    Toolkit toolkit = Toolkit.getDefaultToolkit();
    this.image = toolkit.createImage(filename);

    // initialize to be static image
    this.xImageFrames = 1;
    this.yImageFrames = 1;
    this.msFrameDelay = 0;
    this.msTotalAnimation = 0;

    // initialize to not have collision polygon
    this.hasCollisions = false;
    this.polyCollisions = new Polygon();
    // Sub class must enter width, height and msFrame
    // Sub class must also enter xImageFrames
    // Sub class must enter xImageFrames and yImageFrames
  }

  protected Image getImage() {
    return this.image;
  }

  protected int getImageWidth() {
    return this.width;
  }

  protected int getImageHeight() {
    return this.height;
  }

  protected Rectangle getFrame(int x, int y) {
    return new Rectangle(x * this.width, y * this.height, this.width, this.height);
  }

  protected int getFrameDelay() {
    return this.msFrameDelay;
  }

  protected int getTotalAnimation() {
    return this.msTotalAnimation;
  }
}