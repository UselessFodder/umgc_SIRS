/*File: Controller.java
* Creator: Team 5
* Course: CMSC 495
* Date: April 22, 2024
* Purpose: Add functionality by connecting the GUI with the backend logic.
*/

package controller;

import model.Assignment;
import model.Course;
import view.UI;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class Controller implements ActionListener {
	private UI ui;
	private Course course;

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
			if (!isValidInput(className, assignmentName, gradeReceivedStr, possibleGradeStr)) {
				// Display error message
				JOptionPane.showMessageDialog(ui.getFrame(), "Invalid input! Please check your entries.",
											  "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}

			// If course object is not initialized, initialize it
			if (course.getCourseName().equals("undefined")) {
				initializeCourse(className);
			}

			int gradeReceived = Integer.parseInt(gradeReceivedStr);
			int possibleGrade = Integer.parseInt(possibleGradeStr);

			addAssignment(assignmentName, gradeReceived, possibleGrade);

			// Append inputs to the input screen
			appendInputToScreen(className, assignmentName, gradeReceived, possibleGrade);

			updateAssignmentDisplay();
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
			Integer.parseInt(gradeReceived);
			Integer.parseInt(possibleGrade);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

	private boolean isValidGrade(String grade) {
		// Check if grade is numeric or one of the valid grades
		try {
			int gradeValue = Integer.parseInt(grade);
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

	public void addAssignment(String name, int gradeReceived, int possibleGrade) {
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
			int gradeReceived = assignment.getActualGrade();
			int possibleGrade = assignment.getNeededGrade();
			String assignmentInput = name + " " + gradeReceived + " / " + possibleGrade + "\n";
			assignmentText.append(assignmentInput);
		}

		// Set new assignments output data as results text
		JTextArea resultArea = ui.getResultArea();
		resultArea.setText(assignmentText.toString());
	}

	public void appendInputToScreen(String className, String assignmentName, int gradeReceived, int possibleGrade) {
		JTextArea resultArea = ui.getResultArea();
		resultArea.append("Class Name: " + className + "\n");
		resultArea.append("Assignment Name: " + assignmentName + "\n");
		resultArea.append("Grade Received: " + gradeReceived + "\n");
		resultArea.append("Possible Grade: " + possibleGrade + "\n\n");
	}

	// Getters and setters
	public UI getUI() {
		return this.ui;
	}

	public void setUI(UI ui) {
		this.ui = ui;
	}
}
