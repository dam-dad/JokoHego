package dad.jokoheo.tests;

import dad.jokohego.controllers.CharacterBoxController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PruebaApp extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		CharacterBoxController a = new CharacterBoxController();
		a.getCharacter().setDanyo(100);
		a.getCharacter().setVida(0);
		
		
		
		Scene scene = new Scene(a, 320, 200);

		primaryStage.setTitle("Custom componetns test app");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);

	}

}
