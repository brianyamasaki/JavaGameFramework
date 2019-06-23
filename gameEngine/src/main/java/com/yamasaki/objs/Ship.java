package com.yamasaki.objs;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

public class Ship {
  private int x;
  private int y;
  private double angle;
  private double speed;
  private int[] xPoints = {0, 20, -20 };
  private int[] yPoints = {-20, 20, 20 };

  public Ship(int x, int y, double angle) {
    this.x = x;
    this.y = y;
    this.angle = angle;
    this.speed = 0;
  }

  public void draw(Graphics2D g2) {
    AffineTransform savedTransform;
    AffineTransform transform = new AffineTransform();
    savedTransform = g2.getTransform();
    transform.translate(this.x, this.y);
    transform.rotate(this.angle);
    g2.setTransform(transform);
    g2.drawPolygon(this.xPoints, this.yPoints, 3);
    g2.setTransform(savedTransform);
  }

  public void update() {
    this.x += Math.round(Math.sin(this.angle) * this.speed);
    this.y -= Math.round(Math.cos(this.angle) * this.speed);
  }

  public void turnLeft() {
    this.angle -= 0.05;
  }

  public void turnRight() {
    this.angle += 0.05;
  }

  public void thrust() {
    this.speed += 0.5;
  }

  public void brake() {
    this.speed -= 0.5;
  }

  public void step() {

  }
}