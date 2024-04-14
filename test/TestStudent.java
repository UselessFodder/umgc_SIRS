/*File: TestStudent.java
* Creator: Team 5
* Course: CMSC 495
* Date: April 13, 2024
* Purpose: Create test case to ensure Student object is being created. New test will be created for 
* future methods that have not been created yet. 
*/

package Test;

import static org.junit.Assert.*;
import org.junit.Test;
import model.Student;

public class TestStudent {
	
	//Instance Student class to test
	Student test = new Student();

	@Test
	public void test() {

		//Sets values for test instance of student class
		test.setStudentID(1234);
		test.setStudentUsername("username");
		test.setStudentPassword("password");
		test.setStudentFirstName("John");
		test.setStudentLastName("Doe");
		test.setStudentAddress("address input");
		test.setStudentPhone("555-555-5555");
		
		//Returns the runtime class of this Object
		Object output = test.getClass();	
		
		//Asserts that an object isn't null
		assertNotNull(output);
	}
}// End of TestStudent