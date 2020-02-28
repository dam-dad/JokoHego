package dad.jokohego.model;

import java.util.ArrayList;
import java.util.List;

public class GameDataProvider {

	public static GameData gamedata;
	
	public GameDataProvider(GameData gamedata) {
		this.gamedata=gamedata;
	}
	
	public static List<GameData> getData() {
		ArrayList<GameData> games = new ArrayList<GameData>();
		games.add(gamedata);
		return games;
	}
	
}
