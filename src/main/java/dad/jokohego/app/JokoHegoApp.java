package dad.jokohego.app;

import dad.jokohego.controllers.MainController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class JokoHegoApp extends Application {
	private static Stage stage;

	@Override
	public void start(Stage primaryStage) throws Exception {
		stage = primaryStage;
		MainController juego = new MainController();
		Scene scene = new Scene(juego.getView(), 900, 700);
		primaryStage.getIcons()
				.add(new Image(this.getClass().getResource("/ImagenesGreenStyle/Items/Libro.png").toString()));
		primaryStage.setResizable(false);
		primaryStage.setScene(scene);
		primaryStage.setTitle("JokoHego");
		primaryStage.show();

	}

	public static Stage getStage() {
		return stage;
	}

	public static void main(String[] args) {
		launch(args);
	}

}
