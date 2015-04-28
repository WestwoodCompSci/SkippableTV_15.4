package backend;

import java.util.ArrayList;

public class Series {

	String title;
	ArrayList<Season> s;
	
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
