package javastreams;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MainClass {

	public static void main(String[] args) {
		List<Student> students = StudentDao.fetchStudentData();
		
		List<Student> ageAbove21 = students.stream()
				.filter(s->s.getAge()>=21)
				.collect(Collectors.toList());
		
		List<Student> sortByName = students.stream()
				.sorted(Comparator.comparing(Student::getName))
				.collect(Collectors.toList());
		
		List<Student> increaseAge = students.stream()
				.map(s->{s.setAge(s.getAge()+1); return s;})
				.collect(Collectors.toList());
		
		List<Map<String, String>> studentMap = MapConversion.createStudentMapList(students);
		List<Map<String, String>> mapAgeAbove21 = studentMap.stream()
				.filter(m-> Integer.parseInt(m.get("age"))>=21)
				.collect(Collectors.toList());
		
		for(Student s:increaseAge) {
			System.out.println(s.getName() + s.getAge());
		}
	}

}
