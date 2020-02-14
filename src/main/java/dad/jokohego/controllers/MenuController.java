package dad.jokohego.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class MenuController implements Initializable {

	@FXML
	private AnchorPane root;

	@FXML
	private Button jugarButton;

	@FXML
	private Button ayudaButton;

	@FXML
	private Button salirButton;

	// controllers

	MainController main;

	public AnchorPane getView() {
		return root;
	}

	public MenuController(MainController mainController) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MenuView.fxml"));
		loader.setController(this);
		loader.load();
		main = mainController;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}

	@FXML
	void onAyudaAction(ActionEvent event) {

	}

	@FXML
	void onJugarAction(ActionEvent event) {

		main.setJuego();

	}

	@FXML
	void onSalirAction(ActionEvent event) {
		System.exit(0);
	}

}
