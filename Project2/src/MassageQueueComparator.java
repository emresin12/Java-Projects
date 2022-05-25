

import java.util.Comparator;

public class MassageQueueComparator implements Comparator<PlayerServiceQueue> {
	
	public int compare(PlayerServiceQueue p1 , PlayerServiceQueue p2) {
		if(p1.player.skillLevel>p2.player.skillLevel) {
			return -1;
		}
		else if(p1.player.skillLevel<p2.player.skillLevel) {
			return 1;
		}
		else {
			if(p1.arrivalTime > p2.arrivalTime) {
				return 1;
			}
			else if(p1.arrivalTime<p2.arrivalTime) {
				return-1;
			}
			else {
				if(p1.player.ID>p2.player.ID) {
					return 1;
				}
				else {
					return -1;
						
				}
			}
				
		}	
	}

}
