package CourseReg;

import java.io.Serializable;
import java.util.Comparator;

class CourseCompare implements Comparator<Course>, Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4L;

	@Override
    public int compare(Course cA, Course cB) {
		
		if(cA.numStudents>(cB.numStudents)) return 1;
		if(cA.numStudents<(cB.numStudents)) return -1;
		return 0;
	}
}
