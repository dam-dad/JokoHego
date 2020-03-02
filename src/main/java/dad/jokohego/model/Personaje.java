package dad.jokohego.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;


/**
 * 
 * Beans del personaje con sus atributos.
 * 
 * @author SERGIO GARCÍA DELGADO
 * @author AMARO YANES CABRERA
 */

public class Personaje {

	private IntegerProperty vida = new SimpleIntegerProperty();
	private IntegerProperty danyo = new SimpleIntegerProperty();
	private IntegerProperty vidamax = new SimpleIntegerProperty();
	private IntegerProperty experiencia = new SimpleIntegerProperty();
	private IntegerProperty nivel = new SimpleIntegerProperty();

	public final IntegerProperty vidaProperty() {
		return this.vida;
	}

	public final int getVida() {
		return this.vidaProperty().get();
	}

	public final void setVida(final int vida) {
		this.vidaProperty().set(vida);
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

	public final IntegerProperty experienciaProperty() {
		return this.experiencia;
	}

	public final int getExperiencia() {
		return this.experienciaProperty().get();
	}

	public final void setExperiencia(final int experiencia) {
		this.experienciaProperty().set(experiencia);
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

}
