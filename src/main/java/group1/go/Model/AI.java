package group1.go.Model;

import java.awt.*;

/**
 * Created by giulianoscaglioni on 30/5/17.
 */
public class AI {

    //private Game 

    public TilesPosition play(int depth, State currentState, char AIPlayer)
    {
        MinMaxTree tree = new MinMaxTree(currentState, AIPlayer, depth);
        return tree.getOptimalMove();
    }
    public int heuristic(State s){
    	return -1;
    }
}
