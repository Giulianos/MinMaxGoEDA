package group1.go.Model;
import java.awt.Color;
import java.awt.Point;

public class TilesPosition {
	
	int i;
	int j;
	
	public TilesPosition(int i, int j) {
		this.i = i;
		this.j = j;
	}

	public int hashCode(){
		String s = String.valueOf(i);
		s += String.valueOf(j);
		return s.hashCode();
	}
	
	public boolean equals(Object other)
	{
		if(other == null)
		{
			return false;
		}
		if(other == this)
		{
			return true;
		}
		if(!this.getClass().equals(other.getClass()))
		{
			return false;
		}
		TilesPosition o = (TilesPosition)other;
		return ((o.i == this.i) && (o.j == this.j));
		
	}

	
	public int getI() {
		return i;
	}

	public int getJ() {
		return j;
	}

}
