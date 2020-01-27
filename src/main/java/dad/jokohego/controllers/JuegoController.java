package dad.jokohego.controllers;

import java.awt.Point;
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

	private static Button[][] backButton;
	private static BackType[][] backType;
	private static int nivel = 1;

	// view
	
	@FXML
	private BorderPane root;


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
		root.setCenter(JokoUtils.generarNivel(2));
		backButton = JokoUtils.getButton();
		backType = JokoUtils.getBackType();

	}

	@FXML
	public static void onGeneralAction(ActionEvent event) {
		Button boton = (Button) event.getSource();
		Point coordenadas = (Point) boton.getUserData();
		if (boton.getStyleClass().contains("LosaOscura")) {
			
			boton.getStyleClass().remove("LosaOscura");
			BackType tipofondo = backType[(int) coordenadas.getX()][(int) coordenadas.getY()];
			if (tipofondo != BackType.Monster) {
				System.out.println(tipofondo.toString());
				boton.getStyleClass().add(tipofondo.toString());
			} else {
				System.out.println(JokoUtils.generarMonstruo().toString());
				boton.getStyleClass().add(JokoUtils.generarMonstruo().toString());

			} 
		}
		
	}

	@FXML
	void onPocionAction(ActionEvent event) {
//		if(event.getSource().equals(pocionButton1)) {
//			if(pocionButton1.getStyleClass().contains("pocionvacia")) {
//				pocionButton1.getStyleClass().remove("pocionvacia");
//				pocionButton1.getStyleClass().add("pocion");
//			}else {
//				pocionButton1.getStyleClass().remove("pocion");
//				pocionButton1.getStyleClass().add("pocionvacia");
//			}
//		}
//		if(event.getSource().equals(pocionButton2)) {
//			if(pocionButton2.getStyleClass().contains("pocionvacia")) {
//				pocionButton2.getStyleClass().remove("pocionvacia");
//				pocionButton2.getStyleClass().add("pocion");
//			}else {
//				pocionButton2.getStyleClass().remove("pocion");
//				pocionButton2.getStyleClass().add("pocionvacia");
//			}
//		}
//		if(event.getSource().equals(pocionButton3)) {
//			if(pocionButton3.getStyleClass().contains("pocionvacia")) {
//				pocionButton3.getStyleClass().remove("pocionvacia");
//				pocionButton3.getStyleClass().add("pocion");
//			}else {
//				pocionButton3.getStyleClass().remove("pocion");
//				pocionButton3.getStyleClass().add("pocionvacia");
//			}
//		}
		System.out.println(event.getSource().toString());
		
	}


}
