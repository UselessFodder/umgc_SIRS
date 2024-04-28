/*File: UI.java
* Creator: Team 5
* Course: CMSC 495
* Date: April 22, 2024
* Purpose: Create a GUI for user to add and calculate grade assignments totals and averages. 
*/

package view;

import javax.swing.*;

import controller.Controller;

import java.awt.*;

public class UI {

    private JFrame frame;
    private JTextField classNameField, assignmentNameField, gradeReceivedField, possibleGradeField;
    private JTextArea resultArea;    
	private JButton addAssignmentButton, calculateAverageGradeButton, saveDataButton, loadDataButton, importGradeButton;
    private JComboBox<String> desiredOverallGradeComboBox;

    public UI(Controller controller) {
        initializeUI(controller);
        //register this with controller
        controller.setUI(this);
    }

    private void initializeUI(Controller controller) {
    	frame = new JFrame("UMGC Grade Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

        classNameField = new JTextField();
        classNameField.setPreferredSize(new Dimension(150,20));
        assignmentNameField = new JTextField();
        assignmentNameField.setPreferredSize(new Dimension(150,20));
        gradeReceivedField = new JTextField();
        gradeReceivedField.setPreferredSize(new Dimension(150,20));
        possibleGradeField = new JTextField();
        possibleGradeField.setPreferredSize(new Dimension(150,20));

        addAssignmentButton = new JButton("Add Assignment");
        addAssignmentButton.setActionCommand("addAssignment");
        addAssignmentButton.addActionListener(controller);

        resultArea = new JTextArea(5, 20);
        resultArea.setEditable(false);
        resultArea.setBackground(frame.getBackground());

        calculateAverageGradeButton = new JButton("Calculate Average Grade");
        saveDataButton = new JButton("Save Data");
        loadDataButton = new JButton("Load Data");
        importGradeButton = new JButton("Import Grade");

        String[] grades = {"A", "B", "C", "D", "F"};
        desiredOverallGradeComboBox = new JComboBox<>(grades);

        frame.add(createInputPanel("Class Name:", classNameField));
        frame.add(createInputPanel("Assignment Name:", assignmentNameField));
        frame.add(createInputPanel("Grade Received:", gradeReceivedField));
        frame.add(createInputPanel("Possible Grade:", possibleGradeField));
        frame.add(addAssignmentButton);
        frame.add(resultArea);
        frame.add(calculateAverageGradeButton);
        frame.add(saveDataButton);
        frame.add(loadDataButton);
        frame.add(importGradeButton);
        frame.add(createInputPanel("Desired Overall Grade:", desiredOverallGradeComboBox));

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private JPanel createInputPanel(String label, JComponent field) {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel.add(new JLabel(label));
        panel.add(field);
        return panel;
    }
    
	//getters and setters
	public JFrame getFrame() {
		return frame;
	}
	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
	public JTextArea getResultArea() {
		return resultArea;
	}
	public void setResultArea(JTextArea resultArea) {
		this.resultArea = resultArea;
	}
    public JTextField getClassNameField() {
		return classNameField;
	}
	public void setClassNameField(JTextField classNameField) {
		this.classNameField = classNameField;
	}
    public JTextField getAssignmentNameField() {
		return assignmentNameField;
	}

	public void setAssignmentNameField(JTextField assignmentNameField) {
		this.assignmentNameField = assignmentNameField;
	}

	public JTextField getGradeReceivedField() {
		return gradeReceivedField;
	}

	public void setGradeReceivedField(JTextField gradeReceivedField) {
		this.gradeReceivedField = gradeReceivedField;
	}

	public JTextField getPossibleGradeField() {
		return possibleGradeField;
	}

	public void setPossibleGradeField(JTextField possibleGradeField) {
		this.possibleGradeField = possibleGradeField;
	}

	public JButton getAddAssignmentButton() {
		return addAssignmentButton;
	}

	public void setAddAssignmentButton(JButton addAssignmentButton) {
		this.addAssignmentButton = addAssignmentButton;
	}

}
