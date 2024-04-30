/*File: saveLoad.java
* Creator: Team 5
* Course: CMSC 495
* Date: April 22, 2024
* Purpose: Class creates a assignment object to be added to a course and displayed in the GUI
*/

package model;

import java.io.*;

//Java persistence manager to manage loading courses

public class DataPersistenceManager {
	  public static void saveCourses(Course course, String filename) {
		  try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
			  	out.writeObject(course);
          		out.close();
		  } catch (IOException e) {
			  System.err.println("Error saving courses: " + e.getMessage());
		  }
	  }
	
	
	public static Course loadCourses(String filename) {
		try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
			return (Course)in.readObject();
		} catch (IOException | ClassNotFoundException e) {
		    System.err.println("Error loading courses: " + e.getMessage());
		    return null; 
		}
	}
}
