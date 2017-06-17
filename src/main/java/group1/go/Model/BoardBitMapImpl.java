package group1.go.Model;

import java.util.Collection;

public class BoardBitMapImpl implements Board {

	BitBoard blackBoard;
	BitBoard whiteBoard;
	int blackCaptures;
	int whiteCaptures;
	private static int turn = 0;
	
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
		int index =  j*(Constants.BOARDSIZE+1) + i;
		if(turn%2 == 0){
			blackBoard.add(index);
			return;
		}
			whiteBoard.add(index);		
	}

	public void remove(Collection<TilesPosition> toRemove) {
		int index;
		for(TilesPosition t: toRemove){
			index = t.getJ()*(Constants.BOARDSIZE+1) + t.getI();
			blackBoard.remove(index);
			whiteBoard.remove(index);
		}
		
	}

	public void remove(TilesPosition toRemove) {
		int index = toRemove.getJ()*(Constants.BOARDSIZE+1) + toRemove.getI();
		blackBoard.remove(index);
		whiteBoard.remove(index);
	}

	public Board clone() {
		BoardBitMapImpl newBoard = new BoardBitMapImpl(blackBoard, whiteBoard, blackCaptures, whiteCaptures); 
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
		if (getClass() != obj.getClass())
			return false;
		BoardBitMapImpl other = (BoardBitMapImpl) obj;
		if(blackBoard.equals(other.blackBoard) && whiteBoard.equals(other.whiteBoard)){
			return true;
		}
		return false;
	}
	public boolean isFull() {
		/*if(((blackBoard.getFirst30()|whiteBoard.getFirst30())|(blackBoard.getSecond30()|whiteBoard.getSecond30())|
				(blackBoard.getThird30()()|whiteBoard.getThird30())|(blackBoard.getFourth30()|whiteBoard.getFourth30())|
				(blackBoard.getFifth30()|whiteBoard.getFifth30())|(blackBoard.getSixth30()|whiteBoard.getSixth30())) == 0){
			
		}*/
		return false;
	}
	

}
