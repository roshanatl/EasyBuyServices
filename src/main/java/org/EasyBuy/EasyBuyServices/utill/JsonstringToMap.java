package org.EasyBuy.EasyBuyServices.utill;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

public class JsonstringToMap {
	/**
	 * It takes Json String as input & convert to a map with json keys & values for each entries
	 * @param jsonString
	 * @return
	 * @throws JSONException
	 */
	private JsonstringToMap(){
	}
	public static Map<String, Object> jsonString2Map(String jsonString)  
	{
		return jsonString2Map( jsonString, null);
	}
	/**
	 * 
	 * @param jsonString
	 * @param prependKey
	 * @return
	 * @throws JSONException
	 */
	private static Map<String, Object> jsonString2Map(String jsonString,String prependKey)
			 {
		Map<String, Object> jsonMap = new HashMap<String, Object>();		
		JSONObject jsonObject=null;
		try {
			jsonObject = new JSONObject(jsonString);
			Iterator<?> keyset = jsonObject.keys();
			while (keyset.hasNext()) {
				String key = (String) keyset.next();
				String actualKey=key;
				if(null!= prependKey){
					actualKey=prependKey+"."+key;
				}
				Object value = jsonObject.get(key);
				if (value instanceof JSONObject) {				
					Map<String, Object> temp = jsonString2Map(value.toString(), actualKey);
					jsonMap.putAll(temp);
				}
				if(!value.toString().startsWith("{")){
					jsonMap.put(actualKey, value);
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return jsonMap;
	}

	
}