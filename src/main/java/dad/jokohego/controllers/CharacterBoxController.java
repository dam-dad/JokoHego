package dad.jokohego.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.controlsfx.control.PopOver;
import org.controlsfx.control.PopOver.ArrowLocation;

import dad.jokohego.model.Personaje;
import dad.jokohego.utils.Sounds;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.converter.NumberStringConverter;


/**
 * 
 * 	Componente del personaje.	
 * 
 *  @author SERGIO GARCÍA DELGADO
 * 	@author AMARO YANES CABRERA
 */
public class CharacterBoxController extends VBox implements Initializable {

	@FXML
	private VBox root;

	@FXML
	private ImageView characterImage;

	@FXML
	private Label healthLabel;

	@FXML
	private Label totalhealthLabel;

	@FXML
	private Label damageLabel;

	@FXML
    private Label experienciaLabel;

	@FXML
	private Button potionButton1;

	@FXML
	private Button potionButton2;

	@FXML
	private Button potionButton3;

	@FXML
	private Button lvlUpButton;

	// model

	private static Personaje character = new Personaje();

	public Personaje getCharacter() {
		return character;
	}

	public CharacterBoxController() {
		super();
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/CharacterBox.fxml"));
			loader.setController(this);
			loader.setRoot(this);
			loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		character.setDanyo(10);
		character.setVida(100);
		character.setVidamax(100);
		character.setNivel(1);
		character.setExperiencia(0);
		enablePotion(potionButton1);
		enablePotion(potionButton2);
		enablePotion(potionButton3);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		lvlUpButton.getStyleClass().clear();
		lvlUpButton.getStyleClass().add("lvldisabled");
		potionButton1.getStyleClass().clear();
		potionButton2.getStyleClass().clear();
		potionButton3.getStyleClass().clear();
		
		
		
		// comprobar bindeos
		Bindings.bindBidirectional(healthLabel.textProperty(), character.vidaProperty(),
				new NumberStringConverter("###"));
		Bindings.bindBidirectional(damageLabel.textProperty(), character.danyoProperty(),
				new NumberStringConverter("###"));
		Bindings.bindBidirectional(totalhealthLabel.textProperty(), character.vidamaxProperty(),
				new NumberStringConverter("###"));
		Bindings.bindBidirectional(experienciaLabel.textProperty(), character.experienciaProperty(),
				new NumberStringConverter("###"));
		
		lvlUpButton.setDisable(true);
		
		character.experienciaProperty().addListener((o,ov,nv)->{
			if(character.getExperiencia()>=(10*character.getNivel())) {
				lvlUpButton.getStyleClass().clear();
				lvlUpButton.getStyleClass().add("lvlenabled");
				lvlUpButton.setDisable(false);
			}else {
				lvlUpButton.getStyleClass().clear();
				lvlUpButton.getStyleClass().add("lvldisabled");
				lvlUpButton.setDisable(true);
			}
		});

	}

	/**
	 * 
	 * Consume una poción.
	 * 
	 * @param event
	 */
	
	@FXML
	void onPocionAction(ActionEvent event) {

		if (event.getSource().equals(potionButton1)) {
			disablePotion(potionButton1);
		}
		if (event.getSource().equals(potionButton2)) {
			disablePotion(potionButton2);
		}
		if (event.getSource().equals(potionButton3)) {
			disablePotion(potionButton3);
		}
	}

	/**
	 * 
	 * Muestra un popover con las opciones de mejorar daño o la vida.
	 * 
	 * @param event
	 */
	
	@FXML
	void onlvlUpAction(ActionEvent event) {
		Button boton = (Button) event.getSource();
		
		PopOver a = new PopOver();
		a.setTitle("");
		a.getStyleClass().clear();
		a.getStyleClass().add("popup");
		a.setArrowLocation(ArrowLocation.TOP_CENTER);
		a.setAnchorX(300);
		a.setAnchorY(250);
		a.setArrowSize(10);
		//a.detach();
		a.setAnimated(false);
		a.requestFocus();

		Button item1 = new Button();  
		item1.setPrefHeight(40);
		item1.setPrefWidth(40);
		item1.getStyleClass().clear();
		item1.getStyleClass().add("damage");
		item1.setOnAction(e -> onSkillSelectedAction(e, a));
		Button item2 = new Button();
		item2.setPrefHeight(40);
		item2.setPrefWidth(40);
		item2.getStyleClass().clear();
		item2.getStyleClass().add("vida");
		item2.setOnAction(e -> onSkillSelectedAction(e, a));
		HBox botonera = new HBox(item1, item2);
		botonera.setAlignment(Pos.CENTER);
		botonera.setSpacing(5);
		botonera.setPadding(new Insets(5));
		a.setContentNode(botonera);

		a.show(boton);
	}
	
	private void onSkillSelectedAction(ActionEvent event, PopOver a) {
		Button boton = (Button) event.getSource();
		
		if(boton.getStyleClass().contains("damage")) {
			
			character.setDanyo(character.getDanyo()+1);
		}
		if(boton.getStyleClass().contains("vida")) {
			character.setVidamax(character.getVidamax()+3);
		}
		character.setNivel(character.getNivel()+1);
		character.setExperiencia(character.getExperiencia()-10*(character.getNivel()-1));
		
		a.hide();
		
	}

	public static void disablePotion(Button boton) {
		boton.getStyleClass().remove("pocion");
		boton.getStyleClass().add("pocionvacia");
		boton.setDisable(true);
		Sounds.playEffectSound("drink");
		character.setVida(character.getVidamax());
	}

	public static void enablePotion(Button boton) {
		boton.getStyleClass().remove("pocionvacia");
		boton.getStyleClass().add("pocion");
		boton.setDisable(false);
	}

	public Button getPotionButton1() {
		return potionButton1;
	}

	public Button getPotionButton2() {
		return potionButton2;
	}

	public Button getPotionButton3() {
		return potionButton3;
	}

	public Button getLvlUpButton() {
		return lvlUpButton;
	}

}
