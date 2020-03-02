package dad.jokohego.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


/**
 * 
 * Información necesaria para realiar el informe.
 * 
 * @author SERGIO GARCÍA DELGADO
 * @author AMARO YANES CABRERA
 */

public class GameData {
	
	private IntegerProperty numMonstruos = new SimpleIntegerProperty();
	private IntegerProperty nivel = new SimpleIntegerProperty();
	private StringProperty nombre = new SimpleStringProperty();
	private IntegerProperty danyo = new SimpleIntegerProperty();
	private IntegerProperty vidamax = new SimpleIntegerProperty();
	
	public GameData(String string, Personaje character, int nivel2, int monsterKilled) {
		numMonstruos.set(monsterKilled);
		nivel.set(nivel2);
		nombre.set(string);
		danyo.set(character.getDanyo());
		vidamax.set(character.getVidamax());
	}


	public final IntegerProperty numMonstruosProperty() {
		return this.numMonstruos;
	}
	

	public final int getNumMonstruos() {
		return this.numMonstruosProperty().get();
	}
	

	public final void setNumMonstruos(final int numMonstruos) {
		this.numMonstruosProperty().set(numMonstruos);
	}

	public final IntegerProperty nivelProperty() {
		return this.nivel;
	}
	

	public final int getNivel() {
		return this.nivelProperty().get();
	}
	

	public final void setNivel(final int nivel) {
		this.nivelProperty().set(nivel);
	}
	






	public final StringProperty nombreProperty() {
		return this.nombre;
	}
	


	public final String getNombre() {
		return this.nombreProperty().get();
	}
	


	public final void setNombre(final String nombre) {
		this.nombreProperty().set(nombre);
	}


	public final IntegerProperty danyoProperty() {
		return this.danyo;
	}
	


	public final int getDanyo() {
		return this.danyoProperty().get();
	}
	


	public final void setDanyo(final int danyo) {
		this.danyoProperty().set(danyo);
	}
	


	public final IntegerProperty vidamaxProperty() {
		return this.vidamax;
	}
	


	public final int getVidamax() {
		return this.vidamaxProperty().get();
	}
	


	public final void setVidamax(final int vidamax) {
		this.vidamaxProperty().set(vidamax);
	}
	
	
	
	
	
}
