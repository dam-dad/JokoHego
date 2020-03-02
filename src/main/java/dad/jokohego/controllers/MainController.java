package dad.jokohego.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dad.jokohego.utils.Sounds;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.MediaPlayer;


/**
 * 
 * Controlador principal para cambiar entre los demás controladores.
 * 
 * @author SERGIO GARCÍA DELGADO
 * @author AMARO YANES CABRERA
 */

public class MainController implements Initializable {

	@FXML
	private BorderPane root;
	
	private MediaPlayer music;
	private MenuController menu;
	private JuegoController juego;

	public BorderPane getView() {
		return root;
	}

	public MainController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainView.fxml"));
		loader.setController(this);
		loader.load();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			juego = new JuegoController(this);
			menu = new MenuController(this);
			setMenu();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setJuego() {
		music.stop();
		try {
			juego = new JuegoController(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
		root.setCenter(juego.getView());
		music = Sounds.playBattleSong();
	}

	public void setMenu() {
		if(music!=null)music.stop();
		root.setCenter(menu.getView());
		music = Sounds.playMenuSong();
	}
	
	public MenuController getMenu() {
		return menu;
	}

}
