package group1.go.Model;

import java.util.Collection;

/*
 * this board uses an Array of Arrays (Matrix) as a board. 
 * The matrix is always full with characters.
 */
public class BoardCharImpl extends Board {

	private char[][] board;
	private int blackCaptures;
	private int whiteCaptures;
	
	public BoardCharImpl(char[][] board, int whiteCaptures, int blackCaptures){
		this.board = board;
		this.whiteCaptures = whiteCaptures;
		this.blackCaptures = blackCaptures;
	}
	
	public BoardCharImpl(){
		board = new char[13][13];
		for(int i = 0; i<13*13; i++){
			board[i/13][i%13] = Constants.EMPTY;
		}
		blackCaptures = 0;
		whiteCaptures = 0;
	}
	
	public char get(int i, int j) {
		if(i<0 || j<0 ||j>=13 || i>=13){
			return Constants.EMPTY;
		}
		return board[i][j];
	}

	public void add(int i, int j, char player) {
		if(i<0 || j<0 ||j>=13 || i>=13){
			return;
		}
		board[i][j] = player;
		
	}

	public void remove(Collection<TilesPosition> toRemove) {
		for(TilesPosition t: toRemove){
			board[t.getI()][t.getJ()]= Constants.EMPTY;
		}
		
	}

	public void remove(TilesPosition toRemove) {
		board[toRemove.getI()][toRemove.getJ()] = Constants.EMPTY;
		
	}

	public Board clone() {
		char[][] newBoard = new char[13][13];
		for(int i = 0; i<13*13; i++){
			newBoard[i/13][i%13] = board[i/13][i%13];
		}
		return  new BoardCharImpl(newBoard, whiteCaptures, blackCaptures);
	}

	public boolean isFull() {
		for(int i=0; i<13*13; i++){
			if(board[i/13][i%13] == Constants.EMPTY){
				return false;
			}
		}
		return true;
	}

}
