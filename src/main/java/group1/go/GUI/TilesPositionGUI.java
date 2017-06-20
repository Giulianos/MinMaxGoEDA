package group1.go.GUI;
import java.awt.Color;

public class TilesPositionGUI {
	
	Color color;
	int i;
	int j;
	
	public int getI() {
		return i;
	}
	public int getJ() {
		return j;
	}
	
	
	
	@Override
	public String toString() {
		StringBuilder sb= new StringBuilder();
		sb.append("(");
		sb.append(i);
		sb.append(",");
		sb.append(j);
		sb.append(")");
		return sb.toString();
	}
}
