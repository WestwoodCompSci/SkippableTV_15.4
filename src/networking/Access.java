package networking;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
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
		String parse = con.sendGet("findShow.php?show_name=");
		JSONArray obj = new JSONObject(parse).getJSONArray("shows");
		
		ArrayList<Integer> list = new ArrayList<Integer>();
		for(int i = 0; i < obj.length(); i++) {
			list.add(obj.getJSONObject(i).getInt("id"));
		}
		return list;
	}
	
	public JSONObject getShow(int ID) {
		
		String parse = con.sendGet("getShow.php?show_id="+ID);
		try {
			JSONObject obj = new JSONObject(parse).getJSONArray("show").getJSONObject(0);
			return obj;
		} catch (JSONException e){
			e.printStackTrace();
			return null;
		}
 		
	}
	
	public JSONObject getSeason(int showID, int seasonID) {
		String parse = con.sendGet("getSeason.php?show_id="+showID+"&season_id="+seasonID);
		try {
			JSONObject obj = new JSONObject(parse).getJSONArray("season").getJSONObject(0);
			return obj;
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	public ArrayList<JSONObject> getSeasons(int showID) {
		ArrayList<JSONObject> o = new ArrayList<JSONObject>();
		int max = getShow(showID).getInt("seasons");
		for(int i = 0; i < max; i++) {
			o.add(getSeason(showID,i));
		}
		return o;
	}
	
	
	
	public JSONObject getEpisode(int showID, int seasonID, int episodeNumber) {
		String parse = con.sendGet("getEpisode.php?parent_show="+showID+"&parent_season="+seasonID+"&episode_number="+episodeNumber);
		try {
			JSONObject obj = new JSONObject(parse).getJSONArray("episode").getJSONObject(0);
			return obj;
		}
		catch(JSONException e) {
			return null;
		}
		
	}
	
	public ArrayList<JSONObject> getEpisodes(int showID, int seasonID) {
		ArrayList<JSONObject> o = new ArrayList<JSONObject>();
		int max = getSeason(showID, seasonID).getInt("episodes");
		for(int i = 0; i < max; i++) {
			o.add(getEpisode(showID, seasonID, i));
		}
		return o;
	}
	
	public ArrayList<JSONObject> getRatings(int showID, int seasonID, int episodeID) {
		ArrayList<JSONObject> o = new ArrayList<JSONObject>();
		String parse = con.sendGet("getEpisode.php?episode="+episodeID+"&show="+showID+"&season="+seasonID);
		System.out.println(parse);
		return null;
//		try {
//			JSONObject obj = new JSONObject(parse).getJSONArray("episode").getJSONObject(0);
//			
//		}
//		catch(JSONException e) {
//			return null;
//		}
		
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
		
		System.out.println();
		ax.getRatings(1, 2, 1);
	}
}
