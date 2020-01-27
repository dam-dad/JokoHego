package dad.jokohego.controllers;

import java.awt.Point;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dad.jokohego.model.Monster;
import dad.jokohego.utils.BackType;
import dad.jokohego.utils.JokoUtils;
import dad.jokohego.utils.MonsterType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class JuegoController implements Initializable {

	// model

	private static Button[][] backButton;
	private static BackType[][] backType;
	private static int nivel = 1;
	private static boolean puerta=false;

	// view
	
	@FXML
	private BorderPane root;
	
	private CharacterBoxController character = new CharacterBoxController();

	public JuegoController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/GameView.fxml"));
		loader.setController(this);
		loader.load();
	}

	public BorderPane getView() {
		return root;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		root.setCenter(JokoUtils.generarNivel(nivel++,this));
		root.setRight(character);
		backButton = JokoUtils.getButton();
		backType = JokoUtils.getBackType();

	}

	@FXML
	public  void onGeneralAction(ActionEvent event) {
		Button boton = (Button) event.getSource();
		System.out.println(boton.getStyleClass().toString());
		//Si Losa Oscura 
		if (boton.getStyleClass().contains("LosaOscura")) {
			Point coordenadas = (Point) boton.getUserData();
			boton.getStyleClass().remove("LosaOscura");
			BackType tipofondo = backType[(int) coordenadas.getX()][(int) coordenadas.getY()];
			if (tipofondo != BackType.Monster) {
				boton.getStyleClass().add(tipofondo.toString());
			} else {
				MonsterType tipo = JokoUtils.generarMonstruo();
				boton.getStyleClass().addAll(tipo.toString(),BackType.Monster.toString());
				Object[] MonsterPoint = new Object[2];
				MonsterPoint[0] = new Monster(tipo);
				MonsterPoint[1] = coordenadas;
				boton.setUserData(MonsterPoint);
			} 
		}
		//Si Escalera
		else if (boton.getStyleClass().contains("Escaleras")) {
			JokoUtils.setEscalera(false);
			root.setCenter(JokoUtils.generarNivel(nivel++,this));
			backType = JokoUtils.getBackType();
		//Si Monstruo	
		}else if(boton.getStyleClass().contains("Monster")) {
			Object[] mt = (Object[])boton.getUserData();
			Monster monster = (Monster)mt[0];
			Point punto = (Point)mt[1];
			character.getCharacter().vidaProperty().subtract(monster.getDanyo());
		}
		
	}

}
