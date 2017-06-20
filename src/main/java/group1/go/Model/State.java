package group1.go.Model;
import java.util.HashSet;

import javax.swing.text.html.HTMLDocument.HTMLReader.BlockAction;

public class State {

	Board board;
	int blackTilesCapture; 
	int whiteTilesCapture;
	int blackTerritory;
	int whiteTerritory;
	
	public int getBlackTerritory() {
		return blackTerritory;
	}


	public void setBlackTerritory(int blackTerritory) {
		this.blackTerritory = blackTerritory;
	}


	public int getWhiteTerritory() {
		return whiteTerritory;
	}


	public void setWhiteTerritory(int whiteTerritory) {
		this.whiteTerritory = whiteTerritory;
	}


	public int getBlackTilesCapture() {
		return blackTilesCapture;
	}


	public int getWhiteTilesCapture() {
		return whiteTilesCapture;
	}

	public State(){
		board = new BoardBitMapImpl();
		blackTilesCapture = 0;
		whiteTilesCapture = 0;
	}
	public State(Board board, int blackTilesCapture, int whiteTilesCapture, int whiteTerritory, int blackTerritory ){
		this.board = board;
		this.blackTilesCapture = blackTilesCapture;
		this.whiteTilesCapture = whiteTilesCapture;
		this.blackTerritory = blackTerritory;
		this.whiteTerritory = whiteTerritory;
	}
	
	public State clone() {
		return new State(board.clone(), blackTilesCapture, whiteTilesCapture, whiteTerritory, blackTerritory);
	}
	
	public Board getBoard(){
		return board;
	}
}
