

import java.util.Comparator;

public class CoachQueueComparator implements Comparator<Coach>{
	
	public int compare(Coach c1, Coach c2) {
		if(c1.ID> c2.ID) {
			return 1;
			
		}
		else {
			return -1;
		}
	}
	
}
