package group1.go.Model.heuristics;

import group1.go.Model.Constants;
import group1.go.Model.Heuristic;
import group1.go.Model.State;

public class TrivialHeuristic implements Heuristic {

	public int calculate(State s, char player) {
		return calculate1(s, player);
	}

	public int calculate1(State s, char player) {
		char other= (player==Constants.BLACK)? Constants.WHITE: Constants.BLACK;
		int score=0;
		for(int i=0; i<=Constants.BOARDSIZE; i++) {
			for(int j=0; j<=Constants.BOARDSIZE; j++) {
				if(s.getBoard().get(i, j)==player) {
					score+=1;
				} else if(s.getBoard().get(i, j)==other){
					score--;
				}
			}
		}
		return score;
	}
	
	

}
