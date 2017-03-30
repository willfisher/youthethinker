package com.retrochicken.engine.fx;

import java.util.ArrayList;

public class Settings {
	public static int VOLUME = 100;
	public static ArrayList<SoundClip> ACTIVE_MUSIC = new ArrayList<>();
	
	public static void setVolume(int volume) {
		VOLUME = volume;
		for(SoundClip clip : ACTIVE_MUSIC)
			clip.adjustVolume();
	}
	
	public static float RES_SCALE = 2f;
	
	public static int AMBIENT_COLOR = 0xff000000; //Pixel.getColor(1, 0.4f, 0.5f, 0.6f);
	public static int SELECT_COLOR = 0xff7fff00;
	
	public static int TERMINAL_COLOR = 0xFF00FF00;
}
