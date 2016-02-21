package universite.bordeaux.app.elo;

import org.bson.Document;

import com.mongodb.Block;
import com.mongodb.client.FindIterable;

import universite.bordeaux.app.colors.ColorsTemplate;
import universite.bordeaux.app.game.Game;
import universite.bordeaux.app.game.GameItf;
import universite.bordeaux.app.mapper.MongoMapper;

public final class EloGenerator {

    private static long total = MongoMapper.count();
    private static long part;
    private EloGenerator(){

    }

    public static void Generate(){
        part =0;
        FindIterable<Document> it = MongoMapper.findGamesByDate();
        it.forEach(new Block<Document>() {
                @Override
                public void apply(final Document document) {
                    GameItf g = new Game(document);
                    g.GenerateElo();
                    part++;
                    progressBar(Math.round(((double)part/(double)total)*100),g.getId().toString());

                }
            });

    }

    private static void progressBar( long overall, String parsing){
        String over = "";
        for(int x = 0 ; x < (overall/2); x++){
            over += "=";
        }
        for(int x = 0 ; x < ((100-overall)/2); x++){
            over += "_";
        }

        String progress = "\r"+ColorsTemplate.ANSI_BLUE+ "Progress: "+ColorsTemplate.ANSI_RESET+" [" +ColorsTemplate.ANSI_GREEN +  over + ColorsTemplate.ANSI_RESET +"] "+overall+"%  "+ColorsTemplate.ANSI_BLUE+"Game: "+ColorsTemplate.ANSI_RESET+" [" + ColorsTemplate.ANSI_GREEN + parsing + ColorsTemplate.ANSI_RESET +"] ";

        System.out.print("\r                                                                                  ");
        System.out.print(progress);
    }


}
