package com.yamasaki;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import com.yamasaki.scene.Scene;
import com.yamasaki.scene.Game;
import com.yamasaki.scene.GameOver;
import com.yamasaki.scene.Intro;

public class AppContent extends JPanel {

  private static final long serialVersionUID = 1L;
  private Scene[] scenes;
  private int iScene;
  private Scene currentScene;

  public AppContent(App app) {
    super(new BorderLayout());

    this.scenes = new Scene[3];
    this.scenes[0] = new Intro();
    this.scenes[1] = new Game();
    this.scenes[2] = new GameOver();
    this.setScene(0);
    AppState.setAppContent(this);
    this.addListeners();
  }

  protected void addListeners() {
    AppMouseAdapter mouseAdapter;
    mouseAdapter = new AppMouseAdapter(this);
    addMouseListener(mouseAdapter);
    addMouseMotionListener(mouseAdapter);
  }

  public void setScene(int iScene) {
    if (iScene > 0) {
      this.currentScene.setVisible(false);
    }
    this.currentScene = this.scenes[iScene];
    this.add(this.currentScene, BorderLayout.CENTER);  
    this.iScene = iScene;
    repaint();
  }

  public void menuItemCommand(String part1, String part2) {
    // this.board.menuItemCommand(part2);
  }

  public void mouseClicked(MouseEvent e) {
    this.currentScene.mouseClicked(e);
  }

  public void mouseMoved(MouseEvent e) {
    this.currentScene.mouseMoved(e);
  }

  public void keyPressed(KeyEvent e) {
    this.currentScene.keyPressed(e);
  }

  public void keyReleased(KeyEvent e) {
    this.currentScene.keyReleased(e);
  }

  private class AppMouseAdapter extends MouseAdapter {
    private AppContent appContent;

    public AppMouseAdapter(AppContent appContent) {
      super();
      this.appContent = appContent;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
      super.mouseClicked(e);
      appContent.mouseClicked(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
      super.mouseMoved(e);
      appContent.mouseMoved(e);
    }

  }

}