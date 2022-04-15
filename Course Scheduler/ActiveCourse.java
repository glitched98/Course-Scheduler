// Omar Syed 500809837
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

// Active University Course
 
public class ActiveCourse extends Course
{
   public  ArrayList<Student> students; // map id to name
   private String             semester;
   protected ArrayList<Integer> lectureStart;
   protected ArrayList<Integer> lectureDuration;
   protected ArrayList<String>  lectureDay;

   // Initializes active courses
   public ActiveCourse(String name, String code, String descr, String fmt,String semester,ArrayList<Student> students)
   {
	   super(name, code, descr, fmt);
	   this.semester = semester;
	   this.students = new ArrayList<Student>(students);
      this.lectureDay = new ArrayList<String>();
      this.lectureDuration = new ArrayList<Integer>();
      this.lectureStart = new ArrayList<Integer>();
   }
   // Returns semester
   public String getSemester()
   {
	   return semester;
   }
   // Prints class list
   public void printClassList()
   {
	   for (int i = 0; i < students.size(); i++)
	   {
		   System.out.println(students.get(i).toString());
	   }
   }
   // Print ID, name and grade of student
   public void printGrades()
   {
	   for (int i = 0; i < students.size(); i++)
	   {
		   Student s = students.get(i);
		   System.out.println(s.getId() + " " + s.getName() + " " + s.getGrade(getCode()));
	   }
   }
   // Returns description from super class and semester
   public String getDescription()
   {
	   return super.getDescription() + " " + semester + " Enrolment: " + students.size() +  "\n";
   }
   // Returns Course Description
   public String getCourseDescription()
   {
	   return getDescr();
   }
   // Returns Grade from Student ID
   public double getGrade(String studentId)
   {
	   for (int i = 0; i < students.size(); i++)
	   {
		   if (studentId.equals(students.get(i).getId()))
		   {
			   return students.get(i).getGrade(getCode());
		   }
	   }
	   return 0;
   }
   // Return boolean value to determine if student is enrolled
   public boolean enrolled(String studentId)
   {
	   for (int i = 0; i < students.size(); i++)
	   {
		   if (studentId.equals(students.get(i).getId()))
		     return true;
	   }
	   return false;
   }
   // Removes student from course
   public void remove(String id)
   {
	   for (int j = 0; j < students.size(); j++)
	   {
   		   Student s = students.get(j);
   		   if (s.getId().equals(id))
   		   {
   		     students.remove(j);
   		     return;
   		   }
 	   }
    }
   
   // Sorts by Name using the Comparator interface
   public void sortByName()
   {
 	  Collections.sort(students, new NameComparator());
   }
   private class NameComparator implements Comparator<Student>
   {
   	public int compare(Student a, Student b)
   	{
   	  return a.getName().compareTo(b.getName());	  
   	}
   }
   // Sorts students by ID using Comparator Interface
   public void sortById()
   {
 	  Collections.sort(students, new IdComparator());
   }
   private class IdComparator implements Comparator<Student>
   {
	   // Compares student A ID from student B ID
   	public int compare(Student a, Student b)
   	{
   	  return a.getId().compareTo(b.getId());	  
   	}
   }
}