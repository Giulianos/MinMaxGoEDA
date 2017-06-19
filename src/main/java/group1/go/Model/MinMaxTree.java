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
	
	private HashSet<StateNode> generatedStates = new HashSet<StateNode>();
    private char AIPlayer;
    private char enemyPlayer;
    private StateNode rootNode;
    private int depth;
    private Heuristic heuristic;
    static int num = 0;
    private Game g;
    private boolean poda;

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
      	
      	@Override
      	public int hashCode() {
      		return state.board.hashCode();
      	}
      	
      	@Override
      	public boolean equals(Object obj) {
      		if(obj==null) return false;
      		if(obj==this) return true;
      		
      		if(!obj.getClass().equals(this.getClass())) return false;
      		
      		StateNode s= (StateNode) obj;
      		return s.player==this.player && s.state.board.equals(this.state.board);
      	}
      	
        public StateNode(State state, char player, int level) {
            this.state=state;
            nextStates=new ArrayList<StateNode>();
            this.player = player;
            this.level=level;
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
		//System.out.println("Procesing neighbour states..." + num++);
		StateNode auxState;
		TilesPosition auxPosition;
		Board auxBoard;
		Board currentBoard = n.state.board;
		char nodePlayer = (n.player==Constants.BLACK)?Constants.WHITE:Constants.BLACK;
		List<StateNode> retList = new LinkedList<StateNode>();
		for(int i=0; i<=Constants.BOARDSIZE; i++) {
			for(int j=0; j<=Constants.BOARDSIZE; j++) {
				//System.out.println("trying");
				auxPosition = new TilesPosition(i, j);
				if(GoRules.isPossible(currentBoard, auxPosition, nodePlayer)) {
					System.out.println("Enter:"+ auxPosition.i + " " + auxPosition.j );
					auxBoard = currentBoard.clone();
					auxBoard=Game.add(i, j, auxBoard, nodePlayer);
					auxState = new StateNode(new State(auxBoard, 0, 0), nodePlayer, n.level+1);
					auxState.move= new Move(auxPosition,nodePlayer);
					retList.add(auxState);
				}
			}
		}
		for(int i = 0; i<retList.size(); i++){
			System.out.println(i + " RetList: " +retList.get(i).move.getPosition().i + " " + retList.get(i).move.getPosition().j);
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
	    			//System.out.println("calculating heuristic");
	   	    		currentNode.move.rate(heuristic.calculate(currentNode.state, AIPlayer));
	   	    		//System.out.println("La heuristica es:" + currentNode.move.getScore() );
	    		} else {
	    			if( currentNode.player!=step){
	    				currDepth++;
	    				step=currentNode.player;
	    			}
	    			//System.out.println("Call to neighbour processing...");
	    			List<StateNode> neighbours = neighbourStates(currentNode);
	    			for(StateNode n : neighbours){
	    				if(!generatedStates.contains(n)) {
	    					generatedStates.add(n);
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
		    	} else if(bestState.move.getScore()>st.move.getScore()) {
		    		bestState = st;
		    	}
        }
        if(bestState==null){
        	return new Move(-1,-1,AIPlayer);
        }
        return bestState.move;
    }
	
	public Move getOptimalMoveDFS(boolean poda){
		this.poda=poda;
		rootNode.move= new Move(null, rootNode.player);
		Move m= getOptimalMoveDFS(rootNode, null);
		System.out.println("los podados son:" +podados);
		System.out.println("la heuristica ganadora es:" + m.getScore());
		return m;
	}
	
	static int podados=0;
	
	private Move getOptimalMoveDFS(StateNode n, Integer prev){
		System.out.println(n.level);
		if(n.level==depth){
			n.move.rate(heuristic.calculate(n.state, AIPlayer));
			return n.move;
		}
		Move best=null;
		List<StateNode> neighbours = neighbourStates(n);
		if(neighbours.isEmpty()){
			n.move.rate(heuristic.calculate(n.state, AIPlayer));
			return n.move;
		}
		for(StateNode nod : neighbours){
			if(!generatedStates.contains(nod)) {
				generatedStates.add(nod);
				n.nextStates.add(nod);
				Move aux= getOptimalMoveDFS(nod, (best==null)? null: best.getScore());
				if(aux==null) continue; //hubo poda
				
				if(best==null){
					best=aux;
				}
				if(aux.getScore()>best.getScore() && n.player==enemyPlayer) {
    				best=aux;
    			} else if(aux.getScore()<best.getScore() && n.player==AIPlayer){
    				best=aux;
    			}
				if(prev!=null && poda){
					if(n.player==enemyPlayer && best.getScore()<prev){
						podados++;
						n.move=null;
						return null;
					}else if(n.player==AIPlayer && best.getScore()>prev){
						podados++;
						n.move=null;
						return null;
					}
				}
			}
		
	}
		if(best==null){
			n.move=null;
			return best;
		}
		System.out.println("el nodo es:" + n.level);
		n.move.rate(best.getScore());
		return best;
	}
	
	
	
	
}

