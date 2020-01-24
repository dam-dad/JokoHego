package dad.jokohego.app;

import dad.jokohego.controllers.JuegoController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class JokoHegoApp extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		JuegoController juego = new JuegoController();
		
		Scene scene = new Scene(juego.getView(),650,500);
		primaryStage.setResizable(false);
		primaryStage.setScene(scene);
		primaryStage.setTitle("JokoHego");
		primaryStage.show();
		
	}

	public static void main(String[] args) {
		launch(args);
	}

}
