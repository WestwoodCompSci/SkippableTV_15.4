package backend;

import java.util.ArrayList;

import org.json.JSONObject;

import networking.Access;

public class BackEnd {

	ArrayList<Series> series;
	
	
	Access a;
	
	public BackEnd()
	{
		a=new Access();
		series = getSeries();
	
	}
	public ArrayList<Series> getSeries()
	{
		ArrayList<Series> s=new ArrayList<Series>();
		ArrayList<Integer> i=a.getShows();
		for(int j :i)
		{
			JSONObject temp =a.getShow(j);
			Series t =new Series(temp.getString("name"),temp.getString("total length"),temp.getInt("id"),temp.getInt("episodes"),temp.getInt("seasons"));
			series.add(t);
			
		}
		return s;
	}
	
}
