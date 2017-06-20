package group1.go;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import group1.go.Model.Board;
import group1.go.Model.BoardBitMapImpl;
import group1.go.Model.Constants;

public class BoardBuilder {
	
	static public Board build(String filename) throws IOException {
		FileReader fr = new FileReader(filename);
		BufferedReader br = new BufferedReader(fr);
		BoardBitMapImpl board = new BoardBitMapImpl();
		for(int i=0; i<Constants.BOARDSIZE; i++) {
			String aux = br.readLine();
			if(aux==null) {
				br.close();
				fr.close();
				return null;
			}
			int j = 0;
			for(char c : aux.toCharArray()) {
				char player = (c=='1')?Constants.WHITE:((c=='2')?Constants.BLACK:Constants.EMPTY);
				board.add(i, j, player);
				j++;
			}
			if(j!=Constants.BOARDSIZE+1) {
				br.close();
				fr.close();
				return null;
			}
		}
		br.close();
		fr.close();
		return board;
	}
	
}
