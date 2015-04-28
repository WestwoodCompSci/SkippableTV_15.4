package backend;

import java.util.ArrayList;

public class User {

	String username;
	String password;
	ArrayList<Series> playlist;
	ArrayList<Series> history;

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

}