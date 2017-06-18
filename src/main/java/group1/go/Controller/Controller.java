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
import group1.go.Model.heuristics.TestHeuristic;import group1.go.Model.heuristics.TrivialHeuristic;

public class Controller {

	private Game game;
	private BoardGUI boardGUI;		private int machineDificult;		private boolean isMachinePlaying;
	
	public Controller(Game game, BoardGUI boardGUI, int machineDificult, boolean isMAchinePlaying) {
		this.game = game;
		this.boardGUI = boardGUI;				this.machineDificult = machineDificult;				this.isMachinePlaying = isMAchinePlaying;
		start();
	}
	
	public void start(){
		game.startGame();
	}
	
	public void pass(){
		if(game.pass()){
			boardGUI.endGame();
		}		if(isMachinePlaying){			MinMaxTree minMax=new MinMaxTree(game.getState(), game.getCurrentPlayer(), 2, new TestHeuristic());			Move m= minMax.getOptimalMoveBFS();			game.add(m.getPosition().getI(), m.getPosition().getJ());			boardGUI.drawBoard(game.getState());			if(game.endTurn()){				boardGUI.endGame();			}		}
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
		case Constants.VALID_MOVE:
			game.add(i, j);
			boardGUI.drawBoard(game.getState());
			if(game.endTurn()){				boardGUI.endGame();			};						if(isMachinePlaying){

				MinMaxTree minMax=new MinMaxTree(game.getState(), game.getCurrentPlayer(), machineDificult, new TestHeuristic());
				Move m= minMax.getOptimalMoveBFS();
				game.add(m.getPosition().getI(), m.getPosition().getJ());
				boardGUI.drawBoard(game.getState());
				if(game.endTurn()){
					boardGUI.endGame();
				}			}
		case Constants.KO:
			break;
		}
	}
	
	
	
	

}
