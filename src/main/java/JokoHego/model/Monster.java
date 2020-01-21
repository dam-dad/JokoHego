package JokoHego.model;

import JokoHego.Utils.MonsterType;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Monster {
	
	private IntegerProperty vida = new SimpleIntegerProperty();
	private IntegerProperty danyo = new SimpleIntegerProperty();

	public Monster(MonsterType tipo) {
		switch(tipo) {
			case Cutulu:{
				vida.set(50);
				danyo.set(7);
				break;
			}
			case Gormiti:{
				vida.set(60);
				danyo.set(2);
				break;
			}
			case Minik:{
				vida.set(20);
				danyo.set(3);
				break;
			}
			case Orco:{
				vida.set(40);
				danyo.set(6);
				break;
			}
			case Phantom:{
				vida.set(20);
				danyo.set(14);
				break;
			}
			case Rata:{
				vida.set(10);
				danyo.set(5);
				break;
			}
			case Serpiente:{
				vida.set(20);
				danyo.set(5);
				break;
			}	
		}
	}
	

}

