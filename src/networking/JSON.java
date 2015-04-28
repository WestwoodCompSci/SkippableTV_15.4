package networking;

import org.json.JSONArray;
import org.json.JSONObject;



public class JSON {
	
	
	
	public JSON() {
		URLConnect con = new URLConnect("http://preview.qbizlbk2gkua0pb93crxph878kcvj9k957h3bwfu5v0u23xr.box.codeanywhere.com/web/rest/v1/");

		String parse = con.sendPost("getUser.php?user_id=1", "");
		
		JSONObject obj = new JSONObject(parse);
		
		
		JSONObject obj2 =  obj.getJSONObject("user");
		//if obj2 instanceof JSONObject
		System.out.println(obj2.toString());
		System.out.println(obj2.getString("ratings"));
		
	}
	
	
	
	
	

}
