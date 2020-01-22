package JokoHego.model;

import JokoHego.Utils.MonsterType;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Monster {
	
	private IntegerProperty vida = new SimpleIntegerProperty();
	private IntegerProperty danyo = new SimpleIntegerProperty();
	private IntegerProperty dinero = new SimpleIntegerProperty();

	public Monster(MonsterType tipo) {
		switch(tipo) {
			case Cutulu:{
				vida.set(50);
				danyo.set(7);
				dinero.set(10);
				break;
			}
			case Gormiti:{
				vida.set(60);
				danyo.set(2);
				dinero.set(8);
				break;
			}
			case Minik:{
				vida.set(20);
				danyo.set(3);
				dinero.set(3);
				break;
			}
			case Orco:{
				vida.set(40);
				danyo.set(6);
				dinero.set(5);
				break;
			}
			case Phantom:{
				vida.set(20);
				danyo.set(14);
				dinero.set(5);
				break;
			}
			case Rata:{
				vida.set(10);
				danyo.set(5);
				dinero.set(1);
				break;
			}
			case Serpiente:{
				vida.set(20);
				danyo.set(5);
				dinero.set(4);
				break;
			}
			case EscarabajoOro:{
				vida.set(5);
				danyo.set(0);
				dinero.set(100);
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

	public final IntegerProperty dineroProperty() {
		return this.dinero;
	}
	

	public final int getDinero() {
		return this.dineroProperty().get();
	}
	

	public final void setDinero(final int dinero) {
		this.dineroProperty().set(dinero);
	}
	
	
	

}

