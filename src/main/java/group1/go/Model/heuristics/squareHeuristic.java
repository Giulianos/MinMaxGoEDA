package group1.go.Model.heuristics;

import group1.go.Model.Board;
import group1.go.Model.Constants;
import group1.go.Model.Heuristic;
import group1.go.Model.State;

public class squareHeuristic implements Heuristic{
	
	public int calculate(State s, char player) {
		int a=calculate1(s, player==Constants.BLACK?Constants.WHITE:Constants.BLACK);
		return calculate1(s, player) - a;
	}

	public int calculate1(State s, char player) {
		int score=0;
		char enemy= (player==Constants.BLACK)? Constants.WHITE: Constants.BLACK;
		boolean foundPlayer=false;
		for(int i=0; i<=Constants.BOARDSIZE; i++) {
			for(int j=0; j<=Constants.BOARDSIZE; j++) {
				if(s.getBoard().get(i, j)==player) {
					score+=getSorround(s,i,j, player);
				} 
			}
		}
		return score;
	}

	private int getSorround(State s,int i, int j, char player) {
		Board b= s.getBoard();
		return ((b.get(i-1, j-1)==player)?1:0 )+ ((b.get(i+1, j+1)==player)?1:0) + ((b.get(i-1, j+1)==player)?1:0 )+((b.get(i+1, j-1)==player)?1:0);
	}

}
