package util;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class LectorJson {
	public JSONObject leerJson(HttpServletRequest request) throws IOException
	{
		StringBuffer jb = new StringBuffer();
		String line = null;
		JSONObject jsonObject = null;
		try {
		    BufferedReader reader = request.getReader();
		    while ((line = reader.readLine()) != null)
		    	jb.append(line);
		} catch (Exception e) { /*report an error*/ }
		try {
			JSONParser parser = new JSONParser();
			jsonObject = (JSONObject)parser.parse(jb.toString());			
		} catch (Exception e) {
			// crash and burn
			throw new IOException("Error parsing JSON request string");
		}
		return jsonObject;
	}
}
