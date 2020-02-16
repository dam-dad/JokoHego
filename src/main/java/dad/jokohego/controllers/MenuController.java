package dad.jokohego.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dad.jokohego.utils.Animations;
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

		buttonsAnimation();

	}

	@FXML
	void onAyudaAction(ActionEvent event) {

	}

	@FXML
	void onJugarAction(ActionEvent event) {
		doorsAnimation(puertauno);
		doorsAnimation(puertados);
	}

	@FXML
	void onSalirAction(ActionEvent event) {
		System.exit(0);
	}

	public void buttonsAnimation() {

		FadeTransition transicion = new FadeTransition();

		transicion.setAutoReverse(true);
		transicion.setCycleCount(1);
		transicion.setDelay(Duration.ZERO);
		transicion.setDuration(Duration.seconds(10));
		transicion.setFromValue(0);
		transicion.setToValue(1);
		transicion.setRate(1);
		transicion.setNode(botonera);
		transicion.setInterpolator(Interpolator.LINEAR);

		transicion.play();
	}

	public void doorsAnimation(Node nodo) {
		SequentialTransition transicion = new SequentialTransition();
		TranslateTransition translate = new TranslateTransition();

		if (nodo.getId().equals("puertauno")) {

			translate.setDuration(Duration.seconds(5));
			translate.setFromX(0);
			translate.setToX(-300);
			translate.setNode(nodo);
			translate.setInterpolator(Interpolator.EASE_BOTH);
			translate.setAutoReverse(false);

			transicion = new SequentialTransition();
			transicion.setCycleCount(Transition.INDEFINITE);
			transicion.getChildren().addAll(translate);
			transicion.setAutoReverse(false);

			transicion.setCycleCount(1);

			transicion.play();

		} else if (nodo.getId().equals("puertados")) {

			translate.setDuration(Duration.seconds(5));
			translate.setFromX(0);
			translate.setToX(300);
			translate.setNode(nodo);
			translate.setInterpolator(Interpolator.EASE_BOTH);
			translate.setAutoReverse(false);

			transicion = new SequentialTransition();
			transicion.setCycleCount(Transition.INDEFINITE);
			transicion.getChildren().addAll(translate);
			transicion.setAutoReverse(false);

			transicion.setCycleCount(1);

			transicion.play();
		}
		
		transicion.currentTimeProperty().addListener((o,ov,nv)->{
			if(nv.equals(Duration.seconds(5))) {
				main.setJuego();
			}
		});
		
	}

}
