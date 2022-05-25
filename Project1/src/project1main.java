
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintStream;
import java.util.Scanner;

public class project1main {

	public static void main(String[] args) {
		
		
		ArrayList<Student> students = new ArrayList<Student>();
		ArrayList<House>houses= new ArrayList<House>();
		File inputFile= new File(args[0]);
		File outputFile = new File(args[1]);
		
		try {
			
			Scanner in = new Scanner(inputFile);
			
			
			while(in.hasNextLine()) {
				String[] line= in.nextLine().trim().split("\\s+");
				
				if(line[0].equals("s")) {
					Student std= new Student(Integer.parseInt(line[1]), line[2], Integer.parseInt(line[3]), Float.parseFloat(line[4]));
					students.add(std);
				}else {
					House house = new House(Integer.parseInt(line[1]), Integer.parseInt(line[2]), Float.parseFloat(line[3]));
					houses.add(house);
				}
				
			}
			in.close();
		}
		catch(FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
		
//		for(Student s : students) {
//			System.out.println(s.duration+" "+s.id+" "+s.minRating+" "+s.name);
//		}
//		for(House h : houses) {
//			System.out.println(h.duration+" "+h.id+" "+h.rating);
//		}
		
		students.sort((a,b) -> (a.id - b.id));
		houses.sort((a,b)-> (a.id-b.id));
		
		while(students.size()==0) {
			
		}
		for (int i = 0; i < 8; i++) {
			for (int m = 0; m < students.size(); m++) {
				if(students.get(m).duration!=0 && students.get(m).settled == false ) {
					for (int j = 0; j < houses.size(); j++) {
						if(houses.get(j).duration==0 && students.get(m).minRating <= houses.get(j).rating) {
							houses.get(j).duration=students.get(m).duration;
							students.get(m).settled=true;
							break;
							//remove yerine student e bool değer atayıp kontrol ettirebilirm yerleşip yerleşmediğini
						}
					}
				}
				
			}
			
			
			//öğrencinin çıkmasına kalan zamanı azaltıyorum
//			for (int j = 0; j < students.size(); j++) {
//				Student s = students.get(j);
//				if (s.settled==true) {
//					students.remove(j);
//					
//				}
//				else {
//					if(s.duration>0) {
//						s.duration--;
//					}
//				}
//			}
			Iterator<Student> iter = students.iterator();
			while(iter.hasNext()) {
				Student s = iter.next();
				if(s.settled==true) {
					iter.remove();
				}
				else {
					if(s.duration>0) {
						s.duration--;
					}
				}
			}
			
			
			
			//evlerin kalan doluluk süresini azaltıyorum
			for (int j = 0; j < houses.size(); j++) {
				House h = houses.get(j);
				if(h.duration!=0) {
					h.duration--;
				}
				
			}
			
		}
		
		System.out.println(students.size());
		
		
			try {
				FileWriter fw = new FileWriter(outputFile);
				
				for(Student s: students ) {
				
				
					fw.write(s.name+"\n");
					
				
				}
				
				fw.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
		
		
		
		
	

	}

}
