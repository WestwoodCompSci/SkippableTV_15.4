package backend;

public class Episode {

int time;
int number;
int rating;

	public Episode(int n, int t, int r)
	{
		time =t;
		number = n; 
		rating =r;
	}
	
	
	public int getEpisodeTime()
	{
		return time;
	}
}

