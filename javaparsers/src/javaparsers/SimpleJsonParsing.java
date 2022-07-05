package javaparsers;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class SimpleJsonParsing implements MyParser {
	private List<Student> students;
	private String filename;
	private JSONObject jo;

	public SimpleJsonParsing(List<Student> students) {
		this.students = students;
	}
	
	public SimpleJsonParsing(String filename) {
		this.filename = filename;
	}

	private JSONObject getJsonObject() throws FileNotFoundException, IOException, ParseException {
		return (JSONObject) new JSONParser().parse(new FileReader(filename));
	}
	
	

	@Override
	public List<Map<String, String>> getListOfMaps() {
		List<Map<String, String>> listOfMaps = new ArrayList<Map<String,String>>();
		try {
			jo = getJsonObject();
			JSONArray ja = (JSONArray) jo.get("students");
			Iterator itr = ja.iterator();
	          
	        while (itr.hasNext()) 
	        {
	        	Map<String, String> studentMap = new HashMap<String, String>();
	        	JSONObject obj= (JSONObject) itr.next();
	        	studentMap.put("rollno",(String)obj.get("rollno"));
	        	studentMap.put("name",(String)obj.get("name"));
	        	studentMap.put("dept",(String)obj.get("dept"));
	        	studentMap.put("age",(String)obj.get("age"));
	        	listOfMaps.add(studentMap);
	        }
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return listOfMaps;
	}

}
