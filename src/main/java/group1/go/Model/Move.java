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
		this.score=null;
	}
	
	public Move(int i, int j, char player) {
		this.position = new TilesPosition(i, j);
		this.player = player;
		this.score=null;
		this.poda=false;
	}
	
	public void rate(int score) {
		this.score = score;
	}
	
	public Integer getScore()
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
	
	public boolean isPoda() {
		return poda;
	}
	public boolean isChosen() {
		return chosen;
	}
}