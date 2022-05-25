

import java.util.Comparator;

public class EventQueueComparator implements Comparator<Event> {
	public int compare(Event e1, Event e2) {
		if(e1.startTime > e2.startTime) {
			return 1;	
		}
		else if(e1.startTime<e2.startTime) {
			return -1;
		}
		else {
			if(e1.player.ID>e2.player.ID) {
				return 1;
			}
			else {
				return -1;
			}
		}
	}
}
