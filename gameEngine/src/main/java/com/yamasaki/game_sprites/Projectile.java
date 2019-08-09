package com.yamasaki.game_sprites;

import java.awt.Color;
import java.awt.Point;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import com.yamasaki.AppState;

public class Projectile extends Sprite {
  private final double speed = 10;
  private final long msMax = 6000;
  // private AffineTransform transform;
  private long msTime;

  public Projectile(int x, int y, double theta) {
    super(x, y, theta);
    this.calcSpeed();
    // this.transform = this.createTransform(x, y, theta);
    this.msTime = System.currentTimeMillis();
    setupCollisions();
  }

  private void setupCollisions() {
    this.hasCollisions = true;
    this.pointsCollisions = new ArrayList<Point>();

    //projectile is four sided
    this.pointsCollisions.add(new Point(0, -5));
    this.pointsCollisions.add(new Point(5, -5));
    this.pointsCollisions.add(new Point(0, 5));
    this.pointsCollisions.add(new Point(-5, 5));

    // collides with static and dynamic objects
    this.collidesWith = new boolean[2];
    this.collidesWith[0] = true;
    this.collidesWith[1] = true;
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

  @Override
  public void collisionDetect(long timeNow) {
    super.collisionDetect(timeNow);
    ArrayList<Sprite> spriteList;

    // iterate through spriteLists
    for (int i = 0; i < this.collidesWith.length; i++) {
      if (this.collidesWith[i]) {
        spriteList = AppState.getSpriteList(i);
        for (Sprite sprite : spriteList) {
          if (sprite != this && this.hasCollidedWith(sprite))
            this.toRemove = true;
        }
      }
    }
  }
}