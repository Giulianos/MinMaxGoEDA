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

			boardGUI.endGame(game.getBlackTerritory()+game.getState().getBlackTilesCapture(),game.getWhiteTerritory()+game.getState().getWhiteTilesCapture() );			return;
		}		if(isMachinePlaying){			System.out.println("game es :" + game.isFirstPass());			machinePlay(true);			}		if(game.endTurn()){			boardGUI.endGame(game.getBlackTerritory()+game.getState().getBlackTilesCapture(),game.getWhiteTerritory()+game.getState().getWhiteTilesCapture() );		};
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
		case Constants.VALID_MOVE:			//machineVs();
			game.add(i, j);
			boardGUI.drawBoard(game);
			if(game.endTurn()){				boardGUI.endGame(game.getBlackTerritory()+game.getState().getBlackTilesCapture(),game.getWhiteTerritory()+game.getState().getWhiteTilesCapture() );			};
			if(isMachinePlaying){				machinePlay(false);			}			break;			

		case Constants.KO:			boardGUI.KO();
		}
	}	private void machineVs(){		MinMaxTree minMax=new MinMaxTree(game.getState(), game.getCurrentPlayer(), machineDificult, new squareHeuristic());		Move m= minMax.getOptimalMoveBFS(false, isMachinePlaying, isMachinePlaying, machineDificult);		if(m.getPosition().getI()==-1){			if(game.pass()){				boardGUI.endGame(game.getBlackTerritory()+game.getState().getBlackTilesCapture(),game.getWhiteTerritory()+game.getState().getWhiteTilesCapture() );			}else{				return;			}		}		game.add(m.getPosition().getI(), m.getPosition().getJ());		boardGUI.drawBoard(game);		if(game.endTurn()){			boardGUI.endGame(game.getBlackTerritory()+game.getState().getBlackTilesCapture(),game.getWhiteTerritory()+game.getState().getWhiteTilesCapture() );		}		minMax=new MinMaxTree(game.getState(), game.getCurrentPlayer(), machineDificult, new squareHeuristic());		m= minMax.getOptimalMoveDFS(true, isMachinePlaying);		if(m.getPosition().getI()==-1){			if(game.pass()){				boardGUI.endGame(game.getBlackTerritory()+game.getState().getBlackTilesCapture(),game.getWhiteTerritory()+game.getState().getWhiteTilesCapture() );			}else{				return;			}		}		game.add(m.getPosition().getI(), m.getPosition().getJ());		boardGUI.drawBoard(game);		if(game.endTurn()){			boardGUI.endGame(game.getBlackTerritory()+game.getState().getBlackTilesCapture(),game.getWhiteTerritory()+game.getState().getWhiteTilesCapture() );		}			}

//			MinMaxTree minMax=new MinMaxTree(game.getState(), game.getCurrentPlayer(), 3, new squareHeuristic());////
//			Move m= minMax.getOptimalMoveDFS(false);//			if(m.getPosition().getI()==-1){//				if(game.pass()){////					boardGUI.endGame();////				}else{//					return;//				}//			}////
//			game.add(m.getPosition().getI(), m.getPosition().getJ());////
//			boardGUI.drawBoard(game.getState());////
//			if(game.endTurn()){////
//				boardGUI.endGame();////
//			};//
//		}////
//	}		public void machinePlay(boolean pass) {				MinMaxTree minMax=new MinMaxTree(game.getState(), game.getCurrentPlayer(), machineDificult, new squareHeuristic());		Move m= minMax.getOptimalMoveBFS(pass, pass, pass, machineDificult);		if(m.getPosition().getI()==-2){			System.out.println("Paso la maquina, game en:" + game.isFirstPass());			if(game.pass()){				System.out.println("la partida termino");				boardGUI.endGame(game.getBlackTerritory()+game.getState().getBlackTilesCapture(),game.getWhiteTerritory()+game.getState().getWhiteTilesCapture() );				return;			}else{				if(game.endTurn()){					boardGUI.endGame(game.getBlackTerritory()+game.getState().getBlackTilesCapture(),game.getWhiteTerritory()+game.getState().getWhiteTilesCapture() );				}				return;			}		}		game.add(m.getPosition().getI(), m.getPosition().getJ());		boardGUI.drawBoard(game);		if(game.endTurn()){			boardGUI.endGame(game.getBlackTerritory()+game.getState().getBlackTilesCapture(),game.getWhiteTerritory()+game.getState().getWhiteTilesCapture() );		}	}	private void playerPlay(int i, int j) {		game.add(i, j);		boardGUI.drawBoard(game);		if(game.endTurn()){			boardGUI.endGame(game.getBlackTerritory()+game.getState().getBlackTilesCapture(),game.getWhiteTerritory()+game.getState().getWhiteTilesCapture() );		};			}
	}		

	
	
	
	

