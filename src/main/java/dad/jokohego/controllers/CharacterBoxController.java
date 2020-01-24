package dad.jokohego.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dad.jokohego.model.Personaje;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class CharacterBoxController implements Initializable {
	
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
    private ImageView swordImage;

    @FXML
    private ImageView armorImage;

    @FXML
    private Button potionButton1;

    @FXML
    private Button potionButton2;

    @FXML
    private Button potionButton3;
    
    //model
    
    private Personaje character = new Personaje();
	
	public CharacterBoxController() {
		super();
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/psiblevista.fxml"));
			loader.setController(this);
			loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}
	
	@FXML
    void onPocionAction(ActionEvent event) {
		
    }

}
