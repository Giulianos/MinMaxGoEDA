package group1.go.Model;
import java.util.HashMap;
import java.util.HashSet;

public class Board {
	
	HashMap<TilesPosition,Character> board;
	int blackTilesCapture; 
	int whiteTilesCapture;
	
	
	public Board(HashMap<TilesPosition,Character> board, int blackTilesCapture, int whiteTilesCapture){
		this.board = board;
		this.blackTilesCapture = blackTilesCapture;
		this.whiteTilesCapture = whiteTilesCapture;
	}
	
	public Board(){
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
	
	public Board add(int i, int j, char player){
		HashMap<TilesPosition,Character> aux = (HashMap<TilesPosition, Character>) board.clone();
		aux.put(new TilesPosition(i,j), player);
		return new Board(aux, blackTilesCapture, whiteTilesCapture);
	}
	
	
}
