/*File: TestCourse.java
* Creator: Team 5
* Course: CMSC 495
* Date: April 13, 2024
* Purpose: Create test case to ensure Course object is being created. New test will be created for 
* future methods that have not been created yet. 
*/

package Test;

import static org.junit.Assert.*;
import org.junit.Test;
import model.Course;

public class TestCourse {

	//Instance course class to test
	Course test = new Course();

	@Test
	public void test() {

		//Sets values for test instance of course class
		test.setCourseID(1234);
		test.setCourseCode("A123");
		test.setCourseNumber(4567);
		test.setCourseName("Course Name");
		test.setCourseStartDate("March 10, 2024");
		test.setCourseEndDate("May 10, 202");
		test.setCourseGrade(90);
		
		//Returns the runtime class of this Object
		Object output = test.getClass();	
		
		//Asserts that an object isn't null
		assertNotNull(output);
	}	
} // End of TestCourse



