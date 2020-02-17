package dad.jokohego.utils;


import dad.jokohego.controllers.JuegoController;
import javafx.animation.Interpolator;
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class Animations {

	
	public static void Obtainexperience(Button button,Pane root) {
		
		ParallelTransition secuencia = new ParallelTransition();
		TranslateTransition translate = new TranslateTransition();
		ScaleTransition scale = new ScaleTransition();



		ImageView vida = new ImageView(
				new Image(JuegoController.class.getResourceAsStream("/ImagenesGreenStyle/Items/Vida.png")));
		root.getChildren().add(vida);
		

		translate.setDuration(Duration.seconds(2.5));
		translate.setFromX(button.getLayoutX()+button.getWidth()/2);
		translate.setFromY(button.getLayoutY()+button.getHeight()/2);
		translate.setToX(800);
		translate.setToY(530);
		translate.setNode(vida);
		translate.setInterpolator(Interpolator.EASE_BOTH);
		translate.setAutoReverse(false);
		
		scale.setAutoReverse(true);
		scale.setCycleCount(1);
		scale.setDelay(Duration.ZERO);
		scale.setDuration(Duration.seconds(2.40));
		scale.setFromX(10);
		scale.setToX(0);
		scale.setFromY(10);
		scale.setToY(0);
		scale.setNode(vida);
		scale.setInterpolator(Interpolator.EASE_BOTH);
		

    	secuencia.setAutoReverse(true);
    	secuencia.setCycleCount(1);
    	secuencia.getChildren().addAll(translate, scale);
    	secuencia.play();
	}

}
