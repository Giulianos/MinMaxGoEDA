package group1.go.Model;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by giulianoscaglioni on 30/5/17.
 */
public class MinMaxTree {

    private HashSet<State> generatedStates=new HashSet<State>();
    private int AIPlayer;
    private int EnemyPlayer;

    public MinMaxTree(int AIPlayer) {
        this.AIPlayer = AIPlayer;
        this.EnemyPlayer = (AIPlayer==Constants.WHITE)?Constants.BLACK:Constants.WHITE;
    }

    private static class StateNode {
        private State currentState;
        private ArrayList<StateNode> nextStates;

        public StateNode(State state) {
            currentState=state;
        }

    }

    private void generateNextStates(StateNode state, int depth)
    {
        if(depth==0 || state==null)
            return;

        //Else, generate next states recursively

        int i, j;

        for(i=0; i<Constants.BOARDSIZE; i++)
        {
            for(j=0; j<Constants.BOARDSIZE; j++)
            {
                if(GoRules.isPossibleMove(state.currentState, new Point(i, j), (depth%2==0)?AIPlayer:EnemyPlayer)) {
                  State aux = new State(new Board)
                }
            }
        }

    }

}
