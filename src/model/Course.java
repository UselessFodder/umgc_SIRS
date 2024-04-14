
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
