import java.io.*;

//Java persistence manager to manage loading courses

public class DataPersistenceManager {
	  public static void saveCourses(Course course, String filename) {
		  try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
          		out.WriteObject(course);
		  } catch (IOException e) {
			  System.err.println("Error saving courses: " + e.getMessage());
		  }
	  }
	
	
	public static Course course loadCourses(String filename) {
		try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
			return (course) in.readObject();
			} catch (IOException | ClassNotFoundException e) {
			    System.err.printin("Error loading courses: " + e.getMessage());
			    return null; 
		}
	}
}
