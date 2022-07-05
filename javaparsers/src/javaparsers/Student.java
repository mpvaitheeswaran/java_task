package javaparsers;
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