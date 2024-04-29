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
    private static final Color DARK_GREY = new Color(43, 43, 43);
    private static final Color LIGHT_GREY = new Color(200, 200, 200);
    private static final Color UMGC_YELLOW = new Color(255, 204, 0);
    private static final Font MAIN_FONT = new Font("Helvetica", Font.PLAIN, 14);

    public UI(Controller controller) {
        initializeUI(controller);
        controller.setUI(this);
    }

    private void initializeUI(Controller controller) {
        frame = new JFrame("UMGC Grade Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());
        frame.getContentPane().setBackground(DARK_GREY);
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 10, 5, 10);

        classNameField = createTextField();
        assignmentNameField = createTextField();
        gradeReceivedField = createTextField();
        possibleGradeField = createTextField();

        addAssignmentButton = createButton("Add Assignment", controller);
        calculateAverageGradeButton = createButton("Calculate Average Grade", controller);
        saveDataButton = createButton("Save Data", controller);
        loadDataButton = createButton("Load Data", controller);
        importGradeButton = createButton("Import Grade", controller);

        resultArea = new JTextArea(5, 20);
        resultArea.setFont(MAIN_FONT);
        resultArea.setEditable(false);
        resultArea.setBackground(LIGHT_GREY);
        resultArea.setForeground(Color.BLACK);

        desiredOverallGradeComboBox = new JComboBox<>(new String[]{"A", "B", "C", "D", "F"});
        desiredOverallGradeComboBox.setFont(MAIN_FONT);
        desiredOverallGradeComboBox.setBackground(LIGHT_GREY);
        desiredOverallGradeComboBox.setForeground(Color.BLACK);

        frame.add(createInputPanel("Class Name:", classNameField), gbc);
        frame.add(createInputPanel("Assignment Name:", assignmentNameField), gbc);
        frame.add(createInputPanel("Grade Received:", gradeReceivedField), gbc);
        frame.add(createInputPanel("Possible Grade:", possibleGradeField), gbc);
        frame.add(addAssignmentButton, gbc);
        frame.add(new JScrollPane(resultArea), gbc);
        frame.add(calculateAverageGradeButton, gbc);
        frame.add(saveDataButton, gbc);
        frame.add(loadDataButton, gbc);
        frame.add(importGradeButton, gbc);
        frame.add(createInputPanel("Desired Overall Grade:", desiredOverallGradeComboBox), gbc);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private JButton createButton(String text, Controller controller) {
        JButton button = new JButton(text);
        button.setFont(MAIN_FONT);
        button.setForeground(Color.BLACK);
        button.setBackground(UMGC_YELLOW);
        button.setActionCommand(text);
        button.addActionListener(controller);
        button.setOpaque(true);
        button.setBorderPainted(false);
        return button;
    }

    private JTextField createTextField() {
        JTextField field = new JTextField();
        field.setFont(MAIN_FONT);
        field.setPreferredSize(new Dimension(200, 30));
        field.setMaximumSize(field.getPreferredSize());
        field.setBackground(LIGHT_GREY);
        field.setForeground(Color.BLACK);
        return field;
    }

    private JPanel createInputPanel(String label, JComponent field) {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        JLabel jLabel = new JLabel(label);
        jLabel.setForeground(Color.WHITE);
        panel.add(jLabel, gbc);
        panel.add(field, gbc);
        panel.setBackground(DARK_GREY);
        return panel;
    }
	//Method to enable saving and loading courses
    public CourseView (Controller controller) {
    	this.controller = controller;
    	saveDataButton.addActionListener(e -> controller.saveCourses());
    	loadDataButton.addActionListener(e-> controller.loadCourses());
    	
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
