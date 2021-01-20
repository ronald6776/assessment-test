package utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * <h1>Assessment Test</h1>
 * Json Parser utilities to parse the json files
 * <p>
 * 
 *
 * @author  Ronald Nickson Pasteen Baskar
 * @version 1.0
 * @since   2021-01-18
 */

public class JsonUtils {

	public static Map<String,String> parseJsonToMap(File file) {
		LoggerUtils.info("Entry JsonUtils.parseJsonToMap method");
		JSONParser parser = new JSONParser();
		Object obj;
		Map<String,String> objectRepoMap=null;

		try {
			obj = parser.parse(new FileReader(file));
			JSONObject jsonObject = (JSONObject) obj;
			objectRepoMap=new HashMap<String,String>();
			for(Iterator iterator = jsonObject.keySet().iterator(); iterator.hasNext();){
				String key = (String) iterator.next();
				objectRepoMap.put(key,(String) jsonObject.get(key));
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		catch (ParseException e) {
			e.printStackTrace();
		}
		LoggerUtils.info("Exit JsonUtils.parseJsonToMap method");
		return objectRepoMap;


	}

}
