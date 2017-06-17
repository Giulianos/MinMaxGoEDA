package group1.go.Model;

/**
 * Created by giulianoscaglioni on 30/5/17.
 */
import java.util.LinkedList;
import java.util.Queue;

public class GoRules {
	
	//To speed up trailing methods.
	final static public boolean clearedMatrix[][] = new boolean[Constants.BOARDSIZE+1][Constants.BOARDSIZE+1];
	static public boolean visited[][] = new boolean[Constants.BOARDSIZE+1][Constants.BOARDSIZE+1];
	
	static public boolean isEmpty(Board b, TilesPosition p) {
		return b.get(p) == Constants.EMPTY;
	}
	
	/* Checks if placing a tile in a position is suicide
	 * 
	 * @param b The board to check.
	 * @param p The player trying to place the tile
	 * @param t The position of the tile
	 * 
	 * @return The answer ;)
	 * */
	static public boolean isSuicide(Board b, TilesPosition tp, char p) {
		char enemy = p==Constants.WHITE?Constants.BLACK:Constants.WHITE;
		//Clear visited
		visited = clearedMatrix.clone();
		Queue<TilesPosition> bfsQueue = new LinkedList<TilesPosition>();
		//Enqueue tile
		bfsQueue.offer(tp);
		//Do BFS for empty space
		TilesPosition current;
		while(!bfsQueue.isEmpty()) {
			current = bfsQueue.poll();
			visited[current.i][current.j] = true;
			//If an empty space is found, then it's not suicide
			if(!current.equals(tp) && b.get(current)==Constants.EMPTY)
				return false;
			else {
				if(current.i>0 && b.get(current.i-1, current.j)!=enemy && !visited[current.i-1][current.j])
					bfsQueue.offer(new TilesPosition(current.i-1, current.j));
				if(current.j>0 && b.get(current.i, current.j-1)!=enemy && !visited[current.i][current.j-1])
					bfsQueue.offer(new TilesPosition(current.i, current.j-1));
				if(current.i<(Constants.BOARDSIZE) && b.get(current.i+1, current.j)!=enemy && !visited[current.i+1][current.j])
					bfsQueue.offer(new TilesPosition(current.i+1, current.j));
				if(current.j<(Constants.BOARDSIZE) && b.get(current.i, current.j+1)!=enemy && !visited[current.i][current.j+1])
					bfsQueue.offer(new TilesPosition(current.i, current.j+1));
			}
		}
		//If no empty space is found, then it's suicide
		return true;
	}
	
	/* Applies the move to the board (the tile should be already placed)
	 * 
	 * @param b The board where the move will be applied.
	 * @param p The player whose move will be applied.
	 * @param t The position of the tile placed
	 * 
	 * @return Quantity of tiles eaten
	 * */
	static public int applyMove(Board b, char p, TilesPosition t) {
		char enemy = p==Constants.WHITE?Constants.BLACK:Constants.WHITE;
		TilesPosition auxTile;
		int eaten = 0;
		//Check if the move would been suicide for enemy in adjacent tiles
		//Up
		if(!t.isTopBorder() && b.get(auxTile=new TilesPosition(t.i-1, t.j))==enemy && isSuicide(b, auxTile, enemy)) {
			eaten+=eatTiles(b, auxTile);
			System.out.println("eating up!");
		}
		//Down
		if(!t.isBottomBorder() && b.get(auxTile=new TilesPosition(t.i+1, t.j))==enemy && isSuicide(b, auxTile, enemy)) {
			eaten+=eatTiles(b, auxTile);
			System.out.println("eating down!");
		}
		//Left
		if(!t.isLeftBorder() && b.get(auxTile=new TilesPosition(t.i, t.j-1))==enemy && isSuicide(b, auxTile, enemy)) {
			eaten+=eatTiles(b, auxTile);
			System.out.println("eating left!");
		}
		//Right
		if(!t.isRightBorder() && b.get(auxTile=new TilesPosition(t.i, t.j+1))==enemy && isSuicide(b, auxTile, enemy)) {
			eaten+=eatTiles(b, auxTile);
			System.out.println("eating right!");
		}
		
		return eaten;
	}
	
	/* Removes all the enemy tiles from the region of the tile passed
	 * 
	 * @param b The board whose tiles will be eaten.
	 * @param t The position of the enemy tile
	 * 
	 * @return Quantity of tiles eaten
	 * */
	static public int eatTiles(Board b, TilesPosition t) {
		char enemy = b.get(t);
		int eaten = 0;
		Queue<TilesPosition> bfsQueue = new LinkedList<TilesPosition>();
		//Enqueue tile
		bfsQueue.offer(t);
		//Do BFS eating enemies
		TilesPosition current;
		while(!bfsQueue.isEmpty()) {
			current = bfsQueue.poll();
			if(b.get(current)==enemy) {
				b.add(current, Constants.EMPTY);
				eaten++;
			}
			if(!current.isTopBorder() && b.get(current.i-1, current.j)==enemy)
				bfsQueue.offer(new TilesPosition(current.i-1, current.j));
			if(!current.isLeftBorder() && b.get(current.i, current.j-1)==enemy)
				bfsQueue.offer(new TilesPosition(current.i, current.j-1));
			if(!current.isBottomBorder() && b.get(current.i+1, current.j)==enemy)
				bfsQueue.offer(new TilesPosition(current.i+1, current.j));
			if(!current.isRightBorder() && b.get(current.i, current.j+1)==enemy)
				bfsQueue.offer(new TilesPosition(current.i, current.j+1));
		}
		
		return eaten;
	}
	
	/* Returns whether the tile can be placed or not
	 * 
	 * @param b The board to check.
	 * @param t The position to check
	 * @param p The player trying to place the tile
	 * 
	 * @return The answer ;)
	 * */
	static public boolean isPossible(Board b, TilesPosition t, char p) {
		return isEmpty(b, t) && !isSuicide(b, t, p);
	}
	
	static public void main(String[] args) {
		Board b = new BoardMapImpl();
		
		for(int i=0; i<=Constants.BOARDSIZE; i++)
			b.add(4, i, Constants.WHITE);
		for(int i=0; i<=Constants.BOARDSIZE; i++)
			b.add(6, i, Constants.WHITE);
		
		//Print board
		for(int i=0; i<=Constants.BOARDSIZE; i++) {
			for(int j=0; j<=Constants.BOARDSIZE; j++) {
				switch(b.get(i, j)) {
				case Constants.EMPTY: System.out.print("[ ]"); break;
				case Constants.WHITE: System.out.print("[o]"); break;
				case Constants.BLACK: System.out.print("[x]"); break;
				}
			}
			System.out.println("");
		}
		
		System.out.println(isSuicide(b,new TilesPosition(5,5), Constants.BLACK));
		
		for(int i=0; i<Constants.BOARDSIZE; i++)
			b.add(5, i, Constants.BLACK);
		
		b.add(5, 12, Constants.WHITE);
		applyMove(b, Constants.WHITE, new TilesPosition(5, 12));
		
		//Print board
		for(int i=0; i<=Constants.BOARDSIZE; i++) {
			for(int j=0; j<=Constants.BOARDSIZE; j++) {
				switch(b.get(i, j)) {
				case Constants.EMPTY: System.out.print("[ ]"); break;
				case Constants.WHITE: System.out.print("[o]"); break;
				case Constants.BLACK: System.out.print("[x]"); break;
				}
			}
			System.out.println("");
		}
		
		System.out.println(isSuicide(b,new TilesPosition(5,11), Constants.BLACK));
		
	}
	
}

