

public abstract class Event {
	public Player player;
	public double duration;
	public Services serviceType;
	public double startTime;
	
	public Event(Player p1, double _duration, Services st, double St) {
		player = p1;
		duration = _duration;
		serviceType = st;
		startTime = St;
	}
	
	
}
