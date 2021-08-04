package CourseReg;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;

public class Main implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) throws IOException {
		
		ArrayList<Course> courses = new ArrayList<Course>(); 
		ArrayList<Student> students = new ArrayList<Student>();
		ArrayList<Admin> admins = new ArrayList<Admin>();
		
		//enters our one admin into the arraylist (always the same)
		Admin admin = new Admin("Admin", "Admin001", "edward", "admin");
		admins.add(admin);
		
		Student currStudent = null;
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Is this the first time you are using the course registration system? Enter 'y' for yes and 'n' for no");
		String input = in.readLine();
		
		while (!input.equalsIgnoreCase("y") && !input.equalsIgnoreCase("n")) {
			
			System.out.print("\nPlease enter 'y' or 'n': ");
			input = in.readLine();
		}
		
		//reinitialize all variables, and read the csv file
		if (input.equals(("y"))) {
			
			//reset all variables
			courses = new ArrayList<Course>(); 
			students = new ArrayList<Student>();
			
			// Records is in form [Course_Name, Course_Id, Maximum_Students, Current_Students, List_Of_Names, Course_Instructor, Course_Section_Number, Course_Location]
			List<List<String>> records = new ArrayList<List<String>>();
			
			//reads initial file
			try (BufferedReader br = new BufferedReader(new FileReader("MyUniversityCourses.csv"))) {
			    String line;
			    br.readLine();
			    while ((line = br.readLine()) != null) {
			        String[] values = line.split(",");
			        records.add(Arrays.asList(values));
			    }
			}
			catch(Exception e) {
				
				System.out.println(e);
			}
			
			//initially fills courses from file (assume we start with StudentList as null and # of students as 0)
			for(List<String> x : records) {
				
				Course temp = new Course(x.get(0), x.get(1), Integer.parseInt(x.get(2)), x.get(5), Integer.parseInt(x.get(6)), x.get(7));
				courses.add(temp);
			}
		}
		
		//just deserialize previous data (would add admins here if we had multiple)
		else {
			try{
				  //FileInputSystem recieves bytes from a file
			      FileInputStream fc = new FileInputStream("Courses.ser");
//			      FileInputStream fs = new FileInputStream("Students.ser");
			      
			      //ObjectInputStream reconstructs data
			      ObjectInputStream oc = new ObjectInputStream(fc);
//			      ObjectInputStream os = new ObjectInputStream(fs);

			      //Cast as Employee. readObject will take the object from ObjectInputStream
			      courses = (ArrayList<Course>)oc.readObject();
//			      students = (ArrayList<Student>)os.readObject();
			      
			      //close input streams 
			      oc.close();
			      fc.close();
//			      os.close();
//			      fs.close();
			}
			catch(Exception e) {
			       System.out.println(e);
			}
		}
		
		boolean inSession = true;
		while(inSession) {
			String user = "";
			while(user.equalsIgnoreCase("")) {
				
				System.out.print("Welcome to the Course Registration System. \nEnter 'a' if you are an admin, 's' if you are a student, and 'e' to exit session: ");
				
				String temp = in.readLine();
				
				while (!temp.equalsIgnoreCase("s") && !temp.equalsIgnoreCase("a") && !temp.equalsIgnoreCase("e") ) {
					
					System.out.print("\nPlease enter 'a' for admin, 's' for student, or 'e' to exit: ");
					temp = in.readLine();
				}
				if(temp.equalsIgnoreCase("e")) {
					inSession = false;
					break;
				}
				
				//verify user
				while(!temp.equalsIgnoreCase(user)) {
					
					System.out.print("\nPlease enter your username: ");
					String uname = in.readLine();
					System.out.print("\nPlease enter your password: ");
					String pass = in.readLine();
					
					if(temp.equalsIgnoreCase("a")) {
						for(Admin x : admins) {
							if(x.getUsername().equalsIgnoreCase(uname) && x.getPassword().equalsIgnoreCase(pass)) {
								
								user = temp;
								System.out.println("\nWelcome admin " + admin.getUsername());
								break;
							}
						}
					}
					//if temp equals s
					else {
						for(Student x : students) {
							if(x.getUsername().equalsIgnoreCase(uname) && x.getPassword().equalsIgnoreCase(pass)) {
								
								user = temp; 
								currStudent = x;
								//remove x and add back edited version
								students.remove(x);
								System.out.println("\nWelcome student " + currStudent.getUsername());
								break;
							}
						}
					}
					if(!temp.equalsIgnoreCase(user)) {
						System.out.println("Your username or password were incorrect. Try again.");
					}
				}
			}
			
			//runs while user is an admin
			while(user.equalsIgnoreCase("a")){
				
				System.out.println("\nAdmin menu:");
				System.out.println("Enter 'c' for Course Management");
				System.out.println("Enter 's' for Student Registration");
				System.out.println("Enter 'r' for Reports");
				System.out.println("Enter 'e' to exit Admin");
				
				System.out.print("\nPlease enter  your selection: ");
				
				String choice = in.readLine();
				
				while (!choice.equalsIgnoreCase("c") && !choice.equalsIgnoreCase("s") && !choice.equalsIgnoreCase("r") && !choice.equalsIgnoreCase("e")) {
					
					System.out.print("Please enter 'c' , 's', 'r', or 'e': ");
					choice = in.readLine();
				} 
				//if user chooses course management (the inputs have to be done in the main so the data is saved)
				if(choice.equalsIgnoreCase("c")){
					
					String choice2 = "c";
					while(!choice2.equalsIgnoreCase("e")) {
						
						//actions that can be done
						System.out.print("\nCourse Management: ");
						System.out.print("\nEnter 1 to Create a new Course.");
						System.out.print("\nEnter 2 to Delete a Course.");
						System.out.print("\nEnter 3 to Edit a Course.");
						System.out.print("\nEnter 4 to Display information for a given course.");
						System.out.print("\nEnter e to Exit Course Management.");
							
						System.out.print("\n\nPlease enter a number corresponding to your selection: ");
							
						choice2 = in.readLine();
						
						//loop until valid selection is made
						while (!choice2.equals("1") && !choice2.equals("2") &&!choice2.equals("3") &&!choice2.equals("4") && !choice2.equals("e")) {
							
							System.out.print("\nPlease enter A VALID selection.");
							choice2 = in.readLine();
						}
						//breaks out if user chooses to exit.
						if(choice2.equalsIgnoreCase("e")){
							break;
						}
						courses = admin.manageCourses(courses, choice2);
						
						//checks whether we loop back to manage more courses or exit
						System.out.print("\nEnter 'e' to exit. Enter 'c' to continue managing courses: ");
						choice2 = in.readLine();
						
						while (!choice2.equalsIgnoreCase("e") && !choice2.equalsIgnoreCase("c")) {
							
							System.out.print("\nPlease enter 'e' or 'c': ");
							choice2 = in.readLine();
						}
					}
					
				}
				//student registration (admin does not assign any courses)
				else if (choice.equalsIgnoreCase("s")){
					
					System.out.print("\nStudent Registration: ");
					System.out.print("\nEnter 's' to register a new student. Enter 'e' to exit: ");
					String choice2 = in.readLine();
					while(!choice2.equalsIgnoreCase("e")) {
						
						students = admin.registerStudent(students);
						
						System.out.print("\nEnter 's' to register a new student. Enter 'e' to exit: ");
						choice2 = in.readLine();
						while (!choice2.equalsIgnoreCase("s") && !choice2.equalsIgnoreCase("e")) {
							
							System.out.print("\nPlease enter 's' or 'e': ");
							choice2 = in.readLine();
						}
					}
				}
				else if (choice.equalsIgnoreCase("r")){
					
					String choice2 = "r";
					while(!choice2.equalsIgnoreCase("e")) {
						
						//actions that can be done
						System.out.print("\nReports: ");
						System.out.print("\nEnter 1 to View all Courses.");
						System.out.print("\nEnter 2 to View Filled Courses.");
						System.out.print("\nEnter 3 to Write a file of Filled Courses.");
						System.out.print("\nEnter 4 to View Students in a Course.");
						System.out.print("\nEnter 5 to View Courses a Student is registered in.");
						System.out.print("\nEnter 6 to Sort the courses.");
						System.out.print("\nEnter e to exit Reports.");
						
						System.out.print("\nPlease enter a number corresponding to your selection: ");
							
						choice2 = in.readLine();
						
						//loop until valid selection is made
						while (!choice2.equals("1") && !choice2.equals("2") &&!choice2.equals("3") &&!choice2.equals("4") && !choice2.equals("5") && !choice2.equals("6") && !choice2.equals("e")) {
							
							System.out.print("\nPlease enter A VALID selection: ");
							choice2 = in.readLine();
						}
						//breaks out if user chooses to exit.
						if(choice2.equalsIgnoreCase("e")){
							break;
						}
						admin.reportCourses(courses, students, choice2);
						
						//checks whether we loop back to manage more courses or exit
						System.out.print("\nEnter 'e' to exit. Enter 'r' to view more Reports: ");
						choice2 = in.readLine();
						
						while (!choice2.equalsIgnoreCase("e") && !choice2.equalsIgnoreCase("r")) {
							
							System.out.print("\nPlease enter 'e' or 'r': ");
							choice2 = in.readLine();
						}
					}
				}
				//if equals e
				else {
					//causes it to exit the while loop
					user = "e";
				}
			}
			
			while(user.equalsIgnoreCase("s")){
				
				System.out.println("\nStudent menu:");
				System.out.println("Enter 1 to View all courses.");
				System.out.println("Enter 2 to View all courses that are not FULL.");
				System.out.println("Enter 3 to Register for a course.");
				System.out.println("Enter 4 to Withdraw from a course.");
				System.out.println("Enter 5 to View all your registered courses.");
				System.out.println("Enter e to exit Student.");
				
				System.out.print("\n\nPlease enter a number corresponding to your selection: ");
				String choice = in.readLine();

				while (!choice.equals("1") && !choice.equals("2") &&!choice.equals("3") &&!choice.equals("4") && !choice.equals("5") && !choice.equals("e")) {
					System.out.print("\nPlease enter A VALID selection:");
					choice = in.readLine();
				}
				
				//breaks out if user chooses to exit.
				if(choice.equalsIgnoreCase("e")){
					break;
				}
				
				courses = currStudent.courseManagement(courses, choice);
			} 
		}
		
		//serialization here 
		try {
			
			//FileOutput Stream writes data to a file
			FileOutputStream fc = new FileOutputStream("CourseArray.ser");
//			FileOutputStream fs = new FileOutputStream("Student.ser");

			//ObjectOutputStream writes objects to a stream (A sequence of data)
			ObjectOutputStream oc = new ObjectOutputStream(fc);
//			ObjectOutputStream os = new ObjectOutputStream(fs);
					
			//Writes the array to the output stream
			oc.writeObject(courses);	
//			os.writeObject(students);
					
			//close input streams 
		    oc.close();
		    fc.close();
//		    os.close();
//		    fs.close();

			System.out.println("Your session was exited. All your information has been updated.");
		} 
		catch (IOException e) {
			System.out.println(e);
		}	
	}
}
