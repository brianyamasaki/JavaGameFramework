package com.yamasaki.sound;

public class GameSounds 
{
	private String laser1FilePath = "gameEngine/assets/sounds/laser_1.wav";
	private String laser2FilePath = "gameEngine/assets/sounds/laser_2.wav";
	// private AudioPlayer effectsAudio;
	// private AudioPlayer effectsAudio2;
	// private AudioPlayer backgroundAudio;
	
	public GameSounds()
	{

	}
	
	public void playLaser1Sounds()
	{
		Runnable runnable = new AudioPlayer(laser1FilePath, false);
		Thread thread = new Thread(runnable);
		thread.start();
	}

	public void playLaser2Sounds()
	{
		Runnable runnable = new AudioPlayer(laser2FilePath, false);
		Thread thread = new Thread(runnable);
		thread.start();
	}
}
