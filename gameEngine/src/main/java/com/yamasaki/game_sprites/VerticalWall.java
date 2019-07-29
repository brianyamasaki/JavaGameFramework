package com.yamasaki.game_sprites;

public class VerticalWall extends Sprite {

  public VerticalWall(SpriteImage spriteImage, int x, int y, double theta) {
    super(spriteImage, x, y, theta);
    // static objects can calculate location once
    this.pointsCollisionsInGame = this.transformPoints(this.pointsCollisions);
    this.rectBoundsInGame = this.boundingRectangle(this.pointsCollisionsInGame);
  }

}