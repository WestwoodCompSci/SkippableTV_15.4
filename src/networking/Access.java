package networking;

import java.awt.AWTException;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

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

	public JSONObject getUserData(int userID) {
		String parse = con.sendGet("getUser.php?user_id="+userID);
		JSONObject obj = new JSONObject(parse).getJSONObject("user");
		return obj;
	}

	public void addRating(int id, String hash, int season, int show, int episode, int rating, String comment) {
		ArrayList<String> post = new ArrayList<String>();		
		post.add("is_post=1&");
		post.add("id="+id+"&");
		post.add("hash="+hash+"&");
		post.add("season="+season+"&");
		post.add("show="+show+"&");
		post.add("episode="+episode+"&");
		post.add("rating="+rating+"&");
		if(comment != null) {
			comment = comment.replace(" ", "%20");
			post.add("comment="+comment+"&");
		}
		else {
			post.add("comment="+comment+"&"); }
		con.sendPost("POST/addRating.php", post);
	}

	public boolean addUser(String username, String password, String firstname, String lastname, String email, String birthday, String pictureURL) {

		ArrayList<String> post = new ArrayList<String>();		
		post.add("is_post=1&");
		post.add("username="+username+"&");
		post.add("password="+password+"&");
		post.add("email_address="+email+"&");
		post.add("fname="+firstname+"&");
		post.add("lname="+lastname+"&");
		post.add("bithday="+birthday+"&");
		if(pictureURL != null) {
			pictureURL = pictureURL.replace(" ", "%20");
			post.add("profile_uri="+pictureURL);
		}
		else {
			
		}
		if(con.sendPost("POST/addUser.php", post).contains("\"error\":1")) {
			return false;
		}
		return true;
	}

	public JSONObject login(String username, String password) {
		ArrayList<String> post = new ArrayList<String>();		
		post.add("ispost=1&");
		post.add("username="+username+"&");
		post.add("password="+password+"&");
		return new JSONObject(con.sendPost("POST/login.php", post));

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
	public String logout(String username, int id, String hash) { 
		ArrayList<String> post = new ArrayList<String>();		
		post.add("ispost=1&");
		post.add("username="+username+"&");
		post.add("id="+id+"&");
		post.add("hash="+hash);
		return con.sendPost("logout.php", post);
		
	}
	
	public String logoutAll(String username, int id, String hash) { 
		ArrayList<String> post = new ArrayList<String>();		
		post.add("ispost=1&");
		post.add("username="+username+"&");
		post.add("id="+id+"&");
		post.add("hash="+hash);
		return con.sendPost("logout.sphp?all=1", post);
		
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

	public ArrayList<Rating> getRatings(int showID, int seasonID, int episodeID) {
		String parse = con.sendGet("getRatings.php?episode="+episodeID+"&show="+showID+"&season="+seasonID);
		try {
			JSONArray arr = new JSONObject(parse).getJSONArray("rating");
			ArrayList<Rating> r = new ArrayList<Rating>();
			for(int i  = 0; i < arr.length(); i++) {
				r.add(new Rating(showID,seasonID,episodeID,arr.getJSONObject(i).getString("comment"),arr.getJSONObject(i).getInt("rating")));
			}

			return r;
		}
		catch(JSONException e) {
			return null;
		}

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

		ArrayList<String> post = new ArrayList<String>();			
		post.add("is_post=1&");
		post.add("security_token=xB9wFUSNz3H3j69sUxK3rjz8sSderupCvekhPY5BkWKGAnYj3Uasd7xPtwD9m82d&");
		post.add("token=" +Base64.encode(Base64.encode("420xBlazexIt".getBytes()).getBytes()) + "&");
		post.add("security=420xBlazexIt&");
		post.add("name=" + s.title + "&");
		post.add("num_seasons=" + s.seasonCount +"&");
		post.add("num_episodes=" + s.episodeCount + "&");
		post.add("total_length=" + s.length+"&");
		post.add("genre="+s.genre);
		return con.sendPost("POST/addShow.php", post);
	}

	public String addEpisode(Episode e) {

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
		//TODO 
		//code to update references
		//actually dont do this just update when adding episode and stuff
	}

	public static void main(String[] args) throws AWTException, IOException {
		Access ax = new Access();

		System.out.println("add season test");
		//System.out.println(ax.addSeason(new Season("56:44:27",1,4,555,27)));

		System.out.println("add show test");		
		//System.out.println(ax.addShow(new Series("This is a tst show","56:44:27",256,69,555)));

		System.out.println("add episode test");		
		//System.out.println(ax.addEpisode(new Episode("This is a test show","56:44:27",3,1,1)));

		System.out.println("get episode");
		//System.out.println(ax.getEpisode(1,1,3).toString());

		System.out.println("get ratings");
		//ax.getRatings(1,1,1);

		System.out.println("login test");
		System.out.println(ax.login("vikaspotluri123", "password"));

		//System.out.println("add user");
		//System.out.println(ax.addUser("ayylmao","Chris","Ayoub","asd@gmail.com","05/05/15",null));
		System.out.println("get user");
		System.out.println(ax.getUserData(1).toString());
		
		System.out.println("logout");
		System.out.println(ax.logoutAll("vikaspotluri123", 1, JOptionPane.showInputDialog("please paste the hash")).toString());
		
	}


}
