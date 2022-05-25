

public class Player {
	public int ID;
	public int skillLevel;
	public double trainingTime;
	public int massageAttempts;
	public boolean inQueue;
	public double totalPhysioWaitingTime;
	public double totalMassageWaitingTime;
	public double turnaroundtime;
	
	public Player(int id, int skillevel) {
		ID = id;
		skillLevel= skillevel;
		trainingTime=0;
		massageAttempts=0;
		inQueue= false;
		totalPhysioWaitingTime = 0;
		totalMassageWaitingTime = 0;
		turnaroundtime=0;

	}
	
//	public boolean equals(Player p) {
//		if(p.ID==this.ID) {
//			return true;
//		}
//		else {
//			return false;
//		}
//	}
	
	
	

}
