package backend;

import java.util.ArrayList;

import backend.Season;

public class Series {

	public String title;
	public ArrayList<Season> s;
	public String genre;
	public String length;
	public int id;
	public int episodeCount;
	public int seasonCount;
	public double seriesRating; 
	
	public Series(String b,String l,int i, int e,int s)
	{
		title=b;
		length=l;
		id=i;
		episodeCount=e;
		seasonCount=s;
		seriesRating = this.getAverageRating();
		
	}
	public Season getSeason(int season)
	{
		return s.get(season);
	}
	public double getAverageRating()
	{
		double sum = 0;
		for(Season r : s) {
			sum += r.getAverageRating();
		}
		return (sum / seasonCount);
	}
	
	

	
	
}
