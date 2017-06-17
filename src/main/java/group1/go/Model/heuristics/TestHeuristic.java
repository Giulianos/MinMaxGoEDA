package group1.go.Model.heuristics;

import group1.go.Model.Constants;
import group1.go.Model.Heuristic;
import group1.go.Model.State;

public class TestHeuristic implements Heuristic{

	public int calculate(State s, char player) {
		return calculate1(s, player) - calculate1(s, player==Constants.BLACK?Constants.WHITE:Constants.WHITE);
	}

	public int calculate1(State s, char player) {
		int score=0;
		boolean foundPlayer=false;
		for(int i=0; i<=Constants.BOARDSIZE; i++) {
			for(int j=0; i<=Constants.BOARDSIZE; j++) {
				if(s.getBoard().get(i, j)==player) {
					score+=foundPlayer?2:1; foundPlayer=true;
				} else {
					foundPlayer=false;
				}
			}
		}
		return score;
	}
	
}
