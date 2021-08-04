package CourseReg;

import java.io.Serializable;
import java.util.ArrayList;

public class Course implements Serializable {
	
	private static final long serialVersionUID = 3L;
	
	String name;
	String courseId;
	String courseLoc;
	int maxStudents;
	int numStudents;
	ArrayList<Student> students;
	String instructor;
	int sectNum;
	
	public Course(String name, String id, int max, int num, String inst, int sect, String location, ArrayList<Student> students) {
		
		this.name = name; 
		courseId = id; 
		courseLoc = location; 
		maxStudents = max;
		numStudents = num;
		instructor = inst; 
		sectNum = sect; 
		this.students = students;
		
	}
	
	public Course(String name, String id, int max, String inst, int sect, String location) {
		
		this.name = name; 
		courseId = id; 
		courseLoc = location; 
		maxStudents = max;
		numStudents = 0;
		instructor = inst; 
		sectNum = sect; 
		students = new ArrayList<Student>();
	}
	
	public void addStudent(Student s) {
		
		students.add(s);
		numStudents++; 
	}
	
	public void removeStudent(Student s) {
		
		students.remove(s);
		numStudents--; 
	}
	
	public void viewStudents() {
		
		System.out.print("Students: ");
		if(students.isEmpty()) {
			System.out.println("no students currently enrolled");
		}
		for(Student x : students) {
			System.out.print("\n" + x.getFirstName() + " " + x.getLastName());
		}
	}
	
	//getters and setters 
	public String getName() {
		return this.name;
	}
	
	public void setName(String n) {
		name = n;
	}
	
	public String getId() {
		return this.courseId;
	}
	
	public void setId(String id) {
		courseId = id;
	}
	
	public String getLoc() {
		return this.courseLoc;
	}
	
	public void setLoc(String loc) {
		courseLoc = loc;
	}
	
	public int getMaxStudents() {
		return this.maxStudents;
	}
	
	public void setMaxStudents(int ms) {
		maxStudents = ms;
	}
	
	public int getNumStudents() {
		return this.numStudents;
	}
	
	public void setNumStudents(int ns) {
		numStudents = ns;
	}
	
	public String getInstructor() {
		return this.instructor;
	}
	
	public void setInstructor(String inst) {
		instructor = inst;
	}
	
	public int getSectNum() {
		return this.sectNum;
	}
	
	public void setSectNum(int sect) {
		sectNum = sect;
	}
	
	public ArrayList<Student> getStudents() {
		
		return this.students;
	}
	
	public void setStudents(ArrayList<Student> students) {
		this.students = students;
	}
	
}
