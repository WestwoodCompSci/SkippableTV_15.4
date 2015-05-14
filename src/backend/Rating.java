package backend;

public class Rating {

	public int seasonID;
	public int seriesID;
	public int episodeID;
	public String comment;
	public int rating;
	
	public Rating(int series, int season, int episode, String com, int rate) {
		seasonID = season;
		seriesID = series;
		episodeID = episode;
		comment = com;
		rating = rate;
	}
}
