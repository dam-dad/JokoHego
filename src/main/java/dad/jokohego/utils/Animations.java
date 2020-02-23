package dad.jokohego.utils;


import dad.jokohego.controllers.JuegoController;
import dad.jokohego.controllers.MainController;
import dad.jokohego.controllers.MenuController;
import javafx.animation.Interpolator;
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class Animations {

	
	public static void Obtainexperience(Button button,Pane root) {
		
		SequentialTransition secuencia2 = new SequentialTransition();
		ParallelTransition secuencia = new ParallelTransition();
		TranslateTransition translate = new TranslateTransition();
		ScaleTransition scale = new ScaleTransition();
		ScaleTransition scale2 = new ScaleTransition();




		ImageView vida = new ImageView(
				new Image(JuegoController.class.getResourceAsStream("/ImagenesGreenStyle/Items/exp.png")));
		root.getChildren().add(vida);
		

		translate.setDuration(Duration.seconds(0.5));
		translate.setFromX(button.getLayoutX()+button.getWidth()/2);
		translate.setFromY(button.getLayoutY()+button.getHeight()/2);
		translate.setToX(790);
		translate.setToY(523);
		translate.setNode(vida);
		translate.setInterpolator(Interpolator.EASE_BOTH);
		translate.setAutoReverse(false);
		
		scale.setAutoReverse(true);
		scale.setCycleCount(1);
		scale.setDelay(Duration.ZERO);
		scale.setDuration(Duration.seconds(0.50));
		scale.setFromX(5);
		scale.setToX(2);
		scale.setFromY(5);
		scale.setToY(2);
		scale.setNode(vida);
		scale.setInterpolator(Interpolator.EASE_BOTH);
		
		
		scale2.setAutoReverse(true);
		scale2.setCycleCount(1);
		scale2.setDelay(Duration.ZERO);
		scale2.setDuration(Duration.seconds(0.2));
		scale2.setFromX(2);
		scale2.setToX(0);
		scale2.setFromY(2);
		scale2.setToY(0);
		scale2.setNode(vida);
		scale2.setInterpolator(Interpolator.EASE_BOTH);
		
		

    	secuencia.setAutoReverse(true);
    	secuencia.setCycleCount(1);
    	secuencia.getChildren().addAll(translate, scale);
    	
    	secuencia2.getChildren().addAll(secuencia,scale2);
    	secuencia2.setCycleCount(0);
    	
    	secuencia2.play();
	}
	
	public static void doorsAnimation(Node nodo,MainController main) {
		SequentialTransition transicion = new SequentialTransition();
		TranslateTransition translate = new TranslateTransition();
		if (nodo.getId().equals("puertauno")) {

			translate.setDuration(Duration.seconds(2));
			translate.setFromX(0);
			translate.setToX(-300);
			translate.setNode(nodo);
			translate.setInterpolator(Interpolator.EASE_BOTH);
			translate.setAutoReverse(false);

			transicion = new SequentialTransition();
			transicion.getChildren().addAll(translate);
			transicion.setAutoReverse(false);

			transicion.setCycleCount(1);

			transicion.play();

		} else if (nodo.getId().equals("puertados")) {

			translate.setDuration(Duration.seconds(2));
			translate.setFromX(0);
			translate.setToX(300);
			translate.setNode(nodo);
			translate.setInterpolator(Interpolator.EASE_BOTH);
			translate.setAutoReverse(false);

			transicion = new SequentialTransition();
			transicion.getChildren().addAll(translate);
			transicion.setAutoReverse(false);

			transicion.setCycleCount(1);

			transicion.play();
		}
		
		
		transicion.currentTimeProperty().addListener((o,ov,nv)->{
			if(nv.equals(Duration.seconds(2))) {
				main.setJuego();	
			}
		});	
	}

}
