package com.yamasaki.game_sprites;

import java.awt.Polygon;

public class VerticalWallImage extends SpriteImage {
  public VerticalWallImage(String filename) {
    super(filename);
    int[] xVals = {0, 20, 20, 0};
    int[] yVals = {0, 0, 200, 200};
    this.width = 20;
    this.height = 200;
    this.hasCollisions = true;
    this.polyCollisions = new Polygon(xVals, yVals, xVals.length);
  }
}