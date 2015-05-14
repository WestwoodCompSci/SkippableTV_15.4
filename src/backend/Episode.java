package backend;

import java.util.ArrayList;

public class Episode {

	public String title;
	public String time;
	public int number;
	public ArrayList<Rating> ratings;
	public int season;
	public int series;

	public Episode(String title,  String t,int n, ArrayList<Rating> r, int season_parent, int show_parent)
	{
		this.title = title;
		time = t;
		number = n; 
		ratings = r;
		season = season_parent;
		series = show_parent;
		
	}
	
	public Episode(String title,  String t,int n,int season_parent, int show_parent)
	{
		this.title = title;
		time = t;
		number = n; 
	
		season = season_parent;
		series = show_parent;
		
	}
	
	public void setRatings(ArrayList<Rating> r) {
		ratings = r;
	}


	public String getEpisodeTime()
	{
		return time;
	}
	public int getAverageRating()
	{
		int sum = 0;
		for(Rating r : ratings) {
			sum += r.rating;
		}
		return sum / ratings.size();
	}
	public int getNumber()
	{
		return number;
	}
}

