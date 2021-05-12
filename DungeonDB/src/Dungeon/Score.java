package Dungeon;

public class Score {
	int score;
	
	public Score ()
	{
		
	}
	public Score(int score)
	{
		this.score = score;
	}
	
	public boolean CompareTo(Score obj)
	{
		Score other = (Score) obj;
		return this.score > other.score;
	}
	
	public String ToString()
	{
		return "Score : "+score;
	}
}
