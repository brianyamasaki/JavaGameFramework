package com.yamasaki.game_sprites;

public class BackgroundStarImage extends SpriteImage {
  public BackgroundStarImage(String filename) {
    super(filename);
    this.width = 50;
    this.height = 50;
    this.xImageFrames = 5;
    this.yImageFrames = 1;
    this.msFrameDelay = 500;
    this.msTotalAnimation = this.msFrameDelay * this.xImageFrames;
  }
}