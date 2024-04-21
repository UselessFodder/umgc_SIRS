package controller;

import model.Assignment;
import model.Course;
import view.UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JTextArea;


public class Controller implements ActionListener {
	//attributes
	private UI ui;
	private Course course;

    //constructor
    public Controller() { 
    	this.course = new Course("undefined");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    	//check if event source was the addButton
    	if ("addAssignment".equals(e.getActionCommand())) {
            //check if course object is initialized
        	if (course.getCourseName().equals("undefined")) {
        		//if so, create course object
        		initializeCourse(ui.getClassNameField().getText());
        	}//end if
    		        	
        	//get assignment information from fields
            String name = ui.getAssignmentNameField().getText();
            int gradeReceived = Integer.parseInt(ui.getGradeReceivedField().getText());
            int possibleGrade = Integer.parseInt(ui.getPossibleGradeField().getText());
            
            addAssignment(name, gradeReceived, possibleGrade);
            
            updateAssignmentDisplay();
        }
    }
    
    public void initializeCourse(String courseName) {
    	Course course = new Course(courseName);
    	this.course = course;
    	//prevent UI course name from being editted after intialization
    	ui.getClassNameField().setEditable(false);
    }

    public void addAssignment(String name, int gradeReceived, int possibleGrade) {
        //create an assignment to add to course
        Assignment assignment = new Assignment(name, gradeReceived, possibleGrade);
        
        // Call the method to create the Assignment
        course.addAssignment(assignment);
    }
    
    public void updateAssignmentDisplay() {
    	//get all course assignments
    	List<Assignment> assignments = course.getAssignments();
    	//hold text string outputs
    	String assignmentText = new String();
    	
    	//iterate through all assignments and output data to string
    	for (Assignment assignment : assignments) {
    		String name = assignment.getAssignmentName();
    		int gradeReceived = assignment.getActualGrade();
    		int possibleGrade = assignment.getNeededGrade();
    		String assignmentInput = name + " " + gradeReceived + " / " + possibleGrade  + "\n";    		
    		assignmentText = assignmentText.concat(assignmentInput);
		}//end for loop
    	
    	//set new assignments output data as results text
    	JTextArea resultArea = ui.getResultArea();
    	resultArea.setText(assignmentText);    	
		System.out.println(resultArea.getText());
    	//update ui
		resultArea.update(resultArea.getGraphics()); 	
    }
    
    //getters and setters
    public UI getUI(){
    	return this.ui;
    }
    
    public void setUI(UI ui){
    	this.ui = ui;
    }
}
