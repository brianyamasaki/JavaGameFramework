package com.yamasaki.game_sprites;
import java.awt.Graphics2D;
import java.awt.Panel;
import java.util.ArrayList;

import javax.swing.JPanel;

import com.yamasaki.AppState;

public class Ship2 extends Sprite {
  private final double speedMax = 4.0;
  private long lastShot = 0;
  private final long shotDelay = 500;
  private ArrayList<Projectile> shots;

  public Ship2(SpriteImage spriteImage, int x, int y, double theta) {
    super(spriteImage, x, y, theta);
    this.shots = new ArrayList<Projectile>();
  }
  
  public void update() {
    // move and rotate ship
    this.x += this.dx;
    this.y -= this.dy;

    int appWidth = AppState.getAppWidth();
    int appHeight = AppState.getAppHeight();
    if(this.x>appWidth){
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

    for(Projectile shot : this.shots) {
      shot.update();
    }
    this.shots.removeIf((shot) -> !shot.isVisible());
  }

  @Override
  public void draw(Graphics2D g2, JPanel panel) {
    super.draw(g2, panel);
    for(Projectile shot : shots)
    {
      shot.draw(g2);
    }
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
      this.shots.add(new Projectile(this.x, this.y, this.theta));
      lastShot = time;
    } 
  }
}