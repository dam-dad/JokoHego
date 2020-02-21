package dad.jokohego.utils;

import javafx.animation.Transition;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Sounds {
	
	public static MediaPlayer playMenuSong() {
		
		String musica = Sounds.class.getResource("/music/menuMusic.wav").toString();  
		Media sound = new Media(musica);
		MediaPlayer mediaPlayer = new MediaPlayer(sound);
		mediaPlayer.setCycleCount(Transition.INDEFINITE);
		mediaPlayer.play();
		return mediaPlayer;
		
	}
	public static MediaPlayer playBattleSong() {
		
		String musica = Sounds.class.getResource("/music/juegoMusic.wav").toString();  
		Media sound = new Media(musica);
		MediaPlayer mediaPlayer = new MediaPlayer(sound);
		mediaPlayer.setCycleCount(Transition.INDEFINITE);
		mediaPlayer.play();
		return mediaPlayer;
		
	}
	public static void playEffectSound(String file) {
		
		String musica = Sounds.class.getResource("/music/"+file+".wav").toString();  
		Media sound = new Media(musica);
		MediaPlayer mediaPlayer = new MediaPlayer(sound);
//		mediaPlayer.setCycleCount(Transition.INDEFINITE);
		mediaPlayer.play();
		
	}
}
