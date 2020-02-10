package dad.jokohego.utils;

import java.awt.Point;

import dad.jokohego.controllers.JuegoController;
import dad.jokohego.model.Monster;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;

public class JokoUtils {
	//los botones tienen un objeto point en el userData para determinar la posicion en el array bidimensional
	private static Button[][] button;
	private static boolean escalera;
	private static double nivel;
	
	
	public static GridPane generarNivel(int nivel,JuegoController jg) {
		JokoUtils.nivel = nivel;
		escalera = false;
		int size = nivel + 4;
		button = new Button[size+1][size+1];
		GridPane buttons = new GridPane();
		
		ColumnConstraints conCol =  new ColumnConstraints();
		conCol.setHgrow(Priority.ALWAYS);
		RowConstraints conRow = new RowConstraints();
		conRow.setVgrow(Priority.ALWAYS);
		//generar el gridpane dinamico
		for(int i = 0;i < size;i++ ) {
			for(int j = 0; j < size;j++) {
				
				button[i][j] = new Button();
				button[i][j].setUserData(generarFondo());
				button[i][j].setOnAction(e -> jg.onGeneralAction(e));
				button[i][j].setOnMouseEntered(e -> jg.onMonsterInformation(e));
				button[i][j].setOnMouseExited(e -> jg.onMonsterInformationExited(e));
				buttons.add(button[i][j],i+1,j+1);
				button[i][j].setMaxWidth(Integer.MAX_VALUE);
				button[i][j].setMaxHeight(Integer.MAX_VALUE);
				button[i][j].getStyleClass().add("LosaOscura");
			}
			
			
		}
		//Calcular niveles de peligrosidad
		int danger;
		for(int i = 0;i < size;i++ ) {
			danger = 0;
			for(int j = 0; j < size;j++) {
				if(button[i][j].getUserData().equals(BackType.Monster))danger++;
			

			}
			Button aux = new Button();
			aux.setMaxWidth(Integer.MAX_VALUE);
			aux.setMaxHeight(Integer.MAX_VALUE);
			aux.setDisable(true);
			aux.setText(danger+"");
			buttons.add(aux,i+1,0);
			danger = 0;
			for(int j = 0; j < size;j++) {
				if(button[j][i].getUserData().equals(BackType.Monster))danger++;
			}
			Button aux2 = new Button();
			aux2.setMaxWidth(Integer.MAX_VALUE);
			aux2.setMaxHeight(Integer.MAX_VALUE);
			aux2.setDisable(true);
			aux2.setText(danger+"");
			buttons.add(aux2,0,i+1);
			
			buttons.getColumnConstraints().add(conCol);
			buttons.getRowConstraints().add(conRow);
		}
			buttons.getColumnConstraints().add(conCol);
			buttons.getRowConstraints().add(conRow);
		if(!escalera)button[size-1][size-1].setUserData(BackType.Escaleras);
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
				escalera=true;
			}
		}else if(num <= 40 && num >=35) {
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
		}else if(num >= 100 && num < 120) {
			monster = MonsterType.Orco;
		}else if(num >= 120 && num < 140) {
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
	
}
