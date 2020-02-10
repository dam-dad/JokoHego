package dad.jokohego.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

public class MenuController implements Initializable {
	
	@FXML
    private BorderPane root;

    @FXML
    private Button jugarButton;

    @FXML
    private Button ayudaButton;

    @FXML
    private Button salirButton;
    
    
    //controllers
    
    JuegoController juego = new JuegoController();

    public BorderPane getView() {
    	return root;
    }

	public MenuController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MenuView.fxml"));
		loader.setController(this);
		loader.load();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}
	
    @FXML
    void onAyudaAction(ActionEvent event) {

    }

    @FXML
    void onJugarAction(ActionEvent event) {
    	try {
			FXMLLoader loader = new FXMLLoader(getClass()
			        .getResource("/fxml/GameView.fxml"));
			loader.setController(juego);
			loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	root.getChildren().setAll(juego.getView());
    	
    }

    @FXML
    void onSalirAction(ActionEvent event) {

    }


}
