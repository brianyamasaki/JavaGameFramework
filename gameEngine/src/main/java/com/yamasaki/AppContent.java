package com.yamasaki;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import com.yamasaki.scene.Scene;
import com.yamasaki.scene.GameOver;
import com.yamasaki.scene.Intro;

public class AppContent extends JPanel {

  private static final long serialVersionUID = 1L;
  private Scene[] scenes;
  private int iScene;

  public AppContent(App app) {
    super(new BorderLayout());

    this.scenes = new Scene[2];
    this.scenes[0] = new Intro(app);
    this.scenes[1] = new GameOver(app);
    this.iScene = 0;
    this.add(this.scenes[this.iScene], BorderLayout.CENTER);
    AppState.setAppContent(this);
  }

  public void setScene(int iScene) {
    this.scenes[0].setVisible(false);
    this.add(this.scenes[iScene], BorderLayout.CENTER);
    this.iScene = iScene;
    repaint();
  }

  public void menuItemCommand(String part1, String part2) {
    // this.board.menuItemCommand(part2);
  }
}