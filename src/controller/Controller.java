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

			double gradeReceived = Double.parseDouble(gradeReceivedStr);
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
		// Handle other actions...
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
			return gradeValue >= 0 && gradeValue <= 1000; // Assuming grade is between 0 and 1000
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
		// Get all course assignments
		List<Assignment> assignments = course.getAssignments();
		// Hold text string outputs
		StringBuilder assignmentText = new StringBuilder();

		// Iterate through all assignments and output data to string
		for (Assignment assignment : assignments) {
			String name = assignment.getAssignmentName();
			double gradeReceived = assignment.getActualGrade();
			double possibleGrade = assignment.getNeededGrade();
			String assignmentInput = name + " " + gradeReceived + " / " + possibleGrade + "\n";
			assignmentText.append(assignmentInput);
		}

		// Set new assignments output data as results text
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
	
	public void handleCalculateGrades() {
	    double currentGradePercentage = course.getAssignmentOverallGrade() * 100;
	    double desiredGradePercentage = convertGradeToPercentage(this.desiredGrade);
	    double percentNeeded = course.calculatePercentNeededForGrade(desiredGradePercentage);

	    StringBuilder message = new StringBuilder("<html>You currently have <b>" + String.format("%.2f%%", currentGradePercentage) + "</b> in this course.<br>");
	    message.append("You need to get <b>" + String.format("%.2f%%", percentNeeded) + "</b> on your remaining assignments ");
	    message.append("in order to get your goal grade of <b>" + this.desiredGrade + "</b>.<br>Here is what scores you must get on the future assignments you have input:<br>");

	    List<Assignment> futureAssignments = course.getAssignments().stream()
	                                                .filter(a -> a.getActualGrade() == -1)
	                                                .collect(Collectors.toList());
	    if (futureAssignments.isEmpty()) {
	        message.append("No future assignments found.<br>");
	    } else {
	        for (Assignment assignment : futureAssignments) {
	            double neededGrade = calculateNeededGradeForAssignment(assignment, currentGradePercentage, desiredGradePercentage);
	            message.append(assignment.getAssignmentName() + ": <b>" + String.format("%.2f%%", neededGrade) + "</b><br>");
	        }
	    }

	    message.append("</html>");
	    JOptionPane.showMessageDialog(ui.getFrame(), message.toString(), "Grade Calculation", JOptionPane.INFORMATION_MESSAGE);
	}

	private double calculateNeededGradeForAssignment(Assignment assignment, double currentGradePercentage, double desiredGradePercentage) {
	    double weight = assignment.getWeightedScore();  // Use weightedScore as the weight of the assignment towards the final grade
	    double neededGrade = (desiredGradePercentage - currentGradePercentage) / weight; 
	    return neededGrade > 0 ? neededGrade : 0;  // Ensure no negative grades are suggested
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
