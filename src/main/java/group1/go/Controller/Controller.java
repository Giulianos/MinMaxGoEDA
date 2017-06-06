package group1.go.Controller;

import java.awt.Point;

import group1.go.GUI.BoardGUI;
import group1.go.Model.Constants;
import group1.go.Model.Game;

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
			boardGUI.drawBoard(game.getNewBoard());
			game.endTurn();
		}
	}
	
	
	
	

}
