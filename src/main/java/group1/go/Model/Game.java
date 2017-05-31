package group1.go.Model;
import java.awt.Color;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Game {

	State currentState;
	State previousState;
	State pre_previousState;
	int currentPlayer;
	int otherPlayer;
	
	
	//machine()
	
	public int isposible(int i , int j){
		
		Board currentBoard = currentState.getBoard();
		char tileAtPosition = currentBoard.get(i, j);
		if(tileAtPosition == Constants.EMPTY){
			return -3; //error de que hay una ficha
		}
		
		if(4 == getDegree(i , j , otherPlayer)){
			return -2; //quiere suicidarse
		}
		
		//previous state
		return 0; // no hay error
	}
	
	public add(int i ,int j, int player){
		
	}


	private int getDegree(int i, int j, int color) {
		
		HashMap<TilesPosition, Character> aux = getSorrounding(i, j);
		int white = 0;
		int black = 0;
		
		/*for(TilesPosition t: aux){
			if(t.getPlayer() == Constants.WHITE){
				white++;
			}
			if(t.getPlayer() == Constants.BLACK){
				black++;
			}
		}
		if(color == Constants.BLACK){
			return black;
		}
		if(color == Constants.WHITE){
			return white;
		}
		return black + white;
		*/
	}
	
	private HashMap<TilesPosition, Character> getSorrounding(int i, int j){
		Board board = currentState.getBoard();
		HashMap<TilesPosition, Character> aux = new HashMap<TilesPosition, Character>();
		char playerAux;
		if(j-1 >= 0){
			playerAux = board.get(i, j-1);
			if(playerAux != Constants.EMPTY){
				aux.put( new TilesPosition(i,j), playerAux);
			}
		}
		if(i-1 >= 0){
			playerAux = board.get(i-1, j);
			if(playerAux != Constants.EMPTY){
				aux.put( new TilesPosition(i-1,j), playerAux);
			}
		}
		if(j+1 >= Constants.BOARDSIZE){
			playerAux = board.get(i, j+1);
			if(playerAux != Constants.EMPTY){
				aux.put( new TilesPosition(i,j+1), playerAux);
			}
		}
		if(i+1 >= Constants.BOARDSIZE){
			playerAux = board.get(i+1, j);
			if(playerAux != Constants.EMPTY){
				aux.put( new TilesPosition(i,j-1), playerAux);
			}
		}
		return aux;
	}
	
	

}
