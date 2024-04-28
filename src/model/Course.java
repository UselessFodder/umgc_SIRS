/*File: Course.java
* Creator: Team 5
* Course: CMSC 495
* Date: April 22, 2024
* Purpose: Class craetes a course object.  
*/

package model;
import java.util.ArrayList;
import java.util.List;

//Class
public class Course {
	
	//Attributes
	private int courseID;
	private String courseCode = new String();
	private int courseNumber;
	private String courseName = new String();
	private String courseStartDate = new String();
	private String courseEndDate = new String();
	private int courseGrade;
	//list to hold all assignments related to course
	private List<Assignment> assignments;
	
	//constructor
	public Course(String courseName) {
		//if name is empty, throw error
		if (courseName == null || courseName.trim().isEmpty()) {
            throw new IllegalArgumentException("Course name cannot be empty.");
        } else {
    		this.courseName = courseName;
        }//end if-else

		this.assignments = new ArrayList<>();
	}//end constructor
	
	// Assignment List Control methods
	
	//add new assignment to course
	public void addAssignment(Assignment assignment) {
		assignments.add(assignment);
	}//end addAssignment
	
	//remove assignment from course
	public boolean removeAssignment(Assignment assignment) {
		//returns boolean if assignment is found and removed
		return assignments.remove(assignment);
	}//end removeAssignment
	
	//get full arraylist of assignments
	public List<Assignment> getAssignments(){
		return assignments;
	}//end getAssignments
	
	//retrieve an assignment by name
	public Assignment getAssignmentByName(String name) {
		//loop over all arraylist values
		for (Assignment assignment : assignments) {
			if(assignment.getAssignmentName().equals(name)) {
				return assignment;
			}//end if
		}//end for loop
		
		//if assignment is not in the list, return null
		//***TODO: consider throwing error here
		
		return null;
	}//end getAssignmentByName
	
	//sum all assignments grades within the course
	public int getAssignmentActualGradeTotal() {
		//exit if there are no assignments yet added to course
		if(assignments.size() == 0) {
			return 0;
		}//end if
		
		//used to hold grade values to be averaged
		int totalGrade = 0;
		
		//loop over all arraylist values
		for (Assignment assignment :assignments) {
			//do not add if assignment does not have an actual grade defined
			if(assignment.getActualGrade() >= 0) {
				totalGrade = totalGrade + assignment.getActualGrade();
			}
		}//end for loop
		
		//return average grade
		return totalGrade;		
	}//end getAssignmentActualGradeTotal
	
	//sum all possible assignments grades within the course
	public int getAssignmentNeededGradeTotal() {
		//exit if there are no assignments yet added to course
		if(assignments.size() == 0) {
			return 0;
		}//end if
		
		//used to hold grade values to be averaged
		int totalGrade = 0;
		
		//loop over all arraylist values
		for (Assignment assignment :assignments) {
			//do not add if assignment does not have an actual grade defined
			if(assignment.getActualGrade() >= 0) {
				totalGrade = totalGrade + assignment.getNeededGrade();
			}//end if
		}//end for loop
		
		//return average grade
		return totalGrade;	
	}//end getAssignmentNeededGradeTotal
	
	//get current overall course grade in percentage
	public double getAssignmentOverallGrade() {
		return this.getAssignmentActualGradeTotal() / this.getAssignmentNeededGradeTotal();
	}//end getAssignmentOverallGrade
		
	//average all assignments grades within the course
	public double getAssignmentActualGradeAverage() {
		//exit if there are no assignments yet added to course
		if(assignments.size() == 0) {
			//-1 indicates no assignments to average
			return -1;
		}//end if
		
		//used to hold grade values to be averaged
		double totalGrade = 0;
		int assignmentCount = 0;
		
		//loop over all arraylist values
		for (Assignment assignment :assignments) {
			//do not add if assignment does not have an actual grade defined
			if(assignment.getActualGrade() >= 0) {
				totalGrade = totalGrade + assignment.getActualGrade();
				assignmentCount++;
			}//end if
		}//end for loop
		
		//return average grade
		return totalGrade/assignmentCount;	
	}//end getAssignmentActualGradeAverage
	
	//average all possible assignments grades within the course
	public double getAssignmentNeededGradeAverage() {
		//exit if there are no assignments yet added to course
		if(assignments.size() == 0) {
			//-1 indicates no assignments to average
			return -1;
		}//end if
		
		//used to hold grade values to be averaged
		double totalGrade = 0;
		
		//loop over all arraylist values
		for (Assignment assignment :assignments) {
			totalGrade = totalGrade + assignment.getNeededGrade();
		}//end for loop
		
		//return average grade
		return totalGrade/assignments.size();		
	}//end getAssignmentNeededGradeAverage
	
	//----------- Get and set methods
	
	//For course ID
	public int getCourseID() {
		return courseID;
	}//end getCourseID
	
	public void setCourseID(int cID) {
		courseID = cID;
	}//end setCourseID
	
	//For course code
	
	public String getCourseCode() {
		return courseCode;
	}//end getCourseCode
	
	public void setCourseCode(String code) {
		courseCode = code;
	}//end setCourseCode
	
	//For courseNumber
	public int getCourseNumber() {
		return courseNumber;
	}//end getCourseNumber
	
	public void setCourseNumber(int cNumber) {
		courseNumber = cNumber;
	}//end setCourseNumber
	
	//For course name
	public String getCourseName() {
		return courseName;
	}//end getCourseName
	
	public void setCourseName(String cName) {
		//if name is empty, throw error
		if (cName == null || cName.trim().isEmpty()) {
            throw new IllegalArgumentException("Course name cannot be empty.");
        } else {
    		this.courseName = cName;
        }//end if-else
	}//end setCourseName
	
	//For course start date
	public String getCourseStartDate() {
		return courseStartDate;
	}//end getCourseStartDate
	
	public void setCourseStartDate(String startDate) {
		courseStartDate = startDate;
	}//end setCourseStartDate
	
	//For course end date
	public String getCourseEndDate() {
		return courseEndDate;
	}//end getCourseEndDate
	
	public void setCourseEndDate(String endDate) {
		courseEndDate = endDate;
	}//end setCourseEndDate
	
	//For course grade
	public int getCourseGrade() {
		return courseGrade;
	}//end getCourseGrade
	
	public void setCourseGrade(int grade) {
		courseGrade = grade;
	}//end setCourseGrade

}
