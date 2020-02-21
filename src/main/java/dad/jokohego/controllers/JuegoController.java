package dad.jokohego.controllers;

import java.awt.Point;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.controlsfx.control.PopOver;
import org.controlsfx.control.PopOver.ArrowLocation;

import dad.jokohego.model.Monster;
import dad.jokohego.utils.Animations;
import dad.jokohego.utils.BackType;
import dad.jokohego.utils.JokoUtils;
import dad.jokohego.utils.MonsterType;
import dad.jokohego.utils.Sounds;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
public class JuegoController implements Initializable {

	// model

	private static int nivel = 1;
	private static int numMonster = 0;
	private static BackType[][] backType;
	private static GridPane buttons;

	// view

	@FXML
	private StackPane megaroot;

	@FXML
	private BorderPane root;

	MainController main;

	private PopOver poper = new PopOver();
	private HBox infoPoper;
	private CharacterBoxController character = new CharacterBoxController();

	public JuegoController(MainController mainController) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/GameView.fxml"));
		loader.setController(this);
		loader.load();
		main = mainController;
	}

	public StackPane getView() {
		return megaroot;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		iniciarNivel();
		
		root.setRight(character);

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
		
//		root.setOnMouseMoved(new EventHandler<MouseEvent>() {
//			public void handle(MouseEvent event) {
//				String msg = "(x: " + event.getX() + ", y: " + event.getY() + ") -- " + "(sceneX: " + event.getSceneX()
//						+ ", sceneY: " + event.getSceneY() + ") -- " + "(screenX: " + event.getScreenX() + ", screenY: "
//						+ event.getScreenY() + ")";
//
//				System.out.println(msg);
//			}
//		});
		

	}

	@FXML
	public void onGeneralAction(ActionEvent event) {
		Button boton = (Button) event.getSource();
		System.out.println(boton.getStyleClass().toString());
		// Si Losa Oscura
		if (boton.getStyleClass().contains("LosaOscura")) {
			boton.getStyleClass().remove("LosaOscura");
			Point coordenadas = (Point) boton.getUserData();
			BackType tipofondo = backType[coordenadas.x][coordenadas.y];

			character.getCharacter().setVida(character.getCharacter().getVida() - (numMonster * nivel));

			if (tipofondo != BackType.Monster) {
				boton.getStyleClass().add(tipofondo.toString());
			} else {
				numMonster++;
				MonsterType tipo = JokoUtils.generarMonstruo();
				boton.getStyleClass().addAll(tipo.toString(), BackType.Monster.toString());
				Object[] userdata = new Object[2];
				userdata[0] = coordenadas;
				userdata[1] = new Monster(tipo);
				Sounds.playEffectSound(tipo.toString());
				boton.setUserData(userdata);
			}
		}
		// Si Escalera
		else if (boton.getStyleClass().contains("Escaleras")) {
			JokoUtils.setEscalera(false);
			numMonster = 0;
			nivel++;
			iniciarNivel();		
			// Si Monstruo
		} else if (boton.getStyleClass().contains("Monster")) {
			Object[] userdata= (Object[]) boton.getUserData();
			Point coordenadas = (Point)userdata[0];
			Monster monster = (Monster)userdata[1];
			// intentar hacerlo con bindeos
//			character.getCharacter().vidaProperty().subtract(monster.getDanyo());
			int vidaMonster = monster.getVida() - character.getCharacter().getDanyo();
			monster.setVida(vidaMonster);
			List<Node> nodos = infoPoper.getChildren();
			Label aux = (Label) nodos.get(1);
			aux.setText(vidaMonster + "");

			if (monster.getVida() <= 0) {
				
				numMonster--;
				
				VBox vuno = (VBox)getNode(0,coordenadas.x+1);
				Label uno = (Label) vuno.getChildren().get(0);
				uno.setText((Integer.parseInt(uno.getText())-1)+"");
				VBox vdos = (VBox)getNode(coordenadas.y+1,0);
				Label dos = (Label) vdos.getChildren().get(0);
				dos.setText((Integer.parseInt(dos.getText())-1)+"");
				
				
				character.getCharacter()
						.setExperiencia(character.getCharacter().getExperiencia() + monster.getExperiencia());
				boton.getStyleClass().removeAll(monster.getNombre(), "Monster");
				boton.getStyleClass().add("Losa");
				poper.hide();
				
				Animations.Obtainexperience(boton,megaroot);
				Sounds.playEffectSound("experience1");
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
			a.setAnimated(false);

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
			item3.getStyleClass().add("vida");
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
			character = new CharacterBoxController();
			iniciarNivel();
			try {
				main.setMenu();
			} catch (Exception e2) {
			}

		}

	}
	
	private void iniciarNivel() {
		root.setCenter(JokoUtils.generarNivel(nivel, this));
		backType = JokoUtils.getBackType();
		buttons = JokoUtils.getButtons();
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
		} else if (boton.getStyleClass().contains("damage")) {
			character.getCharacter().setDanyo(character.getCharacter().getDanyo() + 1);
			a.hide();
		} else if (boton.getStyleClass().contains("vida")) {
			character.getCharacter().setVidamax(character.getCharacter().getVidamax() + 10);
			a.hide();
		}

	}

	public void onMonsterInformation(MouseEvent e) {
		Button boton = (Button) e.getSource();
		if (boton.getStyleClass().contains("Monster")) {
			Object[] userdata= (Object[]) boton.getUserData();
			Monster monster = (Monster)userdata[1];
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
	
	
	private Node getNode ( int row,  int column) {
	    Node result = null;
	    ObservableList<Node> childrens = buttons.getChildren();

	    for (Node node : childrens) {
	        if(GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == column) {
	            result = node;
	            break;
	        }
	    }

	    return result;
	}
	
	

	

}
