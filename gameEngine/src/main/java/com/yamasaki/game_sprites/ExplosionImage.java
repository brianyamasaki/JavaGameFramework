package com.yamasaki.game_sprites;

public class ExplosionImage extends SpriteImage {
  public ExplosionImage(String filename) {
    super(filename);
    this.xImageFrames = 3;
    this.lifetimeInMilliseconds = 300;
    this.msTotalAnimation = 300;
    this.msFrameDelay = 100;
    this.height = 60;
    this.width = 60;
  }
}