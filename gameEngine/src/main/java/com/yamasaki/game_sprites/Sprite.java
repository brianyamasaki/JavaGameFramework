package com.yamasaki.game_sprites;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;

public class Sprite {
  protected int x;
  protected int y;
  protected double dx;
  protected double dy;
  protected double theta;
  protected AffineTransform transform;
  protected Image image;
  
  public Sprite(int x, int y, double theta) {
    this.x = x;
    this.y = y;
    this.dx = 0;
    this.dy = 0;
    this.theta = theta;
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
  public void draw(Graphics2D g2, ActionListener actionListener) {
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