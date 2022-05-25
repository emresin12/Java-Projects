

public class Student {

	int id;
	String name;
	int duration;
	float minRating;
	boolean settled;
	public Student(int Id, String Name, int Duration, float Rating) {
		id = Id;
		name= Name;
		duration= Duration;
		minRating= Rating;
		settled = false;
	}
	public void decreaseSem() {
		if(duration<1) {
			duration-=1;
		}
		
	}
}
