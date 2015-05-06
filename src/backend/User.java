package backend;

import java.util.ArrayList;

public class User {
	
	int id;
	String username;
	String password;
	ArrayList<Show> playlist;
	ArrayList<Show> history;
	int progress;
public User(String u, String p, int id)
{
	this.id=id;
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
public void addToPlaylist(Show s)
{
	playlist.add(s);
}
public void removeFromPlaylist(Show s)
{
	playlist.remove(s);
	updateHistory(s);

}
public void updateHistory(Show s)
{
	history.add(s);
}
//public int getProgress(Series s)
{
	//return progress=(timeWatched)/(s.getSeriesTime());
}


}
