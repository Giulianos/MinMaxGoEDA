package group1.go.Model;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;

public class Game {
	
	static PlayerKO whiteko;
	static PlayerKO blackko;
	State currentState;
	State previousState;
	State pre_previousState;
	char currentPlayer;
	char otherPlayer;
	static boolean firstPass;
	
	public char getCurrentPlayer() {
		return currentPlayer;
	}
	
	
	
	//machine()
	
	public   int isposible(int i , int j){
		
		Board currentBoard = currentState.getBoard();
		char tileAtPosition = currentBoard.get(i, j);
		
		
		if(tileAtPosition != Constants.EMPTY){
			return -3; //error de que hay una ficha
		}
		
		if(isKO(i,j)){
			return -4;
		}
		
		
		Board auxBoard1 = currentBoard.clone();
		Board auxBoard2 = currentBoard.clone();
		auxBoard1.add(i, j, currentPlayer);
		auxBoard2.add(i, j, currentPlayer);
		clear();
		if( eat(new ArrayList<TilesPosition>(),i,j,auxBoard1,currentPlayer,false )){
			clear();
			if(eat(i, j,auxBoard2, currentPlayer).isEmpty()){
			return -2; //quiere suicidarse
			}
		}
		
		
		return 0; // no hay error
	}
	
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
		currentState = new State(nextBoard, blackTilesCapture, whiteTilesCaputre);
		
	}
	
	public static Board add(int i ,int j, Board b , char p){
		clear();
		if(firstPass){
			firstPass = false; 
		}
		Board board = b;
		Board nextBoard = b.clone();
		nextBoard.add(i, j, p);
		ArrayList<TilesPosition> toRemove = eat(i,j,nextBoard.clone(), p);
		nextBoard.remove(toRemove);
		
		return nextBoard;
		
		
	} 
	
	private static void clear(){
		for(int i=0; i<=Constants.BOARDSIZE;i++){
			for(int j=0; j<=Constants.BOARDSIZE;j++){
				visited[i][j]=false;
				
			}
		}
	}
	
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
	
	public boolean pass(){
		if(firstPass){
			endGame();
			return true;
		}
		firstPass = true;
		endTurn();
		return false;
	}
	
	public void endGame(){
		
	}
	
	public void startGame(){
		Random rand = new Random();
		int random = rand.nextInt() % 10;
			currentPlayer = Constants.BLACK;
			otherPlayer = Constants.WHITE;
		currentState = new State();
	}
	
	static boolean visited[][] = new boolean[Constants.BOARDSIZE+1][Constants.BOARDSIZE+1];
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

	//te dice cuales fichas tiene alrededor
	//@param color si le pasas white te dice las que estan alrededor de color white
	
	private int getDegree(int i, int j, char color) {
		
		HashMap<TilesPosition, Character> aux = getSorrounding(i, j);
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
		System.out.println(aux.size());
		if(color == Constants.BLACK){
			return black;
		}
		if(color == Constants.WHITE){
			return white;
		}
		if(color==Constants.EMPTY){
			return empty;
		}
		return 4-aux.size();
		
	}
	
	private HashMap<TilesPosition, Character> getSorrounding(int i, int j){
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
	
	public boolean isKO(int i, int j)

	{

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

	if(currentState.board.get(i, j)!=Constants.EMPTY)

	{


	return false;

	}

	if(otherdegree < 3 ||(otherdegree==3 && noMove!=1))

	{

	return false;

	}
	int sideCurr=getDegree(i+1,j,currentPlayer);

	if(sideCurr== 3 ||( sideCurr==2 && getDegree(i+1,j,Constants.NOMOVE)==1 ))

	{

	flag = true;

	}

	else if(getDegree(i-1,j,currentPlayer) == 3 || (getDegree(i-1,j,currentPlayer) == 2 && getDegree(i-1,j,Constants.NOMOVE)==1 ))

	{

	flag = true;

	}

	else if(getDegree(i,j+1,currentPlayer) == 3 || (getDegree(i,j+1,currentPlayer) == 2 && getDegree(i,j+1,Constants.NOMOVE)==1 ))

	{

	flag = true;

	}

	else if(getDegree(i,j-1,currentPlayer) == 3 || (getDegree(i,j-1,currentPlayer) == 2 && getDegree(i,j-1,Constants.NOMOVE)==1 ))

	{

	flag = true;

	}

	System.out.println(flag);

	if(currentPlayer == Constants.BLACK)

	{

	if(!flag){

	blackko.setKo(false);

	}else{

	if(blackko.ko())

	{

	return true;

	}

	else

	{

	System.out.println("entra black");

	blackko.setKo(true);

	blackko.setI(i);

	blackko.setJ(j);

	}

	}

	}

	else 

	{

	if(!flag){

	whiteko.setKo(false);

	}else{

	if(whiteko.ko())

	{

	return true;

	}

	else

	{

	System.out.println("entra white");

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

}
