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
		this.courseName = courseName;
		this.assignments = new ArrayList<>();
	}
	
	// Assignment List Control methods
	
	//add new assignment to course
	public void addAssignment(Assignment assignment) {
		assignments.add(assignment);
	}
	
	//remove assignment from course
	public boolean removeAssignment(Assignment assignment) {
		//returns boolean if assignment is found and removed
		return assignments.remove(assignment);
	}
	
	//get full arraylist of assignments
	public List<Assignment> getAssignments(){
		return assignments;
	}
	
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
	}
	
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
			totalGrade = totalGrade + assignment.getActualGrade();
		}//end for loop
		
		//return average grade
		return totalGrade;		
	}
	
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
			totalGrade = totalGrade + assignment.getNeededGrade();
		}//end for loop
		
		//return average grade
		return totalGrade;	
	}
	
	//get current overall course grade in percentage
	public double getAssignmentOverallGrade() {
		return this.getAssignmentActualGradeTotal() / this.getAssignmentNeededGradeTotal();
	}
		
	//average all assignments grades within the course
	public double getAssignmentActualGradeAverage() {
		//exit if there are no assignments yet added to course
		if(assignments.size() == 0) {
			return 0;
		}//end if
		
		//used to hold grade values to be averaged
		double totalGrade = 0;
		
		//loop over all arraylist values
		for (Assignment assignment :assignments) {
			totalGrade = totalGrade + assignment.getActualGrade();
		}//end for loop
		
		//return average grade
		return totalGrade/assignments.size();		
	}
	
	//average all possible assignments grades within the course
	public double getAssignmentNeededGradeAverage() {
		//exit if there are no assignments yet added to course
		if(assignments.size() == 0) {
			return 0;
		}//end if
		
		//used to hold grade values to be averaged
		double totalGrade = 0;
		
		//loop over all arraylist values
		for (Assignment assignment :assignments) {
			totalGrade = totalGrade + assignment.getNeededGrade();
		}//end for loop
		
		//return average grade
		return totalGrade/assignments.size();		
	}
	
	//get 
	
	//Get and set methods
	//For course ID
	
	public int getCourseID() {
		return courseID;
	}
	
	public void setCourseID(int cID) {
		courseID = cID;
	}
	
	//For course code
	
	public String getCourseCode() {
		return courseCode;
	}
	
	public void setCourseCode(String code) {
		courseCode = code;
	}
	
	//For courseNumber
	
	public int getCourseNumber() {
		return courseNumber;
	}
	
	public void setCourseNumber(int cNumber) {
		courseNumber = cNumber;
	}
	
	//For course name
	
	public String getCourseName() {
		return courseName;
	}
	
	public void setCourseName(String cName) {
		courseName = cName;
	}
	
	//For course start date
	
	public String getCourseStartDate() {
		return courseStartDate;
	}
	
	public void setCourseStartDate(String startDate) {
		courseStartDate = startDate;
	}
	
	//For course end date
	
	public String getCourseEndDate() {
		return courseEndDate;
	}
	
	public void setCourseEndDate(String endDate) {
		courseEndDate = endDate;
	}
	
	//For course grade
	
	public int getCourseGrade() {
		return courseGrade;
	}
	
	public void setCourseGrade(int grade) {
		courseGrade = grade;
	}

}
