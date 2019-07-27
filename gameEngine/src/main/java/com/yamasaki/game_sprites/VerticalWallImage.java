package com.yamasaki.game_sprites;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

public class VerticalWallImage extends SpriteImage {
  private final int WIDTH = 20;
  private final int HEIGHT = 200;
  public VerticalWallImage(String filename) {
    super(filename);
    this.width = WIDTH;
    this.height = HEIGHT;

    this.setupCollisions(this.width, this.height);
  }

  /**
   * This sets up the sprite to have collision physics
   * Note that polygon is centered around 0,0
   */
  private void setupCollisions(int width, int height) {
    this.hasCollisions = true;
    this.pointsCollisions = new ArrayList<Point>();
    this.pointsCollisions.add(new Point(0, 0));
    this.pointsCollisions.add(new Point(width, 0));
    this.pointsCollisions.add(new Point(width, height));
    this.pointsCollisions.add(new Point(0, height));

    this.rectCollisions = new Rectangle(0, 0, width, height);
    // static objects don't check for any collisions, but must maintain physics
    this.collidesWith = new int[0];
  }
}