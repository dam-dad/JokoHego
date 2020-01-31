package dad.jokohego.controllers;

import java.awt.Point;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.management.RuntimeErrorException;

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
	private static int numMonster = 0;
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
		character.getCharacter().setHombre(false);
		root.setRight(character);
		backType = JokoUtils.getBackType();
		character.getCharacter().setDanyo(10);
		character.getCharacter().setArmadura(0);

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
			
			character.getCharacter().setVida(character.getCharacter().getVida()-(numMonster*5));
			
			if (tipofondo != BackType.Monster) {
				boton.getStyleClass().add(tipofondo.toString());
			} else {
				numMonster++;
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
			numMonster=0;
			root.setCenter(JokoUtils.generarNivel(nivel++,this));
			backType = JokoUtils.getBackType();
		//Si Monstruo	
		}else if(boton.getStyleClass().contains("Monster")) {
			Object[] mt = (Object[])boton.getUserData();
			Monster monster = (Monster)mt[0];
			Point punto = (Point)mt[1];
			// intentar hacerlo con bindeos
//			character.getCharacter().vidaProperty().subtract(monster.getDanyo());
			character.getCharacter().setVida(character.getCharacter().getVida()-(monster.getDanyo()));
			monster.setVida(monster.getVida()-character.getCharacter().getDanyo());
			System.out.println(character.getCharacter().getVida());
			if(monster.getVida()<0) {
				numMonster--;
				boton.getStyleClass().removeAll(monster.getNombre(),"Monster");
				boton.getStyleClass().add("Losa");
			
			}
		}else if(boton.getStyleClass().contains("Cofre")) {
			boton.getStyleClass().remove("Cofre");
			boton.getStyleClass().add("CofreAbierto");
			if(character.getPotionButton1().isDisabled()) {
				character.getPotionButton1().setDisable(false);
				character.getPotionButton1().getStyleClass().remove("pocionvacia");
				character.getPotionButton1().getStyleClass().add("pocion");
			} else if(character.getPotionButton2().isDisabled()) {
				character.getPotionButton2().setDisable(false);
				character.getPotionButton2().getStyleClass().remove("pocionvacia");
				character.getPotionButton2().getStyleClass().add("pocion");
			}else if(character.getPotionButton3().isDisabled()) {
				character.getPotionButton3().setDisable(false);
				character.getPotionButton3().getStyleClass().remove("pocionvacia");
				character.getPotionButton3().getStyleClass().add("pocion");
			}
		}
		if(character.getCharacter().getVida()<0) {
			nivel = 0;
			root.setCenter(JokoUtils.generarNivel(nivel++,this));
			character.getCharacter().setHombre(false);
			root.setRight(character);
			backType = JokoUtils.getBackType();
			character.getCharacter().setDanyo(10);
			character.getCharacter().setArmadura(0);
			throw new RuntimeException("jaja rip");
		}
	}

}
