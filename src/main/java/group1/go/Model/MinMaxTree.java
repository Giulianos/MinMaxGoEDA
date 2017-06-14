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
    private Game AIgame;

    public MinMaxTree(State rootState, char AIPlayer, int depth, Heuristic heuristic) {
        this.AIPlayer = AIPlayer;
        this.enemyPlayer = (AIPlayer==Constants.WHITE)?Constants.BLACK:Constants.WHITE;
        this.depth = depth;
        this.heuristic = heuristic;
        AIgame = new Game();
        rootNode = new StateNode(rootState, enemyPlayer);
    }

    private static class StateNode {
        private State state;
        private ArrayList<StateNode> nextStates;
        private char player;
      	private int score;
      	private TilesPosition move;
      	
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
    	
    	public State getState(State currentState){
    		add(pos.i, pos.j, AIPlayer, currentState.clone());
    	}
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
    
    private int completeScores(StateNode n) {
    	if(n.nextStates.isEmpty())
    		return n.score;
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
    	n.score = best;
    	return best;
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
    			nodePlayer = (currentNode.player == enemyPlayer)?AIPlayer:enemyPlayer;
    			List<TilesPosition> movements = AIgame.getMovements(nodePlayer);
    			for(TilesPosition m : movements){
    				addedNode = new StateNode(AIgame.applyMove(m,currentNode.state, nodePlayer), nodePlayer);
    				if(!generatedStates.contains(addedNode.state)) {
    					generatedStates.add(addedNode.state);
    					currentNode.nextStates.add(addedNode);
    					statesQ.offer(addedNode);
    				}
    			}
//    			for(i=0; i<Constants.BOARDSIZE; i++)
//    	        {
//    	            for(j=0; j<Constants.BOARDSIZE; j++)
//    	            {
//    	                if(GoRules.isPossibleMove(currentNode.state, new TilesPosition(i,j), (currentNode.player == enemyPlayer)?AIPlayer:enemyPlayer)) {
//    	                	nodePlayer = (currentNode.player == enemyPlayer)?AIPlayer:enemyPlayer;
//    	                	addedNode = new StateNode(GoRules.applyMove(currentNode.state, new TilesPosition(i,j), nodePlayer), nodePlayer);
//    	                	if(!generatedStates.contains(addedNode.state)) {
//	    	                	currentNode.nextStates.add(addedNode);
//	    	                	generatedStates.add(addedNode.state);
//	    	                	statesQ.offer(addedNode);
//    	                	}
//    	                }
//    	            }
//    	        }
    		}
    	}
        StateNode bestState = null;
        for(StateNode st : rootNode.nextStates) {
        	completeScores(st);
        	if(bestState==null) {
        		bestState = st;
        	} else if(bestState.score<st.score) {
        		bestState = st;
        	}
        }
    }

}
