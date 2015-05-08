package backend;

import java.util.ArrayList;

import org.json.JSONObject;
import backend.Series;
import networking.Access;

public class BackEnd {

	static ArrayList<Series> series;
	
	Access a;
	
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
	
	public ArrayList<Episode> getEpisode(int showID, int seasonID)
	{
		ArrayList<Episode> s=new ArrayList<Episode>();
		ArrayList<JSONObject> i=a.getEpisodes(showID, seasonID);
		for(JSONObject j :i)
		{
			
			s.add(new Episode(j.getString("name"),j.getString("time"),j.getInt("number"),j.getInt("rating"),j.getInt("season"),j.getInt("series")));
			
			
		}
		return s;
	}
	
	
}
