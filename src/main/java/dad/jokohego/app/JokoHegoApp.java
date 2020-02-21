package dad.jokohego.app;

import dad.jokohego.controllers.MainController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class JokoHegoApp extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		MainController juego = new MainController();
		
		Scene scene = new Scene(juego.getView(),900,700);
//		primaryStage.getIcons().add(new Image(this.getClass().getResource("/Items/Libro.png").getPath()));
		primaryStage.setResizable(false);
		primaryStage.setScene(scene);
		primaryStage.setTitle("JokoHego");
		primaryStage.show();
		
	}

	public static void main(String[] args) {
		launch(args);
	}

}
