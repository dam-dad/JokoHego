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
	private static boolean escalera=false;
	
	
	public static GridPane generarNivel(int nivel,JuegoController jg) {
		int size = nivel + 4;
		button = new Button[size+1][size+1];
		backType = new BackType[size+1][size+1];
		GridPane buttons = new GridPane();
		
		ColumnConstraints conCol =  new ColumnConstraints();
		conCol.setHgrow(Priority.ALWAYS);
		RowConstraints conRow = new RowConstraints();
		conRow.setVgrow(Priority.ALWAYS);
		
		for(int i = 0;i < size;i++ ) {
			System.out.println(i+":");
			for(int j = 0; j < size;j++) {
				
				button[i][j] = new Button();
				System.out.println("       "+button[i][j]);
				button[i][j].setUserData(new Point(i,j));
				button[i][j].setOnAction(e -> jg.onGeneralAction(e));
				buttons.add(button[i][j],i,j);
				button[i][j].setMaxWidth(Integer.MAX_VALUE);
				button[i][j].setMaxHeight(Integer.MAX_VALUE);
				button[i][j].getStyleClass().add("LosaOscura");

				backType[i][j] = generarFondo();
			}
			
			buttons.getColumnConstraints().add(conCol);
			buttons.getRowConstraints().add(conRow);
		}
		if(!escalera)backType[size-1][size-1]=BackType.Escaleras;
		return buttons;
	}
	public static BackType generarFondo() {
		BackType[] bt = BackType.values();
		BackType fondo;
		if(escalera) {
			fondo = bt[(int)(Math.random()*(bt.length-2))+2];
		}else {
			fondo = bt[(int)(Math.random()*(bt.length-1))+1];
			if(fondo.equals(BackType.Escaleras))escalera=true;
		}
		return fondo;
		//(hasta-desde+1)+desde
	}
	public static MonsterType generarMonstruo() {
		MonsterType[] mt = MonsterType.values();
		return mt[(int)(Math.random()*mt.length)];
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
	
}
