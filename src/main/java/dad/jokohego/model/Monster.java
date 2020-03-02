package dad.jokohego.model;

import dad.jokohego.utils.MonsterType;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Beans de los monstruos con sus atributos.
 * 
 * @author SERGIO GARCÍA DELGADO
 * @author AMARO YANES CABRERA
 */


public class Monster {
	
	private StringProperty nombre = new SimpleStringProperty();
	private IntegerProperty vida = new SimpleIntegerProperty();
	private IntegerProperty danyo = new SimpleIntegerProperty();
	private IntegerProperty experiencia = new SimpleIntegerProperty();

	public Monster(MonsterType tipo) {
		nombre.set(tipo.toString());
		switch(tipo) {
			case Cutulu:{
				vida.set(80);
				danyo.set(10);
				experiencia.set(40);
				break;
			}
			case Gormiti:{
				vida.set(100);
				danyo.set(4);
				experiencia.set(20);
				break;
			}
			case Minik:{
				vida.set(24);
				danyo.set(3);
				experiencia.set(5);
				break;
			}
			case Orco:{
				vida.set(50);
				danyo.set(6);
				experiencia.set(5);
				break;
			}
			case Phantom:{
				vida.set(25);
				danyo.set(20);
				experiencia.set(10);
				break;
			}
			case Rata:{
				vida.set(11);
				danyo.set(4);
				experiencia.set(1);
				break;
			}
			case Serpiente:{
				vida.set(20);
				danyo.set(5);
				experiencia.set(4);
				break;
			}
			case EscarabajoOro:{
				vida.set(1);
				danyo.set(0);
				experiencia.set(100);
				break;
			}
		}
		
	}

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

	
	

	

	public final StringProperty nombreProperty() {
		return this.nombre;
	}
	

	public final String getNombre() {
		return this.nombreProperty().get();
	}
	

	public final void setNombre(final String nombre) {
		this.nombreProperty().set(nombre);
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
	
	
	
	
	

}

