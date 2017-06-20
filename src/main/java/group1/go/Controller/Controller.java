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
import group1.go.Model.heuristics.TestHeuristic;import group1.go.Model.heuristics.TrivialHeuristic;import group1.go.Model.heuristics.squareHeuristic;

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
			boardGUI.endGame();			return;
		}		if(isMachinePlaying){			machinePlay(true);			}		}

	public void touch(int i, int j) {
		int rta = game.isposible(i, j);
		switch (rta){
		case Constants.SUICIDE:
			boardGUI.isSuicide();
			break;
		case Constants.TILEINPOSITION:
			boardGUI.tileInPosition();
			break;
		case Constants.VALID_MOVE:			//machineVs();
			game.add(i, j);
			boardGUI.drawBoard(game.getState());
			if(game.endTurn()){				boardGUI.endGame();			};
			if(isMachinePlaying){				machinePlay(false);			}			break;			
		case Constants.KO:			boardGUI.KO();
		}
	}		public void machinePlay(boolean pass) {				MinMaxTree minMax=new MinMaxTree(game.getState(), game.getCurrentPlayer(), machineDificult, new squareHeuristic());		long init= System.currentTimeMillis();		Move m= minMax.getOptimalMoveBFS(pass, true,false, 2000);				if(m.getPosition().getI()==-2){			if(game.pass()){				boardGUI.endGame();				return;			}else{				if(game.endTurn()){					boardGUI.endGame();				}				return;			}		}		game.add(m.getPosition().getI(), m.getPosition().getJ());		System.out.println("el delta es:" + (System.currentTimeMillis()-init));		boardGUI.drawBoard(game.getState());		if(game.endTurn()){			boardGUI.endGame();		}	}	private void playerPlay(int i, int j) {		game.add(i, j);		boardGUI.drawBoard(game.getState());		if(game.endTurn()){			boardGUI.endGame();		};			}
	
	
	
	

}
