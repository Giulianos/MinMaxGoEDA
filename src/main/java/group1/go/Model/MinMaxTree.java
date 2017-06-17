package group1.go.Model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by giulianoscaglioni on 30/5/17.
 */

public class MinMaxTree {
	
	private HashSet<State> generatedStates = new HashSet<State>();
    private char AIPlayer;
    private char enemyPlayer;
    private StateNode rootNode;
    private int depth;
    private Heuristic heuristic;
    static int num = 0;

    public MinMaxTree(State rootState, char AIPlayer, int depth, Heuristic heuristic) {
        this.AIPlayer = AIPlayer;
        this.enemyPlayer = (AIPlayer==Constants.WHITE)?Constants.BLACK:Constants.WHITE;
        this.depth = depth;
        this.heuristic = heuristic;
        rootNode = new StateNode(rootState, enemyPlayer, 0);
    }

    private static class StateNode {
        private State state;
        private ArrayList<StateNode> nextStates;
        private char player;
      	private Move move;
      	private int level;
      	
        public StateNode(State state, char player, int level) {
            this.state=state;
            nextStates=new ArrayList<StateNode>();
            this.player = player;
        }

    }
	
	private int completeScores(StateNode n) {
	    	if(n.nextStates.isEmpty())
	    		return n.move.getScore();
	    	Integer best=null, current;
	    	for(StateNode sn : n.nextStates) {
	    		if(best == null) {
	    			best = completeScores(sn);
	    		} else {
	    			current = completeScores(sn);
	    			if(current<best && n.player==enemyPlayer) {
	    				best=current;
	    			} else if(current>best && n.player==AIPlayer){
	    				best=current;
	    			}
	    		}
	    	}
	    	n.move.rate(best);
	    	return best;
    }
	
	private List<StateNode> neighbourStates(StateNode n) {
		System.out.println("Procesing neighbour states..." + num++);
		StateNode auxState;
		TilesPosition auxPosition;
		Board auxBoard;
		Board currentBoard = n.state.board;
		char nodePlayer = n.player==Constants.BLACK?Constants.WHITE:Constants.BLACK;
		List<StateNode> retList = new LinkedList<StateNode>();
		for(int i=0; i<=Constants.BOARDSIZE; i++) {
			for(int j=0; j<=Constants.BOARDSIZE; j++) {
				auxPosition = new TilesPosition(i, j);
				if(GoRules.isPossible(currentBoard, auxPosition, nodePlayer)) {
					auxBoard = currentBoard.clone();
					((BoardMapImpl)auxBoard).add(auxPosition, nodePlayer);
					GoRules.applyMove(auxBoard, nodePlayer, auxPosition);
					auxState = new StateNode(new State(auxBoard, 0, 0), nodePlayer, n.level+1);
					auxState.move= new Move(auxPosition,nodePlayer);
					retList.add(auxState);
				}
			}
		}
		return retList;
	}
	
	public Move getOptimalMoveBFS() {
    	
	    	Queue<StateNode> statesQ = new LinkedList<StateNode>();
	    	
	    	statesQ.offer(rootNode);
	
	    	StateNode currentNode;
	    	boolean didProcessRoot = false;
	    	
	    	int currDepth=0;
	    	 char step= rootNode.player;
	    	
	    	while(!statesQ.isEmpty()) {
	    		currentNode = statesQ.poll();
	    		if(currDepth==depth) {
	    			System.out.println("calculating heuristic");
	   	    		currentNode.move.rate(heuristic.calculate(currentNode.state, currentNode.player));
	    		} else {
	    			if( currentNode.player!=step){
	    				currDepth++;
	    				step=currentNode.player;
	    			}
	    			System.out.println("Call to neighbour processing...");
	    			List<StateNode> neighbours = neighbourStates(currentNode);
	    			for(StateNode n : neighbours){
	    				if(!generatedStates.contains(n.state)) {
	    					generatedStates.add(n.state);
	    					currentNode.nextStates.add(n);
	    					statesQ.offer(n);
	    				}
	    			}
	    		}
	    	}
        StateNode bestState = null;
        for(StateNode st : rootNode.nextStates) {
		    	completeScores(st);
		    	if(bestState==null) {
		    		bestState = st;
		    	} else if(bestState.move.getScore()<st.move.getScore()) {
		    		bestState = st;
		    	}
        }
        return bestState.move;
    }
	
}

