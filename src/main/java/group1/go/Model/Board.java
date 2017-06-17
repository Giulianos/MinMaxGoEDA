package group1.go.Model;

import java.util.Collection;

public interface Board {
	
	public char get(int i, int j);
	
	//public char get(TilesPosition p);
	
	public void add(int i , int j, char player);
	
	//public void add(TilesPosition t, char player);
	
	public  void remove(Collection<TilesPosition> toRemove );
	
	public void remove(TilesPosition toRemove);
	
	public Board clone();

}
