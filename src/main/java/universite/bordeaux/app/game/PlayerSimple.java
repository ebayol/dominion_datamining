package universite.bordeaux.app.game;

import java.util.ArrayList;

import org.bson.Document;
import org.bson.types.ObjectId;

import universite.bordeaux.app.game.player.Player;
import universite.bordeaux.app.mapper.MongoMapper;

public class PlayerSimple {
    private ObjectId id;
    private String name;
    private int elo;

    public PlayerSimple(String name){
        Document doc = MongoMapper.findPlayer(name).first();
        if(doc != null){
            this.id = doc.get("_id",ObjectId.class);
            this.name = doc.get("name",String.class);
            this.elo = doc.get("elo",Integer.class);
        }else{
            this.name = name;
            this.elo = 1000;
        }

    }

    public Document toDoc(){
        return new Document("name",name).append("elo",elo);
    }

    public void save(){
        if(this.id == null){
            this.id = MongoMapper.insertPlayer(this.toDoc());
        }else{
            MongoMapper.updatePlayer(new Document("_id",id), new Document("$set",this.toDoc()));
        }
  }

	/**
	 * @return the id
	 */
  public ObjectId getId() {
		return id;
	}


	/**
	 * @return the elo
	 */
	public int getElo() {
		return elo;
	}

	/**
	 * @param elo the elo to set
	 */
	public void setElo(int elo) {
		this.elo = elo;
	}


}
