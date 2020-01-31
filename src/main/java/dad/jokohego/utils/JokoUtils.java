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
	private static double nivel;
	
	
	public static GridPane generarNivel(int nivel,JuegoController jg) {
		JokoUtils.nivel = nivel;
		int size = nivel + 4;
		button = new Button[size+1][size+1];
		backType = new BackType[size+1][size+1];
		GridPane buttons = new GridPane();
		
		ColumnConstraints conCol =  new ColumnConstraints();
		conCol.setHgrow(Priority.ALWAYS);
		RowConstraints conRow = new RowConstraints();
		conRow.setVgrow(Priority.ALWAYS);
		
		for(int i = 0;i < size;i++ ) {
			for(int j = 0; j < size;j++) {
				
				button[i][j] = new Button();
				button[i][j].setUserData(new Point(i,j));
				button[i][j].setOnAction(e -> jg.onGeneralAction(e));
				button[i][j].setOnMouseEntered(e -> jg.onMonsterInformation(e));
				button[i][j].setOnMouseExited(e -> jg.onMonsterInformationExited(e));
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
		BackType fondo = BackType.Losa;
		int num = (int)((Math.random()*100)*(nivel/10+1));
		if(num < 5 ) {
			if(escalera) {
				fondo = BackType.Losa;
			}else {
				fondo = BackType.Escaleras;
				if(fondo.equals(BackType.Escaleras))escalera=true;
			}
		}else if(num < 10) {
			fondo = BackType.Cofre;
		}else if(num > 90) {
			fondo = BackType.Monster;
		}
		return fondo;
		//(hasta-desde+1)+desde
	}
	public static MonsterType generarMonstruo() {
		MonsterType monster = null;
		
		MonsterType[] mt = MonsterType.values();
		int num = (int)((Math.random()*100)+(nivel*5));
		
		if(num < 40) {
			monster = MonsterType.Rata;
		}else if(num >= 40 && num < 60) {
			monster = MonsterType.Minik;
		}else if(num >= 60 && num < 100) {
			monster = MonsterType.Serpiente;
		}else if(num >= 100 && num < 140) {
			monster = MonsterType.Orco;
		}else if(num >= 140 && num < 160) {
			monster = MonsterType.Phantom;
		}else
			monster = MonsterType.Cutulu;
		
		if(num == 80)monster = MonsterType.EscarabajoOro;
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
	
}
