package com.yamasaki.objs;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import com.yamasaki.game_sprites.Projectile;

import com.yamasaki.AppState;

public class Ship {
  // location on board
  private int x;
  private int y;
  // speed in each dimension
  private double dx;
  private double dy;
  // rotation on plane
  private double angle;
  private ArrayList<Projectile> shots;
  // shot delay
  private long lastShot = 0;
  private final long shotDelay = 500;

  private final double speedMax = 4.0;
  private int[] xPoints = {0, 20, -20 };
  private int[] yPoints = {-25, 25, 25 };
  private AffineTransform transform;

  public Ship(int x, int y, double angle) {
    this.x = x;
    this.y = y;
    this.angle = angle;
    // start ship with no motion
    this.dx = 0;
    this.dy = 0;
    this.shots = new ArrayList<Projectile>();
    
    // intialize Transform
    this.transform = this.createTransform(this.x, this.y, this.angle);
  }

  private AffineTransform createTransform(int x, int y, double angle) {
    AffineTransform transform = new AffineTransform();
    transform.translate(x, y);
    transform.rotate(angle, 0, 0);
    return transform;
  }

  public void draw(Graphics2D g2) {
    AffineTransform savedTransform;
    savedTransform = g2.getTransform();
    g2.setTransform(this.transform);
		g2.setPaint(Color.white);
		g2.setStroke(new BasicStroke(5));
    g2.drawPolygon(this.xPoints, this.yPoints, 3);
    g2.setTransform(savedTransform);
    this.shots.forEach(shot -> shot.draw(g2));
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
    this.transform = this.createTransform(this.x, this.y, this.angle);

    for(Projectile shot : this.shots) {
      shot.update();
    }
    this.shots.removeIf((shot) -> !shot.isVisible());
  }

  private void changeSpeed(double speed) {
    double dxChange = Math.sin(this.angle) * speed;
    double dyChange = Math.cos(this.angle) * speed;
    this.dx = Math.max(Math.min(speedMax, this.dx + dxChange), -speedMax);
    this.dy = Math.max(Math.min(speedMax, this.dy + dyChange), -speedMax);
  }

  private void changeAngle(double angle) {
    this.angle += angle;
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
      this.shots.add(new Projectile(this.x, this.y, this.angle));
      lastShot = time;
    } 
  }
}