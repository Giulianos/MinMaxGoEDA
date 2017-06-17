package group1.go.Model;

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
	
	public boolean isTopBorder() {
		return i==0;
	}
	
	public boolean isLeftBorder() {
		return j==0;
	}
	
	public boolean isBottomBorder() {
		return i==Constants.BOARDSIZE;
	}
	
	public boolean isRightBorder() {
		return j==Constants.BOARDSIZE;
	}
	
	public int getI() {
		return i;
	}

	public int getJ() {
		return j;
	}

}
