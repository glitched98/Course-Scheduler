// Omar Syed 500809837
import java.util.*;

public class Scheduler 
{
    //In main() after you create a Registry object, create a Scheduler object and pass in the students ArrayList/TreeMap
	// If you do not want to try using a Map then uncomment
	// the line below and comment out the TreeMap line
	
	//ArrayList<Student> students;
	
	String[][] schedule = new String[10][10];	
	ArrayList<String> days = new ArrayList<String>(Arrays.asList("mon","tue","wed","thur","fri"));	
	TreeMap<String,ActiveCourse> courseMap = new TreeMap<String,ActiveCourse>();
	// Creates a Tree Map to hold student schedule
	public Scheduler(TreeMap<String,ActiveCourse> courses)
	{
		// creates a TreeMap which maps course code to Active course
		courseMap = courses;

		// stores timetable in a 2D array format
		for(Integer i=1;i<6;i++)
			schedule[0][i] = days.get(i-1);
		
		Integer time = 800;
		
		for(Integer i=1;i<10;i++){
			schedule[i][0] = time.toString();
			time+=100;
		}
		for(Integer i=0;i<10;i++){
			for(Integer j=0;j<6;j++){
				if(schedule[i][j]==null)
					schedule[i][j] = "";
			}
		}
	}
	// Sets day and time of course in schedule
	public void setDayAndTime(String courseCode, String day, int startTime, int duration) throws 
		UnknownCourseException,InvalidDayException,InvalidTimeException,
		InvalidDurationException,LectureTimeCollisionException
	{
		if(courseMap.get(courseCode) == null)
			throw new UnknownCourseException();
		if(!days.contains(day) || day == null)
			throw new InvalidDayException();
		if(duration!=1 && duration!=2 && duration!=3)
			throw new InvalidDurationException();
		if(startTime < 800 || startTime+duration*100 > 1700 || startTime%100!=0)
			throw new InvalidTimeException();

		// to move startTime in the domain of index for 2D array
		startTime/=100;
		startTime-=7;
		Integer j = days.indexOf(day)+1;
		for (Integer i=0 ; i<duration ;i++){
			if(schedule[startTime+i][j]!="")
				throw new LectureTimeCollisionException();
		}
		
		for(Integer i=0;i<duration;i++)
			schedule[startTime+i][j] = courseCode;
		
		courseMap.get(courseCode).lectureDay.add(day);
		courseMap.get(courseCode).lectureDuration.add(duration);
		courseMap.get(courseCode).lectureStart.add(startTime);
	}
	// Sets time of course on schedule Tree Map
	public void scheduleLecture(String courseCode, int duration) throws 
		UnknownCourseException,InvalidDurationException,LectureTimeCollisionException
	{
		if(courseMap.get(courseCode) == null)
			throw new UnknownCourseException();
		if(duration!=1 && duration!=2 && duration!=3)
			throw new InvalidDurationException();

		//checks whether a vacant slot exists for lecture
		boolean flag = false;
		Integer i=0,j=0,k=0;

		for(i=1;i<10;i++){
			for(j=1;j<6;j++){
				flag=false;
				for(k=0;k<duration;k++){
					if(schedule[i+k][j]!="" || i+k>9){
						flag = true;
						break;
					}
				}
				if(!flag) break;
			}
			if(!flag) break;
		}
				
		if(flag)
			throw new LectureTimeCollisionException();
		
		for(k=0;k<duration;k++)
			schedule[i+k][j] = courseCode;
		
		courseMap.get(courseCode).lectureDay.add(days.get(j-1));
		courseMap.get(courseCode).lectureDuration.add(duration);
		courseMap.get(courseCode).lectureStart.add((i+7)*100);
	}
	
	// Removes course from schedule
	public void clearSchedule(String courseCode)
	{
		if(courseMap.get(courseCode)!=null){
		System.out.println(courseCode);
			courseMap.get(courseCode).lectureStart.clear();
			courseMap.get(courseCode).lectureDuration.clear();
			courseMap.get(courseCode).lectureDay.clear();
			for(Integer i=0;i<10;i++){
				for(Integer j=0;j<6;j++){
					if(schedule[i][j].equals(courseCode))
						schedule[i][j] = "";
				}
			}
		}
	}
	// Prints Schedule
	public void printSchedule()
	{
		for(Integer i=0;i<10;i++){
			for(Integer j=0;j<6;j++){
				System.out.print(schedule[i][j] + "\t");
			}
			System.out.println("");
		}
	}
	
}
