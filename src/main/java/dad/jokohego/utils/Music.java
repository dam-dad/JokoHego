package dad.jokohego.utils;

import javafx.animation.Transition;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Music {
	
	public void play() {
		
		String musica = getClass().getResource("/music/DungeonKing.mp3").toString();  
		Media sound = new Media(musica);
		MediaPlayer mediaPlayer = new MediaPlayer(sound);
		mediaPlayer.setOnEndOfMedia(new Loop());
		mediaPlayer.play();
		
	}
}
 class Loop extends Thread {
	
	 MediaPlayer mediaPlayer;
	 
	 public Loop() {
		String musica = getClass().getResource("/music/DungeonKingLoop.wav").toString();  
		Media sound = new Media(musica);
		mediaPlayer = new MediaPlayer(sound);
		mediaPlayer.setCycleCount(Transition.INDEFINITE);
		
	}

	public void run() {
		mediaPlayer.play();
	}



}
