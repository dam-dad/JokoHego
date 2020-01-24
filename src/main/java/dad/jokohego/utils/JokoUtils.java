package dad.jokohego.utils;

import java.awt.Point;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class JokoUtils {
	
	public GridPane generarNivel(int nivel) {
		int size = nivel + 4;
		Button[][] button = new Button[size+1][size+1];
		BackType[][] backType = new BackType[size+1][size+1];;
		GridPane buttons = new GridPane();
		for(int i = 0;i < size;i++ ) {
			for(int j = 0; j < size;j++) {
				
				button[i][j] = new Button();
				button[i][j].setUserData(new Point(i,j));
				buttons.add(button[i][j],i,j);
				button[i][j].setMaxWidth(Integer.MAX_VALUE);
				button[i][j].setMaxHeight(Integer.MAX_VALUE);
				backType[i][j] = BackType.LosaOscura;
				
				
			}
		}
		
		
		return buttons;
	}
}
