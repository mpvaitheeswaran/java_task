package javastreams;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class MapConversion {

	
	static List<Map<String, String>> createStudentMapList(List<Student> students){
        List<Map<String, String>> studentMapList = new ArrayList<Map<String, String>>();
        for(Student s : students){
            Map<String, String> studentsMap = new HashMap<>();
            studentsMap.put("rollNo", s.getRollNo()+"");
            studentsMap.put("name", s.getName());
            studentsMap.put("dept", s.getDept());
            studentsMap.put("age", s.getAge()+"");
            studentMapList.add(studentsMap);
        }
        return studentMapList;
    }
	
	static List<Map<String, List<Map<String, String>>>> createDataMapList(List<List<Student>> tables){
        List<Map<String, List<Map<String, String>>>> dataMapList = new ArrayList<Map<String,List<Map<String,String>>>>();
        Map<String, List<Map<String, String>>> dataMap = new HashMap<String, List<Map<String, String>>>();
//        List<Map<String, String>> studentMapList = new ArrayList<Map<String,String>>();
//        Map<String, String> studentMap
        int i =1;
		for (List<Student> table : tables){
			List<Map<String, String>> studentMapList = createStudentMapList(table);
			dataMap.put("student_map"+i, studentMapList);
			i++;
		}
		dataMapList.add(dataMap);
		dataMapList.add(dataMap);
		dataMapList.add(dataMap);
        return dataMapList;
    }
	
	static void printNestedData(List<Map<String, List<Map<String, String>>>> data) {
		
		Iterator<Map<String, List<Map<String, String>>>> itr=data.iterator();
		
		while(itr.hasNext()) {
			itr.next();
		}
		
		int i = 0;
		for(Map<String, List<Map<String, String>>> dataMap : data ) {
			i++;
			System.out.println("List "+i);
			for (Map.Entry<String, List<Map<String, String>>> studentEntry: dataMap.entrySet()) {
				System.out.println("\t"+studentEntry.getKey());
				for(Map<String, String> studentMapList : studentEntry.getValue() ) {
					for(Map.Entry<String,String> entry : studentMapList.entrySet()) {
						System.out.println("\t\t"+entry.getKey() +
	                             " : " + entry.getValue());
					}
					System.out.println("\n");
				}
			}
			
		}
	}
}
