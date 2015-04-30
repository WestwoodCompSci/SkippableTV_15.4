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
		JSONObject arr = new JSONObject(parse).getJSONArray("show").getJSONObject(0);
		return arr;
	}
	
	public static void main(String[] args) {
		Access ax = new Access();
		ArrayList<Integer> a = ax.getShows();
		System.out.println(a.toString());
		
		System.out.println();
		System.out.println(ax.getShow(1).getString("seasons"));
	}
}
