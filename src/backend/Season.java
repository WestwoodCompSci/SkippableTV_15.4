package backend;

import java.util.ArrayList;

public class Season {
	
	public String length;
	public int parent;
	public int number;
	public int id;
	public int episodeCount;
	public double seasonsRatings;
	 ArrayList<Episode> e;
	
	
	public Season(String l, int p, int n, int i,  int e)
	{
		length =l;
		parent=p;
		number=n;
		id=i;
		episodeCount=e;
	
		seasonsRatings = this.getAverageRating();
	}
//	public int getSeasonTime()
//	{
//		int n = 0; 
//		for (Episode f: e)
//		{
//			n += f.getEpisodeTime();
//		}
//		return n; 
//	}
//	
	public Episode getEpisode(int episode)
	{
		return e.get(episode);
	}
	public double getAverageRating()
	{
		double sum = 0;
		for(Episode r : e) {
			sum += r.getAverageRating();
		}
		return (sum / episodeCount);
	}
	
	

}
