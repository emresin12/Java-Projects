

public class TerminationEvent extends Event {
	
	
	public Coach coach;
	
	public TerminationEvent(Player p1, double _duration, Services st, double St, Coach _coach) {
		super(p1, _duration, st, St);
		coach=_coach;
		
		
		// TODO Auto-generated constructor stub
	}

	
	
	
}
