package backend;

import java.util.ArrayList;

public class Series {

	public String title;
	public ArrayList<Season> s;

	
	public Series(String b, ArrayList<Season> a)
	{
		title=b;
		s = a; 
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
	

	
	
}
