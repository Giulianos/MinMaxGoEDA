package group1.go.Model.heuristics;

import group1.go.Model.Heuristic;
import group1.go.Model.State;

public class RandomHeuristic implements Heuristic {

	public int calculate(State s, char player) {
		return (int)(Math.random()*100);
	}

}
