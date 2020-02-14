package dad.jokohego.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

public class MainController implements Initializable {

	@FXML
	private BorderPane root;

	private  MenuController menu;
	private  JuegoController juego;

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
			root.setCenter(menu.getView());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setJuego() {
		root.setCenter(juego.getView());
	}
	public void setMenu() {
		root.setCenter(menu.getView());
	}

}
