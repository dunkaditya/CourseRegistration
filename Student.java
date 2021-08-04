package CourseReg;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Student extends User implements StudentInt {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	ArrayList<Course> myCourses = new ArrayList<Course>();
	
	BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	
	public Student(String username, String password, String first, String last) {
		
		super(username, password, first, last);
	}

	@Override
	public void displayNotFull(ArrayList<Course> c) {
		// TODO Auto-generated method stub
		int count = 0; 
		for(Course x : myCourses) {
			if(x.getNumStudents() < x.getMaxStudents()) {
				
				displayCourse(x);
				count++;
			}
		}
		if(count==0) {
			System.out.println("There are no courses that are not full.");
		}
	}

	@Override
	public void displayStudentCourses() {
		
		for(Course x : myCourses) {
			displayCourse(x);
		}
	}
	
	public ArrayList<Course> courseManagement(ArrayList<Course> courses, String choice) throws IOException {
		
		if (choice.equals("1")) {
			
			displayAllCourses(courses);
		}
		
		if (choice.equals("2")){
			
			displayNotFull(courses);
		}
		
		if (choice.equals("3")){
			
			//note that student full name is known
			boolean found = false; 
			System.out.print("\nEnter the Course Name for the course you would like to register in: ");
			String cName = in.readLine();
			System.out.print("\nEnter the Course Section for the course you would like to register in: ");
			String sectNum = in.readLine();

			for(Course x : courses) {
				if(x.getName().equalsIgnoreCase(cName) && x.getSectNum() == Integer.parseInt(sectNum)) {
					
					found = true; 
					myCourses.add(x);
					x.addStudent(this);
					System.out.println("\nThe Course was successfully registered. ");
				}
			}
			if(!found) {
				System.out.println("\nThe entered information was invalid. Course not registered. ");
			}
		}
		
		if (choice.equals("4")){
			
			//note that student's full name is known (we don't want students registering the wrong name)
			boolean found = false; 
			System.out.print("\nEnter the Course Name for the course you would like to withdraw from: ");
			String cName = in.readLine();
			System.out.print("\nEnter the Course Section for the course you would like to withdraw from: ");
			String sectNum = in.readLine();

			for(Course x : courses) {
				if(x.getName().equalsIgnoreCase(cName) && x.getSectNum() == Integer.parseInt(sectNum)) {
					
					found = true; 
					myCourses.remove(x);
					x.removeStudent(this);
					System.out.println("\nThe Course was successfully withdrawn. ");
				}
			}
			if(!found) {
				System.out.println("\nThe entered information was invalid. Course not withdrawn. ");
			}
		}
		
		if(choice.equals("5")){
			
			System.out.print("\nYou are registered in the following courses: ");
			boolean hit = false; 
			for(Course x : myCourses) {
				System.out.print(x.getName() + ", ");
				hit = true; 
			}
			if(!hit) {
				System.out.print("You are not registered in any courses");
			}
			else {
				System.out.print("\nWould you like to view more information? (y/n): ");
				String input = in.readLine();
				if(input.equalsIgnoreCase("y")) {
					displayStudentCourses();
				}
			}
		}
		return courses;
	}
	
	public ArrayList<Course> getCourses() {
		return myCourses;
	}
	
	

}
