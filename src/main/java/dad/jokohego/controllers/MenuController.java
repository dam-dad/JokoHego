package dad.jokohego.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dad.jokohego.utils.Animations;
import dad.jokohego.utils.Sounds;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.SequentialTransition;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class MenuController implements Initializable {

	@FXML
	private AnchorPane root;

	@FXML
	private Button jugarButton;

	@FXML
	private Button ayudaButton;

	@FXML
	private Button salirButton;

	@FXML
	private VBox botonera;

	@FXML
	private ImageView puertauno;

	@FXML
	private ImageView puertados;

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

		Animations.buttonsAnimation(botonera);

	}

	@FXML
	void onAyudaAction(ActionEvent event) {

	}

	@FXML
	void onJugarAction(ActionEvent event) {
		Animations.doorsAnimation(puertauno,main);
		Animations.doorsAnimation(puertados,main);
		Sounds.playEffectSound("door");
	}

	@FXML
	void onSalirAction(ActionEvent event) {
		System.exit(0);
	}




}
