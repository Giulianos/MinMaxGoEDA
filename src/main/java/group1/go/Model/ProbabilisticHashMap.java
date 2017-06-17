package group1.go.Model;

import java.util.HashMap;
import java.util.Random;

public class ProbabilisticHashMap {
	
	private static HashMap<BoardBitMapImpl, Double> probability = new HashMap<BoardBitMapImpl, Double>();
	
	public void run(){
		Game game = new Game();
		Random rnd = new Random(System.currentTimeMillis());
		int i = 0;
		int j= 0;
		int level;
		boolean flag = true;
		do{
			i = rnd.nextInt(13);
			j = rnd.nextInt(13);
		}while(game.isposible(i, j) != 0);
		game.add(i, j);
		game.endTurn();
	}
}
