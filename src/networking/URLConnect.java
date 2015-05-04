package networking;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;



 
public class URLConnect {
 
	private final String USER_AGENT = "SkippableTV 1.1";
	private URL baseurl;
 	
	public URLConnect(String url) {
		try {
			baseurl = new URL(url);
		} catch (MalformedURLException e) {
			
			e.printStackTrace();
		}
	}
	
	// HTTP GET request
	public String sendGet(String suburl) {
		try {
			URL obj = new URL(baseurl, suburl);
			
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
	 
			// optional default is GET
			con.setRequestMethod("GET");
	 
			//add request header
			con.setRequestProperty("User-Agent", USER_AGENT);
	 
			
			
			BufferedReader in = new BufferedReader(
			        new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
	 
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
				//response.append("\n");
			}
			in.close();
			con.disconnect();
			//print result
			return response.toString();
		}
		
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
 
	}
 
	// HTTP POST request
	public String sendPost(String suburl, String params) {
 
		try {
			URL obj = new URL(baseurl, suburl);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		
	 
			//add reuqest header
			con.setRequestMethod("POST");
			con.setRequestProperty("User-Agent", USER_AGENT);
			//con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
	 
			
	 
			// Send post request
			con.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(params);
			wr.flush();
			wr.close();
	 
			//int responseCode = con.getResponseCode();
			//System.out.println("\nSending 'POST' request to URL : " + suburl);
			//System.out.println("Post parameters : " + params);
			//System.out.println("Response Code : " + responseCode);
	 
			BufferedReader in = new BufferedReader(
			        new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
	 
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
				//response.append("\n");
			}
			in.close();
			con.disconnect();
			//print result
			//System.out.println(response.toString());
			
			return response.toString();
	 
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
 
}