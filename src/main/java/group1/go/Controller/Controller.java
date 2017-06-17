package group1.go.Controller;

import java.awt.Point;

import group1.go.GUI.BoardGUI;
import group1.go.Model.Constants;
import group1.go.Model.Game;
import group1.go.Model.Heuristic;
import group1.go.Model.MinMaxTree;
import group1.go.Model.Move;
import group1.go.Model.State;
import group1.go.Model.heuristics.RandomHeuristic;
import group1.go.Model.heuristics.TestHeuristic;

public class Controller {

	private Game game;
	private BoardGUI boardGUI;
	
	public Controller(Game game, BoardGUI boardGUI) {
		this.game = game;
		this.boardGUI = boardGUI;
		start();
	}
	
	public void start(){
		game.startGame();
	}

	public void touch(int i, int j) {
		int rta = game.isposible(i, j);
		switch (rta){
		
		case Constants.SUICIDE:
			boardGUI.isSuicide();
			break;
		case Constants.TILEINPOSITION:
			boardGUI.tileInPosition();
			break;
		case 0:
			game.add(i, j);
			boardGUI.drawBoard(game.getState());
			game.endTurn();
			MinMaxTree minMax=new MinMaxTree(game.getState(), game.getCurrentPlayer(), 2, new TestHeuristic());
			Move m= minMax.getOptimalMoveBFS();
			game.add(m.getPosition().getI(), m.getPosition().getJ());
			boardGUI.drawBoard(game.getState());
			game.endTurn();
		case -4:
			break;
		}
	}
	
	
	
	

}
