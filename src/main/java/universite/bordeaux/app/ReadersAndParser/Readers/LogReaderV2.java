package universite.bordeaux.app.ReadersAndParser.Readers;

import java.io.File;

import org.bson.Document;

import universite.bordeaux.app.GameDataStructure.Player;
import universite.bordeaux.app.GameDataStructure.PlayerItf;

public class LogReaderV2 extends LogReaderAbs implements LogReader {

    /**
     * @param fileLog name of the log file
     */
    public LogReaderV2(final File fileLog) {
        super(fileLog);
        this.version = 2;
    }

    /**
     * @param doc jsoup Docment to be parsed .
     * @return the new object player
     */
    protected final PlayerItf createPlayer(final org.jsoup.nodes.Document doc) {
        String name = doc.select("b").text().split(":")[0];
        return new Player(name);
    }


    /**
     * generates a Document compatible with mongodb based on the log.
     * @return a Document
     */
    public final Document getDoc() {
        return this.toDoc().append("version", this.version);
    }
}
