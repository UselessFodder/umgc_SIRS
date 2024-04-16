/*File: TestAssignment.java
* Creator: Team 5
* Course: CMSC 495
* Date: April 13, 2024
* Purpose: Create test case to ensure Assignment object is being created. New test will be created for 
* future methods that have not been created yet. 
*/

package Test;

import static org.junit.Assert.*;
import org.junit.Test;
import model.Assignment;

public class TestAssignment {

	//Instance assignment class to test
	Assignment test = new Assignment();

	@Test
	public void test() {

		//Sets values for test instance of assignment class
		test.setAssignmentName("Homework 1");
		test.setWeightedScore(10);
		test.setNeededGrade(90);
		test.setActualGrade(70);
		
		//Returns the runtime class of this Object
		Object output = test.getClass();	
		
		//Asserts that an object isn't null
		assertNotNull(output);
	}
} // End of TestAssignment