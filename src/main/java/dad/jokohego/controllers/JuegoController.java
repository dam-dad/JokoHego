package dad.jokohego.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dad.jokohego.utils.BackType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

public class JuegoController implements Initializable {

	//model
	
	private Button[][] backButton = new Button[6][6];
	private BackType[][] backType = new BackType[6][6];
	private int nivel = 1;
	
	
	
	//view
	@FXML
    private BorderPane root;

	
	public JuegoController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/psiblevista.fxml"));
		loader.setController(this);
		loader.load();
	}	
	
	public BorderPane getView(){
		return root;
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}
	
	

}
