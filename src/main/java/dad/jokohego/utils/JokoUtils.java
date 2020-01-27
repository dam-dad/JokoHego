package dad.jokohego.utils;

import java.awt.Point;

import dad.jokohego.controllers.JuegoController;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;

public class JokoUtils {
	//los botones tienen un objeto point en el userData para determinar la posicion en el array bidimensional
	private static Button[][] button;
	private static BackType[][] backType;
	
	
	public static GridPane generarNivel(int nivel) {
		int size = nivel + 4;
		button = new Button[size+1][size+1];
		backType = new BackType[size+1][size+1];;
		GridPane buttons = new GridPane();
		
		ColumnConstraints conCol =  new ColumnConstraints();
		conCol.setHgrow(Priority.ALWAYS);
		RowConstraints conRow = new RowConstraints();
		conRow.setVgrow(Priority.ALWAYS);
		
		for(int i = 0;i < size;i++ ) {
			for(int j = 0; j < size;j++) {
				button[i][j] = new Button();
				button[i][j].setUserData(new Point(i,j));
				button[i][j].setOnAction(e -> JuegoController.onGeneralAction(e));
				buttons.add(button[i][j],i,j);
				button[i][j].setMaxWidth(Integer.MAX_VALUE);
				button[i][j].setMaxHeight(Integer.MAX_VALUE);
				button[i][j].getStyleClass().add("LosaOscura");

				backType[i][j] = generarFondo();
			}
			buttons.getColumnConstraints().add(conCol);
			buttons.getRowConstraints().add(conRow);
		}
		return buttons;
	}
	public static BackType generarFondo() {
		BackType[] bt = BackType.values();
		return bt[(int)(Math.random()*(bt.length-1))+1];
		//(hasta-desde+1)+desde
	}
	public static MonsterType generarMonstruo() {
		MonsterType[] mt = MonsterType.values();
		return mt[(int)(Math.random()*mt.length)];
	}

	public static Button[][] getButton() {
		return button;
	}


	public static BackType[][] getBackType() {
		return backType;
	}
	
}
