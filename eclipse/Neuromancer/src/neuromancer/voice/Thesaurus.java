package neuromancer.voice;

import java.io.BufferedReader; 
import java.net.HttpURLConnection; 
import java.net.URL; 
import java.net.URLEncoder; 
import org.json.simple.*; // json package, download at http://code.google.com/p/json-simple/ 

//Oh dear God what have I done
public class Thesaurus { 
	//URL of API
	public static String endpoint = "http://thesaurus.altervista.org/thesaurus/v1"; 
	
	//Request things and retrun them
	public static String[] getPartsOfSpeech(String word) {
		String[] toReturn = null;
	    try { 
	      URL serverAddress = new URL(endpoint + "?word="+URLEncoder.encode(word, "UTF-8")+"&language=en_US&key=lDFiDd23YLqO9I8d3lz3&output=json"); 
	      HttpURLConnection connection = (HttpURLConnection)serverAddress.openConnection(); 
	      connection.connect(); 
	      int rc = connection.getResponseCode(); 
	      if (rc == 200) { 
	        String line = null; 
	        BufferedReader br = new BufferedReader(new java.io.InputStreamReader(connection.getInputStream())); 
	        StringBuilder sb = new StringBuilder(); 
	        while ((line = br.readLine()) != null) 
	          sb.append(line + '\n'); 
	        JSONObject obj = (JSONObject) JSONValue.parse(sb.toString()); 
	        connection.disconnect();
	        JSONArray array = (JSONArray)obj.get("response"); 
	        toReturn = new String[array.size()];
	        for (int i=0; i < array.size(); i++) { 
	          JSONObject list = (JSONObject) ((JSONObject)array.get(i)).get("list"); 
	          toReturn[i] = (list.get("category")+"").replace("(", "").replace(")", "");
	        } 
	      } else System.out.println("HTTP error:"+rc); 
	      connection.disconnect(); 
	    } catch (Exception e) { 
	      e.printStackTrace(); 
	    }
	    return toReturn;
	  }
}