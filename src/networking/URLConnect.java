package networking;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
 

import javax.net.ssl.HttpsURLConnection;


 
public class URLConnect {
 
	private final String USER_AGENT = "Mozilla/5.0";
	private URL baseurl;
 
	public static void main(String[] args) throws Exception {
 
		URLConnect http = new URLConnect();
 
		System.out.println("Testing 1 - Send Http GET request");
		http.sendGet("http://www.google.com/");
 
		System.out.println("\nTesting 2 - Send Http POST request");
		http.sendPost("https://selfsolve.apple.com/wcResults.do");
 
	}
 
	public URLConnect() { };
	
	public URLConnect(String url) {
		try {
			baseurl = new URL(url);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// HTTP GET request
	private void sendGet(String suburl) throws Exception {
 
		
		URL obj = new URL(baseurl, suburl);
		
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
 
		// optional default is GET
		con.setRequestMethod("GET");
 
		//add request header
		con.setRequestProperty("User-Agent", USER_AGENT);
 
		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + suburl);
		System.out.println("Response Code : " + responseCode);
 
		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
 
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
			//response.append("\n");
		}
		in.close();
 
		//print result
		System.out.println(response.toString());
 
	}
 
	// HTTP POST request
	private void sendPost(String suburl) throws Exception {
 
		
		
		URL obj = new URL(baseurl, suburl);
		HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
 
		//add reuqest header
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
 
		String urlParameters = "sn=C02G8416DRJM&cn=&locale=&caller=&num=12345";
 
		// Send post request
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();
 
		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'POST' request to URL : " + suburl);
		System.out.println("Post parameters : " + urlParameters);
		System.out.println("Response Code : " + responseCode);
 
		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
 
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
			//response.append("\n");
		}
		in.close();
 
		//print result
		System.out.println(response.toString());
 
	}
 
}