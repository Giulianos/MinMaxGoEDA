package group1.go.GUI;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashSet;

import javax.swing.JPanel;

import group1.go.Model.Board;
import group1.go.Model.Constants;

public class TilesPanel extends JPanel {

	private final int ovalWith = 20;
	private final int ovalHeight = 20;
	private final double deltaY =  39.6;
	private final double deltaX = 40;
	private final int firstPositionX = 12;
	private final int firstPositionY = 19;
	private Board board;
	
	public TilesPanel() {
		
	}
	
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		 Graphics2D g2 = (Graphics2D) g;
		if(board != null){
		for(int i = 0; i<Constants.BOARDSIZE; i++){
			for(int j = 0; j<Constants.BOARDSIZE; j++){
				char tile = board.get(i, j);
				if(tile != Constants.EMPTY){
					if(tile == Constants.BLACK){
						g2.setColor(Color.BLACK);
					}
					else{
						g2.setColor(Color.WHITE);
					}
					int x = (int) (firstPositionX + deltaX*i);
					int y =  (int) (firstPositionY + deltaY*j);
					g2.fillOval(x, y, ovalWith, ovalHeight);
				}
			}
		}
	}
	   
	    
	   
	}
	
	public void drawBoard(Board board){
		this.board = board;
		this.repaint();
	}
	
	

}
