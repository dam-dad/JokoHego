package dad.jokohego.utils;

import java.awt.Point;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import dad.jokohego.app.JokoHegoApp;
import dad.jokohego.controllers.JuegoController;
import dad.jokohego.model.GameData;
import dad.jokohego.model.GameDataProvider;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * 
 * 	Colección de métodos estáticos para el funcionamiento del videojuego.	
 * 
 *  @author SERGIO GARCÍA DELGADO
 * 	@author AMARO YANES CABRERA
 */

public class JokoUtils {
	// los botones tienen en el  userData un tipo BackType
	private static Button[][] button;
	private static boolean escalera;
	private static double nivel;
	private static BackType[][] backType;
	private static GridPane buttons;

	
	/**
	 * 
	 * Genera dinámicamente un gridpane de botones dependinedo del parámeto nivel.
	 * 
	 * @param nivel Nivel actual del juego.
	 * @param jg Juego.
	 * @return
	 */
	
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

	/**
	 * 
	 * Retorna un BackType de forma aleatoria entre los elementos del enumerado.
	 * 
	 * @return
	 */
	
	public static BackType generarFondo() {
		BackType fondo = BackType.Losa;
		int num = (int) ((Math.random() * 100) * (nivel / 10 + 1));
		if (num < 5) {
			if (escalera) {
				fondo = BackType.Losa;
			} else {
				fondo = BackType.Escaleras;
				escalera = true;
			}
		} else if (num <= 48 && num >= 45) {
			fondo = BackType.Cofre;
		} else if (num > 90) {
			fondo = BackType.Monster;
		}
		return fondo;
		// (hasta-desde+1)+desde
	}
	/**
	 * 
	 * Retorna un MonsterType de forma aleatoria entre los elementos del enumerado.
	 * 
	 * 
	 * @return
	 */
	public static MonsterType generarMonstruo() {
		MonsterType monster = null;

		int num = (int) ((Math.random() * 100) + (nivel * 10));

		if (num < 50) {
			monster = MonsterType.Rata;
		} else if (num >= 50 && num < 70) {
			monster = MonsterType.Minik;
		} else if (num >= 70 && num < 90) {
			monster = MonsterType.Serpiente;
		} else if (num >= 90 && num < 110) {
			monster = MonsterType.Orco;
		} else if (num >= 110 && num < 130) {
			monster = MonsterType.Phantom;
		} else if (num >= 130 && num < 150) {
			monster = MonsterType.Gormiti;
		} else
			monster = MonsterType.Cutulu;

		if (num == 100)
			monster = MonsterType.EscarabajoOro;
		return monster;
	}
	
	/**
	 * 
	 * Genera un pdf con los datos recibidos del juego.
	 * 
	 * 
	 * @param game Datos del juego
	 * @throws JRException
	 * @throws IOException
	 */
	
	public static void generarPdf(GameData game) throws JRException, IOException {
		GameDataProvider gamedata = new GameDataProvider(game);

		JasperReport report = JasperCompileManager.compileReport(JokoUtils.class.getResourceAsStream("/reports/JokoHegoReport.jrxml"));		

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("IMAGEN", ImageIO.read(JokoUtils.class.getResourceAsStream("/ImagenesGreenStyle/Fondos/jokohegologo.png"))); 
		
        JasperPrint print  = JasperFillManager.fillReport(report, parameters, new JRBeanCollectionDataSource(gamedata.getData()));
        
        
        DirectoryChooser chooser = new DirectoryChooser();
		

		chooser.setTitle("Elegir directorio");

		try {
			JasperExportManager.exportReportToPdfFile(print, chooser.showDialog(JokoHegoApp.getStage()).toString()+"\\"+game.getNombre()+".pdf");
		} catch (Exception e) {
		}
        
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
