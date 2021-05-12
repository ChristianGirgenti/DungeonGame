package Dungeon;

import com.db4o.*;

public class ScoreTracker {
	private ObjectContainer db;
	
	public ScoreTracker(String filename) {
		db = Db4oEmbedded.openFile(filename);
	}
	
	public void scoreUpdate(Score score) {
		//just put it in the database
		
		db.store(score);
	}
	public void clear() {
		ObjectSet<Score> objects = db.queryByExample(new Score());
		for(Score object : objects) {
			db.delete(object);
		}
	}
	public Score[] getScores() {
		//create a blank info object, and set the right job ID
		
		Score score = new Score(0);
		//query the database for all objects matching the blank
		ObjectSet<Score> resultset = (ObjectSet<Score>) db.queryByExample(score);
		
		//put the results into an array
		Score[] results = new Score[resultset.size()];		
		for(int i=0;i<resultset.size();i++) {
			results[i] = resultset.get(i);
		}
		
		return results;
	}
	
	public void close()
	{
		db.close();
	}
}
