package javaparsers;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JacsonJsonParsing implements MyParser {
	
	private String filename;
	
	public JacsonJsonParsing(String filename) {
		this.filename = filename;
	}
	
	private List<Student> getStudentList() {
		ObjectMapper mapper = new ObjectMapper();
		List<Student> students = null;
		File jsonFile = new File(filename);
		try {
			students = Arrays.asList(mapper.readValue(jsonFile, Student[].class));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return students;
	}

	@Override
	public List<Map<String, String>> getListOfMaps() {
		List<Student> students = getStudentList();
		List<Map<String, String>> studentMapList = new ArrayList<Map<String,String>>();
        for(Student s : students){
            Map<String, String> studentsMap = new HashMap<String, String>();
            studentsMap.put("rollNo",String.valueOf(s.getRollNo()));
            studentsMap.put("name", s.getName());
            studentsMap.put("dept", s.getDept());
            studentsMap.put("age", String.valueOf(s.getAge()));
            studentMapList.add(studentsMap);
        }
        return studentMapList;
	}

}
