package JokoHego.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

public class JuegoController implements Initializable {

	
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
