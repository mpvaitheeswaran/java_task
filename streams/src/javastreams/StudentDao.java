package javastreams;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

class StudentDao {
	private static final String url = "jdbc:oracle:thin:@BSIT-CH-D005:1521:orcl";
	private static final String user = "ORCLTEST";
	private static final String password = "ORCLTEST";

	public static Connection connect() throws SQLException {
		return DriverManager.getConnection(url, user, password);
	}

	static List<Student> fetchStudentData() {
		List<Student> students = new ArrayList<Student>();
		try {
			Connection conn = connect();

			Statement stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery("select * from students");
			while (rs.next()) {
				int rollNo = rs.getInt(1);
				String name = rs.getString(2);
				String dept = rs.getString(3);
				int age = rs.getInt(4);
				Student s = new Student(rollNo, name, dept, age);
				students.add(s);
			}
			System.out.println("Database connected.");
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
			System.out.println("Database connection Failed.");
		}

		return students;
	}

	static void addStudent(Student student) {
		try {
			String SQL = "INSERT INTO students(ROLLNO,NAME,DEPT,AGE) " + "VALUES(?,?,?,?)";
			Connection conn = connect();
			PreparedStatement pstmt = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1, student.getRollNo());
			pstmt.setString(2, student.getName());
			pstmt.setString(3, student.getDept());
			pstmt.setInt(4, student.getAge());
			pstmt.executeUpdate();
			conn.close();
			System.out.println("Data added.");
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("Database connection Failed.");
		}
	}

}