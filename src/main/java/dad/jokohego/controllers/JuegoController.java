package dad.jokohego.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dad.jokohego.utils.BackType;
import dad.jokohego.utils.JokoUtils;
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

	private Button[][] backButton;
	private BackType[][] backType;
	private int nivel = 1;

	// view
	
	@FXML
	private BorderPane root;

	@FXML
	private GridPane panel;

	@FXML
	private Button button42;

	@FXML
	private Button button40;

	@FXML
	private Button button30;

	@FXML
	private Button button20;

	@FXML
	private Button button10;

	@FXML
	private Button button13;

	@FXML
	private Button button03;

	@FXML
	private Button button12;

	@FXML
	private Button button02;

	@FXML
	private Button button11;

	@FXML
	private Button button01;

	@FXML
	private Button button41;

	@FXML
	private Button button23;

	@FXML
	private Button button22;

	@FXML
	private Button button21;

	@FXML
	private Button button33;

	@FXML
	private Button button32;

	@FXML
	private Button button31;

	@FXML
	private Button button43;

	@FXML
	private Button button53;

	@FXML
	private Button button52;

	@FXML
	private Button button51;

	@FXML
	private Button button50;

	@FXML
	private Button button14;

	@FXML
	private Button button04;

	@FXML
	private Button button54;

	@FXML
	private Button button44;

	@FXML
	private Button button34;

	@FXML
	private Button button24;

	@FXML
	private Button button45;

	@FXML
	private Button button35;

	@FXML
	private Button button25;

	@FXML
	private Button button15;

	@FXML
	private Button button05;

	@FXML
	private Button button00;

	@FXML
	private Button button55;

	@FXML
	private ImageView personajeImage;

	@FXML
	private Label vida;

	@FXML
	private Label vidatotal;

	@FXML
	private Label danyoLabel;

	@FXML
	private Label armaduraLabel;

	@FXML
	private ImageView espadaImage;

	@FXML
	private ImageView armaduraImage;

	@FXML
	private Button pocionButton1;

	@FXML
	private Button pocionButton2;

	@FXML
	private Button pocionButton3;

	public JuegoController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/psiblevista.fxml"));
		loader.setController(this);
		loader.load();
	}

	public BorderPane getView() {
		return root;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		root.setCenter(JokoUtils.generarNivel(8));
		backButton = JokoUtils.getButton();
		backType = JokoUtils.getBackType();

	}

	@FXML
	void onGeneralAction(ActionEvent event) {

	}

	@FXML
	void onPocionAction(ActionEvent event) {
		if(event.getSource().equals(pocionButton1)) {
			if(pocionButton1.getStyleClass().contains("pocionvacia")) {
				pocionButton1.getStyleClass().remove("pocionvacia");
				pocionButton1.getStyleClass().add("pocion");
			}else {
				pocionButton1.getStyleClass().remove("pocion");
				pocionButton1.getStyleClass().add("pocionvacia");
			}
		}
		if(event.getSource().equals(pocionButton2)) {
			if(pocionButton2.getStyleClass().contains("pocionvacia")) {
				pocionButton2.getStyleClass().remove("pocionvacia");
				pocionButton2.getStyleClass().add("pocion");
			}else {
				pocionButton2.getStyleClass().remove("pocion");
				pocionButton2.getStyleClass().add("pocionvacia");
			}
		}
		if(event.getSource().equals(pocionButton3)) {
			if(pocionButton3.getStyleClass().contains("pocionvacia")) {
				pocionButton3.getStyleClass().remove("pocionvacia");
				pocionButton3.getStyleClass().add("pocion");
			}else {
				pocionButton3.getStyleClass().remove("pocion");
				pocionButton3.getStyleClass().add("pocionvacia");
			}
		}
		System.out.println(event.getSource().toString());
		
	}

}
