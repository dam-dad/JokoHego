package dad.jokohego.utils;

import javafx.animation.Transition;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Sounds {
	
	/**
	 * 
	 * Ejecuta la música del menú de forma indefinida y retorna el objeto MediaPlayer que está en ejecución.
	 * 
	 * @return
	 */
	
	public static MediaPlayer playMenuSong() {
		
		String musica = Sounds.class.getResource("/music/menuMusic.wav").toString();  
		Media sound = new Media(musica);
		MediaPlayer mediaPlayer = new MediaPlayer(sound);
		mediaPlayer.setCycleCount(Transition.INDEFINITE);
		mediaPlayer.play();
		return mediaPlayer;
	}
	
	/**
	 * 
	 * Ejecuta la música del juego de forma indefinida y retorna el objeto MediaPlayer que está en ejecución.
	 * 
	 * @return
	 */
	
	public static MediaPlayer playBattleSong() {
		
		String musica = Sounds.class.getResource("/music/juegoMusic.wav").toString();  
		Media sound = new Media(musica);
		MediaPlayer mediaPlayer = new MediaPlayer(sound);
		mediaPlayer.setCycleCount(Transition.INDEFINITE);
		mediaPlayer.play();
		return mediaPlayer;
		
	}
	
	/**
	 * 
	 *  Reproduce un sonido que es obtenido por parámetro.
	 * 
	 * @param file Nombre del archivo a reproducir.
	 */
	
	public static void playEffectSound(String file) {
		
		String musica = Sounds.class.getResource("/music/"+file+".wav").toString();  
		Media sound = new Media(musica);
		MediaPlayer mediaPlayer = new MediaPlayer(sound);
		mediaPlayer.setVolume(0.5);
		mediaPlayer.play();
		
	}
}
