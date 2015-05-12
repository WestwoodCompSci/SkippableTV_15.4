package backend;

public class Episode {

	public String name;
	public String time;
	public int number;
	public int rating;
	public int season;
	public int series;
	/**
	 * 
	 * @param n is the name of episode 
	 * @param t is the time length
	 * @param i is the number episode in the season
	 * @param r is the overall rating of show 
	 * @param sea is the parent season
	 * @param ser is the parent series
	 */
	public Episode(String n,String t, int i, int r, int sea, int ser)
	{
		name=n;
		time=t;
		number=i;
		rating=r;
		season=sea;
		series=ser;

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

