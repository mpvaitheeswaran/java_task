package javaparsers;

import java.util.List;
import java.util.Map;

public class ParserMainClass {

	public static void main(String[] args) {
		MyParser xmlParse = new XMLParser("Students.xml", "student");
		List<Map<String, String>> studentData = xmlParse.getListOfMaps();
		for(Map<String, String> student :studentData) {
			for (Map.Entry<String, String> entry : student.entrySet()) {
				System.out.println(entry.getKey() +":"+entry.getValue());
			}
		}
	}

}
