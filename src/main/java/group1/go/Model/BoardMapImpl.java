package group1.go.Model;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class BoardMapImpl extends Board {
	
	private HashMap<TilesPosition,Character> board;
	private int blackTilesCapture; 
	private int whiteTilesCapture;
	
	
	public BoardMapImpl(HashMap<TilesPosition,Character> board, int blackTilesCapture, int whiteTilesCapture){
		this.board = board;
		this.blackTilesCapture = blackTilesCapture;
		this.whiteTilesCapture = whiteTilesCapture;
	}
	
	public BoardMapImpl(){
		this.board = new HashMap<TilesPosition,Character>();
		this.blackTilesCapture = 0;
		this.whiteTilesCapture = 0;
	}
	
	public char get(int i,int j){
		TilesPosition tilePosition = new TilesPosition(i,j);
		Character tile = board.get(tilePosition);
		if(tile == null){
			return Constants.EMPTY;
		}
		return tile;
	}
	
	public void add(int i, int j, char player) {
		board.put(new TilesPosition(i,j), player);
		
	}

	public void remove(Collection<TilesPosition> toRemove) {
		for(TilesPosition t: toRemove){
			board.remove(t);
		}
		
	}

	public void remove(TilesPosition toRemove) {
		board.remove(toRemove);
		
	}

	public Board clone() {
		Board cloned= new BoardMapImpl((HashMap<TilesPosition, Character>) board.clone(), blackTilesCapture, whiteTilesCapture);
		return cloned;
	}
	
	public void add(TilesPosition t, char player) {
		add(t.i, t.j, player); //Better clone the TilesPosition passed
	}

	public char get(TilesPosition p) {
		return get(p.i, p.j);
	}

	public boolean isFull() {
		if(board.size() == 13*13){
			return true;
		}
		return false;
	}
	
}
