//Class

public class Assignment {
	
	//Attributes
	
	private String assignmentName = new String();
	private int weightedScore;
	private int neededGrade;
	private int actualGrade;
	
	//Getters and setters
	
	//For assignment name
	
	public String getAssignmentName() {
		return assignmentName;
	}
	
	public void setAssignmentName(String aName) {
		assignmentName = aName;
	}
	
	//For weighted score
	
	public int getWeightedScore() {
		return weightedScore;
	}
	
	public void setWeightedScore(int wScore) {
		weightedScore = wScore;
	}
	
	//For needed grade
	
	public int getNeededGrade() {
		return neededGrade;
	}
	
	public void setNeededGrade(int needGrade) {
		neededGrade = needGrade;
	}
	
	//For actual grade
	
	public int getActualGrade() {
		return actualGrade;
	}
	
	public void setActualGrade(int actGrade) {
		actualGrade = actGrade;
	}
	
	

}
