package backend;

import java.util.ArrayList;

public class User {
	
	public int id;
	public String username;
	public String password;
	public ArrayList<Series> playlist;
	public ArrayList<Series> history;
	public int progress;
public User(String u, String p)
{
	id=id;
	username=u;
	password=p;
}

public String toString()	
{
	return super.toString();
}
public void setUsername(String s)
{
	username=s;
}
public void setPassword(String s)
{
	password =s;
}
public void addToPlaylist(Series s)
{
	playlist.add(s);
}
public void removeFromPlaylist(Series s)
{
	playlist.remove(s);
	updateHistory(s);

}
public void updateHistory(Series s)
{
	history.add(s);
}
//public int getProgress(Series s)
{
	//return progress=(timeWatched)/(s.getSeriesTime());
}


}
