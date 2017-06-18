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

}
