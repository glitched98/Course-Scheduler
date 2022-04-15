// Omar Syed 500809837
import java.util.ArrayList;

public class Student implements Comparable<Student>
{
    private String name;
    private String id;
    private ArrayList<CreditCourse> courses;
  
    public Student(String name, String id)
    {
	    this.name = name;
	    this.id   = id;
	    courses = new ArrayList<CreditCourse>();
    }
    // Returns Student ID
    public String getId()
    {
	    return id;
    }
    // Returns Student Name
    public String getName()
    {
	    return name;
    }
    // Returns boolean value to determine if course is taken or not
    public boolean takenCourse(String courseCode)
    {
        for (int j = 0; j < courses.size(); j++)
        {
            if (courses.get(j).getCode().equalsIgnoreCase(courseCode))
            return true;
	    }
        return false;
    }
    // Adds Course to student along with course description, semester and course grade
    public void addCourse(String courseName, String courseCode, String descr, String format,String sem, double grade)
    {
	    CreditCourse cc = new CreditCourse(courseName,courseCode,descr,format,sem, grade);
	    cc.setActive();
	    courses.add(cc);
    }  
    // Returns Student grade from course
    public double getGrade(String courseCode)
    {
	    for (int i = 0; i < courses.size(); i++)
	    {
		    if (courses.get(i).getCode().equals(courseCode))
            {
               return courses.get(i).grade; 
            }
        }
	    return 0;
    }
    // Sets grade of course
    public void setGrade(String courseCode, double grade)
    {
        for (int k = 0; k < courses.size(); k++)
        {
	       if (courses.get(k).getCode().equalsIgnoreCase(courseCode))
	       {
		        courses.get(k).grade = grade;
		        courses.get(k).setInactive();
	       }
        }
    }
    // Prints grades of courses
    public void printTranscript()
    {
	    for (int i = 0; i < courses.size(); i++)
	    { 
		    if (!courses.get(i).active) 
			    System.out.println(courses.get(i).displayGrade());
	    }
    }
    // Prints all active courses
    public void printActiveCourses()
    {
	   for (int i = 0; i < courses.size(); i++)
	   {
		    if (courses.get(i).active)
		    System.out.println(courses.get(i).getDescription());
	   } 
    }
    // Prints all completed course
    public void printCompletedCourses()
    {
        for (int i = 0; i < courses.size(); i++)
        {
            if (!courses.get(i).active)
            System.out.println(courses.get(i).getDescription());
        }
    }
  
    // Removes active course using course code
    public void removeActiveCourse(String courseCode)
    {
        for (int i = 0; i < courses.size(); i++)
        {
            if (courses.get(i).getCode().equals(courseCode) && courses.get(i).active) 
            {
                courses.remove(i);
                return;
            }
        }
    }
    // Returns String with Student ID and Student Name
    public String toString()
    {
        return "Student ID: " + id + " Name: " + name;
    }
    // Compares two students
    public int compareTo(Student other)
    {
        return this.name.compareTo(other.name);
    }
    // Checks if two student ID are equal
    public boolean equals(Object other)
    {
        Student s = (Student) other;
        return this.name.equals(s.name) && this.id.equals(s.id);
    }
  
}
