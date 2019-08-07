package com.yamasaki.game_sprites;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

public class Projectile extends Sprite {
  private final double speed = 10;
  private final long msMax = 6000;
  private AffineTransform transform;
  private long msTime;

  public Projectile(int x, int y, double theta) {
    super(x, y, theta);
    this.calcSpeed();
    this.transform = this.createTransform(x, y, theta);
    this.msTime = System.currentTimeMillis();
  }

  private void calcSpeed() {
    this.dx = Math.sin(theta) * speed;
    this.dy = Math.cos(theta) * speed;
  }

  @Override
  public void draw(Graphics2D g2) {
    super.draw(g2);
    AffineTransform savedTransform;
    savedTransform = g2.getTransform();
    g2.setTransform(this.transform);

    Color colorSave = g2.getColor();
    g2.setColor(Color.white);
    g2.fillArc(-5, -5, 10, 10, 0, 360);
    g2.setColor(colorSave);

    g2.setTransform(savedTransform);
  }

  @Override
  public void update(long timeNow) {
    super.update(timeNow);
    this.x += (int) Math.round(this.dx);
    this.y -= (int) Math.round(this.dy);
    this.transform = this.createTransform(x, y, theta);
    if (timeNow - this.msTime > this.msMax) {
      this.toRemove = true;
    }
  }
}