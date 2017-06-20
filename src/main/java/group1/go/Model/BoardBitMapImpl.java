package group1.go.Model;

import java.util.Collection;

/*
 * This board uses to BitBoards as a board. One BitBoard is for the white tiles and
 * the other one is for the black tiles. 
 * It transform the int i,j of the methods into an index as the BitBoard uses and index to access the 
 * elements
 * This board try to save space by using BitBoards to represent the board. 
 */

public class BoardBitMapImpl extends Board {

	private BitBoard blackBoard;
	private BitBoard whiteBoard;
	private int blackCaptures;
	private int whiteCaptures;
	
	public BoardBitMapImpl(){
		
		blackBoard = new BitBoard();
		whiteBoard = new BitBoard();
	}
	public BoardBitMapImpl(BitBoard blackBoard, BitBoard whiteBoard, int blackCaptures, int whiteCaptures){
		this.blackBoard = blackBoard;
		this.whiteBoard = whiteBoard;
		this.whiteCaptures = whiteCaptures;
		this.blackCaptures = blackCaptures;
	}
	
	public char get(int i, int j) {
		if(!this.isAvailable(i, j)){
			return Constants.OUTOFBOUNDS;
		}
		int index = j*(Constants.BOARDSIZE+1) + i;
		if(whiteBoard.isInPosition(index)){
			return Constants.WHITE;
		}
		if(blackBoard.isInPosition(index)){
			return Constants.BLACK;
		}
		return Constants.EMPTY;
	}

	public void add(int i, int j, char player) {
		if(!this.isAvailable(i, j)){
			return ;
		}
		int index =  j*(Constants.BOARDSIZE+1) + i;
		if(player==Constants.BLACK){
			blackBoard.add(index);
			return;
		}
			whiteBoard.add(index);		
	}

	public void remove(Collection<TilesPosition> toRemove) {
		for(TilesPosition t: toRemove){
			remove(t);
		}
		
	}

	public void remove(TilesPosition toRemove) {
		if(!this.isAvailable(toRemove.getI(), toRemove.getJ())){
			return ;
		}
		int index = toRemove.getJ()*(Constants.BOARDSIZE+1) + toRemove.getI();
		blackBoard.remove(index);
		whiteBoard.remove(index);
	}

	public Board clone() {
		BoardBitMapImpl newBoard = new BoardBitMapImpl(blackBoard.clone(), whiteBoard.clone(), blackCaptures, whiteCaptures); 
		return newBoard;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((blackBoard == null) ? 0 : blackBoard.hashCode());
		result = prime * result + blackCaptures;
		result = prime * result + ((whiteBoard == null) ? 0 : whiteBoard.hashCode());
		result = prime * result + whiteCaptures;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!getClass().equals(obj.getClass()))
			return false;
		BoardBitMapImpl other = (BoardBitMapImpl) obj;
		if(blackBoard.equals(other.blackBoard) && whiteBoard.equals(other.whiteBoard)){
			return true;
		}
		return false;
	}
	
	public boolean isFull() {
		if(((blackBoard.getFirst30() == whiteBoard.getFirst30())&&(blackBoard.getSecond30()==whiteBoard.getSecond30())&&
				(blackBoard.getThird30()==whiteBoard.getThird30())&&(blackBoard.getFourth30()==whiteBoard.getFourth30())&&
				(blackBoard.getFifth30()==whiteBoard.getFifth30())&&(blackBoard.getSixth30()==whiteBoard.getSixth30()))){
			return true;
		}
		return false;
	}
	
	private boolean isAvailable(int i, int j){
		return (i>=0)&&(j>=0) &&(i<=Constants.BOARDSIZE) && (j<=Constants.BOARDSIZE);
	}
	

}
