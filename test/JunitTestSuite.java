/*File: JunitTestSuite.java
* Creator: Team 5
* Course: CMSC 495
* Date: April 13, 2024
* Purpose: Create test used to bundle a few unit test cases and run them together.
*/

package Test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)

@SuiteClasses({ 
	TestAssignment.class, 
	TestCourse.class, 
	TestStudent.class 
	})

public class JunitTestSuite {

	//Will fill later when more complex code is tested
}