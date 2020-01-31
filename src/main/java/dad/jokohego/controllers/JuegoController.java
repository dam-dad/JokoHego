package dad.jokohego.controllers;

import java.awt.Point;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.management.RuntimeErrorException;

import org.controlsfx.control.PopOver;
import org.controlsfx.control.PopOver.ArrowLocation;

import dad.jokohego.model.Monster;
import dad.jokohego.utils.BackType;
import dad.jokohego.utils.JokoUtils;
import dad.jokohego.utils.MonsterType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class JuegoController implements Initializable {

	// model

	private static Button[][] backButton;
	private static BackType[][] backType;
	private static int nivel = 0;
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
		root.setCenter(JokoUtils.generarNivel(++nivel,this));
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
			
			character.getCharacter().setVida(character.getCharacter().getVida()-(numMonster*nivel));
			
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
			//popvida
			PopOver a = new PopOver();
			a.setArrowLocation(ArrowLocation.TOP_CENTER);
			a.show(boton);
			
			if(monster.getVida()<0) {
				numMonster--;
				boton.getStyleClass().removeAll(monster.getNombre(),"Monster");
				boton.getStyleClass().add("Losa");
			
			}
		}else if(boton.getStyleClass().contains("Cofre")) {
			boton.getStyleClass().remove("Cofre");
			boton.getStyleClass().add("CofreAbierto");
			
			PopOver a = new PopOver();
			a.getRoot().getStyleClass().add("popup");
			a.setArrowLocation(ArrowLocation.TOP_CENTER);
			a.setAnchorX(300);
			a.setAnchorY(250);
			a.detach();
			a.requestFocus();
			
			Button item1 = new Button();
			item1.setPrefHeight(40);
			item1.setPrefWidth(40);
			item1.getStyleClass().add("pocion");
			Button item2 = new Button();
			item2.setPrefHeight(40);
			item2.setPrefWidth(40);
			item2.getStyleClass().add("pocion");
			Button item3 = new Button();
			item3.setPrefHeight(40);
			item3.setPrefWidth(40);
			item3.getStyleClass().add("pocion");
			HBox botonera = new HBox(item1,item2,item3);
			botonera.setAlignment(Pos.CENTER);
			botonera.setSpacing(5);
			botonera.setPadding(new Insets(5));
			a.setContentNode(botonera);
			
			a.show(boton);
			
			/*
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
			*/
		}

		if(character.getCharacter().getVida()<0) {
			nivel = 0;
			numMonster=0;
			root.setCenter(JokoUtils.generarNivel(++nivel,this));
			backType = JokoUtils.getBackType();
			character.getCharacter().setVida(100);
			character.getCharacter().setDanyo(10);
			character.getCharacter().setArmadura(0);
		}

	}

}
