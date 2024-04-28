/*File: Assignment.java
* Creator: Team 5
* Course: CMSC 495
* Date: April 22, 2024
* Purpose: Class creates a assignment object to be added to a course and displayed in the GUI
*/

package model;

//Class

public class Assignment {
	
	//Attributes
	
	private String assignmentName = new String();
	private int weightedScore;
	private int neededGrade;
	private int actualGrade;
	
	//constructor
	public Assignment(String assignmentName, int actualGrade, int neededGrade) {
		//if name is empty, throw error
		if (assignmentName == null || assignmentName.trim().isEmpty()) {
            throw new IllegalArgumentException("Assignment name cannot be empty.");
        } else {
    		this.assignmentName = assignmentName;
        }//end if-else
		
		//check if value is invalid below 1
		if(neededGrade < 1) {
			//if invalid, throw error
	        throw new IllegalArgumentException("Possible Grade Value Must Be Greater than 0");
		} else {
			this.neededGrade = neededGrade;
		}//end if-else

		this.actualGrade = actualGrade;
		this.weightedScore = (actualGrade/1000) * 100;
	}//end constructor
	
	//constructor to calculate weighted score
	public Assignment(String assignmentName, int neededGrade) {
		//if name is empty, throw error
		if (assignmentName == null || assignmentName.trim().isEmpty()) {
            throw new IllegalArgumentException("Assignment name cannot be empty.");
        } else {
    		this.assignmentName = assignmentName;
        }//end if-else
		
		//check if value is invalid below 1
		if(neededGrade < 1) {
			//if invalid, throw error
	        throw new IllegalArgumentException("Possible Grade Value Must Be Greater than 0");
		} else {
			this.neededGrade = neededGrade;
		}//end if-else
		
		this.actualGrade = -1;
	}//end constructor

	//Function to calculate points that the user still has to get to reach the needed grade for an assignment
	public int remainingPoints(int neededGrade, int actualGrade) {
		int remainingPoints = neededGrade - actualGrade;
			return remainingPoints;
	}
	
	//--------	Getters and setters
	//For assignment name
	
	public String getAssignmentName() {
		return assignmentName;
	}//end getAssignmentName
	
	public void setAssignmentName(String aName) {
		//if name is empty, throw error
		if (aName == null || aName.trim().isEmpty()) {
            throw new IllegalArgumentException("Assignment name cannot be empty.");
        } else {
        	assignmentName = aName;
        }//end if-else
	}//end setAssignmentName
	
	//For weighted score
	public int getWeightedScore() {
		return weightedScore;
	}//end getWeightedSCore
	
	public void setWeightedScore(int wScore) {
		weightedScore = wScore;
	}//end setWeightedScore
	
	//For needed grade
	public int getNeededGrade() {
		return neededGrade;
	}//end getNeededGrade
	
	public void setNeededGrade(int needGrade) {
		//check if value is above 0
		if(needGrade > 0) {
			neededGrade = needGrade;
		} else {
			//if not, throw error
	        throw new IllegalArgumentException("Possible Grade Value Must Be Greater than 0");
		}//end if-else
	}//end setNeededGrade
	
	//For actual grade
	public int getActualGrade() {
		return actualGrade;
	}//end getActuakGrade
	
	public void setActualGrade(int actGrade) {
		actualGrade = actGrade;
	}//end setActualGrade
}
