package backend;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import GUI.Profile;

import org.json.JSONObject;

import backend.Series;
import networking.Access;

public class BackEnd {

	static ArrayList<Series> series;
	
	Access a;
	
	public HashMap Genres;
	
	
	public BackEnd()
	{
		a=new Access();
		series = getSeries();
	
	}
	
	public static void main(String [] args)
	{
		BackEnd b = new BackEnd();
		System.out.println(b.series.get(0).title);
		
		b.getSeasons(series.get(0).id);
	}
	
	public boolean addUser(String email, String username, String password) {
		if(username.contains(" ")) {
			return false;
		}
		a.addUser(email, username, password, null);
		
		return true;
	}
	
	public ArrayList<Series> sortSeries(ArrayList<Series> s)
	{
		
		Collections.sort(s, new SeriesComparator());
		return s;
		
	}
	

	public ArrayList<Series> topTen()
	{
		ArrayList<Series> topTen = new ArrayList<Series>();
		sortSeries(series);
		for(int i=0; i<10; i++)
		{
			topTen.add(series.get(i));
		}
			return topTen;
	}
	
	public ArrayList<Series> getSeries()
	{
		ArrayList<Series> s=new ArrayList<Series>();
		ArrayList<Integer> i=a.getShows();
		for(int j :i)
		{
			JSONObject temp =a.getShow(j);
			Series t =new Series(temp.getString("name"),temp.getString("total length"),temp.getInt("id"),temp.getInt("episodes"),temp.getInt("seasons"));
			s.add(t);
			
		}
		return s;
	}
	
	public ArrayList<Season> getSeasons(int showID)
	{
		ArrayList<Season> s =new ArrayList<Season>();
		ArrayList<JSONObject> j = a.getSeasons(showID);
		
		for(JSONObject k : j)
		{
			k.toString();
			s.add(new Season(k.getString("length"),k.getInt("parent"),k.getInt("number"),k.getInt("id"),k.getInt("episodes")));
		}
		return s;
		
	}
	
	
	public ArrayList<Episode> getEpisode(int showID, int seasonID)
	{
		ArrayList<Episode> s=new ArrayList<Episode>();
		ArrayList<JSONObject> i=a.getEpisodes(showID, seasonID);
		for(JSONObject j :i)
		{
			Episode e = new Episode(j.getString("name"),j.getString("time"),j.getInt("number"),j.getInt("season"),j.getInt("series"));
			e.setRatings(a.getRatings(showID, seasonID, j.getInt("number")));
			s.add(e);
			
			
		}
		return s;
	
	}


	
	public void addSeason(Season s)
	{
		a.addSeason(s);
	}
	public void addShow(Series s)
	{
		a.addShow(s);
	}
	public void addEpisode(Episode e)
	{
		a.addEpisode(e);
	}
	
}
