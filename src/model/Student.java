/*File: Student.java
* Creator: Team 5
* Course: CMSC 495
* Date: April 22, 2024
* Purpose: Class craetes a Student object.  
*/

package model;

//Class
public class Student {
	//Attributes
	private int studentID;
	private String studentUsername;
	private String studentPassword = new String();
	private String studentFirstName = new String();
	private String studentLastName = new String();
	private String studentAddress = new String();
	private String studentPhone = new String();
	
	//constructor
	public Student (int studentID, String studentUsername) {
		this.studentID = studentID;
		this.studentUsername = studentUsername;
	}
	
	//Get and set statements
	//For studentID
	public int getStudentID() {
		return studentID;
	}
	
	public void setStudentID(int sID) {
		studentID = sID;
	}
	
	//For student username
	
	public String getStudentUsername() {
		return studentUsername;
	}
	
	public void setStudentUsername(String username) {
		studentUsername = username;
	}
	
	//For student password
	
	public String getStudentPassword() {
		return studentPassword;
	}
	
	public void setStudentPassword(String password) {
		studentPassword = password;
	}
	
	//For student first name
	
	public String getStudentFirstName() {
		return studentFirstName;
	}
	
	public void setStudentFirstName(String firstName) {
		studentFirstName = firstName;
	}
	
	//For student last name
	public String getStudentLastName() {
		return studentLastName;
	}
	
	public void setStudentLastName(String lastName) {
		studentLastName = lastName;
	}
	
	//For student address
	
	public String getStudentAddress() {
		return studentAddress;
	}
	
	public void setStudentAddress(String address) {
		studentAddress = address;
	}
	
	//For student phone
	
	public String getStudentPhone() {
		return studentPhone;
	}
	
	public void setStudentPhone(String phoneNumber) {
		studentPhone = phoneNumber;
	}
	
	
	
	
	
	

}
