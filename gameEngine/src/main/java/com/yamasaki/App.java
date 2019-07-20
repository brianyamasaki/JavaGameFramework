package com.yamasaki;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

/**
 * Hello world!
 */
public final class App extends JFrame implements ActionListener, KeyListener {
    private static final long serialVersionUID = 1L;
    private static final String appName = "Java Game Engine";
    private static final int initialWidth = 800;
    private static final int initialHeight = 600;
    private AppState appState;
    private AppContent appContent;

    private App() {
        // Returns the size when user resizes the Window
        this.addComponentListener(new CAdapter());
        this.initUI();
    }

    private void initUI() {

        this.appState = new AppState(this);
        AppState.setAppSize(initialWidth, initialHeight);
        this.appContent = new AppContent(this);
        this.add(this.appContent);
        this.addKeyListener(this);

        // set the window title
        this.setTitle(appName);
        // set the size of the window
        this.setSize(initialWidth, initialHeight);

        // this centers the window on the screen
        this.setLocationRelativeTo(null);
        // allows user to resize the window
        this.setResizable(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // add a Menubar with menus
        this.setJMenuBar(new AppMenuBar(this, this.appState));
    }

    private static void resized(int width, int height) {
        AppState.setAppSize(width, height);
    }

    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        String[] actionParts = action.split(AppMenuBar.actionFormatSplitRegex);
        if (actionParts[0].matches(AppMenuBar.app)) {
            // signifies this is an App command
            switch (actionParts[1]) {
                case AppMenuBar.exitItem:
                    System.exit(0);
                    break;
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        this.appContent.keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        this.appContent.keyReleased(e);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    /** Component Adapter
    *  Listen for the messages from the window frame
    */
    private class CAdapter extends ComponentAdapter {
        /* catch when the user resizes the window
        *  Receives events that tell us what size the window is
        *  @parm parameter string that tells us the size
        */
        @Override
        public void componentResized(ComponentEvent e) {
            // expecting paramString to be of the form "COMPONENT_RESIZED (350,160 800x600)""
            String[] tokens = e.paramString().split(" ");
            if (tokens[0].matches("COMPONENT_RESIZED")) {
                // now expecting tokens[2] to be "800x600)"
                String[] dimTokens = tokens[2].split("x");
                dimTokens[1] = dimTokens[1].substring(0, dimTokens[1].length()-1);
                // System.out.println("Component resized " + dimTokens[0] + "x" + dimTokens[1]);
                App.resized(Integer.parseInt(dimTokens[0], 10), Integer.parseInt(dimTokens[1], 10));
            }
        }
    }
    /**
     * Says hello to the world.
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                App ex = new App();
                ex.setVisible(true);
            }
        });
    }
}
