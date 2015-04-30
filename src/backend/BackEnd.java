package backend;

public class BackEnd {

	Series s;
	User u;
	
	
	public BackEnd(Series t, User v)
	{
		s=t;
		u=v;
	}
	
	public void addSeries()
	{
		u.addToPlaylist(s);
	}
	
	public int barOutput(int t, int f)
	{
		if(s.getSeason(t).getEpisode(f).getRating()<1)
			return 1;
		else if(s.getSeason(t).getEpisode(f).getRating()<2)
			return 2;
		else if(s.getSeason(t).getEpisode(f).getRating()<4)
			return 4;
		else 
			return 5;
	}
}
