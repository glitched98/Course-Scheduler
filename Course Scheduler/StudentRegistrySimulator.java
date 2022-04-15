// Omar Syed 500809837
import java.util.ArrayList;
import java.util.Scanner;

class UnknownCourseException extends RuntimeException{
	private static final long serialVersionUID = -123456;
	public UnknownCourseException(){
		super();
	}
	public String toString(){
		return "Invalid Course ID.";
	}
}
class InvalidDayException extends RuntimeException{
	private static final long serialVersionUID = -123455;

	public InvalidDayException(){
		super();
	}
	public String toString(){
		return "Invalid Day provided.";
	}
}
class InvalidTimeException extends RuntimeException{
	private static final long serialVersionUID = -123454;
	public InvalidTimeException(){
		super();
	}
	public String toString(){
		return "Invalid Start Time.";
	}
}
// Returns Exception for Invalid Duration Exception
class InvalidDurationException extends RuntimeException{
	private static final long serialVersionUID = -123453;
	public InvalidDurationException(){
		super();
	}
	public String toString(){
		return "Invalid Duration.";
	}
}
// Return Exception for Overlapping class timings
class LectureTimeCollisionException extends RuntimeException{
	private static final long serialVersionUID = -123452;
	public LectureTimeCollisionException(){
		super();
	}
	public String toString(){
		return "Overlapping Lecture slots.";
	}
}

public class StudentRegistrySimulator 
{
	public static void main(String[] args)
	{
	  Registry registry = new Registry();
	  Scheduler schedule = new Scheduler(registry.courses);
	  
	  Scanner scanner = new Scanner(System.in);
	  System.out.print(">");
	  
	  while (scanner.hasNextLine())
	  {
		  String inputLine = scanner.nextLine();
		  if (inputLine == null || inputLine.equals("")) continue;
		  
		  Scanner commandLine = new Scanner(inputLine);
		  String command = commandLine.next();
		  
		  if (command == null || command.equals("")) continue;
		  
		  else if (command.equalsIgnoreCase("L") || command.equalsIgnoreCase("LIST"))
		  {
			  registry.printAllStudents();
		  }
		  else if (command.equals("Q") || command.equals("QUIT"))
			  return;
		  
		  else if (command.equalsIgnoreCase("REG"))
		  {
			  String name = null;
			  String id   = null;
			  if (commandLine.hasNext())
			  {
				  name = commandLine.next();
				  // check for all alphabetical
				  String lcase = name.toLowerCase();
				  if (!isStringOnlyAlphabet(lcase))
				  {
				    System.out.println("Invalid Characters in Name " + name);
				    continue;
				  }
			  }
			  if (commandLine.hasNext())
			  {
				  id = commandLine.next();
				  // check for all numeric
				  if (!isNumeric(id))
				  {
				    System.out.println("Invalid Characters in ID " + id);
				    continue;
				  }
				  if (!registry.addNewStudent(name,id))
					  System.out.println("Student " + name + " already registered");
			  }
			 
		  }
		  else if (command.equalsIgnoreCase("DEL"))
		  {
			  if (commandLine.hasNext())
			  {
				  String id = commandLine.next();
				  // check for all numeric
				 
				  if (!isNumeric(id))
				    System.out.println("Invalid Characters in student id " + id);
				  registry.removeStudent(id);
			  }
		  }
		  else if (command.equalsIgnoreCase("PAC"))
		  {
			  registry.printActiveCourses();
		  }		  
		  else if (command.equalsIgnoreCase("PCL"))
		  {
			  String courseCode = null;
			  if (commandLine.hasNext())
			  {
				  courseCode = commandLine.next();
			    registry.printClassList(courseCode);
			  }
		  }
		  else if (command.equalsIgnoreCase("PGR"))
		  {
			  String courseCode = null;
			  if (commandLine.hasNext())
			  {
				 courseCode = commandLine.next();
			     registry.printGrades(courseCode);
			  }
		  }
		  else if (command.equalsIgnoreCase("ADDC"))
		  {
			  String courseCode = null;
			  String id         = null;
			  
			  if (commandLine.hasNext())
			  {
				 id = commandLine.next();
			  }
			  if (commandLine.hasNext())
			  {
				 courseCode = commandLine.next();
				 registry.addCourse(id, courseCode);
			  }
			  
		  }
		  else if (command.equalsIgnoreCase("DROPC"))
		  {
			  String courseCode = null;
			  String id         = null;
			  
			  if (commandLine.hasNext())
			  {
				 id = commandLine.next();
			  }
			  if (commandLine.hasNext())
			  {
				 courseCode = commandLine.next();
				 registry.dropCourse(id, courseCode);
			  }
			  
		  }
		  
		  else if (command.equalsIgnoreCase("PSC"))
		  {
			  String studentId = null;
			  if (commandLine.hasNext())
			  {
				 studentId = commandLine.next();
			     registry.printStudentCourses(studentId);
			  }
		  }
		  else if (command.equalsIgnoreCase("PST"))
		  {
			  String studentId = null;
			  if (commandLine.hasNext())
			  {
				 studentId = commandLine.next();
			     registry.printStudentTranscript(studentId);
			  }
		  }
		  else if (command.equalsIgnoreCase("SFG"))
		  {
			  String courseCode = null;
			  String id         = null;
			  String grade      = null;
			  double numGrade = 0;
			  
			  if (commandLine.hasNext())
			  {
				 courseCode = commandLine.next();
			  }
			  if (commandLine.hasNext())
			  {
				 id = commandLine.next();
			  }
			  if (commandLine.hasNext())
			  {
				  grade = commandLine.next();
				  if (!isNumeric(grade)) continue;
				  numGrade = Integer.parseInt(grade);
				  registry.setFinalGrade(courseCode, id, numGrade);
			  }
			  
		  }
		  else if (command.equalsIgnoreCase("SCN"))
		  {
			  String courseCode = null;
			  if (commandLine.hasNext())
			  {
				 courseCode = commandLine.next();
			     registry.sortCourseByName(courseCode);
			  }
		  }
		  else if (command.equalsIgnoreCase("SCI"))
		  {
			  String courseCode = null;
			  if (commandLine.hasNext())
			  {
				 courseCode = commandLine.next();
				 registry.sortCourseById(courseCode);
			  }
		  }
		  
		  // Adds courses to schedule Tree Map
		  else if (command.equalsIgnoreCase("SCH"))
		  {
			  String courseCode = null, day = null;
			  Integer start = 0, duration = 0;
			  if (commandLine.hasNext())
				  courseCode = commandLine.next();
			      courseCode = courseCode.toUpperCase();
				if (commandLine.hasNext())
				  day = commandLine.next();
				day = day.toLowerCase();
				if (commandLine.hasNext())
				  start = Integer.parseInt(commandLine.next());
				if (commandLine.hasNext())
				  duration = Integer.parseInt(commandLine.next());
				try{
					schedule.setDayAndTime(courseCode,day,start,duration);
				}catch(RuntimeException e){
					System.out.println(e);
				} 
		  }
		  
		  else if (command.equalsIgnoreCase("SCHL"))
		  {
			  	String courseCode = null;
			  	Integer duration = 0;
			  	if (commandLine.hasNext())
				  courseCode = commandLine.next();
			  	courseCode = courseCode.toUpperCase(); 
				if (commandLine.hasNext())
				  duration = Integer.parseInt(commandLine.next());
				try{
					schedule.scheduleLecture(courseCode,duration);
				}catch(RuntimeException e){
					System.out.println(e);
				} 
		  }
		  // Clears specific course from schedule
		  else if (command.equalsIgnoreCase("CSCH"))
		  {
			  String courseCode = null;
			  if (commandLine.hasNext())
				  courseCode = commandLine.next();
			  courseCode = courseCode.toUpperCase();
				schedule.clearSchedule(courseCode);
		  }
		  else if (command.equalsIgnoreCase("PSCH"))
		  {
			  schedule.printSchedule();
		  }
		  System.out.print("\n>");
	  }
	}
  // Returns boolean value if String is only alphabets
  private static boolean isStringOnlyAlphabet(String str) 
  { 
      return ((!str.equals("")) 
              && (str != null) 
              && (str.matches("^[a-zA-Z]*$"))); 
  } 
  // Returns boolean value if String is Numeric
  public static boolean isNumeric(String str)
  {
      for (char c : str.toCharArray())
      {
          if (!Character.isDigit(c)) return false;
      }
      return true;
  }
  
  // A2 - use double.parseDouble in A2
}