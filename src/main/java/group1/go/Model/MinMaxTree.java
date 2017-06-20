package group1.go.Model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.BaseStream;

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
	private static long idCount=0;
	private TreeGenerator t= new TreeGenerator();

    public MinMaxTree(State rootState, char AIPlayer, int depth, Heuristic heuristic) {
        this.AIPlayer = AIPlayer;
        this.enemyPlayer = (AIPlayer==Constants.WHITE)?Constants.BLACK:Constants.WHITE;
        this.depth = depth;
        this.heuristic = heuristic;
        rootNode = new StateNode(rootState, enemyPlayer, 0);
        rootNode.move = new Move(0, 0, enemyPlayer);
    }
    
    private static class StateNode {
        private State state;
        private ArrayList<StateNode> nextStates;
        private char player;
      	private Move move;
      	private int level;
      	private long id;
      	
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
            this.id=idCount++;
        }

    }
	
	private int completeScores(StateNode n, boolean pass,  Integer prev) {
		Integer best=null, current;
		StateNode better=null;
		boolean currPass=false;
			if((n.move.getPosition().getI() == -2) ){
				if(pass){
					if(Game.isWinner(n.state, AIPlayer)){
						n.move.rate(Constants.MAX_HEURISTIC_VALUE);
					}else{
						n.move.rate(-1*Constants.MAX_HEURISTIC_VALUE);
					}
				return n.move.getScore();
			}else {
				currPass=true;
			}
			}
			if(n.nextStates.isEmpty()){
	    		n.move.rate(heuristic.calculate(n.state, AIPlayer));
	    		return n.move.getScore();
			}
	    	for(StateNode sn : n.nextStates) {
	    		if(best == null) {
	    			best = completeScores(sn,currPass,(best==null)? null: best);
	    			better=sn;
	    		} else {
	    			current = completeScores(sn, currPass,(best==null)? null: best);
	    			if(current>best && n.player==enemyPlayer) {
	    				best=current;
	    				better=sn;
	    			} else if(current<best && n.player==AIPlayer){
	    				best=current;
	    				better=sn;
	    			}
	    			if(prev!=null && poda){
						if((n.player==enemyPlayer) && best>prev){
							podados++;
							sn.move.setPoda(true);
							sn.move.rate(best);
							continue;
						}else if((n.player==AIPlayer )&& best<prev){
							podados++;
							sn.move.setPoda(true);
							sn.move.rate(best);
							continue;
						}
					}
	    		}
	 
	   		}
	    	better.move.setChosen(true);
	    	n.move.rate(best);
	    	return best;
			
    }
	
	private List<StateNode> neighbourStates(StateNode n) {
		
		StateNode auxState;
		TilesPosition auxPosition;
		Board auxBoard;
		Board currentBoard = n.state.board;
		char nodePlayer = (n.player==Constants.BLACK)?Constants.WHITE:Constants.BLACK;
		List<StateNode> retList = new LinkedList<StateNode>();
		for(int i=0; i<=Constants.BOARDSIZE; i++) {
			for(int j=0; j<=Constants.BOARDSIZE; j++) {
				auxPosition = new TilesPosition(i, j);
				if(GoRules.isPossible(currentBoard, auxPosition, nodePlayer)) {
					auxBoard = currentBoard.clone();
					auxBoard=Game.add(i, j, auxBoard, nodePlayer);
					auxState = new StateNode(new State(auxBoard, 0, 0, 0 ,0), nodePlayer, n.level+1);
					auxState.move= new Move(auxPosition,nodePlayer);
					retList.add(auxState);
				}
			}
		}
		if(n.state.board.tilesCardinal()>=100){
			StateNode repeat= new StateNode(n.state.clone(), nodePlayer, n.level+1);
			repeat.move= new Move(-2,-2, nodePlayer);
			retList.add(repeat);
		}
		return retList;
	}
	private void optimizeDepth(){
		if(rootNode.state.board.tilesCardinal()<=5 &&depth>=1){
			depth=1;
		}else if(rootNode.state.board.tilesCardinal()<=30&&depth>=2){
			depth=2;
		} else if(rootNode.state.board.tilesCardinal()<=40&&depth>=3){
			depth=3;
		}else if(rootNode.state.board.tilesCardinal()<=50&&depth>=4){
			depth=4;
		}else if(rootNode.state.board.tilesCardinal()<=60&&depth>=5){
			depth=5;
		}else if(rootNode.state.board.tilesCardinal()<=70&&depth>=6){
			depth=6;
		}else if(rootNode.state.board.tilesCardinal()<=80&&depth>=7){
			depth=7;
		}else if(rootNode.state.board.tilesCardinal()<=90&&depth>=8){
			depth=8;
		}else if(rootNode.state.board.tilesCardinal()<=100&&depth>=9){
			depth=9;
		}else if(rootNode.state.board.tilesCardinal()<=110&&depth>=10){
			depth=10;
		}
	}
	public Move getOptimalMoveBFS(boolean pass, boolean pod, boolean timer, long timemilis, boolean generateDOT) {
			if(!generateDOT) optimizeDepth();
			this.poda=pod;
			long init= System.currentTimeMillis();
    	
	    	Queue<StateNode> statesQ = new LinkedList<StateNode>();
	    	statesQ.offer(rootNode);
	
	    	StateNode currentNode;
	    	
	    	while(!statesQ.isEmpty() &&(!timer || System.currentTimeMillis()-init<timemilis-Constants.DELTA)) {
	    		currentNode = statesQ.poll();
	    		if(currentNode.level<depth) {
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
        		completeScores(st, pass,(bestState==null)? null: bestState.move.getScore());
		    	if(bestState==null) {
		    		bestState = st;
		    	} else if(bestState.move.getScore()<st.move.getScore()) {
		    		bestState = st;
		    	}
        }
        if(bestState==null){
        	return new Move(-2,-2,AIPlayer);
        }
        rootNode.move=new Move(-1,-1,AIPlayer); //dummy node
        rootNode.move.setChosen(true);
        if(generateDOT) treeGenerator();
        return bestState.move;
    }
	
	public Move getOptimalMoveDFS(boolean pod, boolean pass, boolean generateDOT){
		if(!generateDOT) optimizeDepth();
		this.poda=pod;
		rootNode.move= new Move(1,1, rootNode.player);
		rootNode.move.setChosen(true);
		Move m= getOptimalMoveDFS(rootNode,pass, null);
		if(m==null){ //la maquina dice paso
			return new Move(-2,-2, AIPlayer);
		}
		
		if(generateDOT) this.treeGenerator();
		return m;
	}
	
	static int podados=0;
	
	private Move getOptimalMoveDFS(StateNode n,  boolean pass, Integer prev){
		boolean currPass=false;
		if(n.move.getPosition().getI()==-2){
			if(pass){
				if(Game.isWinner(n.state, AIPlayer)){
					n.move.rate(Constants.MAX_HEURISTIC_VALUE);
				}else{
					n.move.rate(-1*Constants.MAX_HEURISTIC_VALUE);
				}
			
			return n.move;
		}else {
			currPass=true;
		}
			
		}
		if(n.level==depth){
			n.move.rate(heuristic.calculate(n.state, AIPlayer));
			return n.move;
		}
		Move best=null;
		StateNode better=null;
		List<StateNode> neighbours = neighbourStates(n);
		if(neighbours.isEmpty()){
			n.move.rate(heuristic.calculate(n.state, AIPlayer));
			return n.move;
		}
		for(StateNode nod : neighbours){
			if(!generatedStates.contains(nod)) {
				generatedStates.add(nod);
				n.nextStates.add(nod);
				
				
				if(prev!=null && poda &&best!=null){
					if(n.player==enemyPlayer && best.getScore()>prev){
						podados++;
						nod.move.setPoda(true);
						continue;
					}else if(n.player==AIPlayer && best.getScore()<prev){
						podados++;
						nod.move.setPoda(true);
						continue;
					}
				}
				Move aux= getOptimalMoveDFS(nod,currPass, (best==null)? null: best.getScore());
				if(aux==null) {
					continue; //hubo poda
				}
				
				if(best==null){
					best=aux;
					better=nod;
				}
				if(aux.getScore()>best.getScore() && (n.player==enemyPlayer)) {
    				best=aux;
    				better=nod;
    			} else if(aux.getScore()<best.getScore() && (n.player==AIPlayer)){
    				best=aux;
    				better=nod;
    			}
				
			}
		
	}
		if(best==null){
			n.move=null;
			return best;
		}
		n.move.rate(best.getScore());
		better.move.setChosen(true);
		if(n.level==0){
			return best;
		}
		return n.move;
	}
	
	
	
	// GENERACION DE DOT --------------------------------------------------
	
	
	/**
	 * must be call getOptimizedBFS before calling treeGenerator
	 * @return
	 */
	
	
	private void treeGenerator(){
		
		getDOT(rootNode);
		t.close();
	}
	
	public void getDOT(StateNode current){

		if(current.level == 0){
			t.drawNode(0, "START", "square", "red");
		}
		else{
			boolean score=true;
			String color= "white";
			if(current.move.isPoda()){
				color="grey";
				score=false;
			}
			else if(current.move.isChosen()){
				color="red";
			}
			t.drawNode(current.id, "\""+current.move.getPosition().toString()+" "+((score)?current.move.getScore():"PODA")+"\"",current.level%2==0? "square" : "box", color);
		}
		
		for(StateNode s : current.nextStates){
			t.drawArc(current.id, s.id);
			getDOT(s);
		}
	}
}

