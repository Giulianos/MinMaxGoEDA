package group1.go;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import group1.go.Model.Board;
import group1.go.Model.BoardBitMapImpl;
import group1.go.Model.Constants;


public class BoardBuilder {
	
	static public Board build(String filename) throws IOException {
		FileReader fr = new FileReader(filename);
		BufferedReader br = new BufferedReader(fr);
		BoardBitMapImpl board = new BoardBitMapImpl();
		for(int i=0; i<=Constants.BOARDSIZE; i++) {
			String aux = br.readLine();
			//System.out.println(aux);
			if(aux==null || aux.length()<13) {
				br.close();
				fr.close();
				return null;
			}
			for(int j=0; j<=Constants.BOARDSIZE; j++) {
				char c = aux.charAt(j);
				switch(c) {
				case '1' : board.add(i, j, Constants.WHITE); break;
				case '2' : board.add(i, j, Constants.BLACK); break;
				default: break;
				}
			}
		}
		br.close();
		fr.close();
		return board;
	}
	
//	static public void main(String[] args) {
//		try {
//			Board b = build("board.txt");
//			for(int i=0; i<13; i++) {
//				for(int j=0; j<13; j++) {
//					switch(b.get(i, j)) {
//					case Constants.BLACK: System.out.print("x"); break;
//					case Constants.WHITE: System.out.print("o"); break;
//					case Constants.EMPTY: System.out.print(" "); break;
//					}
//				}
//				System.out.println("");
//			}
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
}
