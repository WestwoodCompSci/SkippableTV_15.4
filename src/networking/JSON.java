package networking;

import org.json.JSONObject;



public class JSON {
	
	JSONObject obj;
	
	public JSON() {
		URLConnect con = new URLConnect("http://preview.qbizlbk2gkua0pb93crxph878kcvj9k957h3bwfu5v0u23xr.box.codeanywhere.com/web/rest/v1/");

		String parse = con.sendPost("getUser.php?user_id=1", "");
		
		 obj = new JSONObject(parse);
		
		
		
		
	}
	
	public JSONObject getObject() {
		return obj;
	}
	
	
	
	
	
	

}
