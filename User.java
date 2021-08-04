package CourseReg;

import java.io.Serializable;
import java.util.*;

public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;
	
	private String username;
	private String password;
	private String firstName; 
	private String lastName;  
	
	public User(String username, String password, String first, String last) {
		
		this.username = username;
		this.password = password;
		firstName = first;
		lastName = last;
	}
	
	public void displayCourse(Course c) {
		
		System.out.println("\nThis is the information for Course " + c.getId() + ".");

		System.out.println("Course name: " + c.getName());
		System.out.println("Maximum Students: " + c.getMaxStudents());
		System.out.println("Number of Students: " + c.getNumStudents());
		System.out.println("Course location: " + c.getLoc());
		System.out.println("Course section: " + c.getSectNum());
				
		System.out.println("Course Instructor: " + c.getInstructor());
	}
	
	//simple display that prints all course names 
	public void displayAllCourses(ArrayList<Course> c) {
		
		System.out.println("Courses :"); 
		for(Course x : c) {
			displayCourse(x);
		}
	}
	
	public ArrayList<Course> courseSort(ArrayList<Course> c) {
		
		Collections.sort(c, new CourseCompare());
		return c;
	}
	
	//view list of courses student is in
	public void displayStudentCourses(Student x) {
		
		System.out.print(x.getFirstName() + " " + x.getLastName() + " is registered in the following courses: ");
		
		for(Course y : x.getCourses()) {
			System.out.print(y.getName()+ " ");
		}
	}

	//getters and setters 
	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}
	
	public void setUsername(String un) {
		username = un;
	}
	
	public void setPassword(String pass) {
		password = pass;
	}
	
	public void setFirstName(String first) {
		firstName = first;
	}

	public void setLastName(String last) {
		lastName = last;
	}
	
}
