package group1.go.Model;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Random;
import java.util.Set;

public class Game {
	
	private static PlayerKO whiteko;
	private static PlayerKO blackko;
	private State currentState;
	private State previousState;
	private State pre_previousState;
	private char currentPlayer;
	private char otherPlayer;
	private static boolean firstPass;
	private static boolean visited[][] = new boolean[Constants.BOARDSIZE+1][Constants.BOARDSIZE+1];
	
	
	
	/*
	 * @param int i,j 
	 * add to the board the current player to the position i,j
	 * also remove from the board, the tiles that the player "eat" when putting the tile in i,j
	 */
	public  void add(int i ,int j){
		clear();
		if(firstPass){
			firstPass = false; 
		}
		Board board = currentState.getBoard();
		Board nextBoard = board.clone();
		int blackTilesCapture = currentState.getBlackTilesCapture();
		int whiteTilesCaputre = currentState.getWhiteTilesCapture(); 
		nextBoard.add(i, j, currentPlayer);
		ArrayList<TilesPosition> toRemove = eat(i,j,nextBoard.clone(), currentPlayer);
		nextBoard.remove(toRemove);
		
		if(otherPlayer == Constants.BLACK){
			blackTilesCapture += toRemove.size();
		}
		if(otherPlayer == Constants.WHITE){
			whiteTilesCaputre += toRemove.size();
		}
		pre_previousState = previousState;
		previousState = currentState;
		currentState = new State(nextBoard.clone(), blackTilesCapture, whiteTilesCaputre,0,0);
	}
	
	/*
	 * end the turn by changing the current player 
	 * it calculates if the current board is full;
	 * @return true is the board is full and the game ended
	 */
	public boolean endTurn(){
		char aux = currentPlayer;
		currentPlayer = otherPlayer;
		otherPlayer = aux;
		if(currentState.getBoard().isFull()){
			endGame();
			return true;
		}
		return false;
	}
	
	/*
	 * validate if is the second time in a row that this method is called and there for 
	 * the game ended. If it's the first time then it ends the turn
	 *@return true if the player before passed and the game ended 
	 */
	public boolean pass(){
		if(firstPass){
			endGame();
			return true;
		}
		firstPass = true;
		endTurn();
		return false;
	}
	
	/*
	 * this method is call when the game end, and counts the territory of the current state
	 */
	public void endGame(){
		countTerritory(currentState);
	}
	
	/*
	 * this method set up the players and state 
	 */
	public void startGame(){
		currentPlayer = Constants.BLACK;
		otherPlayer = Constants.WHITE;
		currentState = new State();
		this.firstPass=false;
	}
	
	public void startGame(char currentPlayer, Board board){
		this.currentPlayer = currentPlayer;
		otherPlayer = currentPlayer==Constants.BLACK?Constants.WHITE:Constants.BLACK;
		currentState = new State(board, 0, 0, 0, 0);
		this.firstPass=false;
	}
	
	
	/*
	 * this methods calculates the tiles that are "eaten" by putting a tile of player
	 * in the position i, j
	 * @param  board the board to analyze the tiles that are eaten
	 * @return the ArrayList of the positions that are eaten
	 */
	public  static  ArrayList<TilesPosition> eat(int i, int j, Board board, char player){
		char enemy= (player==Constants.BLACK)? Constants.WHITE: Constants.BLACK;
		ArrayList<TilesPosition> toRemoveUp = new ArrayList<TilesPosition>();
		ArrayList<TilesPosition> toRemoveDown = new ArrayList<TilesPosition>();
		ArrayList<TilesPosition> toRemoveRight = new ArrayList<TilesPosition>();
		ArrayList<TilesPosition> toRemoveLeft = new ArrayList<TilesPosition>();
		ArrayList<TilesPosition> rta = new ArrayList<TilesPosition>();
		visited[i][j]=true;
		
		if((board.get(i-1, j)==enemy)&&visited[i-1][j]==false &&eat(toRemoveUp, i-1, j, board,player, true)){
			rta.addAll(toRemoveUp);
		}
		if((board.get(i+1, j)==enemy)&&visited[i+1][j]==false &&eat(toRemoveDown, i+1, j, board,player, true)){
			rta.addAll(toRemoveDown);
		}

		if((board.get(i, j-1)==enemy)&&visited[i][j-1]==false &&eat(toRemoveLeft, i, j-1, board,player, true)){
			rta.addAll(toRemoveLeft);
		}
		if((board.get(i, j+1)==enemy)&&visited[i][j+1]==false&&eat(toRemoveRight, i, j+1, board,player, true)){
			rta.addAll(toRemoveRight);
		}
		return rta;
		
	}
	
	/*
	 * @param toRemove the array where the method will put all the tiles that are eaten
	 * @param eaten if eaten is false then the playerToMove is the one that is being eaten
	 * 				 if it is true then is the on that eats 
	 * @return true if the tiles that are in toRemove, are surrounded by tiles of curr    
	 */
	public static  boolean eat(ArrayList<TilesPosition> toRemove, int i, int j, Board board,char playerToMove, boolean eaten){
		char enemy= (playerToMove==Constants.BLACK)? Constants.WHITE: Constants.BLACK;
		char upC = board.get(i, j-1);
		char downC = board.get(i, j+1);
		char leftC = board.get(i-1, j);
		char rightC = board.get(i+1, j);
		boolean up = true;
		boolean down = true;
		boolean left = true;
		boolean right = true;
		char cur;
		char other;
		if(eaten){
			cur = playerToMove;
			other = enemy;
		}
		else {
			cur = enemy;
			other = playerToMove;
		}
		
		if(j-1<0){
			upC = cur;
		}
		if(j+1 > Constants.BOARDSIZE){
			downC = cur;
		}
		if(i-1<0){
			leftC = cur;
		}
		if(i+1>Constants.BOARDSIZE){
			rightC = cur;
		}
		
		visited[i][j]=true;
		board.add(i, j, cur);
		if(upC == Constants.EMPTY || downC == Constants.EMPTY || leftC == Constants.EMPTY || rightC == Constants.EMPTY){
			return false;
		}
		if ( upC == other &&visited[i][j-1]==false){
			up = eat(toRemove, i, j-1, board,playerToMove, eaten);
		}
		if(downC == other &&visited[i][j+1]==false){
			down = eat(toRemove, i, j+1, board,playerToMove, eaten);
		}
		if(leftC == other&&visited[i-1][j]==false){
			left = eat(toRemove, i-1, j, board, playerToMove,eaten);
		}
		if(rightC == other && visited[i+1][j]==false ){
			right = eat(toRemove, i+1, j, board,playerToMove, eaten);
		}
		
		boolean rta =  up && down && left && right;
		if(rta == true){
			toRemove.add(new TilesPosition(i, j));
			return rta;
		}
		return rta;
		
	}

	/*
	 * this methods show you how man tiles of player surround the position i,j
	 */
	
	private int getDegree(int i, int j, char player) {
		
		HashMap<TilesPosition, Character> aux = getSurrounding(i, j);
		int white = 0;
		int black = 0;
		int empty=0;
		for(Entry<TilesPosition, Character> e: aux.entrySet()){
			if(e.getValue() == Constants.WHITE){
				white++;
			}
			if(e.getValue() == Constants.BLACK){
				black++;
			}
			if(e.getValue() == Constants.EMPTY){
				empty++;
			}
		}
		
		if(player == Constants.BLACK){
			return black;
		}
		if(player == Constants.WHITE){
			return white;
		}
		if(player==Constants.EMPTY){
			return empty;
		}
		return 4-aux.size();
		
	}
	
	/*
	 * this method calculate all tiles that are around the position i,j
	 * @return HashMap with all the positions and tiles that surrounds the position i,j 
	 */
	private HashMap<TilesPosition, Character> getSurrounding(int i, int j){
		Board board = currentState.getBoard();
		HashMap<TilesPosition, Character> aux = new HashMap<TilesPosition, Character>();
		char playerAux;
		if(j-1 >= 0){
			playerAux = board.get(i, j-1);
				aux.put( new TilesPosition(i,j-1), playerAux);
		}
		if(i-1 >= 0){
			playerAux = board.get(i-1, j);
				aux.put( new TilesPosition(i-1,j), playerAux);
		}
		if(j+1 <= Constants.BOARDSIZE){
			playerAux = board.get(i, j+1);
				aux.put( new TilesPosition(i,j+1), playerAux);
		}
		if(i+1 <= Constants.BOARDSIZE){
			playerAux = board.get(i+1, j);
				aux.put( new TilesPosition(i+1,j), playerAux);
		}
		return aux;
	}
	
	public State getState(){
		return currentState;
	}
	
	public char getCurrentPlayer() {
		return currentPlayer;
	}

	/*
	 * @return true if putting a tile in the position i,j generates a KO movment
	 */
	public boolean isKO(int i, int j){
	
		if(blackko==null){
			blackko= new PlayerKO(-1,-1,false);
			whiteko= new PlayerKO(-1,-1,false);
		}
		if(currentPlayer==Constants.BLACK){
			if(blackko.getI()!=i ||blackko.getJ() !=j){
				blackko.setI(-1);
				blackko.setJ(-1);
				blackko.setKo(false);
			}
		}
		if(currentPlayer==Constants.WHITE){
			if(whiteko.getI()!=i ||blackko.getJ() !=j){
				whiteko.setI(-1);
				whiteko.setJ(-1);
				whiteko.setKo(false);
			}
		}
		
		boolean flag = false;
	
		int otherdegree = getDegree(i,j,otherPlayer);
		int myDegree= getDegree(i,j, currentPlayer);
		int noMove= getDegree(i,j,Constants.NOMOVE);
	
		if(currentState.board.get(i, j)!=Constants.EMPTY){
			return false;
		}
		if(otherdegree < 3 ||(otherdegree==3 && noMove!=1)){
			return false;
		}
		int sideCurr=getDegree(i+1,j,currentPlayer);
	
		if(sideCurr== 3 ||( sideCurr==2 && getDegree(i+1,j,Constants.NOMOVE)==1 )){
			flag = true;
		}
		else if(getDegree(i-1,j,currentPlayer) == 3 || (getDegree(i-1,j,currentPlayer) == 2 && getDegree(i-1,j,Constants.NOMOVE)==1 )){
			flag = true;
		}
		else if(getDegree(i,j+1,currentPlayer) == 3 || (getDegree(i,j+1,currentPlayer) == 2 && getDegree(i,j+1,Constants.NOMOVE)==1 )){
			flag = true;
		}
		else if(getDegree(i,j-1,currentPlayer) == 3 || (getDegree(i,j-1,currentPlayer) == 2 && getDegree(i,j-1,Constants.NOMOVE)==1 )){
			flag = true;
		}
		if(currentPlayer == Constants.BLACK){
			if(!flag){
				blackko.setKo(false);
			}
			else{
				if(blackko.ko()){
					return true;
				}
				else{
					blackko.setKo(true);
					blackko.setI(i);
					blackko.setJ(j);
				}
			}
		}
		else{
			if(!flag){
				whiteko.setKo(false);
			}
			else{
				if(whiteko.ko()){
					return true;
				}
				else{
					whiteko.setKo(true);
					whiteko.setI(i);
					whiteko.setJ(j);
				}
			}
		}
		return false;
	}

	private boolean isAvailable(int i, int j){
		return (i<=Constants.BOARDSIZE+1) && (j<=Constants.BOARDSIZE+1);
	}
	public static boolean isFirstPass() {
		return firstPass;
	}
	private static void clear(){
		for(int i=0; i<=Constants.BOARDSIZE;i++){
			for(int j=0; j<=Constants.BOARDSIZE;j++){
				visited[i][j]=false;
			}
		}
	}
	
	public static Board add(int i ,int j, Board b , char p){
		clear();
		Board nextBoard = b.clone();
		nextBoard.add(i, j, p);
		ArrayList<TilesPosition> toRemove = eat(i,j,nextBoard.clone(), p);
		nextBoard.remove(toRemove);
		return nextBoard;
	} 
	
	/*
	 * this method counts the white territory and black territory given a state;
	 * it calls countTerritory for every position that is empty and that is not a white or 
	 * black territory
	 * It set the territories of the given state 
	 */
	public static void countTerritory(State state){
		Board currentBoard = state.getBoard().clone();
		HashSet<TilesPosition> whiteTerritory = new HashSet<TilesPosition>();
		HashSet<TilesPosition> blackTerritory = new HashSet<TilesPosition>();
		for(int i = 0; i <= Constants.BOARDSIZE; i++){
			for(int j = 0; j <=Constants.BOARDSIZE; j++ ){
				char tile = currentBoard.get(i, j);
				if(tile == Constants.EMPTY){
					TilesPosition aux = new TilesPosition(i, j);
					if(!whiteTerritory.contains(aux) && !blackTerritory.contains(aux)){
						if(!whiteTerritory.contains(aux)){
							clear();
							HashSet<TilesPosition> whiteAux = new HashSet<TilesPosition>();
							if(countTerritory(currentBoard.clone(), i, j, Constants.WHITE, whiteAux)){
								whiteTerritory.addAll(whiteAux);
							}
						}
						if(!blackTerritory.contains(aux)){
							clear();
							HashSet<TilesPosition>blackAux = new HashSet<TilesPosition>();
							if(countTerritory(currentBoard.clone(), i, j, Constants.BLACK, blackAux)){
								blackTerritory.addAll(blackAux);
							}
						}
					}
				}
			}
		}
		state.setBlackTerritory(blackTerritory.size());
		state.setWhiteTerritory( whiteTerritory.size());
		return;

	}

	
	/*
	 * @param set the set will contain the position of all the tiles in the territory
	 * In case the method returns false, the tiles in set do not form a territory and therefore 
	 * should be discarded
	 * this method uses a BFS algorithm to know if the tiles and all the surroundings are
	 * a territory
	 * in the case there is an enemy tile in the territory of player it finished looking
	 * @return true if the position and its surrounding is a territory from player
	 */
	public static boolean countTerritory(Board board, int i, int j, char player, HashSet<TilesPosition> set){
		Queue<TilesPosition> queue = new LinkedList<TilesPosition>();
		queue.offer(new TilesPosition(i, j));
		char enemy= (player==Constants.BLACK)? Constants.WHITE: Constants.BLACK;
		while(!queue.isEmpty()){
			TilesPosition current = queue.poll();
			if(visited[current.getI()][current.getJ()]){
				continue;
			}
			visited[current.getI()][current.getJ()] = true;
			char position = board.get(current);
			if(position == enemy){
				return false;
			}
			if(position == player){
				continue;
			}
			if(current.getJ()-1>0){
				queue.offer(new TilesPosition(current.getI(),current.getJ()-1));
			}
			if(current.getJ()+1 < Constants.BOARDSIZE){
				queue.offer(new TilesPosition(current.getI(),current.getJ()+1));
			}
			if(current.getI()-1>0){
				queue.offer(new TilesPosition(current.getI()-1,current.getJ()));
			}
			if(current.getI()+1<Constants.BOARDSIZE){
				queue.offer(new TilesPosition(current.getI()+1,current.getJ()));
			}
			set.add(current);
		}
		return true;
	}
	
	/*
	 * return the outcome putting a tile of currentPlayer in the position
	 * i,j (if it is a valid move or a KO, etc..)
	 * @return the out come of the move given by the class Constans
	 */
	public   int isposible(int i , int j){
		
		Board currentBoard = currentState.getBoard();
		char tileAtPosition = currentBoard.get(i, j);
		
		
		if(tileAtPosition != Constants.EMPTY){
			return Constants.TILEINPOSITION; //error de que hay una ficha
		}
		
		if(isKO(i,j)){
			return Constants.KO;
		}
		
		
		Board auxBoard1 = currentBoard.clone();
		Board auxBoard2 = currentBoard.clone();
		auxBoard1.add(i, j, currentPlayer);
		auxBoard2.add(i, j, currentPlayer);
		clear();
		if( eat(new ArrayList<TilesPosition>(),i,j,auxBoard1,currentPlayer,false )){
			clear();
			if(eat(i, j,auxBoard2, currentPlayer).isEmpty()){
			return Constants.SUICIDE; //quiere suicidarse
			}
		}
		
		
		return Constants.VALID_MOVE; // no hay error
	}
	
	public static boolean isWinner(State state, char player){
		countTerritory(state);
		int blackScore = state.getBlackTerritory() + state.getBlackTilesCapture();
		int whiteScore =  state.getWhiteTerritory() + state.getWhiteTilesCapture();
		if(player == Constants.BLACK){
			if(blackScore > whiteScore){
				return true;
			}
		}
		if(player == Constants.WHITE){
			if(whiteScore > blackScore){
				return true;
			}
		}
		return false;
	}


	

}
