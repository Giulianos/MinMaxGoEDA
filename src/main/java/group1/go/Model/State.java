package group1.go.Model;
import java.util.HashSet;

import javax.swing.text.html.HTMLDocument.HTMLReader.BlockAction;

public class State {

	Board board;
	int blackTilesCapture; 
	int whiteTilesCapture;
	
	public int getBlackTilesCapture() {
		return blackTilesCapture;
	}


	public int getWhiteTilesCapture() {
		return whiteTilesCapture;
	}


	public State(Board board, int blackTilesCapture, int whiteTilesCapture){
		this.board = board;
		this.blackTilesCapture = blackTilesCapture;
		this.whiteTilesCapture = whiteTilesCapture;
	}
	
	
	public Board getBoard(){
		return board;
	}
}
