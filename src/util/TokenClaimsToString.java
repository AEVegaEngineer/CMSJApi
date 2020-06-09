package util;

import java.security.KeyStore.Entry;
import java.util.HashMap;
import java.util.Map;

import io.jsonwebtoken.Claims;

public  class TokenClaimsToString {
	public Map<String, Object>  FormatearString(Claims claims){
	    Map<String, Object> expectedMap = new HashMap<String, Object>();
	    for(java.util.Map.Entry<String, Object> entry : claims.entrySet()) {
	        expectedMap.put(entry.getKey() , entry.getValue());
	    }
	    
	    return expectedMap;
		
	    }
  
	  
	public String ConvertMapStringToString(Map<String, ?> map) {
	    StringBuilder mapAsString = new StringBuilder("{");
	    for (String key : map.keySet()) {
	        mapAsString.append(key + "=" + map.get(key) + ", ");
	    }
	    mapAsString.delete(mapAsString.length()-2, mapAsString.length()).append("}");
	    return mapAsString.toString();
		}
	}	
