/*File: Controller.java
* Creator: Team 5
* Course: CMSC 495
* Date: April 22, 2024
* Purpose: Add functionality by connecting the GUI with the backend logic.
*/

package controller;

import model.Assignment;
import model.Course;
import model.DataPersistenceManager;
import view.UI;

//Imports for the JFileChooser
import javax.swing.JButton;
import javax.swing.JFileChooser;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.stream.Collectors;
import java.text.DecimalFormat;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class Controller implements ActionListener {
	private UI ui;
	private Course course;
	private String desiredGrade = "A";

	public Controller() {
		this.course = new Course("undefined");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			if ("Add Assignment".equals(e.getActionCommand())) {
				// Get assignment information from fields
				String className = ui.getClassNameField().getText();
				String assignmentName = ui.getAssignmentNameField().getText();
				String gradeReceivedStr = ui.getGradeReceivedField().getText();
				String possibleGradeStr = ui.getPossibleGradeField().getText();
	
				// Validate input fields
		        	if (!ui.getFutureAssignmentCheckbox().isSelected() && gradeReceivedStr.isEmpty()) {
		            		JOptionPane.showMessageDialog(ui.getFrame(), "Please enter an actual grade.", "Error", JOptionPane.ERROR_MESSAGE);
		            		return;
		        	}
	
		        	try {
		            	double gradeReceived = gradeReceivedStr.isEmpty() ? 0 : Double.parseDouble(gradeReceivedStr);
		            	double possibleGrade = Double.parseDouble(possibleGradeStr);
		            	double percentage = (gradeReceived / possibleGrade) * 100;
		            	DecimalFormat df = new DecimalFormat("#.##");
		            	String formattedOutput = assignmentName + " Grade: " + (gradeReceivedStr.isEmpty() ? "*" : gradeReceivedStr) +
		                                     "/" + possibleGrade + " = " + (gradeReceivedStr.isEmpty() ? "*" : df.format(percentage) + "%");
	
		            	ui.getResultArea().append(formattedOutput + "\n");
	
		            	// Clear fields except for the class name after adding the assignment
		            	ui.getAssignmentNameField().setText("");
		            	ui.getGradeReceivedField().setText("");
		            	ui.getPossibleGradeField().setText("");
	
		        	} catch (NumberFormatException ex) {
		            		JOptionPane.showMessageDialog(ui.getFrame(), "Please enter valid numbers for grades.", "Error", JOptionPane.ERROR_MESSAGE);
		        	}
	
				// If course object is not initialized, initialize it
				if (course.getCourseName().equals("undefined")) {
					initializeCourse(className);
				}
	
				double gradeReceived;
				try{
					gradeReceived = Double.parseDouble(gradeReceivedStr);
				} catch (Exception noGradeException) {
					gradeReceived = -1;
				}//end try-catch
				double possibleGrade = Double.parseDouble(possibleGradeStr);
	
				addAssignment(assignmentName, gradeReceived, possibleGrade);
	
				// Append inputs to the input screen *** UNNEEDED DUE TO ADDITION OF LINES 46-61 ABOVE
				//appendInputToScreen(className, assignmentName, gradeReceived, possibleGrade);
				//updateAssignmentDisplay();
			}
			if("Save Data".equals(e.getActionCommand())) {
				//save the data
				saveCourses();
			}
			if("Load Data".equals(e.getActionCommand())) {
				//save the data
				loadCourses();
			}
			if ("Calculate Needed Grades".equals(e.getActionCommand())) {
	            handleCalculateGrades();
			}
			if("Delete Last Assignment".equals(e.getActionCommand())) {
				//remove last assignment added to course
				removeLastAssignment();
			}
		} catch (Exception unhandledException) {
			JOptionPane.showMessageDialog(ui.getFrame(), unhandledException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}//end try-catch
	}

	private boolean isValidInput(String className, String assignmentName, String gradeReceived, String possibleGrade) {
		// Check for inappropriate characters or empty fields
		if (className.isEmpty() || assignmentName.isEmpty() || gradeReceived.isEmpty() || possibleGrade.isEmpty() ||
			!isValidGrade(gradeReceived) || !isValidGrade(possibleGrade) ||
			containsInvalidCharacters(className) || containsInvalidCharacters(assignmentName)) {
			return false;
		}
		try {
			Double.parseDouble(gradeReceived);
			Double.parseDouble(possibleGrade);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

	private boolean isValidGrade(String grade) {
		// Check if grade is numeric or one of the valid grades
		try {
			double gradeValue = Double.parseDouble(grade);
			return gradeValue >= -1 && gradeValue <= 1000; // Assuming grade is between 0 and 1000 w/-1 as unassigned value
		} catch (NumberFormatException e) {
			String[] validGrades = {"A", "B", "C", "D", "F"};
			for (String validGrade : validGrades) {
				if (grade.equalsIgnoreCase(validGrade)) {
					return true;
				}
			}
			return false;
		}
	}

	private boolean containsInvalidCharacters(String input) {
		// Check for invalid characters like '/n'
		return input.contains("/n");
	}

	public void initializeCourse(String courseName) {
		Course course = new Course(courseName);
		this.course = course;
		// Prevent UI course name from being edited after initialization
		ui.getClassNameField().setEditable(false);
	}

	public void addAssignment(String name, double gradeReceived, double possibleGrade) {
		// Create an assignment to add to course
		Assignment assignment = new Assignment(name, gradeReceived, possibleGrade);

		// Call the method to create the Assignment
		course.addAssignment(assignment);

		// Print added assignment to console
		System.out.println("Added Assignment: " + assignment.getAssignmentName() + " - Grade Received: " + assignment.getActualGrade() + " - Possible Grade: " + assignment.getNeededGrade());
	}

	public void updateAssignmentDisplay() {
	    if (course == null || course.getAssignments() == null) {
	        ui.getResultArea().setText("No assignments to display."); // Handle case with no course or assignments
	        return;
	    }

	    StringBuilder assignmentText = new StringBuilder();
	    DecimalFormat df = new DecimalFormat("#.##"); // Format numbers to two decimal places

	    for (Assignment assignment : course.getAssignments()) {
	        String name = assignment.getAssignmentName();
	        double gradeReceived = assignment.getActualGrade();
	        double possibleGrade = assignment.getNeededGrade();

	        String percentageDisplay = (gradeReceived == -1) ? "Future" : df.format((gradeReceived / possibleGrade) * 100) + "%";
	        String assignmentInput = String.format("%s - %s / %s = %s\n",
	            name,
	            (gradeReceived == -1) ? "*" : df.format(gradeReceived),
	            df.format(possibleGrade),
	            percentageDisplay);

	        assignmentText.append(assignmentInput);
	    }

	    // Access resultArea through the getter and set the text
	    JTextArea resultArea = ui.getResultArea();
	    resultArea.setText(assignmentText.toString());
	}



	public void appendInputToScreen(String className, String assignmentName, double gradeReceived, double possibleGrade) {
		JTextArea resultArea = ui.getResultArea();
		resultArea.append("Class Name: " + className + "\n");
		resultArea.append("Assignment Name: " + assignmentName + "\n");
		resultArea.append("Grade Received: " + gradeReceived + "\n");
		resultArea.append("Possible Grade: " + possibleGrade + "\n\n");
	}

	//JFileChooser to choose assignments
	public String JFileChooser(String chooserType) {
		JButton action = new JButton();
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new java.io.File(".")); //The dot stands in for the files directory.
		fileChooser.setDialogTitle("Welcome! Please select your file:");
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		if(chooserType == "open") {
			if (fileChooser.showOpenDialog(action) == JFileChooser.APPROVE_OPTION) {
				String fileOutput= fileChooser.getSelectedFile().toString();//Opens file
				return fileOutput;
			}
		}
		if (chooserType == "save") {
			if (fileChooser.showSaveDialog(action) == JFileChooser.APPROVE_OPTION) {
				String fileOutput= fileChooser.getSelectedFile().toString()+".dat";//Opens file
				return fileOutput;
			}
		}
		return null;
	}
	
	//Method to save courses
	public void saveCourses() {
		String fileOutput = JFileChooser("save"); //Calling file chooser
		DataPersistenceManager.saveCourses(course, fileOutput);
		
	}
	
	//Method to load courses
	public void loadCourses() {
		String fileOutput = JFileChooser("open"); //Calling file chooser
		course = DataPersistenceManager.loadCourses(fileOutput);
		if (course == null) {
			//course = new Course();
			throw new IllegalArgumentException("No saved SIRS file found");
			
		} else {
			this.course = course;
			updateAssignmentDisplay();
		}
	}
	
	private void removeLastAssignment() {
		boolean didRemove = course.removeAssignmentLastAdded();
		if (!didRemove) {
			JOptionPane.showMessageDialog(ui.getFrame(), "There is no assignment to delete.", "Error", JOptionPane.ERROR_MESSAGE);
		}
		updateAssignmentDisplay();
	}//end removeLastAssignment()
	
	public void handleCalculateGrades() {
	    if (course == null || course.getAssignments() == null) {
	        JOptionPane.showMessageDialog(ui.getFrame(), "No course or assignments loaded.");
	        return;
	    }

	    double currentGradePercentage = course.getAssignmentOverallGrade() * 100;  // Calculate the current overall grade percentage
	    double desiredGradePercentage = convertGradeToPercentage(this.desiredGrade);  // Convert the desired grade from a letter to a percentage

	    calculateNeededGradeForFutureAssignments(currentGradePercentage, desiredGradePercentage);  // Calculate required grades for future assignments
	}


	public void calculateNeededGradeForFutureAssignments(double currentGradePercentage, double desiredGradePercentage) {
	    double totalPossiblePoints = 0;
	    double totalAchievedPoints = 0;
	    double futurePointsAvailable = 0;

	    // Sum up points from all assignments
	    for (Assignment assignment : course.getAssignments()) {
	        double maxPoints = assignment.getNeededGrade();  // Assuming neededGrade represents the max points for this assignment
	        totalPossiblePoints += maxPoints;
	        if (assignment.getActualGrade() >= 0) {  // Check if the assignment has been graded
	            totalAchievedPoints += assignment.getActualGrade();
	        } else {  // Sum future assignment points
	            futurePointsAvailable += maxPoints;
	        }
	    }

	    double totalPointsNeeded = (totalPossiblePoints * desiredGradePercentage / 100);
	    double pointsStillNeeded = totalPointsNeeded - totalAchievedPoints;

	    // Calculate how much each future assignment needs to contribute
	    StringBuilder results = new StringBuilder("<html>You currently have <b>" + String.format("%.2f%%", currentGradePercentage) + "</b> of the total grade.<br>");
	    results.append("You need to score an additional <b>" + String.format("%.2f", pointsStillNeeded) + "</b> points to achieve a <b>" + desiredGrade + "</b> grade.<br>");
	    results.append("Required scores for future assignments:<br>");

	    for (Assignment assignment : course.getAssignments()) {
	        if (assignment.getActualGrade() == -1) {  // Only consider future assignments
	            double maxPoints = assignment.getNeededGrade();
	            double requiredPoints = (maxPoints / futurePointsAvailable) * pointsStillNeeded;
	            results.append(String.format("%s: <b>%.2f</b> out of %.2f points<br>", 
	                assignment.getAssignmentName(), 
	                requiredPoints, 
	                maxPoints));
	        }
	    }

	    results.append("</html>");
	    JOptionPane.showMessageDialog(ui.getFrame(), results.toString(), "Grade Calculation Results", JOptionPane.INFORMATION_MESSAGE);
	}

	
	public void setDesiredGrade(String grade) {
        this.desiredGrade = grade;
    }
	
	private double convertGradeToPercentage(String grade) {
        switch(grade) {
            case "A": return 90.0;
            case "B": return 80.0;
            case "C": return 70.0;
            case "D": return 60.0;
            case "F": return 50.0;
            default: return 0.0;
        }
    }

	// Getters and setters
	public UI getUI() {
		return this.ui;
	}

	public void setUI(UI ui) {
		this.ui = ui;
	}
}

