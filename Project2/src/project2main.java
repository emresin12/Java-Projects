

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import java.util.PriorityQueue;
import java.util.Scanner;

public class project2main {
	
	public static void main(String[] args) throws FileNotFoundException {
		
		int invalid=0;
		int cancelled=0;
		double time=0;
		ArrayList<Player> players = new ArrayList<Player>();
		ArrayList<Coach> coaches = new ArrayList<Coach>();
		ArrayList<Event> events = new ArrayList<Event>();
		ArrayList<Process> processes=new ArrayList<Process>();
		
		File fileIn = new File("/Users/emresin/eclipse-workspace-new/Project2/src/input0.txt");
		File fileOut= new File("/Users/emresin/eclipse-workspace-new/Project2/src/output0.txt");
		Scanner in = new Scanner(fileIn);
		PrintStream out = new PrintStream(fileOut);
		
		int N = Integer.parseInt(in.nextLine());
		for (int i = 0; i < N; i++) {
			String[]arr = in.nextLine().split(" ");
			Player player1 = new Player(Integer.parseInt(arr[0]), Integer.parseInt(arr[1]));
			players.add(player1);
			
		}
		int A = Integer.parseInt(in.nextLine());
		
		PriorityQueue<Event> eventsPQ = new PriorityQueue<Event>(new EventQueueComparator());
		
		for (int i = 0; i < A; i++) {
			String[]arr = in.nextLine().split(" ");
			Services st;
			if(arr[0].equals("t")) {
			
				st= Services.TRAINING;
			}
			else {
				st= Services.MASSAGE;
			}
			int id= Integer.parseInt(arr[1]);
			
			double aT = Double.parseDouble(arr[2]);
			double dur = Double.parseDouble(arr[3]);
			
			Event event1= new InitializationEvent(players.get(id),dur,st,aT );
			events.add(event1);
			eventsPQ.add(event1);
			
			
		}
		
		//koçları ekle
		PriorityQueue<Physiotherapist> coachPhysioPQ = new PriorityQueue<Physiotherapist>(new CoachQueueComparator());
		PriorityQueue<Masseur> coachMassagePQ = new PriorityQueue<Masseur>(new CoachQueueComparator());
		PriorityQueue<Trainer> coachTrainingPQ = new PriorityQueue<Trainer>(new CoachQueueComparator());
		
		String line = in.nextLine();
		String[] arr = line.split(" ");
		for(int i =0; i<Integer.parseInt(arr[0]);i++) {
			Physiotherapist pyt = new Physiotherapist(i,Double.parseDouble(arr[i+1]));
			coaches.add(pyt);
			coachPhysioPQ.add(pyt);
			
		}
		String line2= in.nextLine();
		String[]arr2=line2.split(" ");
		for (int i = 0; i < Integer.parseInt(arr2[0]); i++) {
			Trainer tr= new Trainer(i);
			coaches.add(tr);
			coachTrainingPQ.add(tr);
			
			
		}
		for (int i = 0; i < Integer.parseInt(arr2[1]); i++) {
			Masseur masseur= new Masseur(i);
			coaches.add(masseur);
			coachMassagePQ.add(masseur);
		}
				
		
		
		
		
		
		PriorityQueue<PlayerServiceQueue> trainingPQ = new PriorityQueue<PlayerServiceQueue>(new TrainingQueueComparator());
		PriorityQueue<PlayerServiceQueue> massagePQ = new PriorityQueue<PlayerServiceQueue>(new MassageQueueComparator());
		PriorityQueue<PlayerServiceQueue> physioPQ = new PriorityQueue<PlayerServiceQueue>(new PhysioQueueComparator());
		
		
		
		
		int maxTRQ=0;
		int maxPSHQ=0;
		int maxMQ=0;
		
		
		
		//eventi ekliyoruz
		while(eventsPQ.size()>0) {
			
			Event event = eventsPQ.poll();
			time = event.startTime;
			

			if(event.player.massageAttempts==3 && event.serviceType==Services.MASSAGE && event.getClass().getSimpleName().equals("InitializationEvent")) {
				invalid++;
				continue;
			}
			
			if(event.player.inQueue==true && event.getClass().getSimpleName().equals("InitializationEvent")) {
				cancelled++;
				continue;
			}
			
			
			
			//eventi ekle
			if(event.getClass().getSimpleName().equals("InitializationEvent")) {
				switch(event.serviceType) {
				case TRAINING:
					PlayerServiceQueue psq = new PlayerServiceQueue(event.player,event.duration,event.startTime);
					psq.player.inQueue=true;
					trainingPQ.add(psq);
									
					break;
					//burası gereksiz aslında
				case MASSAGE:
					
					PlayerServiceQueue psqm = new PlayerServiceQueue(event.player,event.duration,event.startTime);
					psqm.player.inQueue=true;
					massagePQ.add(psqm);
					
					break;
					
				}
			}
			else {
				//çıkış eventi demektir
				TerminationEvent eventout = (TerminationEvent) event;
				switch(eventout.serviceType) {
				case TRAINING:
					
					//eventout.player.inQueue=false;
					coachTrainingPQ.add((Trainer)eventout.coach);
					
					eventout.player.trainingTime=eventout.duration;
					//physio eventi oluşturmadan ekle
					//duration 0 girdim zaten proccess oluştururken her türlü koçunkinin alacak
					//eventoutstart time time event starttime hepsi aynı olması lazım
					PlayerServiceQueue psqp = new PlayerServiceQueue(eventout.player, 0.0, eventout.startTime);
					physioPQ.add(psqp);
					
					
					break;
				
				case PHYSIO:
					eventout.player.inQueue=false;
					coachPhysioPQ.add((Physiotherapist)eventout.coach);
					//process burda da eklenebilir
					
					
					break;
					
				case MASSAGE:
					eventout.player.inQueue=false;
					coachMassagePQ.add((Masseur)eventout.coach);
					
					
					
					break;
					
				}
				
			}
			
			//Training queue eşleşmeleri ve process kaydı
			while(coachTrainingPQ.size()> 0 && trainingPQ.size() > 0) {
				Trainer trainer = coachTrainingPQ.poll();
				PlayerServiceQueue player = trainingPQ.poll();
				
				Process proc = new Process(player.player, trainer, player.duration, time,  time+player.duration, player.arrivalTime, Services.TRAINING);
				double waitingTime=(proc.endTime)- player.arrivalTime;
				player.player.turnaroundtime+=waitingTime;
				//çıkış eventi oluştur
	
				//çıkış eventinde çıkılan işlemin duration ı tutuluyor
				
				Event leaveEvent= new TerminationEvent(player.player, player.duration, Services.TRAINING, proc.endTime,trainer);
				
				
				
				eventsPQ.add(leaveEvent);
				processes.add(proc);
				
				player.player.trainingTime+=player.duration;
				
				//oyuncuların giriş zamanlarını kaydet
				
				
				
			}
			
			
			
			//physio eşleşmeler
			while(coachPhysioPQ.size() > 0 && physioPQ.size() > 0) {
				Physiotherapist physiocoach = coachPhysioPQ.poll();
				PlayerServiceQueue player = physioPQ.poll();
				
				Process proc = new Process(player.player, physiocoach, physiocoach.serviceTime ,time,  time + physiocoach.serviceTime, player.arrivalTime, Services.PHYSIO);
				
				double waittime=(time+physiocoach.serviceTime)-player.arrivalTime;
				
				player.player.turnaroundtime+=waittime;
						
						
				//çıkış eventi oluştur
				
				Event leaveEvent= new TerminationEvent(player.player, 0.0, Services.PHYSIO, proc.endTime, physiocoach);
			
				
				eventsPQ.add(leaveEvent);
				processes.add(proc);
				
				//oyuncuların giriş zamanlarını kaydet

				
			}
			
			
			//massage queue eşlemeler
			while(coachMassagePQ.size()> 0 && massagePQ.size() > 0) {
				Masseur masseur= coachMassagePQ.poll();
				PlayerServiceQueue player = massagePQ.poll();
				
				Process proc = new Process(player.player, masseur, player.duration ,time,  time + player.duration, player.arrivalTime, Services.MASSAGE);
				
				
				//çıkış eventi oluştur
				
				Event leaveEvent= new TerminationEvent(player.player, 0.0, Services.MASSAGE, proc.endTime, masseur);
				
				
				player.player.massageAttempts++;
				eventsPQ.add(leaveEvent);
				processes.add(proc);
				
				//oyuncuların giriş zamanlarını kaydet
				
				
			}
			
			if(trainingPQ.size()>maxTRQ) {
				maxTRQ=trainingPQ.size();
			}
			if(physioPQ.size()>maxPSHQ) {
				maxPSHQ=physioPQ.size();
			}
			if(massagePQ.size()>maxMQ) {
				maxMQ=massagePQ.size();
			}
			
		}
		
		//phs maxları
		double maxPhsWaiting=0;
		int idmaxphswait=0;
		
		//3 massage maxları
		double minmaswait=Double.MAX_VALUE;
		int idminmaswait=Integer.MAX_VALUE;
		boolean flagmas=false; //false means no 3 attempt massage
		
		double totDurTr=0;
		double totWtTr=0;
		double avWtTr=0;
		int trcount= 0;
		
		double totDurPhs=0;
		double totWtPhs=0;
		double avWtPhs=0;
		int phscount=0;
		
		double totDurMas=0;
		double totWtMas=0;
		double avWtMas= 0;
		int mascount=0;
		
		
		for(Process proc : processes) {
			switch(proc.serviceType) {
			case TRAINING:
				totDurTr+=proc.duration;
				trcount++;
				totWtTr += proc.waitingTime;
				break;
			case PHYSIO:
				
				totDurPhs+=proc.duration;
				phscount++;
				totWtPhs+=proc.waitingTime;
				
				if(proc.player.totalPhysioWaitingTime > maxPhsWaiting) {
					maxPhsWaiting= proc.player.totalPhysioWaitingTime;
					idmaxphswait= proc.player.ID;
				}
				else if(proc.player.totalPhysioWaitingTime < maxPhsWaiting) {
					
					
				}
				else {
					if(idmaxphswait > proc.player.ID) {
						idmaxphswait=proc.player.ID;
					}
				}
				
				
				break;
			case MASSAGE:
				
				totDurMas+=proc.duration;
				mascount++;
				totWtMas+=proc.waitingTime;
				
				if(proc.player.massageAttempts==3) {
					flagmas=true;
					if(proc.player.totalMassageWaitingTime < minmaswait) {
						minmaswait= proc.player.totalMassageWaitingTime;
						idminmaswait= proc.player.ID;
					}
					else if(proc.player.totalMassageWaitingTime > minmaswait) {
						
						
					}
					else {
						if(idminmaswait > proc.player.ID) {
							idminmaswait=proc.player.ID;
						}
					}
				}
				
				
				
				break;
				
			}
		}
		if(flagmas==false) {
			idminmaswait=-1;
			minmaswait=-1;
		}
		
		double totTAT=0;
		int count=0;
		for(Player pl:players) {
			totTAT+=pl.turnaroundtime;
		}
		
		
		out.println(maxTRQ);
		out.println(maxPSHQ);
		out.println(maxMQ);
		out.printf("%.3f" ,totWtTr/trcount);
		out.println();
		
		out.printf(Locale.US,"%.3f",totWtPhs/phscount);
		out.println();
		out.printf(Locale.US,"%.3f",totWtMas/mascount);
		out.println();
		out.printf(Locale.US,"%.3f",totDurTr/trcount);
		out.println();
		out.printf(Locale.US,"%.3f",totDurPhs/phscount);
		out.println();
		out.printf(Locale.US,"%.3f",totDurMas/mascount);
		out.println();
		out.printf(Locale.US,"%.3f",totTAT/trcount);//turnover data
		out.println();
		
		out.printf(Locale.US,"%d %.3f",idmaxphswait,maxPhsWaiting);
		out.println();
		out.printf(Locale.US,"%d %.3f",idminmaswait,minmaswait);
		out.println();
		out.println(invalid);
		out.println(cancelled);
		out.printf(Locale.US,"%.3f",time);
		
		in.close();
		out.close();
		
		
		
		
		
	}
	


}