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

				backType[i][j] = BackType.LosaOscura;
			}
			buttons.getColumnConstraints().add(conCol);
			buttons.getRowConstraints().add(conRow);
		}
		return buttons;
	}


	public static Button[][] getButton() {
		return button;
	}


	public static BackType[][] getBackType() {
		return backType;
	}
	
}
