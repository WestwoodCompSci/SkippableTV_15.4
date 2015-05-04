package networking;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

public class Access {
	private URLConnect con;
	 
	public Access() {	
		con = new URLConnect("http://preview.qbizlbk2gkua0pb93crxph878kcvj9k957h3bwfu5v0u23xr.box.codeanywhere.com/web/rest/v1/");
	}
	
	public JSONObject getUserData(int userID) {
		
		String parse = con.sendPost("getUser.php?user_id="+userID, "");
		JSONObject obj = new JSONObject(parse).getJSONObject("user");
		return obj;
	}
	
	public ArrayList<Integer> getShows() {
		String parse = con.sendPost("findShow.php?show_name=", "");
		JSONArray obj = new JSONObject(parse).getJSONArray("shows");
		
		ArrayList<Integer> list = new ArrayList<Integer>();
		for(int i = 0; i < obj.length(); i++) {
			list.add(obj.getJSONObject(i).getInt("id"));
		}
		return list;
	}
	
	public JSONObject getShow(int ID) {
		
		String parse = con.sendPost("getShow.php?show_id="+ID, "");
		JSONObject obj = new JSONObject(parse).getJSONArray("show").getJSONObject(0);
		return obj;
	}
	
	public JSONObject getSeason(int showID, int seasonID) {
		String parse = con.sendPost("getSeason.php?show_id="+showID+"&season_id="+seasonID, "");
		JSONObject obj = new JSONObject(parse).getJSONArray("season").getJSONObject(0);
		return obj;
	}
	
	public ArrayList<JSONObject> getSeasons(int showID) {
		ArrayList<JSONObject> o = new ArrayList<JSONObject>();
		int max = getShow(showID).getInt("seasons");
		for(int i = 1; i < max + 1; i++) {
			o.add(getSeason(showID,i));
		}
		return o;
	}
	
	
	
	public JSONObject getEpisode(int showID, int seasonID, int episodeNumber) {
		String parse = con.sendPost("getEpisode.php?parent_show="+showID+"&parent_season="+seasonID+"&episode_number="+episodeNumber, "");
		
		//JSONObject obj = new JSONObject(parse)getJSONArray("episode").getJSONObject(0);
		System.out.println(parse);
		return null;
	}
	
	public ArrayList<JSONObject> getEpisodes(int showID, int seasonID) {
		ArrayList<JSONObject> o = new ArrayList<JSONObject>();
		int max = getSeason(showID, seasonID).getInt("episodes");
		for(int i = 1; i < max + 1; i++) {
			o.add(getEpisode(showID, seasonID, i));
		}
		return o;
	}
	
	public static void main(String[] args) {
		Access ax = new Access();
		ArrayList<Integer> a = ax.getShows();
		System.out.println(a.toString());
		
		System.out.println();
		System.out.println(ax.getSeason(0,1));
		
		System.out.println();
		ax.getSeason(1,2);
		
		System.out.println();
		System.out.println(ax.getEpisode(1, 2, 1));
	}
}
