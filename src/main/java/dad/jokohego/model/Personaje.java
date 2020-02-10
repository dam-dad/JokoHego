package dad.jokohego.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Personaje {
	
	private IntegerProperty vida = new SimpleIntegerProperty();
	private IntegerProperty danyo = new SimpleIntegerProperty();
	private IntegerProperty vidamax = new SimpleIntegerProperty();
	private IntegerProperty dinero = new SimpleIntegerProperty();
	private BooleanProperty hombre = new SimpleBooleanProperty();

	
	
	
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
	

	public final IntegerProperty dineroProperty() {
		return this.dinero;
	}
	

	public final int getDinero() {
		return this.dineroProperty().get();
	}
	

	public final void setDinero(final int dinero) {
		this.dineroProperty().set(dinero);
	}

	public final BooleanProperty hombreProperty() {
		return this.hombre;
	}
	

	public final boolean isHombre() {
		return this.hombreProperty().get();
	}
	

	public final void setHombre(final boolean hombre) {
		this.hombreProperty().set(hombre);
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
