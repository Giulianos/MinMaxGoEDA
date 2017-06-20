package group1.go.Controller;
import group1.go.GUI.BoardGUI;
import group1.go.Model.Constants;
import group1.go.Model.Game;
import group1.go.Model.MinMaxTree;
import group1.go.Model.Move;import group1.go.Model.heuristics.squareHeuristic;
public class Controller {
	private Game game;
	private BoardGUI boardGUI;	private int machineDificult;	private boolean isMachinePlaying;
	private boolean depthNotTime;
	private long maxTime;
	private boolean prune;
	
	public Controller(Game game, BoardGUI boardGUI, int machineDificult, boolean isMAchinePlaying, boolean depthNotTime, int maxTime, boolean prune) {
		
		this.game = game;
		this.boardGUI = boardGUI;		this.machineDificult = machineDificult;		this.isMachinePlaying = isMAchinePlaying;
		this.depthNotTime = depthNotTime;
		this.maxTime = maxTime*1000; //Lo paso a milisegundos
		this.prune = prune;
		start();
	}
	
	public Controller(Game game, BoardGUI boardGUI, int machineDificult, boolean isMAchinePlaying) {
		
		this.game = game;
		this.boardGUI = boardGUI;
		this.machineDificult = machineDificult;
		this.isMachinePlaying = isMAchinePlaying;
		this.depthNotTime = true;
		prune=true;
		maxTime = 1000;
		start();
	}
	public void start(){
		game.startGame();
	}
	
	public void pass(){
		if(game.pass()){
			boardGUI.endGame(game.getState().getBlackTerritory()+game.getState().getBlackTilesCapture(),game.getState().getWhiteTerritory()+game.getState().getWhiteTilesCapture() );			return;
		}				if(isMachinePlaying){			machinePlay(true);			}		}
	public void touch(int i, int j) {		int rta = game.isposible(i, j);		switch (rta){			case Constants.SUICIDE:				boardGUI.isSuicide();				break;			case Constants.TILEINPOSITION:				boardGUI.tileInPosition();				break;			case Constants.VALID_MOVE:				game.add(i, j);				boardGUI.drawBoard(game.getState());				if(game.endTurn()){					boardGUI.endGame(game.getState().getBlackTerritory()+game.getState().getBlackTilesCapture(),game.getState().getWhiteTerritory()+game.getState().getWhiteTilesCapture() );				};				if(isMachinePlaying){					machinePlay(false);				}				break;			case Constants.KO:				boardGUI.KO();			}
	}		public void machinePlay(boolean pass) {				MinMaxTree minMax=new MinMaxTree(game.getState(), game.getCurrentPlayer(), machineDificult, new squareHeuristic());		Move m= minMax.getOptimalMoveBFS( false, prune,  !depthNotTime,  maxTime, false);		if(m.getPosition().getI()==-2){ //-2 indica que se decide pasar			if(game.pass()){				boardGUI.endGame(game.getState().getBlackTerritory()+game.getState().getBlackTilesCapture(),game.getState().getWhiteTerritory()+game.getState().getWhiteTilesCapture() );				return;			}else{				if(game.endTurn()){					boardGUI.endGame(game.getState().getBlackTerritory()+game.getState().getBlackTilesCapture(),game.getState().getWhiteTerritory()+game.getState().getWhiteTilesCapture() );				}				return;			}		}		game.add(m.getPosition().getI(), m.getPosition().getJ());		boardGUI.drawBoard(game.getState());		if(game.endTurn()){			boardGUI.endGame(game.getState().getBlackTerritory()+game.getState().getBlackTilesCapture(),game.getState().getWhiteTerritory()+game.getState().getWhiteTilesCapture() );		}	}
}

	
	
	
	

