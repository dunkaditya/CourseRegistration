package CourseReg;

import java.io.IOException;
import java.util.ArrayList;

public interface StudentInt {
	
	public void displayNotFull(ArrayList<Course> c);
	
	public void displayStudentCourses();
	
	public ArrayList<Course> courseManagement(ArrayList<Course> courses, String choice) throws IOException;
	
	public ArrayList<Course> getCourses() ;
	
}
