package dad.jokohego.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dad.jokohego.model.Personaje;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.util.converter.NumberStringConverter;

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
	private Label armorLabel;

	@FXML
	private Label moneyLabel;

	@FXML
	private ImageView swordImage;

	@FXML
	private ImageView armorImage;

	@FXML
	private Button potionButton1;

	@FXML
	private Button potionButton2;

	@FXML
	private Button potionButton3;

	// model

	private Personaje character = new Personaje();

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
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		Bindings.bindBidirectional(healthLabel.textProperty(), character.vidaProperty(),
				new NumberStringConverter("###"));
		Bindings.bindBidirectional(damageLabel.textProperty(), character.danyoProperty(),
				new NumberStringConverter("###"));
		Bindings.bindBidirectional(armorLabel.textProperty(), character.armaduraProperty(),
				new NumberStringConverter("###"));
		Bindings.bindBidirectional(moneyLabel.textProperty(), character.dineroProperty(),
				new NumberStringConverter("###"));
		character.hombreProperty().addListener((o,ov,nv) -> {
			if (nv) {
				characterImage.setImage(
						new Image(this.getClass().getResourceAsStream("/ImagenesGreenStyle/Personajes/Hombre.png")));
			} else {
				characterImage.setImage(
						new Image(this.getClass().getResourceAsStream("/ImagenesGreenStyle/Personajes/Mujer.png")));
			}
		});
		
	}

	@FXML
	void onPocionAction(ActionEvent event) {
		character.setVida(100);
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

}
