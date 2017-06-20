package group1.go;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.Option;

import java.util.Formatter;

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
        options.addOption(new Option("P", "prune", true, "Habilita la poda alfa-beta en el algoritmo."));
        options.addOption(new Option("t", "tree", true, "Genera un archivo llamado tree.dot con una representacion del arbol explorado. Solo se puede utilizar con el parametro file."));
        HelpFormatter hformatter = new HelpFormatter();
        CommandLineParser parser = new DefaultParser();
        try {
        	
        	//Parseo argumentos
        	CommandLine cmd = parser.parse(options, args);
        	boolean visual = cmd.hasOption("visual");
        	boolean file = cmd.hasOption("file");
        	boolean tree = cmd.hasOption("tree");
        	boolean podaAlfaBeta = cmd.hasOption("prune");
        	
        	
        	System.out.println("GUI: "+(visual?"Si":"No"));
        	if(file)
        		System.out.println("File: " + cmd.getOptionValue("file"));
        	
        	
        } catch(ParseException e) {
        	hformatter.printHelp("java -jar tpe.jar", options);
        	return;
        }
    }
}
