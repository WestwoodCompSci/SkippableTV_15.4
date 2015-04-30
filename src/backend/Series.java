package backend;

import java.util.ArrayList;

public class Series {

	public String title;
	public ArrayList<Season> s;
	public String length;
	public int id;
	public int episodeCount;
	public int seasonCount;
	
	public Series(String b,String l,int i, int e,int s)
	{
		title=b;
		length=l;
		id=i;
		episodeCount=e;
		seasonCount=s;
	} 
	public int getSeriesTime()
	{
		int n = 0; 
		for(Season a: s)
		{
			n += a.getSeasonTime();
		}
		return n; 
	}
	
	public Season getSeason(int season)
	{
		return s.get(season);
	}
	

	
	
}
