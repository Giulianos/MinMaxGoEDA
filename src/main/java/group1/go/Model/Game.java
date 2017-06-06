package group1.go.Model;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;

public class Game {

	State currentState;
	State previousState;
	State pre_previousState;
	char currentPlayer;
	char otherPlayer;
	
	
	//machine()
	
	public int isposible(int i , int j){
		
		Board currentBoard = currentState.getBoard();
		char tileAtPosition = currentBoard.get(i, j);
		if(tileAtPosition != Constants.EMPTY){
			return -3; //error de que hay una ficha
		}
		
		if(4 == getDegree(i , j , otherPlayer)){
			return -2; //quiere suicidarse
		}
		
		//previous state
		return 0; // no hay error
	}
	
	public void add(int i ,int j){
		Board board = currentState.getBoard();
		Board nextBoard = board.clone();
		int blackTilesCapture = currentState.getBlackTilesCapture();
		int whiteTilesCaputre = currentState.getWhiteTilesCapture(); 
		nextBoard.add(i, j, currentPlayer);
		ArrayList<TilesPosition> toRemove = eat(i,j,nextBoard.clone());
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
	
	public void endTurn(){
		char aux = currentPlayer;
		currentPlayer = otherPlayer;
		otherPlayer = aux;
	}
	
	public void startGame(){
		Random rand = new Random();
		int random = rand.nextInt() % 10;
		if(random > 5){
			currentPlayer = Constants.BLACK;
			otherPlayer = Constants.WHITE;
		}
		if(random < 5){
			otherPlayer = Constants.BLACK;
			currentPlayer = Constants.WHITE;
		}
		currentState = new State();
	}
	
	public ArrayList<TilesPosition> eat(int i, int j, Board board){
		ArrayList<TilesPosition> toRemoveUp = new ArrayList<TilesPosition>();
		ArrayList<TilesPosition> toRemoveDown = new ArrayList<TilesPosition>();
		ArrayList<TilesPosition> toRemoveRight = new ArrayList<TilesPosition>();
		ArrayList<TilesPosition> toRemoveLeft = new ArrayList<TilesPosition>();
		ArrayList<TilesPosition> rta = new ArrayList<TilesPosition>();
		
		if((board.get(i-1, j)==otherPlayer)&&eat(toRemoveUp, i-1, j, board)){
			rta.addAll(toRemoveUp);
		}
		if((board.get(i+1, j)==otherPlayer)&&eat(toRemoveDown, i+1, j, board)){
			rta.addAll(toRemoveDown);
		}

		if((board.get(i, j-1)==otherPlayer)&&eat(toRemoveLeft, i, j-1, board)){
			rta.addAll(toRemoveLeft);
		}
		if((board.get(i, j+1)==otherPlayer)&&eat(toRemoveRight, i, j+1, board)){
			rta.addAll(toRemoveRight);
		}
		return rta;
		
	}
	
	public boolean eat(ArrayList<TilesPosition> toRemove, int i, int j, Board board){
		
		char upC = board.get(i, j-1);
		char downC = board.get(i, j+1);
		char leftC = board.get(i-1, j);
		char rightC = board.get(i+1, j);
		boolean up = true;
		boolean down = true;
		boolean left = true;
		boolean right = true;
		board.add(i, j, currentPlayer);
		if(upC == Constants.EMPTY || downC == Constants.EMPTY || leftC == Constants.EMPTY || rightC == Constants.EMPTY){
			return false;
		}
		if ( upC == otherPlayer){
			up = eat(toRemove, i, j-1, board);
		}
		if(downC == otherPlayer){
			down = eat(toRemove, i, j+1, board);
		}
		if(leftC == otherPlayer){
			left = eat(toRemove, i-1, j, board);
		}
		if(rightC == otherPlayer){
			right = eat(toRemove, i+1, j, board);
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
		for(Entry<TilesPosition, Character> e: aux.entrySet()){
			if(e.getValue() == Constants.WHITE){
				white++;
			}
			if(e.getValue() == Constants.BLACK){
				black++;
			}
		}
		System.out.println(aux.entrySet().size());
		System.out.println(black);
		System.out.println(white);
		if(color == Constants.BLACK){
			return black;
		}
		if(color == Constants.WHITE){
			return white;
		}
		return black + white;
		
	}
	
	private HashMap<TilesPosition, Character> getSorrounding(int i, int j){
		Board board = currentState.getBoard();
		HashMap<TilesPosition, Character> aux = new HashMap<TilesPosition, Character>();
		char playerAux;
		if(j-1 >= 0){
			playerAux = board.get(i, j-1);
			if(playerAux != Constants.EMPTY){
				aux.put( new TilesPosition(i,j-1), playerAux);
			}
		}
		if(i-1 >= 0){
			playerAux = board.get(i-1, j);
			if(playerAux != Constants.EMPTY){
				aux.put( new TilesPosition(i-1,j), playerAux);
			}
		}
		if(j+1 <= Constants.BOARDSIZE){
			playerAux = board.get(i, j+1);
			if(playerAux != Constants.EMPTY){
				aux.put( new TilesPosition(i,j+1), playerAux);
			}
		}
		if(i+1 <= Constants.BOARDSIZE){
			playerAux = board.get(i+1, j);
			if(playerAux != Constants.EMPTY){
				aux.put( new TilesPosition(i+1,j), playerAux);
			}
		}
		return aux;
	}
	
	public Board getNewBoard(){
		return currentState.getBoard();
	}
	
	

}
