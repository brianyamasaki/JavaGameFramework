package com.yamasaki.game_sprites;

import com.yamasaki.AppState;

import java.awt.Point;
import java.awt.Polygon;
import java.util.ArrayList;

public class Ship2 extends Sprite {
  private final double speedMax = 4.0;
  private long lastShot = 0;
  private final long shotDelay = 500;

  public Ship2(SpriteImage spriteImage, int x, int y, double theta) {
    super(spriteImage, x, y, theta);
  }

  /**
   * Update position and rotation
   */
  public void update() {
    super.update();

    int appWidth = AppState.getAppWidth();
    int appHeight = AppState.getAppHeight();

    // move and rotate ship
    this.x += this.dx;
    this.y -= this.dy;
    this.y -= this.dy;

    if(this.x > appWidth){
      this.x = 0;
    }
    else if(this.x<0){
      this.x = appWidth;
    }
    if(this.y>appHeight){
      this.y = 0;
    }
    else if(this.y<0){
      this.y = appHeight;
    }
    this.transform = this.createTransform(this.x, this.y, this.theta);

  }

  @Override
  public void collisionDetect() {
    super.collisionDetect();
    ArrayList<Sprite> staticSprites = AppState.getSpriteList(0);

    // iterate through staticSprites
    for (Sprite sprite : staticSprites) {
      if (this.hasCollidedWith(sprite))
        this.receiveDamage();
    }
  }
  
  private void receiveDamage() {
    this.toRemove = true;
    AppState.setSceneIndex(AppState.getSceneIndex()+1);
  }

  private void changeSpeed(double speed) {
    double dxChange = Math.sin(this.theta) * speed;
    double dyChange = Math.cos(this.theta) * speed;
    this.dx = Math.max(Math.min(speedMax, this.dx + dxChange), -speedMax);
    this.dy = Math.max(Math.min(speedMax, this.dy + dyChange), -speedMax);
  }

  private void changeAngle(double theta) {
    this.theta += theta;
  }

  public void turnLeft() {
    this.changeAngle(-0.15);
  }

  public void turnRight() {
    this.changeAngle(0.15);
  }

  public void thrust() {
    this.changeSpeed(0.5);
  }

  public void brake() {
    this.changeSpeed(-0.5);
  }

  public void fire() {
    long time = System.currentTimeMillis();
    if(time-this.lastShot>=shotDelay){
      AppState.addDynamicSprite(new Projectile(this.x, this.y, this.theta));
      lastShot = time;
    } 
  }
}