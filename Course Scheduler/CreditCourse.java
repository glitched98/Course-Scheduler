// Omar Syed 500809837
import java.util.ArrayList;

public class CreditCourse extends Course 
{
	private String  semester;
	public  double  grade;
	public  boolean active;
	// Initializes variables
	public CreditCourse(String name, String code, String descr, String fmt,String semester, double grade)
	{
		// redundant 
		super(name, code, descr, fmt);
		this.semester = semester;
		this.grade    = grade;
		active = false;
	}
	// Sets active variable to true
	public void setActive()
	{
		active = true;
	}
	// Sets active variable to false
	public void setInactive()
	{
		active = false;
	}
	// Returns the course code, name of student, semester and letter grade for that class
	public String displayGrade()
	{
		return getCode() + " " + getName() + " " + semester + " Grade " + convertNumericGrade(grade); 
	}
	
}
