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
	
	public int barOutput()
	{
		return 0;
	}
}
