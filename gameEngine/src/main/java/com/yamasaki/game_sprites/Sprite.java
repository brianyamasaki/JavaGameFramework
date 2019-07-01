package com.yamasaki.game_sprites;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

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
  
  public Sprite(int x, int y, double theta) {
    this.x = x;
    this.y = y;
    this.dx = 0;
    this.dy = 0;
    this.theta = theta;
    this.transform = this.createTransform(x, y, theta);
  }

  public Sprite(SpriteImage spriteImage, int x, int y, double theta) {
    this.spriteImage = spriteImage;
    this.x = x;
    this.y = y;
    this.dx = 0;
    this.dy = 0;
    this.theta = theta;
    this.frameDelay = this.spriteImage.getFrameDelay();
    this.transform = this.createTransform(x, y, theta);
  }

  protected AffineTransform createTransform(int x, int y, double theta) {
    AffineTransform transform = new AffineTransform();
    transform.translate(x, y);
    transform.rotate(theta, 0, 0);
    return transform;
  }

  /* draw function for images - we need the actionListener for images
  */
  public void draw(Graphics2D g2, JPanel panel) {
    // draw it
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