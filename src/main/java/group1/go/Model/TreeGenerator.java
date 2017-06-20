package group1.go.Model;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class TreeGenerator {

   PrintWriter p;

	public TreeGenerator() {
		try {
				p = new PrintWriter("tree.dot");
					p.println("digraph{");
					p.println("size="+"\"10,10\"");
			} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
			e.printStackTrace();
			}
	}


	public void drawNode(long ID, String label, String shape, String color){

		p.println(ID+"[label= "+label+" shape= "+shape+" fillcolor= "+color+" style=filled"+"]");
	}


	public void drawArc(long IDfrom, long IDto){
		p.println(IDfrom+"->"+IDto);
	}


	public void close(){
		p.println("}");
		p.close();
	}

}