package dad.jokohego.controllers;

import java.awt.Point;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

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
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class JuegoController implements Initializable {

	// model

	private static Button[][] backButton;
	private static int nivel = 1;
	private static int numMonster = 0;
	private static boolean puerta = false;

	// view

	@FXML
	private BorderPane root;

	private PopOver poper = new PopOver();
	private HBox infoPoper;
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
		root.setCenter(JokoUtils.generarNivel(nivel, this));
		character.getCharacter().setHombre(false);
		root.setRight(character);
		character.getCharacter().setDanyo(10);
		character.getCharacter().setVida(100);
		character.getCharacter().setVidamax(100);
		character.getCharacter().setHombre(false);
		character.getCharacter().setDinero(0);

		// poper

		poper.setArrowLocation(ArrowLocation.TOP_CENTER);
		poper.setArrowSize(0);
		poper.setAnimated(false);

		ImageView vida = new ImageView(
				new Image(this.getClass().getResourceAsStream("/ImagenesGreenStyle/Items/Vida.png")));
		ImageView danyo = new ImageView(
				new Image(this.getClass().getResourceAsStream("/ImagenesGreenStyle/Items/Danyo.png")));
		Label vidanum = new Label();
		Label danyonum = new Label();
		infoPoper = new HBox(vida, vidanum, danyo, danyonum);
		infoPoper.setAlignment(Pos.CENTER);
		infoPoper.setSpacing(5);
		infoPoper.setPadding(new Insets(5));

	}

	@FXML
	public void onGeneralAction(ActionEvent event) {
		Button boton = (Button) event.getSource();
		System.out.println(boton.getStyleClass().toString());
		// Si Losa Oscura
		if (boton.getStyleClass().contains("LosaOscura")) {
			boton.getStyleClass().remove("LosaOscura");
			BackType tipofondo = (BackType) boton.getUserData();

			character.getCharacter().setVida(character.getCharacter().getVida() - (numMonster * nivel));

			if (tipofondo != BackType.Monster) {
				boton.getStyleClass().add(tipofondo.toString());
			} else {
				numMonster++;
				MonsterType tipo = JokoUtils.generarMonstruo();
				boton.getStyleClass().addAll(tipo.toString(), BackType.Monster.toString());
				boton.setUserData(new Monster(tipo));
			}
		}
		// Si Escalera
		else if (boton.getStyleClass().contains("Escaleras")) {
			JokoUtils.setEscalera(false);
			numMonster = 0;
			root.setCenter(JokoUtils.generarNivel(++nivel, this));
			// Si Monstruo
		} else if (boton.getStyleClass().contains("Monster")) {
			Monster monster = (Monster) boton.getUserData();
			// intentar hacerlo con bindeos
//			character.getCharacter().vidaProperty().subtract(monster.getDanyo());
			int vidaMonster = monster.getVida() - character.getCharacter().getDanyo();
			monster.setVida(vidaMonster);
			List<Node> nodos = infoPoper.getChildren();
			Label aux = (Label) nodos.get(1);
			aux.setText(vidaMonster + "");

			if (monster.getVida() <= 0) {
				numMonster--;

				boton.getStyleClass().removeAll(monster.getNombre(), "Monster");
				boton.getStyleClass().add("Losa");
				poper.hide();

			} else {
				character.getCharacter().setVida(character.getCharacter().getVida() - (monster.getDanyo()));
			}
		} else if (boton.getStyleClass().contains("Cofre")) {
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
			item1.setOnAction(e -> onGetChestItemAction(e, a));
			Button item2 = new Button();
			item2.setPrefHeight(40);
			item2.setPrefWidth(40);
			item2.getStyleClass().add("damage");
			item2.setOnAction(e -> onGetChestItemAction(e, a));
			Button item3 = new Button();
			item3.setPrefHeight(40);
			item3.setPrefWidth(40);
			item3.getStyleClass().add("armor");
			item3.setOnAction(e -> onGetChestItemAction(e, a));
			HBox botonera = new HBox(item1, item2, item3);
			botonera.setAlignment(Pos.CENTER);
			botonera.setSpacing(5);
			botonera.setPadding(new Insets(5));
			a.setContentNode(botonera);

			a.show(boton);
		}

		if (character.getCharacter().getVida() <= 0) {
			nivel = 1;
			numMonster = 0;
			puerta = false;
			CharacterBoxController.enablePotion(character.getPotionButton1());
			CharacterBoxController.enablePotion(character.getPotionButton2());
			CharacterBoxController.enablePotion(character.getPotionButton3());
			root.setCenter(JokoUtils.generarNivel(nivel, this));
			character.getCharacter().setVida(100);
			character.getCharacter().setDanyo(10);
			character.getCharacter().setVidamax(100);
		}

	}

	private void onGetChestItemAction(ActionEvent event, PopOver a) {
		Button boton = (Button) event.getSource();

		if (boton.getStyleClass().contains("pocion")) {

			if (character.getPotionButton1().isDisabled()) {
				CharacterBoxController.enablePotion(character.getPotionButton1());
			} else if (character.getPotionButton2().isDisabled()) {
				CharacterBoxController.enablePotion(character.getPotionButton2());
			} else if (character.getPotionButton3().isDisabled()) {
				CharacterBoxController.enablePotion(character.getPotionButton3());
			}
			a.hide();
		}else if (boton.getStyleClass().contains("damage")) {
			character.getCharacter().setDanyo(character.getCharacter().getDanyo()+1);
			a.hide();
		}else if (boton.getStyleClass().contains("armor")) {
			character.getCharacter().setVidamax(character.getCharacter().getVidamax()+10);
			a.hide();
		}

	}

	public void onMonsterInformation(MouseEvent e) {
		Button boton = (Button) e.getSource();
		if (boton.getStyleClass().contains("Monster")) {
			Monster monster = (Monster) boton.getUserData();
			List<Node> nodos = infoPoper.getChildren();
			Label aux = (Label) nodos.get(1);
			Label aux2 = (Label) nodos.get(3);
			aux.setText(monster.getVida() + "");
			aux2.setText(monster.getDanyo() + "");
			poper.setContentNode(infoPoper);
			poper.show(boton);
		}

	}

	public void onMonsterInformationExited(MouseEvent e) {
		Button boton = (Button) e.getSource();
		if (boton.getStyleClass().contains("Monster")) {
			poper.hide();
		}
	}
	


}
