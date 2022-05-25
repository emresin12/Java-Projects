

import java.util.Comparator;

public class PhysioQueueComparator implements Comparator<PlayerServiceQueue>{
	public int compare(PlayerServiceQueue p1, PlayerServiceQueue p2) {
		if(p1.player.trainingTime>p2.player.trainingTime) {
			return -1;
		}
		else if(p1.player.trainingTime<p2.player.trainingTime) {
			return 1;
		}
		else {
			if(p1.player.ID> p2.player.ID) {
				return 1;
			}
			else {
				return -1;
			}
		}
	}
}
