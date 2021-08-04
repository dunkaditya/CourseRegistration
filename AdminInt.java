package CourseReg;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public interface AdminInt {
	
	public ArrayList<Course> addCourse(String cName, String cId, String mStudents, String instructor,
			String cSection, String cLocation, ArrayList<Course> c);
	
	public ArrayList<Course> editCourse(String courseid, ArrayList<Course> c) throws IOException;
	
	public ArrayList<Course> removeCourse(String courseid, ArrayList<Course> c) throws IOException;
	
	public void displayAllCourses(ArrayList<Course> c);
	
	public void displayFull(ArrayList<Course> c);
	
	public void writeFull(ArrayList<Course> c) throws FileNotFoundException;
	
	public ArrayList<Course> manageCourses(ArrayList<Course> courses, String choice) throws IOException;
	
	public ArrayList<Student> registerStudent(ArrayList<Student> students) throws IOException;
	
	public void reportCourses(ArrayList<Course> courses, ArrayList<Student> students, String choice) throws IOException;
	
	
}
