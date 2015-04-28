package backend;

import java.util.ArrayList;

public class Season {
	
	int numSeason;
	
	
	
	ArrayList<Episode> e;
	public Season(int n, int total)
	{
		numSeason=n;
	}
	public int getTotalTime()
	{
		int n = 0; 
		for (Episode f: e)
		{
			n += f.getEpisodeTime();
		}
		return n; 
	}

	

}
