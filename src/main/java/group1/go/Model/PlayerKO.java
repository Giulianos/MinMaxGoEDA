package group1.go.Model;

public class PlayerKO {
	
	private int i;

	private int j;

	private boolean ko;


	public PlayerKO(int i, int j, boolean ko) {

	this.i = i;

	this.j = j;

	this.ko = ko;

	}

	public int getI() {

	return i;

	}

	public int getJ() {

	return j;

	}

	public boolean ko()

	{

	return ko;

	}

	public void setI(int i) {

	this.i = i;

	}

	public void setJ(int j) {

	this.j = j;

	}

	public void setKo(boolean ko) {

	this.ko = ko;

	}



}
