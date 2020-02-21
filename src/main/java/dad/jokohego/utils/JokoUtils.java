package dad.jokohego.utils;

import java.awt.Point;

import dad.jokohego.controllers.JuegoController;
import dad.jokohego.model.Monster;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class JokoUtils {
	// los botones tienen en el  userData un tipo BackType
	private static Button[][] button;
	private static boolean escalera;
	private static double nivel;
	private static BackType[][] backType;
	private static GridPane buttons;

	public static GridPane generarNivel(int nivel, JuegoController jg) {
		JokoUtils.nivel = nivel;
		escalera = false;
		int size = nivel + 4;
		button = new Button[size + 1][size + 1];
		backType = new BackType[size + 1][size + 1];
		buttons = new GridPane();

		// generar el gridpane dinamico
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				
				button[i][j] = new Button();
				button[i][j].getStyleClass().clear();
				button[i][j].setUserData(new Point(i,j));
				button[i][j].setOnAction(e -> jg.onGeneralAction(e));
				button[i][j].setOnMouseEntered(e -> jg.onMonsterInformation(e));
				button[i][j].setOnMouseExited(e -> jg.onMonsterInformationExited(e));
				buttons.add(button[i][j], i + 1, j + 1);
				button[i][j].setMaxWidth(Integer.MAX_VALUE);
				button[i][j].setMaxHeight(Integer.MAX_VALUE);
				button[i][j].getStyleClass().add("LosaOscura");
				
				backType[i][j] = generarFondo();
			}

		}
		if (!escalera)
			backType[size - 1 ][size - 1] = BackType.Escaleras;
		// Calcular niveles de peligrosidad
		int danger;
		for (int i = 0; i < size; i++) {
			danger = 0;
			for (int j = 0; j < size; j++) {
				if (backType[i][j].equals(BackType.Monster))
					danger++;

			}
			VBox aux = new VBox();
			aux.getStyleClass().add("danger");
			aux.getChildren().add(new Label(danger + ""));
			aux.setAlignment(Pos.CENTER);
			buttons.add(aux, i + 1, 0);
			danger = 0;
			for (int j = 0; j < size; j++) {
				if (backType[j][i].equals(BackType.Monster))
					danger++;
			}
			VBox aux2 = new VBox();
			aux2.getChildren().add(new Label(danger + ""));
			aux2.getStyleClass().add("danger");
			aux2.setAlignment(Pos.CENTER);
			buttons.add(aux2, 0, i + 1);

			
			ColumnConstraints conCol = new ColumnConstraints();
			conCol.setHgrow(Priority.ALWAYS);
			RowConstraints conRow = new RowConstraints();
			conRow.setVgrow(Priority.ALWAYS);
			buttons.getColumnConstraints().add(conCol);
			buttons.getRowConstraints().add(conRow);
		}
		VBox aux3 = new VBox();
		aux3.getChildren().add(new Label("" + nivel));
		aux3.getStyleClass().add("nivel");
		aux3.setAlignment(Pos.CENTER);
		buttons.add(aux3, 0, 0);
		
		
		ColumnConstraints conCol = new ColumnConstraints();
		conCol.setHgrow(Priority.ALWAYS);
		RowConstraints conRow = new RowConstraints();
		conRow.setVgrow(Priority.ALWAYS);
		buttons.getColumnConstraints().add(conCol);
		buttons.getRowConstraints().add(conRow);
		
		
		return buttons;
	}

	public static BackType generarFondo() {
		BackType[] bt = BackType.values();
		BackType fondo = BackType.Losa;
		int num = (int) ((Math.random() * 100) * (nivel / 10 + 1));
		if (num < 5) {
			if (escalera) {
				fondo = BackType.Losa;
			} else {
				fondo = BackType.Escaleras;
				escalera = true;
			}
		} else if (num <= 40 && num >= 35) {
			fondo = BackType.Cofre;
		} else if (num > 90) {
			fondo = BackType.Monster;
		}
		return fondo;
		// (hasta-desde+1)+desde
	}

	public static MonsterType generarMonstruo() {
		MonsterType monster = null;

		MonsterType[] mt = MonsterType.values();
		int num = (int) ((Math.random() * 100) + (nivel * 5));

		if (num < 40) {
			monster = MonsterType.Rata;
		} else if (num >= 40 && num < 60) {
			monster = MonsterType.Minik;
		} else if (num >= 60 && num < 100) {
			monster = MonsterType.Serpiente;
		} else if (num >= 100 && num < 120) {
			monster = MonsterType.Orco;
		} else if (num >= 120 && num < 140) {
			monster = MonsterType.Phantom;
		} else
			monster = MonsterType.Cutulu;

		if (num == 80)
			monster = MonsterType.EscarabajoOro;
		return monster;
	}
	public static void setEscalera(boolean escalera) {
		JokoUtils.escalera = escalera;
	}

	public static boolean isEscalera() {
		return escalera;
	}

	public static Button[][] getButton() {
		return button;
	}

	public static BackType[][] getBackType() {
		return backType;
	}

	public static GridPane getButtons() {
		return buttons;
	}
	
	

}
