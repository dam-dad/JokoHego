package dad.jokohego.app;

import dad.jokohego.controllers.JuegoController;
import dad.jokohego.controllers.MenuController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class JokoHegoApp extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		MenuController juego = new MenuController();
		
		Scene scene = new Scene(juego.getView(),900,700);
		primaryStage.setResizable(false);
		primaryStage.setScene(scene);
		primaryStage.setTitle("JokoHego");
		primaryStage.show();
		
	}

	public static void main(String[] args) {
		launch(args);
	}

}
