package group1.go;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import group1.go.Controller.Controller;
import group1.go.GUI.BoardGUI;
import group1.go.GUI.StartGUI;
import group1.go.Model.Board;
import group1.go.Model.Constants;
import group1.go.Model.Game;
import group1.go.Model.MinMaxTree;
import group1.go.Model.Move;
import group1.go.Model.heuristics.squareHeuristic;

import org.apache.commons.cli.Option;

import java.io.IOException;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Options options = new Options();
        options.addOption(new Option("v", "visual", false, "Ejecuta la aplicación en modo visual."));
        options.addOption(new Option("f", "file", true, "Lee de un archivo el tablero e imprime la jugada en formato fila, columna o PASS"));
        options.addOption(new Option("p", "player", true, "Utilizado con file para indicar el juagador que realizara la jugada."));
        options.addOption(new Option("m", "maxtime", true, "Tiempo maximo (en segundos) para obtener una solucion."));
        options.addOption(new Option("d", "depth", true, "Indica la profundidad del arbol que se desea explorar."));
        options.addOption(new Option("P", "prune", false, "Habilita la poda alfa-beta en el algoritmo."));
        options.addOption(new Option("t", "tree", false, "Genera un archivo llamado tree.dot con una representacion del arbol explorado. Solo se puede utilizar con el parametro file."));
        HelpFormatter hformatter = new HelpFormatter();
        CommandLineParser parser = new DefaultParser();
        try {
        	
        	//Parseo argumentos
        	CommandLine cmd = parser.parse(options, args);
        	
        	if(cmd.hasOption("visual") && !cmd.hasOption("file")) {
        		if(cmd.hasOption("maxtime") && !cmd.hasOption("depth")) {
        			//Inicio con limite de tiempo
        			BoardGUI boardGUI = new BoardGUI();
					Game game = new Game();
					Controller controller = new Controller(game, boardGUI, 1000, true, false, new Integer(cmd.getOptionValue("maxtime")), cmd.hasOption("prune"));
					boardGUI.setController(controller);
        		} else if(!cmd.hasOption("maxtime") && cmd.hasOption("depth")) {
        			//Inicio con limite en profundidad
        			BoardGUI boardGUI = new BoardGUI();
					Game game = new Game();
					Controller controller = new Controller(game, boardGUI, new Integer(cmd.getOptionValue("depth")), true, true, 1000, cmd.hasOption("prune"));
					boardGUI.setController(controller);
        		} else {
        			//Muestro el menu de inicio
        			new StartGUI();
        		}
        	} else if(!cmd.hasOption("visual") && cmd.hasOption("file") && cmd.hasOption("player")) {
        		if(!cmd.getOptionValue("player").equals("1") && !cmd.getOptionValue("player").equals("2")) {
        			System.out.println("El jugador debe ser 1 o 2");
        			return;
        		}
        			
        		if(cmd.hasOption("maxtime") && !cmd.hasOption("depth")) {
        			//Corro el algoritmo con limite de tiempo
        			Game game = new Game();
        			Board loadedBoard = BoardBuilder.build(cmd.getOptionValue("file"));
        			char player = new Integer(cmd.getOptionValue("player"))==1?Constants.WHITE:Constants.BLACK;
        			game.startGame(player, loadedBoard);
        			MinMaxTree minMax=new MinMaxTree(game.getState(), game.getCurrentPlayer(), 10000, new squareHeuristic());
        			Move m= minMax.getOptimalMoveBFS( false, cmd.hasOption("prune"),  true,  new Integer(cmd.getOptionValue("maxtime"))*1000, cmd.hasOption("tree"));
        			if(m.getPosition().getI() == -2)
        				System.out.println("PASS!");
        			else
        				System.out.println(m.getPosition().getI() + ", " + m.getPosition().getJ());
        		} else if(!cmd.hasOption("maxtime") && cmd.hasOption("depth")) {
        			//Corro el algoritmo con limite en profundidad
        			Game game = new Game();
        			Board loadedBoard = BoardBuilder.build(cmd.getOptionValue("file"));
        			char player = new Integer(cmd.getOptionValue("player"))==1?Constants.WHITE:Constants.BLACK;
        			game.startGame(player, loadedBoard);
        			MinMaxTree minMax=new MinMaxTree(game.getState(), game.getCurrentPlayer(), new Integer(cmd.getOptionValue("depth")), new squareHeuristic());
        			Move m= minMax.getOptimalMoveBFS( false, cmd.hasOption("prune"),  false,  10000, cmd.hasOption("tree"));
        			if(m.getPosition().getI() == -2)
        				System.out.println("PASS!");
        			else
        				System.out.println(m.getPosition().getI() + ", " + m.getPosition().getJ());
        		} else {
        			System.out.println("Muchos argumentos!");
        			hformatter.printHelp("java -jar tpe.jar", options);
        		}
        	} else {
        		hformatter.printHelp("java -jar tpe.jar", options);
            	return;
        	}
        	
        	
        } catch(ParseException e) {
        	hformatter.printHelp("java -jar tpe.jar", options);
        	return;
        }  catch(IOException e) {
        	System.out.println("Error al abrir el archivo!");
        	return;
        }
    }
}
