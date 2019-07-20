package com.yamasaki.game_sprites;

public class ShipImage extends SpriteImage {
  public ShipImage(String filename) {
    super(filename);
    this.width = 50;
    this.height = 70;
    this.xImageFrames = 4;
    this.yImageFrames = 1;
    this.msFrameDelay = 100;
    this.msTotalAnimation = this.msFrameDelay * this.xImageFrames;
  }


}