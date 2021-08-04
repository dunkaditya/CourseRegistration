package CourseReg;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class Admin extends User implements AdminInt {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	
	public Admin(String username, String password, String first, String last) {
		
		super(username, password, first, last);
	}
	
	public ArrayList<Course> addCourse(String cName, String cId, String mStudents, String instructor,
			String cSection, String cLocation, ArrayList<Course> c) {
				
		Course x = new Course(cName, cId, Integer.parseInt(mStudents), instructor, Integer.parseInt(cSection), cLocation);
		c.add(x);
		return c; 
	}

	//allows the admin to change course information (except numStudents b/c that doesn't make sense unless we add students)
	public ArrayList<Course> editCourse(String courseid, ArrayList<Course> c) throws IOException {
		
		int index = -1;
		while(index == -1) {
			
			//checks through list of courses for given id
			for(int i=0; i<c.size(); i++) {
				if(c.get(i).getId().equals(courseid)) {
					
					index = i;
					break;
				}
			}
			//if course is not found, print error message
			if(index == -1) {
				System.out.print("A course with that ID was not found. Please enter a valid Course ID: ");
				courseid = in.readLine();
			}
		}
		
		//displays current course information
		displayCourse(c.get(index));
		
		System.out.print("\nNew location (Enter 'nc' for no change): ");
		String newVal = in.readLine();
		if(!(newVal.equals("nc"))) {
			c.get(index).setLoc(newVal);
		}
		
		//loops until a valid integer is entered 
		newVal = "";
		while(!(newVal.equals("nc"))) {
			System.out.print("\nMax students (Enter 'nc' for no change): ");
			newVal = in.readLine();
			if(!(newVal.equals("nc"))) {
				try {
					c.get(index).setMaxStudents(Integer.parseInt(newVal));
					break;
				}
				//catches number format exception
				catch(Exception e){
					System.out.print("\nPlease enter a valid integer.");
				}
			}
		}
		
		System.out.print("\nNew instructor  (Enter 'nc' for no change): ");
		newVal = in.readLine();
		if(!(newVal.equals("nc"))) {
			c.get(index).setInstructor(newVal);
		}
		
		//loops until valid sect number is entered 
		newVal = "";
		while(!(newVal.equals("nc"))) {
			System.out.print("\nNew section number  (Enter 'nc' for no change): ");
			newVal = in.readLine();
			if(!(newVal.equals("nc"))) {
				try {
					c.get(index).setSectNum(Integer.parseInt(newVal));
					break;
				}
				//catches number format exception
				catch(Exception e){
					System.out.print("\nPlease enter a valid integer. ");
				}
			}
		}
		
		return c;
	}

	public ArrayList<Course> removeCourse(String courseid, ArrayList<Course> c) throws IOException {
		
		int index = -1;
		while(index == -1) {
			
			//checks through list of courses for given id
			for(int i=0; i<c.size(); i++) {
				if(c.get(i).getId().equals(courseid)) {
					
					index = i; //to break out of the while loop
					c.remove(i);
					break;
				}
			}
			//if course is not found, print error message
			if(index == -1) {
				System.out.print("A course with that ID was not found. Please enter a valid Course ID: ");
				courseid = in.readLine();
			}
		}
		
		System.out.print("\nThe course " + courseid + " was successfully deleted.");

		return c; 
	}
	
	@Override
	public void displayAllCourses(ArrayList<Course> c) {
		
		for(Course x : c) {
			
			System.out.println("Course " + x.getId() + ", " + x.getName() + ": ");
			System.out.print("Enrolled Students: ");
			if(x.getStudents().isEmpty()) {
				System.out.print("none ");
			}
			else {
				for(Student y : x.getStudents()) {
					System.out.print(y.getUsername() + " ");
				}
			}
			System.out.print(", Number of Registered Students: " + x.getNumStudents());
			System.out.println(", Maximum Number of Students: " + x.getMaxStudents());
		}
	}
	
	public void displayFull(ArrayList<Course> c) {
		
		boolean none = true;
		System.out.print("Full courses are: ");
		
		for (int i = 0; i < c.size(); i++) {
	
			if (c.get(i).getNumStudents() == c.get(i).getMaxStudents()) {
				
				System.out.println(c.get(i).getName());
				none = false;
			}
		}
		
		if (none == true) {
			
			System.out.println("There are no full courses.");
		}
			
	}

	public void writeFull(ArrayList<Course> c) throws FileNotFoundException {
		
		try (PrintWriter x = new PrintWriter ("FullCourses.txt")) {
			boolean none = true;
			x.println("Full courses are: ");
			
			for (int i = 0; i < c.size(); i++) {
		
				if (c.get(i).getNumStudents() == c.get(i).getMaxStudents()) {
					
					x.println(c.get(i).getName());
					none = false;
				}
			}
			
			if (none == true) {
				
				x.println("There are no full courses.");
			}
		}
			
		
	}
	
	//inputs choices 1-4, and edits the course
	public ArrayList<Course> manageCourses(ArrayList<Course> courses, String choice) throws IOException {
		// TODO Auto-generated method stub
		
		//alters the passed in courses variable
		if (choice.equals("1")) {
			
			System.out.print("\nYou have chosen to add a course. ");
			
			System.out.print("\n\nEnter a Course Name: ");
			String cName = in.readLine();
			
			System.out.print("\nEnter a Course ID: ");
			String cId = in.readLine();
			
			System.out.print("\nEnter max student capacity: ");
			String mStudents = in.readLine();
			

			System.out.print("\nEnter an instructor: ");
			String instructor = in.readLine();
			
			
			System.out.print("\nEnter the course section number (must be integer): ");
			String sectNum = in.readLine();
			
			System.out.print("\nEnter course location: ");
			String location = in.readLine();

			courses = addCourse(cName, cId, mStudents, instructor, sectNum, location, courses);
			
			System.out.print("\nThe course was successfully created.");
			
			
		}
		
		if (choice.equals("2")){
			
			System.out.print("\nEnter Course ID of course you would like to delete: ");
			String id = in.readLine();
			courses = removeCourse(id, courses);				
		}
		if (choice.equals("3")){
			
			System.out.print("\nEnter the Course ID for the course you would like to edit: ");
			String id = in.readLine();
			courses = editCourse(id, courses);
		}
		
		if (choice.equals("4")){

			System.out.print("\nEnter the Course ID for the course you would like to view: ");
			String cID = in.readLine();
			
			boolean check = false;
			while(!check) {
				for(int i=0; i<courses.size(); i++) {
					if(courses.get(i).getId().equalsIgnoreCase(cID)) {
						
						displayCourse(courses.get(i));
						check=true;
						break;
					}
				}
				if(!check) System.out.print("\nThat ID was not valid. Please enter a VALID Course ID: ");
			}
		}
		
		return courses;
		
	}

	public ArrayList<Student> registerStudent(ArrayList<Student> students) throws IOException {
		// TODO Auto-generated method stub
		
		System.out.print("Enter the first name of the student: ");
		String fname = in.readLine();
		System.out.print("\nEnter the last name of the student: ");
		String lname = in.readLine();
		System.out.print("\nEnter the username for the student: ");
		String uname = in.readLine();
		System.out.print("\nEnter password for the student: ");
		String pass = in.readLine();
		
		Student x = new Student(uname, pass, fname, lname);
		students.add(x);
		
		return students;
	}

	public void reportCourses(ArrayList<Course> courses, ArrayList<Student> students, String choice) throws IOException {

		if (choice.equals("1")) {
			
			displayAllCourses(courses);
		}
		
		if (choice.equals("2")){
			
			displayFull(courses);
		}
		
		if (choice.equals("3")){
			
			writeFull(courses);
			System.out.println("File successfully written. ");
		}
		
		if (choice.equals("4")){
			
			boolean found = false;
			System.out.print("\nEnter the Course ID for the course you would like to view students in: ");
			String id = in.readLine();
			for(Course y : courses){
					
				if(y.getId().equalsIgnoreCase(id)) {
						
					y.viewStudents();
					found = true; 
				}
			}
			if(!found) {
				System.out.print("\nThat Course ID is invalid. ");
			}
		}
		
		if(choice.equals("5")){
			
			boolean found = false; 
			System.out.println("\nEnter Student's first name: ");
			String first = in.readLine();
			System.out.println("Enter Student's last name: ");
			String last = in.readLine();
				
			for (Student x : students) {
				if (x.getFirstName().equalsIgnoreCase(first) && x.getLastName().equalsIgnoreCase(last)) {
						
					displayStudentCourses(x);
					found=true;
				}		
			}
			if(!found) {
				System.out.println("This student was not found.");
			}
		}
		
		if(choice.equals("6")) {
			
			courses = courseSort(courses);
			
			System.out.println("Sorted courses from least to most registered students: ");
			
			for (Course x: courses) {
				System.out.println(x.getName());
			}
		}
	}
	
}
