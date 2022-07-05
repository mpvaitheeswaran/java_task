import java.io.*;
import java.util.*;

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
}
class StudentDao{

    static ArrayList<Student> getDataFromFile(){
        ArrayList<Student> students = new ArrayList<Student>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("Student.txt"));
            String line;
            while((line = reader.readLine()) != null) {
                //If the first line is '#' skip the line and handling IndexOutOfBounds Exception.
                try{
                    if(! (line.charAt(0)=='#')){
                        String[] data=line.split(",");
                        int rollNo = Integer.parseInt(data[0]);
                        String name = data[1]; String dept = data[2];
                        int age = Integer.parseInt(data[3]);
                        Student s = new Student(rollNo,name,dept,age);
                        students.add(s);
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return students;
    }
    static void setDataIntoFile(ArrayList<Student> students){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("Student.txt"));

            for (Student s : students) {
                writer.write("\n"+s.getRollNo()+","+s.getName()+","+s.getDept()+","+s.getAge());
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
class StudentRecorder {
    private static ArrayList<Student> students;

    public static void main(String[] args) {
        //Creating students
        //createStudentList();
        //Get data from a file
        students = StudentDao.getDataFromFile();
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
        students.add(new Student(rollNo,name,dept,age));
		//Writing into file
        StudentDao.setDataIntoFile(students);
    }
    static void listAllStudents(){
        for(HashMap<String, String> sMap : createStudentMapList(students)){
            for (Map.Entry<String,String> entry : sMap.entrySet())
            System.out.println(entry.getKey() +
                             " : " + entry.getValue());

            System.out.println("\n");
        }
    }
    static void createStudentList(){
        Student s1 = new Student(100,"Pandiyan","BCA",18);
        Student s2 = new Student(101,"Arun","Bsc",20);
        Student s3 = new Student(102,"Surya","BBA",17);
        Student s4 = new Student(103,"Karthick","BCA",21);
        Student s5 = new Student(104,"agustin","BCA",23);

        students = new ArrayList<Student>();
        students.add(s1);
        students.add(s2);
        students.add(s3);
        students.add(s4);
        students.add(s5);
        StudentDao.setDataIntoFile(students);
    }
    static ArrayList<Student> getAllStudends(){
        return students;
    }
    static ArrayList<Student> sortStudentsByAge(ArrayList<Student> s){
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
   static List<HashMap<String, String>> createStudentMapList(ArrayList<Student> students){
        List<HashMap<String, String>> studentMapList = new ArrayList<HashMap<String, String>>();
        for(Student s : students){
            HashMap<String, String> studentsMap = new HashMap<>();
            studentsMap.put("rollNo", s.getRollNo()+"");
            studentsMap.put("name", s.getName());
            studentsMap.put("dept", s.getDept());
            studentsMap.put("age", s.getAge()+"");
            studentMapList.add(studentsMap);
        }
        return studentMapList;
    }
}


