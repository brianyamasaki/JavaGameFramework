# How This Game Framework Works

## App.java
The game starts from App.java, which creates a window frame with title bar and close buttons. A Menubar and App Content window gets created and added at this time. If you don't want to Menu Bar, comment out the code that creates it. 

All Keyboard events get collected at this point and gets passed down to the AppContent window.

## AppMenuBar.java
This is where you describe your menus. 

## AppContent.java
This is the content of the top level window frame. This window goes through various Scenes of the Game, from Introduction screen to Game Over. 

## AppState.java
This object substitutes for global state to pass data to and from various parts of the application.

## Scenes
Many game frameworks have the concept of a scene, which describes the complete contents and actions of the game window. In this example, I have included an Intro Scene, a Game Scene and a Game Over Scene. 

## Sprites
Sprites are a fundamental concept of games that describe any object, explosion, character or even puffs of smoke. Sprites can move, animate, disappear and collide all at once or simply be a background image, it all depends how you define them.
