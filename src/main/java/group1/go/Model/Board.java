package group1.go.Model;

import java.util.Collection;

public abstract class Board {
	
	public abstract char get(int i, int j);
	
	public char get(TilesPosition p){
		return get(p.getI(), p.getJ());
	}
	
	public abstract void add(int i , int j, char player);
	
	public void add(TilesPosition t, char player){
		add(t.getI(), t.getJ(), player);
	}
	
	public  abstract void remove(Collection<TilesPosition> toRemove );
	
	public abstract void remove(TilesPosition toRemove);
	
	public abstract Board clone();
	
	public abstract boolean isFull();
	
	public int tilesCardinal(){
		int sum=0;
		for(int i=0; i<=Constants.BOARDSIZE; i++){
			for(int j=0; j<=Constants.BOARDSIZE; j++){
				if(get(i, j)==Constants.WHITE|| get(i, j)==Constants.BLACK) sum++;
			}
		}
		return sum;
	}

}
