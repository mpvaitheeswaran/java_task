import java.io.*;
import java.util.*;
import java.sql.*

class Student
{

    private int rollNo;
    private String name;
    private String dept;
    private int age;

    Student(int rollNo,String name,String dept,int age){
        this.rollNo = rollNo;
        this.name = name;
        this.dept = dept;
        this.age = age;
    }
    void setRollNo(int rollNo){
        this.rollNo = rollNo;
    }
    int getRollNo(){
        return this.rollNo;
    }
    void setName(String name){
        this.name = name;
    }
    String getName(){
        return this.name;
    }
    void setDept(String dept){
        this.dept = dept;
    }
    String getDept(){
        return this.dept;
    }
    void setAge(int age){
        this.age = age;
    }
    int getAge(){
        return this.age;
    }
	public String toString(){ 
	  return "students";  
	}  
}
class StudentDao{
	private final String url = "jdbc:oracle:thin:@localhost:1521:xe";
    private final String user = "system";
    private final String password = "oracle";

    public Connection connect() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
	static List<Student> fetchStudentData(){
		List<Student> students = new ArrayList<Student>();
		try{ 
			Connection conn = connect();  
			  
			Statement stmt=con.createStatement();  
			  
			ResultSet rs=stmt.executeQuery("select * from students");  
			while(rs.next()){
				int rollNo = rs.getInt(1);
                String name =rs.getString(2); String dept = rs.getString(3);
                int age = rs.getInt(4);
                Student s = new Student(rollNo,name,dept,age);
                students.add(s);
			}
			con.close();  
		}catch(Exception e){ System.out.println(e); System.out.println("Database connection Failed.");}  
			  
		return students;
	}
	static void AddStudent(Student student){
        try{ 
			String SQL = "INSERT INTO students(roll_no,name,dept,age) " + "VALUES(?,?,?,?)";
			Connection conn = connect(); 
			PreparedStatement pstmt = conn.prepareStatement(SQL,Statement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1, student.getRollNo());
            pstmt.setString(2, student.getName());
			pstmt.setString(3, student.getDept());
			pstmt.setInt(4, student.getAge());
			con.close();  
		}catch(Exception e){ System.out.println(e); System.out.println("Database connection Failed.");}
    }
		
			
	}
    
}
class StudentRecorder {
    private static ArrayList<Student> students;

    public static void main(String[] args) {
       
        students = StudentDao.fetchStudentData();
        int input=0;
        Scanner scan = new Scanner(System.in);
        while(input!=3){
            printMenu();
            input = scan.nextInt();
            switch(input){
                case 1:
                    createStudent();
                    break;
                case 2:
                    listAllStudents();
                    break;
                default :
                    System.out.println("Invalid Input.");
            }
        }


    }
    static void printMenu(){
        System.out.println("1. Create a new student.");
        System.out.println("2. List all student.");
        System.out.println("3. close.");
    }
    static void createStudent(){
        Scanner scan = new Scanner(System.in);
        int rollNo, age;
        String name, dept;
        System.out.println("Enter the Student Roll No");
        rollNo = Integer.parseInt(scan.nextLine());
        System.out.println("Enter the Student Name");
        name = scan.nextLine();
        System.out.println("Enter the Student Department");
        dept = scan.nextLine();
        System.out.println("Enter the Student Age");
        age = Integer.parseInt(scan.nextLine());
		Student s = Student(rollNo,name,dept,age);
        StudentDao.AddStudent(s);
    }
    static void listAllTables(){
        for(Map<String, List<Object>> sMap : createDataMapList(tableList)){
            for (Map.Entry<String,List<Student>> entry : sMap.entrySet()){
				System.out.println(entry.getKey());
				for (table : entry.getValue()){
					System.out.println("\t"+table);
				}
			}

            System.out.println("\n");
        }
    }
   
    static List<Student> getAllStudends(){
        return students;
    }
    static List<Student> sortStudentsByAge(List<Student> s){
        //Using Comparator to sort.
        Collections.sort(s,new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                if (s1.getAge() == s2.getAge())
                return 0;
                else if (s1.getAge() > s2.getAge())
                    return 1;
                else
                    return -1;
            }
        });
        return s;
    }
	static List<Map<String, List<Object>>> createDataMapList(List<Object> tables){
        List<Map<String, List<Student>>> dataMapList = new ArrayList<Map<String, List<Student>>>();
		for (table : tables){
			dataMapList.put(String.valueOf(table),table);
		}
        return dataMapList;
    }
}


