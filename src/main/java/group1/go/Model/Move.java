package group1.go.Model;

public class Move {
	private TilesPosition position;
	private char player;
	private Integer score; //For the ai to rate the move
	private boolean poda;
	private boolean chosen;
	public void setPoda(boolean poda) {
		this.poda = poda;
	}
	public void setChosen(boolean chosen) {
		this.chosen = chosen;
	}
	
	public Move(TilesPosition position, char player) {
		if(position==null){
			this.position=null;
		}else{
		this.position = new TilesPosition(position.i, position.j); //Better clone the position (might reduce performance)
		}
		this.player = player;
		this.poda=false;
		this.chosen=false;
	}
	
	public Move(int i, int j, char player) {
		this.position = new TilesPosition(i, j);
		this.player = player;
		this.score=null;
	}
	
	public void rate(int score) {
		this.score = score;
	}
	
	public int getScore()
	{
		return score;
	}
	
	public TilesPosition getPosition() {
		return position;
	}
	
	public char getPlayer() {
		return player;
	}
	
	public Move clone(){
		Move m= new Move(position, player);
		m.rate(score);
		return m;
	}
}