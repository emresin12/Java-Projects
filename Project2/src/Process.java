public class Process {
	public Player player;
	public Coach coach;
	public double duration;
	public double startTime;
	public double endTime;
	public double arriveTime;
	public double waitingTime;
	public Services serviceType;
	
	public Process(Player pl, Coach ch,double dur, double startt,double endt, double arrtime, Services servtype) {
		player = pl;
		coach= ch;
		duration=dur;
		startTime=startt;
		endTime=endt;
		arriveTime=arrtime;
		serviceType= servtype;
		waitingTime= startTime-arriveTime;
		
		switch(serviceType) {
		case MASSAGE:
			player.totalMassageWaitingTime+=waitingTime;
			break;
		case PHYSIO:
			player.totalPhysioWaitingTime+=waitingTime;
			
		}
		
	}
	
	
}
