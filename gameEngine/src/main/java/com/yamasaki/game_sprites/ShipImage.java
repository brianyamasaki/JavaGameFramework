package com.yamasaki.game_sprites;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

public class ShipImage extends SpriteImage {
  public ShipImage(String filename) {
    super(filename);
    this.width = 50;
    this.height = 70;
    this.xImageFrames = 4;
    this.yImageFrames = 1;
    this.msFrameDelay = 100;
    this.msTotalAnimation = this.msFrameDelay * this.xImageFrames;
    this.setupCollisions();
  }

  /**
   * This sets up the sprite to have collision physics
   * Note that polygon is centered around 0,0
   */
  private void setupCollisions() {
    this.hasCollisions = true;
    this.pointsCollisions = new ArrayList<Point>();

    // ship is just a triangle
    this.pointsCollisions.add(new Point(this.width / 2, 0));
    this.pointsCollisions.add(new Point(this.width, 58));
    this.pointsCollisions.add(new Point(0, 58));

    this.rectCollisions = new Rectangle(0, 0, this.width, this.height);
    //  ship collides with static and dynamic objects
    this.collidesWith = new boolean[2];
    this.collidesWith[0] = true;
    this.collidesWith[1] = true;
  }

}