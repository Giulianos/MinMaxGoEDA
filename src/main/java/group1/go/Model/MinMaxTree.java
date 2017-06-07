package group1.go.Model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
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

    public MinMaxTree(State rootState, char AIPlayer, int depth, Heuristic heuristic) {
        this.AIPlayer = AIPlayer;
        this.enemyPlayer = (AIPlayer==Constants.WHITE)?Constants.BLACK:Constants.WHITE;
        this.depth = depth;
        this.heuristic = heuristic;
        rootNode = new StateNode(rootState, enemyPlayer);
    }

    private static class StateNode {
        private State state;
        private ArrayList<StateNode> nextStates;
        private char player;
      	private int score;
      	
        public StateNode(State state, char player) {
            this.state=state;
            nextStates=new ArrayList<StateNode>();
            this.player = player;
        }

    }
    
    private static class Move {
    	private TilesPosition pos;
    	private int score;
    	
    	public Move(TilesPosition pos, int score) {
    		this.pos = pos;
    		this.score = score;
		}
    }
    
    public TilesPosition getOptimalMove() {
    	return getOptimalMoveBFS(rootNode, depth).pos;
    }
    
    public TilesPosition getOptimalMoveDFS(StateNode state){
    	return getOptimalMoveDFSr(state, Constants.MAX_DEPTH).pos;
    }
    private Move getOptimalMoveDFSr(StateNode currentNode, int maxDepth, TilesPosition lastMove){
    	if(maxDepth == 0){
    		currentNode.score = heuristic.calculate(currentNode.state, currentNode.player);
    		return new Move(lastMove, currentNode.score);
    	} else{
    		
    	}
    	return null;
    }
    
    private Move completeScores(StateNode n) {
    	if(n.nextStates.isEmpty())
    		return 
    }
    
    public Move getOptimalMoveBFS() {
    	
    	Queue<StateNode> statesQ = new LinkedList<StateNode>();
    	
    	statesQ.offer(rootNode);

        int i, j;

    	StateNode currentNode;
    	StateNode addedNode;
    	char nodePlayer;
    	int currentDepth = 0;
    	
    	while(!statesQ.isEmpty()) {
    		currentNode = statesQ.poll();
    		if(currentDepth == depth) {
    			currentNode.score = heuristic.calculate(currentNode.state, currentNode.player);
    		} else {
    			for(i=0; i<Constants.BOARDSIZE; i++)
    	        {
    	            for(j=0; j<Constants.BOARDSIZE; j++)
    	            {
    	                if(GoRules.isPossibleMove(currentNode.state, new TilesPosition(i,j), (currentNode.player == enemyPlayer)?AIPlayer:enemyPlayer)) {
    	                	nodePlayer = (currentNode.player == enemyPlayer)?AIPlayer:enemyPlayer;
    	                	addedNode = new StateNode(GoRules.applyMove(currentNode.state, new TilesPosition(i,j), nodePlayer), nodePlayer);
    	                	if(!generatedStates.contains(addedNode.state)) {
	    	                	currentNode.nextStates.add(addedNode);
	    	                	generatedStates.add(addedNode.state);
	    	                	statesQ.offer(addedNode);
    	                	}
    	                }
    	            }
    	        }
    		}
    	}
        return completeScores(rootNode);
    }

}
