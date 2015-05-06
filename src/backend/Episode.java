package backend;

public class Episode {

	public String title;
	public String time;
	public int number;
	public int rating;
	public int season;
	public int series;

	public Episode(String title,  String t,int n, int r, int season_parent, int show_parent)
	{
		this.title = title;
		time = t;
		number = n; 
		rating = r;
		season = season_parent;
		series = show_parent;
		
	}


	public String getEpisodeTime()
	{
		return time;
	}
	public int getRating()
	{
		return rating;
	}
	public int getNumber()
	{
		return number;
	}
}

