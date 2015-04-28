package backend;

import java.util.ArrayList;

public class User {
	
	int id;
	String username;
	String password;
	ArrayList<Series> playlist;
	ArrayList<Series> history;
	int progress;


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
public void getProgress(Series s)
{
	//progress=(timeWatched)/(s.getSeriesTime());
}

}
