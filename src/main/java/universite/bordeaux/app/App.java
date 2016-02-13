package universite.bordeaux.app;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jfree.ui.RefineryUtilities;

import universite.bordeaux.app.plotter.Chart;
import universite.bordeaux.app.reader.FileHandler;
/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws Exception
    {
        Logger.getLogger( "org.mongodb.driver" ).setLevel(Level.OFF);
        File child = new File("sampleLogs/");
        FileHandler test = new FileHandler(child);
         // test.runParser(5);
         // EloGenerator.Generate();

        Chart ch = new Chart("Elo curve", "Elo curve", "ELO", "DAY", "RomaNorgy");
        ch.pack( );
        RefineryUtilities.centerFrameOnScreen( ch );
        ch.setVisible( true );
    }
}
