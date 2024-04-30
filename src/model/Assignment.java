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
	private double weightedScore;
	private double neededGrade;
	private double actualGrade;
	
	//constructor
	public Assignment(String assignmentName, double actualGrade, double neededGrade) {
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
	public Assignment(String assignmentName, double neededGrade) {
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
	public double remainingPoints(double neededGrade, double actualGrade) {
		double remainingPoints = neededGrade - actualGrade;
			return remainingPoints;
	}
	
	//updates weightedScore to new actualGrade
	public double calculateWeightedScore() {
		if (actualGrade > 0) {
			this.weightedScore = (actualGrade/1000) * 100;
			return this.weightedScore;
		} else {
			return 0;
		}//end if-else
	}//end calculateWeightedScore
	
	public double calculateGradeFromPercentage(double desiredPercentage) {
		//condition desiredPercentage into whole numbers
		if(desiredPercentage < 1) {
			desiredPercentage = desiredPercentage * 100;
		}//end if
		
		double exactGrade = neededGrade * (desiredPercentage / 100);
		//round up to nearest whole number
		return exactGrade;
		
	}//end calculateGradeFromPercentage
	
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
	public double getWeightedScore() {
		return weightedScore;
	}//end getWeightedSCore
	
	public void setWeightedScore(double wScore) {
		weightedScore = wScore;
	}//end setWeightedScore
	
	//For needed grade
	public double getNeededGrade() {
		return neededGrade;
	}//end getNeededGrade
	
	public void setNeededGrade(double needGrade) {
		//check if value is above 0
		if(needGrade > 0) {
			neededGrade = needGrade;
		} else {
			//if not, throw error
	        throw new IllegalArgumentException("Possible Grade Value Must Be Greater than 0");
		}//end if-else
	}//end setNeededGrade
	
	//For actual grade
	public double getActualGrade() {
		return actualGrade;
	}//end getActuakGrade
	
	public void setActualGrade(double actGrade) {
		actualGrade = actGrade;
	}//end setActualGrade
}
