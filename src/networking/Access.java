package networking;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import backend.*;


public class Access {
	private URLConnect con;
	 
	public Access() {	
		con = new URLConnect("http://preview.qbizlbk2gkua0pb93crxph878kcvj9k957h3bwfu5v0u23xr.box.codeanywhere.com/web/rest/v1/");
	}
	
	//public JSONObject getUserData(int userID) {
		
		//String parse = con.sendPost("getUser.php?user_id="+userID, "");
		//JSONObject obj = new JSONObject(parse).getJSONObject("user");
		//return obj;
	//}
	
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
			//System.out.println(new JSONObject(parse).getJSONArray("season").toString());
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
		//System.out.println("number of seasons: " + max);
		for(int i = 1; i < max + 1; i++) {
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
		String parse = con.sendGet("getRatings.php?episode="+episodeID+"&show="+showID+"&season="+seasonID);
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
	
	public String addSeason(Season s) {
		ArrayList<String> post = new ArrayList<String>();		
		post.add("is_post=1&");
		post.add("security_token=fUheHuhaSaH82haswU8ReSAcreD6wre5gevanEPaWrerEca6HacHAqechEnazEq2&");
		post.add("token=ZG1scllYTT0%3D&");
		post.add("security=vikas&");
		post.add("season_parent=" + s.parent + "&");
		post.add("season_number=" + s.number +"&");
		post.add("num_episodes=" + s.episodeCount + "&");
		post.add("season_length=" + s.length);
		return con.sendPost("POST/addSeason.php", post);
	}
	
	public String addShow(Series s) {
		//if a show with the same name exists, will return an error
		ArrayList<String> post = new ArrayList<String>();			
		post.add("is_post=1&");
		post.add("security_token=xB9wFUSNz3H3j69sUxK3rjz8sSderupCvekhPY5BkWKGAnYj3Uasd7xPtwD9m82d&");
		post.add("token=" +Base64.encode(Base64.encode("420xBlazexIt".getBytes()).getBytes()) + "&");
		post.add("security=420xBlazexIt&");
		post.add("name=" + s.title + "&");
		post.add("num_seasons=" + s.seasonCount +"&");
		post.add("num_episodes=" + s.episodeCount + "&");
		post.add("total_length=" + s.length);
		return con.sendPost("POST/addShow.php", post);
	}
	
	public String addEpisode(Episode e) {
		//if a show with the same name exists, will return an error
		ArrayList<String> post = new ArrayList<String>();			
		post.add("is_post=1&");
		post.add("security_token=xB9wFUSNz3H3j69sUxK3rjz8sSderupCvekhPY5BkWKGAnYj3Uasd7xPtwD9m82d&");
		post.add("token=" +Base64.encode(Base64.encode("420xBlazexIt".getBytes()).getBytes()) + "&");
		post.add("security=420xBlazexIt&");
		post.add("season_parent=" + e.season + "&");
		post.add("show_parent=" + e.series + "&");
		post.add("name=" + e.title + "&");
		post.add("length=" + e.time +"&");
		post.add("number=" + e.number);		
		return con.sendPost("POST/addEpisode.php", post);
	}
	
	public void updateReferences() {
		// TODO 
		//code to update references
	}
	
	public static void main(String[] args) {
		Access ax = new Access();
		
		System.out.println("add season test");
		System.out.println(ax.addSeason(new Season("56:44:27",1,4,555,27)));
		
		System.out.println("add show test");		
		System.out.println(ax.addShow(new Series("This is a tst show","56:44:27",256,69,555)));
		
		System.out.println("add episode test");		
		System.out.println(ax.addEpisode(new Episode("This is a test show","56:44:27",3,4,1,1)));
		
		System.out.println("get ratings");
		ax.getRatings(1,1,1);
		
		System.out.println("get episode");
		ax.getEpisode(1,1,1);
	}
}
