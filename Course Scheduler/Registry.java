// Omar Syed 500809837
import java.util.ArrayList;
// import java.util.Collections;
import java.util.Scanner;
import java.io.*;
import java.util.*;

public class Registry
{
    protected TreeMap<Integer,Student> students = new TreeMap<Integer,Student>();
    protected TreeMap<String,ActiveCourse> courses  = new TreeMap<String,ActiveCourse>();
   
    public Registry()
    {
    	// Trying in attempt to catch exceptions
   		try{
   		    Scanner sc = new Scanner(new FileReader("students.txt"));
		    while(sc.hasNextLine()){
		   		String name = null;
				String id   = null;
				if (sc.hasNext())
				{
				    name = sc.next();
				    // check for all alphabetical
					String lcase = name.toLowerCase();
					if (!isStringOnlyAlphabet(lcase))
				    {
					   System.out.println("Invalid Characters in Name " + name);
					   return;
					}
				}
			    if (sc.hasNext())
			    {
				    id = sc.next();
				    // check for all numeric
				    if (!isNumeric(id))
			        {
			   			System.out.println("Invalid Characters in ID " + id);
				   		return;
				 	}
				 	if (!addNewStudent(name,id))
					   System.out.println("Student " + name + " already registered");
			    }
		    }
		    // Catches exception "FileNotFoundException"
	   	}catch(FileNotFoundException exception){
	   		System.out.println("Input file named students.txt does not exist.");
	   		return;
	   	}
	   	   
		ArrayList<Student> list = new ArrayList<Student>();

		// Add some active courses with students
		String courseName = "Computer Science II";
		String courseCode = "CPS209";
		String descr = "Learn how to write complex programs!";
		String format = "3Lec 2Lab";
		list.add(students.get(25347)); list.add(students.get(38467)); list.add(students.get(34562));
		courses.put(courseCode,new ActiveCourse(courseName,courseCode,descr,format,"W2020",list));
		// Add course to student list of credit courses
		students.get(25347).addCourse(courseName,courseCode,descr,format,"W2020", 0); 
		students.get(38467).addCourse(courseName,courseCode,descr,format,"W2020", 0); 
		students.get(34562).addCourse(courseName,courseCode,descr,format,"W2020", 0); 

		// CPS511
		list.clear();
		courseName = "Computer Graphics";
		courseCode = "CPS511";
		descr = "Learn how to write cool graphics programs";
		format = "3Lec";
		list.add(students.get(57643)); list.add(students.get(46532)); list.add(students.get(98345));
		courses.put(courseCode,new ActiveCourse(courseName,courseCode,descr,format,"F2020",list));
		students.get(57643).addCourse(courseName,courseCode,descr,format,"W2020", 0); 
		students.get(46532).addCourse(courseName,courseCode,descr,format,"W2020", 0); 
		students.get(98345).addCourse(courseName,courseCode,descr,format,"W2020", 0);

		// CPS643
		list.clear();
		courseName = "Virtual Reality";
		courseCode = "CPS643";
		descr = "Learn how to write extremely cool virtual reality programs";
		format = "3Lec 2Lab";
		list.add(students.get(57643)); list.add(students.get(25347)); list.add(students.get(34562)); list.add(students.get(98345));
		courses.put(courseCode,new ActiveCourse(courseName,courseCode,descr,format,"W2020",list));
		students.get(57643).addCourse(courseName,courseCode,descr,format,"W2020", 0); 
		students.get(25347).addCourse(courseName,courseCode,descr,format,"W2020", 0); 
		students.get(34562).addCourse(courseName,courseCode,descr,format,"W2020", 0); 
		students.get(98345).addCourse(courseName,courseCode,descr,format,"W2020", 0);   
    }
   // Adds new student to a course with name and student ID
    public boolean addNewStudent(String name, String id)
    {
	    if (students.containsValue(Integer.parseInt(id))) 
	   		return false;
	   	   
	    students.put(Integer.parseInt(id),new Student(name, id));
	    return true;
    }
   // Removes student from a course by using Student ID
    public boolean removeStudent(String studentId)
    {
    	if(students.containsValue(Integer.parseInt(studentId))){
      		students.remove(Integer.parseInt(studentId));
      		return true;
        }
	 	return false;
    }
   // Prints all enrolled students
    public void printAllStudents()
    {
	    for (Student elem : students.values()){
		    System.out.println("ID: " + elem.getId() + " Name: " + elem.getName() );   
	    }
    }
   // Returns student from student ID
    private Student findStudent(String id)
    {
        return students.get(Integer.parseInt(id));
    }
   // Returns course from course code
    private ActiveCourse findCourse(String code)
    {
        return courses.get(code);
    }
   // Adds Course to student using Student ID and CourseCode
    public void addCourse(String studentId, String courseCode)
    {
	    Student s = findStudent(studentId);
	    if (s == null) return;
	   
	    if (s.takenCourse(courseCode)) return;
	   
	    ActiveCourse ac = findCourse(courseCode);
	    if (ac == null) return;
	   
	    if (ac.enrolled(studentId)) return;
			   
	    ac.students.add(s);
	    s.addCourse(ac.getName(),ac.getCode(),ac.getCourseDescription(),ac.getFormat(),ac.getSemester(),0);
    }
   
    // Drops student from a course
    public void dropCourse(String studentId, String courseCode)
    {
	    Student s = findStudent(studentId);
	    if (s == null) return;
	    
	    ActiveCourse ac = findCourse(courseCode);
	    if (ac == null) return;
	   
	    ac.remove(studentId);
	    s.removeActiveCourse(courseCode);
    }
   // Prints all active courses
	public void printActiveCourses()
	{
		for (ActiveCourse elem : courses.values())
 			System.out.println(elem.getDescription());
	}
	// Prints class list using CourseCode
	public void printClassList(String courseCode)
	{
	   ActiveCourse ac = findCourse(courseCode);
	   if (ac == null) return;
	   
	   ac.printClassList();
	}
	// Sorts Courses by name
	public void sortCourseByName(String courseCode)
	{
	   ActiveCourse ac = findCourse(courseCode);
	   if (ac == null) return;
	   
	   ac.sortByName();
	}
	// Sorts Course By ID
	public void sortCourseById(String courseCode)
	{
	   ActiveCourse ac = findCourse(courseCode);
	   if (ac == null) return;
	   
	   ac.sortById();	   
	}
	// Prints grades of Course
	public void printGrades(String courseCode)
	{
	   ActiveCourse ac = findCourse(courseCode);
	   if (ac == null) return;
	   
	   ac.printGrades();
	}
	// Prints courses of a student
	public void printStudentCourses(String studentId)
	{
	   Student s = findStudent(studentId);
	   if (s == null) return;
	   
	   s.printActiveCourses();
	}
	// Prints Student Transcript
	public void printStudentTranscript(String studentId)
	{
	   Student s = findStudent(studentId);
	   if (s == null) return;
	   
	   s.printTranscript();
	}
	// Sets final grade of a course for a student using student ID
	public void setFinalGrade(String courseCode, String studentId, double grade)
	{
	   Student s = findStudent(studentId);
	   if (s == null) return;
	   s.setGrade(courseCode, grade);
	   
	   //ActiveCourse ac = findCourse(courseCode);
	   //if (ac == null) return;
	   //ac.remove(studentId);
	}
	// Returns a boolean value if String only has Alphabet
	private static boolean isStringOnlyAlphabet(String str) 
	{ 
	    return ((!str.equals("")) 
	          && (str != null) 
	          && (str.matches("^[a-zA-Z]*$"))); 
	} 
	// Returns a boolean value if String has digits
	public static boolean isNumeric(String str)
	{
	    for (char c : str.toCharArray())
	    {
	        if (!Character.isDigit(c)) return false;
	    }
	   return true;
	}
} 
